package com.aluracursos.desafio.service;

import com.aluracursos.desafio.model.Autor;
import com.aluracursos.desafio.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorServiceImpl(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    @Override
    public List<Autor> listarAutoresVivosEnAnio(int anio) {

        return autorRepository.findAll()
                .stream()
                .filter(autor -> Integer.parseInt(autor.getFechaDeNacimiento()) > (anio - 100))
                .collect(Collectors.toList());
    }
}
