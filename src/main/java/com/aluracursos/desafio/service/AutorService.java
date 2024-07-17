package com.aluracursos.desafio.service;

import com.aluracursos.desafio.model.Autor;

import java.util.List;

public interface AutorService {

    List<Autor> listarAutores();

    List<Autor> listarAutoresVivosEnAnio(int anio);
}
