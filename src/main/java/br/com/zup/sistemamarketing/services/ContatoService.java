package br.com.zup.sistemamarketing.services;

import br.com.zup.sistemamarketing.models.Contato;
import br.com.zup.sistemamarketing.models.Produto;
import br.com.zup.sistemamarketing.repositories.ContatoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final ProdutoService produtoService;

    public ContatoService(ContatoRepository contatoRepository,
                          ProdutoService produtoService) {
        this.contatoRepository = contatoRepository;
        this.produtoService = produtoService;
    }

    public Contato cadastrarNovoContato(Contato contato) {
        contato.setProdutos(verificarProdutos(contato.getProdutos()));
        return contatoRepository.save(contato);
    }

    private List<Produto> verificarProdutos(List<Produto> produtos) {
        List<Produto> produtosAdcionarContato = new ArrayList<>();

        for (Produto produto : produtos) {
            produtosAdcionarContato.add(
                    produtoService.buscarProdutoPorId(produto.getId())
            );
        }

        return produtosAdcionarContato;
    }
}
