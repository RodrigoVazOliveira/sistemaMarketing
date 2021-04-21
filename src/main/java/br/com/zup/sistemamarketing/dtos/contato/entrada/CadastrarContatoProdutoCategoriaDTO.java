package br.com.zup.sistemamarketing.dtos.contato.entrada;

import br.com.zup.sistemamarketing.models.Categoria;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CadastrarContatoProdutoCategoriaDTO {

    @NotNull(message = "O campo categorias não foi informado")
    private List<String> categorias;

    public CadastrarContatoProdutoCategoriaDTO() {
    }

    public CadastrarContatoProdutoCategoriaDTO(List<String> categorias) {
        this.categorias = categorias;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public List<Categoria> converterDtoParaModelo() {
        List<Categoria> categoriasModelos = new ArrayList<>();
        for (String nome : categorias) {
            categoriasModelos.add(
                    new Categoria(nome)
            );
        }
        return categoriasModelos;
    }
}
