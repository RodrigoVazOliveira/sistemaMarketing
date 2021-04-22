package br.com.zup.sistemamarketing.dtos.contato.entrada;

import br.com.zup.sistemamarketing.models.Categoria;
import br.com.zup.sistemamarketing.models.Produto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CadastrarContatoProdutoDTO {

    @NotNull(message = "O campo nome não foi informado")
    @NotEmpty(message = "O campo nome deve ser preenchido!")
    @Length(max = 90, message = "O campo nome possui um limite de 90 caracteres")
    private String nome;

    @NotNull(message = "O campo categorias não foi informado")
    private List<String> categorias;

    public CadastrarContatoProdutoDTO() {
    }

    public CadastrarContatoProdutoDTO(String nome, List<String> categorias) {
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
        return new Produto(
                nome,
                converterNomeCategoriasParaCategorias()
        );
    }

    private List<Categoria> converterNomeCategoriasParaCategorias() {
        List<Categoria> categoriasModelos = new ArrayList<>();
        for (String nome : categorias) {
            categoriasModelos.add(
                    new Categoria(nome)
            );
        }
        return categoriasModelos;
    }
}
