package com.aluracursos.desafio.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibros {

    @JsonAlias("results")
    private List<LibroInfo> resultados;

    public List<LibroInfo> getResultados() {
        return resultados;
    }

    public void setResultados(List<LibroInfo> resultados) {
        this.resultados = resultados;
    }

    public static class LibroInfo {
        @JsonAlias("title")
        private String titulo;

        @JsonAlias("authors")
        private List<AutorInfo> autores;

        @JsonAlias("languages")
        private List<String> idiomas;

        @JsonAlias("download_count")
        private Integer descargas;

        // Getters y setters para todos los campos

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public List<AutorInfo> getAutores() {
            return autores;
        }

        public void setAutores(List<AutorInfo> autores) {
            this.autores = autores;
        }

        public List<String> getIdiomas() {
            return idiomas;
        }

        public void setIdiomas(List<String> idiomas) {
            this.idiomas = idiomas;
        }

        public Integer getDescargas() {
            return descargas;
        }

        public void setDescargas(Integer descargas) {
            this.descargas = descargas;
        }
    }

    public static class AutorInfo {
        @JsonAlias("name")
        private String nombre;

        @JsonAlias("birth_year")
        private String fechaDeNacimiento;

        // Getters y setters para todos los campos

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getFechaDeNacimiento() {
            return fechaDeNacimiento;
        }

        public void setFechaDeNacimiento(String fechaDeNacimiento) {
            this.fechaDeNacimiento = fechaDeNacimiento;
        }
    }
}
