package br.com.zup.sistemamarketing.dtos.categoria.entrada;

import br.com.zup.sistemamarketing.models.Categoria;

import java.util.ArrayList;
import java.util.List;

public class BuscarCategoriaDTO {

    private Integer id;
    private String nome;

    public BuscarCategoriaDTO() {
    }

    public BuscarCategoriaDTO(Integer id, String nome) {
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

    public static BuscarCategoriaDTO converterModeloParaDto(Categoria categoria) {
        return new BuscarCategoriaDTO(categoria.getId(), categoria.getNome());
    }

    public static Iterable<BuscarCategoriaDTO> gerarListaDeDto(Iterable<Categoria> categorias) {
        List<BuscarCategoriaDTO> dtos = new ArrayList<>();
        for (Categoria categoria : categorias) {
            dtos.add(converterModeloParaDto(categoria));
        }
        return dtos;
    }
}
