package br.com.zup.sistemamarketing.dtos.contato.entrada;

import br.com.zup.sistemamarketing.models.Contato;
import br.com.zup.sistemamarketing.models.Produto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CadastrarContatoDTO {

    @NotNull(message = "O campo nome não foi informado")
    @NotEmpty(message = "O campo nome deve ser preenchido!")
    @Length(max = 150, message = "O campo nome possui um limite de 150 caracteres")
    private String nomeCompleto;

    @NotNull(message = "O campo email não foi informado")
    @NotEmpty(message = "O campo email deve ser preenchido!")
    @Length(max = 255, message = "O campo email possui um limite de 255 caracteres")
    @Email(message = "E-Mail inválido!")
    private String email;

    @NotNull(message = "O campo telefone não foi informado")
    @NotEmpty(message = "O campo telefone deve ser preenchido!")
    @Length(max = 25, message = "O campo telefone possui um limite de 25 caracteres")
    private String telefone;

    @NotNull(message = "O campo produtos não foi informado")
    private List<String> produtos;


    public CadastrarContatoDTO() {
    }

    public CadastrarContatoDTO(String nome, String email, String telefone,
                               List<String> produtos) {
        this.nomeCompleto = nome;
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

    public Contato converterDtoParaModelo() {
        return new Contato(
                nomeCompleto,
                email,
                telefone,
                converterListaContatoProdutoDtoParaListaModelo()
        );
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
