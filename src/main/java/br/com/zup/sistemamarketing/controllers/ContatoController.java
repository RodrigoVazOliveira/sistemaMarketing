package br.com.zup.sistemamarketing.controllers;

import br.com.zup.sistemamarketing.dtos.contato.entrada.CadastrarContatoDTO;
import br.com.zup.sistemamarketing.dtos.contato.saida.ContatoDTO;
import br.com.zup.sistemamarketing.models.Contato;
import br.com.zup.sistemamarketing.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("contatos/")
public class ContatoController {

    private final ContatoService contatoService;

    @Autowired
    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoDTO cadastrarContato(@RequestBody @Valid CadastrarContatoDTO cadastrarContatoDTO) {
        try {
            Contato contato = contatoService.cadastrarNovoContato(
                    cadastrarContatoDTO.converterDtoParaModelo()
            );
            return ContatoDTO.converterModeloParaDto(contato);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<ContatoDTO> mostrarTodosContatos() {
        return ContatoDTO.converterListaModeloParaListaDto(
                contatoService.obterTodosContatos()
        );
    }
}
