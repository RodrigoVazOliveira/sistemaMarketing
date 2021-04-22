package br.com.zup.sistemamarketing.controllers;

import br.com.zup.sistemamarketing.dtos.categoria.entrada.AtualizarCategoriaDTO;
import br.com.zup.sistemamarketing.dtos.categoria.entrada.BuscarCategoriaDTO;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<SaidaCategoriaDTO> mostrarTodasCategoria() {
        return SaidaCategoriaDTO.gerarListaDeDto(categoriaService.obterTodasCategoria());
    }

    @PutMapping("{id}/")
    @ResponseStatus(HttpStatus.CREATED)
    public SaidaCategoriaDTO atualizarCategoria(@RequestBody @Valid
                                                        AtualizarCategoriaDTO AtualizarCategoriaDTO,
                                                @PathVariable Integer id) {
        Categoria categoria = categoriaService.atualizarCategoria(
                AtualizarCategoriaDTO.converterDtoParaCategoria(id));
        return SaidaCategoriaDTO.converterModeloParaDto(categoria);
    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirCategoriaPorId(@PathVariable Integer id) {
        categoriaService.excluirCategoriaPorId(id);
    }

    @GetMapping("buscar")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<SaidaCategoriaDTO> procurarPOrCategoriaPorIdOuNome(@ModelAttribute
                                                                               BuscarCategoriaDTO buscarCategoriaDTO) {
        Iterable<Categoria> categorias = categoriaService.procurarCategoriaPorIdOuNome(
                buscarCategoriaDTO.getId(), buscarCategoriaDTO.getNome());
        return SaidaCategoriaDTO.gerarListaDeDto(categorias);
    }
}
