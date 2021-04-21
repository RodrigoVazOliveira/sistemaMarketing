package br.com.zup.sistemamarketing.dtos.contato.saida;

import br.com.zup.sistemamarketing.dtos.contato.entrada.CadastrarContatoProdutoDTO;
import br.com.zup.sistemamarketing.models.Contato;
import br.com.zup.sistemamarketing.models.Produto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ContatoDTO {

    private Integer id;
    private String nomeCompleto;
    private String email;
    private String telefone;
    private List<ContatoProdutoDTO> produtos;

    public ContatoDTO() {
    }

    public ContatoDTO(Integer id,
                      String nomeCompleto,
                      String email,
                      String telefone,
                      List<ContatoProdutoDTO> produtos) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.telefone = telefone;
        this.produtos = produtos;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ContatoProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ContatoProdutoDTO> produtos) {
        this.produtos = produtos;
    }

    public static ContatoDTO converterModeloParaDto(Contato contato) {
        return new ContatoDTO(
                contato.getId(),
                contato.getNomeCompleto(),
                contato.getEmail(),
                contato.getTelefone(),
                ContatoProdutoDTO.converterListaProduto(contato.getProdutos())
        );
    }
}
