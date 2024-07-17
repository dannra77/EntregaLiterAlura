package com.aluracursos.desafio.controller;

import com.aluracursos.desafio.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class MenuController implements CommandLineRunner {

    private final Principal principal;

    public MenuController(Principal principal) {
        this.principal = principal;
    }

    @Override
    public void run(String... args) throws Exception {
        principal.muestraElMenu();
    }
}
