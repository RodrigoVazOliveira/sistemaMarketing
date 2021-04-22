package br.com.zup.sistemamarketing.dtos.categoria.entrada;

import br.com.zup.sistemamarketing.models.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CadastrarCategoriaDTO {

    @NotNull(message = "{campo.nulo}")
    @NotEmpty(message = "{campo.vazio}")
    @Length(max = 80, message = "{campo.limite}")
    @NotBlank(message = "{campo.branco}")
    private String nome;

    public CadastrarCategoriaDTO() {
    }

    public CadastrarCategoriaDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria converterDtoParaCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome(this.nome);
        return categoria;
    }
}
