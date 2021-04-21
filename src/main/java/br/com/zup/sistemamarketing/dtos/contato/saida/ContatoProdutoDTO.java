package br.com.zup.sistemamarketing.dtos.contato.saida;

import br.com.zup.sistemamarketing.models.Categoria;
import br.com.zup.sistemamarketing.models.Produto;

import java.util.ArrayList;
import java.util.List;

public class ContatoProdutoDTO {

    private Integer id;
    private String nome;
    private List<String> categorias;

    public ContatoProdutoDTO() {
    }

    public ContatoProdutoDTO(Integer id, String nome, List<String> categorias) {
        this.id = id;
        this.nome = nome;
        this.categorias = categorias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public static List<ContatoProdutoDTO> converterListaProduto(List<Produto> produtos) {
        List<ContatoProdutoDTO> dto = new ArrayList<>();

        produtos.stream().forEach(produto -> {
            dto.add(new ContatoProdutoDTO(
                    produto.getId(),
                    produto.getNome(),
                    converterCategorias(produto.getCategorias())
            ));
        });

        return dto;
    }

    private static List<String> converterCategorias(List<Categoria> categorias) {
        List<String> dto = new ArrayList<>();
        for (Categoria categoria : categorias) {
            dto.add(categoria.getNome());
        }
        return dto;
    }
}
