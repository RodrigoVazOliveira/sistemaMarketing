package br.com.zup.sistemamarketing.dtos.produto.saida;

import br.com.zup.sistemamarketing.models.Categoria;
import br.com.zup.sistemamarketing.models.Produto;

import java.util.ArrayList;
import java.util.List;

public class SaidaProdutoDTO {
    private Integer id;
    private String nome;
    private List<String> categorias;

    public SaidaProdutoDTO() {
    }

    public SaidaProdutoDTO(Integer id, String nome, List<String> categorias) {
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

    public static SaidaProdutoDTO converterModeloParaDto(Produto produto) {
        List<String> categoriasDto = new ArrayList<>();
        for (Categoria categoria : produto.getCategorias()) {
            categoriasDto.add(categoria.getNome());
        }
        return new SaidaProdutoDTO(produto.getId(), produto.getNome(), categoriasDto);
    }
}
