package com.aluracursos.desafio.principal;

import com.aluracursos.desafio.model.Libro;
import com.aluracursos.desafio.model.Autor;
import com.aluracursos.desafio.service.LibroService;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Principal {

    private final LibroService libroService;
    private final Scanner teclado;

    @Autowired
    public Principal(LibroService libroService) {
        this.libroService = libroService;
        this.teclado = new Scanner(System.in);
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar y guardar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarYGuardarLibro();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                    break;
            }
        }
    }

    private void buscarYGuardarLibro() {
        System.out.println("Ingrese el título del libro a buscar y guardar:");
        var titulo = teclado.nextLine();
        Libro libro = libroService.buscarYGuardarLibro(titulo);
        System.out.println("Libro encontrado y guardado: " + libro);
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroService.listarLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en la base de datos.");
        } else {
            System.out.println("Listado de libros registrados:");
            libros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = libroService.listarAutores();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados en la base de datos.");
        } else {
            System.out.println("Listado de autores registrados:");
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosEnAnio() {
        System.out.println("Ingrese el año para listar autores vivos:");
        int anio = teclado.nextInt();
        List<Autor> autores = libroService.listarAutoresVivosEnAnio(anio);
        if (autores.isEmpty()) {
            System.out.println("No hay autores vivos en el año " + anio);
        } else {
            System.out.println("Listado de autores vivos en el año " + anio + ":");
            autores.forEach(System.out::println);
        }
        teclado.nextLine();
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Ingrese el idioma para listar libros:");
        var idioma = teclado.nextLine();
        List<Libro> libros = libroService.listarLibrosPorIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No hay libros en el idioma '" + idioma + "'");
        } else {
            System.out.println("Listado de libros en el idioma '" + idioma + "':");
            libros.forEach(System.out::println);
        }
    }
}
