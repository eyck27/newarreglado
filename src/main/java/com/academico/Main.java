package com.academico;

import com.academico.application.port.in.EstudianteUseCase;
import com.academico.application.service.EstudianteService;
import com.academico.domain.repository.EstudianteRepository;
import com.academico.infrastructure.adapter.out.persistence.EstudianteRepositorySqlServer;
import com.academico.presentation.EstudianteUI;

public class Main {
    public static void main(String[] args) {
        // Cadena de conexión a tu SQL Server local
        // Cambia 'localhost', 'usuario' y 'contraseña' con los datos de tu SQL Server
        String connectionUrl = "jdbc:sqlserver://localhost:1433;"
                + "databaseName=academico_db;"
                + "user=sa;"
                + "password=123456789;"
                + "encrypt=false;"
                + "trustServerCertificate=true;";

        // Inyección del repositorio de SQL Server
        EstudianteRepository repository = new EstudianteRepositorySqlServer(connectionUrl);

        // Conexión con los casos de uso y la interfaz de consola
        EstudianteUseCase service = new EstudianteService(repository);
        EstudianteUI ui = new EstudianteUI(service);

        ui.iniciar();
    }
}