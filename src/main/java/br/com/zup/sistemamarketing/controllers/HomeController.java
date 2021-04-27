package br.com.zup.sistemamarketing.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String index() {
        return "Olá!";
    }
}
