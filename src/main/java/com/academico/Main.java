package com.academico;

import com.academico.application.CursoService;
import com.academico.application.EstudianteService;
import com.academico.domain.repository.CursoRepository;
import com.academico.domain.repository.EstudianteRepository;
import com.academico.infrastructure.persistence.CursoRepositoryJson;
import com.academico.infrastructure.persistence.EstudianteRepositoryJson;
import com.academico.presentation.CursoUI;
import com.academico.presentation.EstudianteUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicialización e Inyección de Dependencias
        EstudianteRepository estudianteRepository = new EstudianteRepositoryJson();
        EstudianteService estudianteService = new EstudianteService(estudianteRepository);
        EstudianteUI estudianteUI = new EstudianteUI(estudianteService);

        CursoRepository cursoRepository = new CursoRepositoryJson();
        CursoService cursoService = new CursoService(cursoRepository);
        CursoUI cursoUI = new CursoUI(cursoService);

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n==================================================");
            System.out.println("  SISTEMA DE GESTIÓN ACADÉMICA (CLEAN ARCHITECTURE)");
            System.out.println("==================================================");
            System.out.println("1. Gestión de Estudiantes");
            System.out.println("2. Gestión de Cursos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    estudianteUI.mostrarMenu(sc);
                    break;
                case 2:
                    cursoUI.mostrarMenu(sc);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}