package br.com.zup.sistemamarketing.dtos.contato.entrada;

import br.com.zup.sistemamarketing.models.Contato;
import br.com.zup.sistemamarketing.models.Produto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class AtualizarContatoDTO {

    private Integer id;

    @NotNull(message = "O campo nome não foi informado")
    @NotEmpty(message = "O campo nome deve ser preenchido!")
    @Length(max = 150, message = "O campo nome possui um limite de 150 caracteres")
    private String nomeCompleto;

    @NotNull(message = "O campo email não foi informado")
    @NotEmpty(message = "O campo email deve ser preenchido!")
    @Length(max = 255, message = "O campo email possui um limite de 255 caracteres")
    @Email(message = "E-Mail inválido!")
    private String email;

    @NotNull(message = "O campo telefone não foi informado")
    @NotEmpty(message = "O campo telefone deve ser preenchido!")
    @Length(max = 25, message = "O campo telefone possui um limite de 25 caracteres")
    private String telefone;

    @NotNull(message = "O campo telefone não foi informado")
    private List<CadastrarContatoProdutoDTO> produtos;


    public AtualizarContatoDTO() {
    }

    public AtualizarContatoDTO(Integer id,
                               String nomeCompleto,
                               String email,
                               String telefone,
                               List<CadastrarContatoProdutoDTO> produtos) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.telefone = telefone;
        this.produtos = produtos;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<CadastrarContatoProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<CadastrarContatoProdutoDTO> produtos) {
        this.produtos = produtos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Contato converterDtoParaModelo(Integer id) {
        Contato contato = new Contato(
                nomeCompleto,
                email,
                telefone,
                converterListaContatoProdutoDtoParaListaModelo()
        );
        contato.setId(id);
        return contato;
    }

    private List<Produto> converterListaContatoProdutoDtoParaListaModelo() {
        List<Produto> produtosModelo = new ArrayList<>();

        for (CadastrarContatoProdutoDTO dto : produtos) {
            produtosModelo.add(
                    dto.converterDtoParaModelo()
            );
        }

        return produtosModelo;
    }
}
