package com.academico.presentation;

import com.academico.application.port.in.EstudianteUseCase;
import com.academico.domain.model.Estudiante;

import java.util.List;
import java.util.Scanner;

public class EstudianteUI {

    private final EstudianteUseCase estudianteUseCase;
    private final Scanner scanner;

    public EstudianteUI(EstudianteUseCase estudianteUseCase) {
        this.estudianteUseCase = estudianteUseCase;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = -1;
        while (opcion != 5) {
            System.out.println("\n=== GESTIÓN DE ESTUDIANTES ===");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Actualizar estudiante");
            System.out.println("4. Eliminar estudiante");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> registrar();
                    case 2 -> listar();
                    case 3 -> actualizar();
                    case 4 -> eliminar();
                    case 5 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void registrar() {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();

        estudianteUseCase.registrar(new Estudiante(id, nombre, correo));
        System.out.println("Estudiante registrado con éxito.");
    }

    private void listar() {
        List<Estudiante> lista = estudianteUseCase.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
          lista.forEach(System.out::println);
        }
    }

    private void actualizar() {
        System.out.print("ID a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nuevo Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nuevo Correo: ");
        String correo = scanner.nextLine();

        estudianteUseCase.actualizar(new Estudiante(id, nombre, correo));
        System.out.println("Estudiante actualizado.");
    }

    private void eliminar() {
        System.out.print("ID a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        estudianteUseCase.eliminar(id);
        System.out.println("Estudiante eliminado.");
    }
}