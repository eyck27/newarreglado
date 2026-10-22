package com.academico.presentation;

import com.academico.application.service.CursoService;
import com.academico.domain.model.Curso;

import java.util.List;
import java.util.Scanner;

public class CursoUI {

    private final CursoService cursoService;

    public CursoUI(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    public void mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE CURSOS ---");
            System.out.println("1. Listar Cursos");
            System.out.println("2. Registrar Curso");
            System.out.println("3. Actualizar Curso");
            System.out.println("4. Eliminar Curso");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar búfer

            switch (opcion) {
                case 1:
                    listar();
                    break;
                case 2:
                    registrar(sc);
                    break;
                case 3:
                    actualizar(sc);
                    break;
                case 4:
                    eliminar(sc);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void listar() {
        List<Curso> cursos = cursoService.listar();
        System.out.println("\nLista de Cursos:");
        if (cursos.isEmpty()) {
            System.out.println("(No hay cursos registrados)");
        } else {
            for (Curso c : cursos) {
                System.out.println("ID: " + c.getId() + " | Nombre: " + c.getNombre() + " | Créditos: " + c.getCreditos());
            }
        }
    }

    private void registrar(Scanner sc) {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Créditos: ");
        int creditos = sc.nextInt();
        sc.nextLine();

        cursoService.registrar(new Curso(id, nombre, creditos));
        System.out.println("¡Curso registrado exitosamente!");
    }

    private void actualizar(Scanner sc) {
        System.out.print("ID del curso a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nuevo Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevos Créditos: ");
        int creditos = sc.nextInt();
        sc.nextLine();

        cursoService.actualizar(new Curso(id, nombre, creditos));
        System.out.println("¡Curso actualizado exitosamente!");
    }

    private void eliminar(Scanner sc) {
        System.out.print("ID del curso a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();

        cursoService.eliminar(id);
        System.out.println("¡Curso eliminado exitosamente!");
    }
}