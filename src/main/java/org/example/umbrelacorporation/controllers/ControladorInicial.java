package org.example.umbrelacorporation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorInicial {

    @GetMapping("/")
    public String inicio(){
        return "index";  // Retorna la vista "index.html" ubicada en /templates
    }
}
