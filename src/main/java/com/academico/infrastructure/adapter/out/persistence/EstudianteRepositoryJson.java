package com.academico.infrastructure.adapter.out.persistence;

import com.academico.domain.model.Estudiante;
import com.academico.domain.repository.EstudianteRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EstudianteRepositoryJson implements EstudianteRepository {

    private final String archivo = "data/estudiantes.json";
    private final Gson gson = new Gson();

    @Override
    public List<Estudiante> listar() {
        try (Reader reader = new FileReader(archivo)) {
            Type tipo = new TypeToken<List<Estudiante>>() {}.getType();
            List<Estudiante> estudiantes = gson.fromJson(reader, tipo);
            return estudiantes != null ? estudiantes : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void guardar(List<Estudiante> estudiantes) {
        try (Writer writer = new FileWriter(archivo)) {
            gson.toJson(estudiantes, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar estudiantes.");
        }
    }
}