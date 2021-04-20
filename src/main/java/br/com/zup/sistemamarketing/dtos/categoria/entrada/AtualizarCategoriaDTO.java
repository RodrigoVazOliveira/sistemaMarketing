package br.com.zup.sistemamarketing.dtos.categoria.entrada;

import br.com.zup.sistemamarketing.models.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizarCategoriaDTO {

    @NotNull(message = "O campo nome não foi informado")
    @NotEmpty(message = "O campo nome deve ser preenchido!")
    @Length(message = "O campo nome possui um limite de 80 caracteres")
    private String nome;

    public AtualizarCategoriaDTO() {
    }

    public AtualizarCategoriaDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria converterDtoParaCategoria(Integer id) {
        Categoria categoria = new Categoria();
        categoria.setId(id);
        categoria.setNome(this.nome);
        return categoria;
    }
}
