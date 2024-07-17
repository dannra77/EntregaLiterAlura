package com.aluracursos.desafio.service;

import com.aluracursos.desafio.model.DatosLibros;
import com.aluracursos.desafio.model.Libro;
import com.aluracursos.desafio.model.Autor;
import com.aluracursos.desafio.repository.LibroRepository;
import com.aluracursos.desafio.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    private final ConsumoAPI consumoAPI;
    private final ConvierteDatos conversor;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public LibroServiceImpl(ConsumoAPI consumoAPI, ConvierteDatos conversor, LibroRepository libroRepository, AutorRepository autorRepository) {
        this.consumoAPI = consumoAPI;
        this.conversor = conversor;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    @Override
    public Libro buscarYGuardarLibro(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "+");
        String json = consumoAPI.obtenerDatos(url);
        DatosLibros datosLibros = conversor.obtenerDatos(json, DatosLibros.class);

        if (datosLibros != null && datosLibros.getResultados() != null && !datosLibros.getResultados().isEmpty()) {
            DatosLibros.LibroInfo libroInfo = datosLibros.getResultados().get(0); // Accede al primer libro encontrado
            Libro libro = new Libro();
            libro.setTitulo(libroInfo.getTitulo());
            libro.setAutor(libroInfo.getAutores().stream().map(a -> a.getNombre()).reduce((a, b) -> a + ", " + b).orElse(""));
            libro.setIdioma(String.join(", ", libroInfo.getIdiomas()));
            libro.setNumeroDeDescargas(libroInfo.getDescargas());

            // Guardar el autor si no existe en la base de datos
            for (DatosLibros.AutorInfo autorInfo : libroInfo.getAutores()) {
                Autor autorExistente = autorRepository.findByNombre(autorInfo.getNombre());
                if (autorExistente == null) {
                    Autor autorNuevo = new Autor(autorInfo.getNombre(), autorInfo.getFechaDeNacimiento());
                    autorRepository.save(autorNuevo);
                }
            }

            return libroRepository.save(libro);
        }

        return null;
    }

    @Override
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @Override
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    @Override
    public List<Autor> listarAutoresVivosEnAnio(int anio) {
        // Implementación de listarAutoresVivosEnAnio si es necesario
        return null;
    }

    @Override
    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdiomaContainingIgnoreCase(idioma);
    }
}
