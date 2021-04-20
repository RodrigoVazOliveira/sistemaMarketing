package br.com.zup.sistemamarketing.dtos.categoria.saida;

import br.com.zup.sistemamarketing.models.Categoria;

import java.util.ArrayList;
import java.util.List;

public class SaidaCategoriaDTO {

    private Integer id;
    private String nome;

    public SaidaCategoriaDTO() {
    }

    public SaidaCategoriaDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public static SaidaCategoriaDTO converterModeloParaDto(Categoria categoria) {
        return new SaidaCategoriaDTO(categoria.getId(), categoria.getNome());
    }

    public static Iterable<SaidaCategoriaDTO> gerarListaDeDto(Iterable<Categoria> categorias) {
        List<SaidaCategoriaDTO> dtos = new ArrayList<>();
        for (Categoria categoria : categorias) {
            dtos.add(converterModeloParaDto(categoria));
        }
        return dtos;
    }
}
