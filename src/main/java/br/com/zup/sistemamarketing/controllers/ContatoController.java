package br.com.zup.sistemamarketing.controllers;

import br.com.zup.sistemamarketing.dtos.contato.entrada.AtualizarContatoDTO;
import br.com.zup.sistemamarketing.dtos.contato.entrada.AtualizarParcialContatoDTO;
import br.com.zup.sistemamarketing.dtos.contato.entrada.CadastrarContatoDTO;
import br.com.zup.sistemamarketing.dtos.contato.entrada.FiltroPorNomeDeProdutoDTO;
import br.com.zup.sistemamarketing.dtos.contato.saida.ContatoDTO;
import br.com.zup.sistemamarketing.models.Contato;
import br.com.zup.sistemamarketing.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Contato contato = contatoService.cadastrarNovoContato(
                cadastrarContatoDTO.converterDtoParaModelo()
        );
        return ContatoDTO.converterModeloParaDto(contato);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<ContatoDTO> mostrarTodosContatos() {
        return ContatoDTO.converterListaModeloParaListaDto(
                contatoService.obterTodosContatos()
        );
    }

    @PutMapping("{id}/")
    public ResponseEntity<ContatoDTO> atualizarContatoCompleto(@PathVariable Integer id,
                                                               @RequestBody @Valid
                                                                       AtualizarContatoDTO atualizarContatoDTO) {
        if (contatoService.contatoExiste(id)) {
          return atualizarContatoExistente(atualizarContatoDTO.converterDtoParaModelo(id));
        }
        return atualizarContatoNaoExistente(atualizarContatoDTO.converterDtoParaModelo(null));
    }

    private ResponseEntity<ContatoDTO> atualizarContatoExistente(Contato contato) {
        contatoService.atualizarContatoCompleto(contato);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<ContatoDTO> atualizarContatoNaoExistente(Contato contato) {
        Contato contatoSalvo = contatoService.cadastrarNovoContato(contato);
        ContatoDTO contatoDTO = ContatoDTO.converterModeloParaDto(contatoSalvo);
        return ResponseEntity.status(201).body(contatoDTO);
    }

    @PatchMapping("{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ContatoDTO atualizarContatoParcialmente(@PathVariable Integer id,
                                                   @RequestBody
                                                   @Valid
                                                   AtualizarParcialContatoDTO atualizarParcialContatoDTO) {

            Contato contatoAtualizado = contatoService.atualizarContatoParcialmente(
                    atualizarParcialContatoDTO.converterDtoParaModelo(id)
            );
            return ContatoDTO.converterModeloParaDto(contatoAtualizado);

    }

    @DeleteMapping("{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirContatoPorId(@PathVariable Integer id) {
        contatoService.excluirContatoPorId(id);
    }

    @GetMapping("produtos")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<ContatoDTO> filtrarContatoPorNomeDeProduto(
            @ModelAttribute FiltroPorNomeDeProdutoDTO filtroPorNomeDeProdutoDTO) {
        return ContatoDTO.converterListaModeloParaListaDto(
                contatoService.procurarContatoPorNomeDeProduto(filtroPorNomeDeProdutoDTO.getNome())
        );
    }

    @GetMapping("produtos/categorias")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<ContatoDTO> filtrarContatoPorNomeDeCategoriaDoProduto(
            @ModelAttribute FiltroPorNomeDeProdutoDTO filtroPorNomeDeProdutoDTO) {
        return ContatoDTO.converterListaModeloParaListaDto(
                contatoService.procurarContatoPorNomeDeCategoriaDoProduto(filtroPorNomeDeProdutoDTO.getNome())
        );
    }
}
