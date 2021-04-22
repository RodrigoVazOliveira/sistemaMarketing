package br.com.zup.sistemamarketing.services;

import br.com.zup.sistemamarketing.models.Contato;
import br.com.zup.sistemamarketing.models.Produto;
import br.com.zup.sistemamarketing.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Iterable<Contato> obterTodosContatos() {
        return contatoRepository.findAll();
    }

    public Contato procurarContatoPorId(Integer id) {
        Optional<Contato> optionalContato = contatoRepository.findById(id);

        if (optionalContato.isEmpty()) {
            throw new RuntimeException("Contato com id " + id + " não existe");
        }

        return optionalContato.get();
    }

    public Boolean contatoExiste(Integer id) {
        return contatoRepository.existsById(id);
    }

    public Contato atualizarContatoCompleto(Contato contatoAtualizado) {
        Contato contatoAtual = procurarContatoPorId(contatoAtualizado.getId());
        contatoAtual.setNomeCompleto(contatoAtualizado.getNomeCompleto());
        contatoAtual.setEmail(contatoAtualizado.getEmail());
        contatoAtual.setTelefone(contatoAtualizado.getTelefone());

        List<Produto> listaProdutos = verificarProdutos(contatoAtualizado.getProdutos());
        listaProdutos.addAll(contatoAtual.getProdutos());
        contatoAtual.setProdutos(listaProdutos);

        return contatoRepository.save(contatoAtual);
    }
}
