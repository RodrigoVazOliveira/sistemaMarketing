package br.com.zup.sistemamarketing.dtos.contato.entrada;

import br.com.zup.sistemamarketing.models.Produto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CadastrarContatoProdutoDTO {

    @NotNull(message = "O campo nome não foi informado")
    @NotEmpty(message = "O campo nome deve ser preenchido!")
    @Length(max = 90, message = "O campo nome possui um limite de 90 caracteres")
    private String nome;

    @NotNull(message = "O campo categorias não foi informado")
    private CadastrarContatoProdutoCategoriaDTO categorias;

    public CadastrarContatoProdutoDTO() {
    }

    public CadastrarContatoProdutoDTO(String nome,
                                      CadastrarContatoProdutoCategoriaDTO categorias) {
        this.nome = nome;
        this.categorias = categorias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CadastrarContatoProdutoCategoriaDTO getCategorias() {
        return categorias;
    }

    public void setCategorias(CadastrarContatoProdutoCategoriaDTO categorias) {
        this.categorias = categorias;
    }

    public Produto converterDtoParaModelo() {
        return new Produto(
                nome,
                categorias.converterDtoParaModelo()
        );
    }
}
