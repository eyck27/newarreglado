package com.academico.application.service;

import com.academico.application.port.in.EstudianteUseCase;
import com.academico.domain.model.Estudiante;
import com.academico.domain.repository.EstudianteRepository;

import java.util.List;

public class EstudianteService implements EstudianteUseCase {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public List<Estudiante> listar() {
        return estudianteRepository.listar();
    }

    @Override
    public void registrar(Estudiante estudiante) {
        List<Estudiante> estudiantes = estudianteRepository.listar();
        for (Estudiante e : estudiantes) {
            if (e.getId() == estudiante.getId()) {
                throw new IllegalArgumentException("El estudiante con ID " + estudiante.getId() + " ya existe.");
            }
        }
        estudiantes.add(estudiante);
        estudianteRepository.guardar(estudiantes);
    }

    @Override
    public void actualizar(Estudiante estudianteActualizado) {
        List<Estudiante> estudiantes = estudianteRepository.listar();
        boolean encontrado = false;

        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getId() == estudianteActualizado.getId()) {
                estudiantes.set(i, estudianteActualizado);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new IllegalArgumentException("No se encontró el estudiante con ID: " + estudianteActualizado.getId());
        }

        estudianteRepository.guardar(estudiantes);
    }

    @Override
    public void eliminar(int id) {
        List<Estudiante> estudiantes = estudianteRepository.listar();
        boolean eliminado = estudiantes.removeIf(e -> e.getId() == id);

        if (!eliminado) {
            throw new IllegalArgumentException("No se encontró el estudiante con ID: " + id);
        }

        estudianteRepository.guardar(estudiantes);
    }
}