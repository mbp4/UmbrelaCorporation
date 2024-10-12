package org.example.umbrelacorporation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controladorInicial {

    @GetMapping("/")
    public String inicio(){
        return "index";
    }
}
