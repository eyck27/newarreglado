package com.academico.infrastructure.adapter.out.persistence;

import com.academico.domain.model.Estudiante;
import com.academico.domain.repository.EstudianteRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteRepositoryJdbc implements EstudianteRepository {

    private final String url;

    public EstudianteRepositoryJdbc(String url) {
        this.url = url;
        crearTabla();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }

    private void crearTabla() {
        String sql = """
            CREATE TABLE IF NOT EXISTS estudiantes (
                id INT PRIMARY KEY,
                nombre TEXT NOT NULL,
                correo TEXT NOT NULL
            );
        """;
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al crear tabla en BD: " + e.getMessage());
        }
    }

    @Override
    public List<Estudiante> listar() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, correo FROM estudiantes";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Estudiante(rs.getInt("id"), rs.getString("nombre"), rs.getString("correo")));
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar BD: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void guardar(List<Estudiante> estudiantes) {
        String deleteSql = "DELETE FROM estudiantes";
        String insertSql = "INSERT INTO estudiantes (id, nombre, correo) VALUES (?, ?, ?)";

        try (Connection conn = connect()) {
            conn.setAutoCommit(false);
            try (Statement stmtDelete = conn.createStatement();
                 PreparedStatement pstmtInsert = conn.prepareStatement(insertSql)) {

                stmtDelete.executeUpdate(deleteSql);
                for (Estudiante e : estudiantes) {
                    pstmtInsert.setInt(1, e.getId());
                    pstmtInsert.setString(2, e.getNombre());
                    pstmtInsert.setString(3, e.getCorreo());
                    pstmtInsert.addBatch();
                }
                pstmtInsert.executeBatch();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Error en transacción: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión BD: " + e.getMessage());
        }
    }
}