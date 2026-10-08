package com.academico.application;

import com.academico.domain.model.Curso;
import com.academico.domain.repository.CursoRepository;

import java.util.List;

public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listar() {
        return cursoRepository.listar();
    }

    public void registrar(Curso curso) {
        List<Curso> cursos = cursoRepository.listar();
        cursos.add(curso);
        cursoRepository.guardar(cursos);
    }

    public void actualizar(Curso cursoActualizado) {
        List<Curso> cursos = cursoRepository.listar();
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getId() == cursoActualizado.getId()) {
                cursos.set(i, cursoActualizado);
                break;
            }
        }
        cursoRepository.guardar(cursos);
    }

    public void eliminar(int id) {
        List<Curso> cursos = cursoRepository.listar();
        cursos.removeIf(c -> c.getId() == id);
        cursoRepository.guardar(cursos);
    }
}