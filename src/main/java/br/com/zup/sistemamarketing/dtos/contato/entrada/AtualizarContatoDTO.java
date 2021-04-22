package br.com.zup.sistemamarketing.dtos.contato.entrada;

import br.com.zup.sistemamarketing.models.Contato;
import br.com.zup.sistemamarketing.models.Produto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class AtualizarContatoDTO {

    private Integer id;

    @NotNull(message = "{campo.nulo}")
    @NotEmpty(message = "{campo.vazio}")
    @NotBlank(message = "{campo.branco}")
    @Length(max = 150, message = "{campo.limite}")
    private String nomeCompleto;

    @NotNull(message = "{campo.nulo}")
    @NotEmpty(message = "{campo.vazio}")
    @NotBlank(message = "{campo.branco}")
    @Length(max = 255, message = "{campo.limite}")
    @Email(message = "{campo.email}")
    private String email;

    @NotNull(message = "{campo.nulo}")
    @NotEmpty(message = "{campo.vazio}")
    @NotBlank(message = "{campo.branco}")
    @Length(max = 25, message = "{campo.limite}!")
    private String telefone;

    @NotNull(message = "{campo.nulo}")
    private List<String> produtos;


    public AtualizarContatoDTO() {
    }

    public AtualizarContatoDTO(Integer id,
                               String nomeCompleto,
                               String email,
                               String telefone,
                               List<String> produtos) {
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

    public List<String> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<String> produtos) {
        this.produtos = produtos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Contato converterDtoParaModelo(Integer id) {
        Contato contato = new Contato(
                nomeCompleto,
                email,
                telefone,
                converterListaContatoProdutoDtoParaListaModelo()
        );
        contato.setId(id);
        return contato;
    }

    private List<Produto> converterListaContatoProdutoDtoParaListaModelo() {
        List<Produto> produtosModelo = new ArrayList<>();

        for (String nome : produtos) {
            produtosModelo.add(
                    new Produto(nome, null)
            );
        }

        return produtosModelo;
    }
}
