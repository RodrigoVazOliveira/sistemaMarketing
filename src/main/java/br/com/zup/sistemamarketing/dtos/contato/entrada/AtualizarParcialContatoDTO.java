package br.com.zup.sistemamarketing.dtos.contato.entrada;

import br.com.zup.sistemamarketing.models.Contato;
import br.com.zup.sistemamarketing.models.Produto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class AtualizarParcialContatoDTO {

    private Integer id;
    private String nomeCompleto;
    private String email;
    private String telefone;
    private List<CadastrarContatoProdutoDTO> produtos;


    public AtualizarParcialContatoDTO() {
    }

    public AtualizarParcialContatoDTO(Integer id,
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

        if (produtos == null) {
            return null;
        }

        for (CadastrarContatoProdutoDTO dto : produtos) {
            produtosModelo.add(
                    dto.converterDtoParaModelo()
            );
        }

        return produtosModelo;
    }
}
