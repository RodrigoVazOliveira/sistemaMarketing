package br.com.zup.sistemamarketing.controllers;

import br.com.zup.sistemamarketing.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categorias/")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired  
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
}
