package com.academico.application.port.in;

import com.academico.domain.model.Estudiante;
import java.util.List;

public interface EstudianteUseCase {
    void registrar(Estudiante estudiante);
    List<Estudiante> listar();
    void actualizar(Estudiante estudiante);
    void eliminar(int id);
}