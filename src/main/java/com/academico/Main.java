package com.academico;

import com.academico.application.port.in.EstudianteUseCase;
import com.academico.application.service.EstudianteService;
import com.academico.domain.repository.EstudianteRepository;
import com.academico.infrastructure.adapter.out.persistence.EstudianteRepositoryJdbc;
import com.academico.presentation.EstudianteUI;

public class Main {
    public static void main(String[] args) {
        // Conexión a la BD SQLite (creará data/academico.db automáticamente)
        EstudianteRepository repository = new EstudianteRepositoryJdbc("jdbc:sqlite:data/academico.db");

        // Inyección de dependencias
        EstudianteUseCase service = new EstudianteService(repository);
        EstudianteUI ui = new EstudianteUI(service);

        ui.iniciar();
    }
}