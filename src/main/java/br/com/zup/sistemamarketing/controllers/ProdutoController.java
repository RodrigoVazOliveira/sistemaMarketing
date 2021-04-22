package br.com.zup.sistemamarketing.controllers;

import br.com.zup.sistemamarketing.dtos.produto.entrada.AtualizarProdutoDTO;
import br.com.zup.sistemamarketing.dtos.produto.entrada.CadastrarProdutoDTO;
import br.com.zup.sistemamarketing.dtos.produto.saida.SaidaProdutoDTO;
import br.com.zup.sistemamarketing.models.Produto;
import br.com.zup.sistemamarketing.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;

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
        return SaidaProdutoDTO.converterModeloParaDto(produto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<SaidaProdutoDTO> mostrarProduto() {
        Iterable<Produto> produtos = produtoService.obterTodosProduto();
        return SaidaProdutoDTO.converterListaModeloParaListaDto(produtos);
    }

    @PutMapping("{id}/")
    public ResponseEntity<SaidaProdutoDTO> atualizar(@PathVariable Integer id,
                                                        @RequestBody
                                                        @Valid
                                                        AtualizarProdutoDTO atualizarProdutoDTO) {
        try {
            if (produtoService.verificarProdutoExistePorId(id)) {
               return atualizarSeProdutoExiste(atualizarProdutoDTO.converterDtoParaModelo(id));
            }
            return atualizarSeProdutoNaoExiste(atualizarProdutoDTO.converterDtoParaModelo(null));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private ResponseEntity<SaidaProdutoDTO> atualizarSeProdutoExiste(Produto produto) {
        produtoService.atualizarProduto(produto);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<SaidaProdutoDTO> atualizarSeProdutoNaoExiste(Produto produto) {
        Produto produtoNovo = produtoService.cadastrarNovoProduto(produto);
        return ResponseEntity
                .status(201)
                .body(SaidaProdutoDTO.converterModeloParaDto(produtoNovo));
    }

    @GetMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public SaidaProdutoDTO obterProdutoPorId(@PathVariable Integer id) {
        try {
            return SaidaProdutoDTO.converterModeloParaDto(
                    produtoService.procurarProdutoPorId(id)
            );
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.OK, e.getMessage());
        }
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProdutoPorId(@PathVariable Integer id) {
        try {
            produtoService.deletarProdutoPorId(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}