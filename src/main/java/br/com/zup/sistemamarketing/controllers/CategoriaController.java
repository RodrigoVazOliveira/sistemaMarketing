package br.com.zup.sistemamarketing.controllers;

import br.com.zup.sistemamarketing.dtos.categoria.entrada.CadastrarCategoriaDTO;
import br.com.zup.sistemamarketing.dtos.categoria.saida.SaidaCategoriaDTO;
import br.com.zup.sistemamarketing.models.Categoria;
import br.com.zup.sistemamarketing.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("categorias/")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaidaCategoriaDTO cadastrarNovaCategoria(@RequestBody @Valid
                                                            CadastrarCategoriaDTO cadastrarCategoriaDTO) {
        Categoria categoria = categoriaService.cadastrarNovaCategoria(
                cadastrarCategoriaDTO.converterDtoParaCategoria());
        return SaidaCategoriaDTO.converterModeloParaDto(categoria);
    }
}
