package br.com.zup.sistemamarketing.services;

import br.com.zup.sistemamarketing.models.Contato;
import br.com.zup.sistemamarketing.models.Produto;
import br.com.zup.sistemamarketing.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;

    @Autowired
    public ContatoService(ContatoRepository contatoRepository, ProdutoService produtoService, CategoriaService categoriaService) {
        this.contatoRepository = contatoRepository;
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
    }

    public Contato cadastrarNovoContato(Contato contato) {
        if (!contatoRepository.existsByEmail(contato.getEmail())) {
            contato.setProdutos(verificarProdutos(contato.getProdutos()));
            return contatoRepository.save(contato);
        }
        return contatoRepository.save(contato);
    }

    private List<Produto> verificarProdutos(List<Produto> produtos) {
        List<Produto> produtosAdcionarContato = new ArrayList<>();

        for (Produto produto : produtos) {
            produtosAdcionarContato.add(
                    produtoService.procurarProdutoPorNome(produto.getNome())
            );
        }

        return produtosAdcionarContato;
    }
}
