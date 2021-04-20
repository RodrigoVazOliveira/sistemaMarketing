package br.com.zup.sistemamarketing.controllers;

import br.com.zup.sistemamarketing.dtos.produto.entrada.CadastrarProdutoDTO;
import br.com.zup.sistemamarketing.models.Produto;
import br.com.zup.sistemamarketing.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("produtos/")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaidaProdutoDTO cadastrarProduto(@RequestBody @Valid CadastrarProdutoDTO cadastrarProdutoDTO) {
        Produto produto = produtoService.cadastrarNovoProduto(
                cadastrarProdutoDTO.converterDtoParaModelo()
        );
    }
}
