package com.aluracursos.desafio.service;

import com.aluracursos.desafio.model.Libro;
import com.aluracursos.desafio.model.Autor;

import java.util.List;

public interface LibroService {

    Libro buscarYGuardarLibro(String titulo);

    List<Libro> listarLibros();

    List<Autor> listarAutores();

    List<Autor> listarAutoresVivosEnAnio(int anio);

    List<Libro> listarLibrosPorIdioma(String idioma);
}
