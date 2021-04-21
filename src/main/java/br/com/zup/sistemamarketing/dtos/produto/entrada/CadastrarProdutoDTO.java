package br.com.zup.sistemamarketing.dtos.produto.entrada;

import br.com.zup.sistemamarketing.models.Categoria;
import br.com.zup.sistemamarketing.models.Produto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class CadastrarProdutoDTO {

    @NotNull(message = "O campo nome não foi informado")
    @NotEmpty(message = "O campo nome deve ser preenchido!")
    @Length(message = "O campo nome possui um limite de 80 caracteres")
    @NotBlank(message = "O campo nome está em branco!")
    private String nome;

    @NotNull(message = "O campo categorias não foi informado")
    @Size(min = 1, message = "deve ser informado ao menos uma categoria")
    private List<String> categorias;

    public CadastrarProdutoDTO() {
    }

    public CadastrarProdutoDTO(String nome, List<String> categorias) {
        this.nome = nome;
        this.categorias = categorias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public Produto converterDtoParaModelo() {
        Produto produto = new Produto();
        produto.setNome(this.nome);

        List<Categoria> categorias = new ArrayList<>();

        for (String nome : this.categorias) {
            Categoria categoria = new Categoria();
            categoria.setNome(nome);
            categorias.add(categoria);
        }

        produto.setCategorias(categorias);

        return produto;
    }
}
