package br.com.zup.sistemamarketing.services;

import br.com.zup.sistemamarketing.exceptions.produto.ProdutoNaoExisteException;
import br.com.zup.sistemamarketing.models.Categoria;
import br.com.zup.sistemamarketing.models.Produto;
import br.com.zup.sistemamarketing.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, CategoriaService categoriaService) {
        this.produtoRepository = produtoRepository;
        this.categoriaService = categoriaService;
    }

    public Produto cadastrarNovoProduto(Produto produto) {
        produto.setCategorias(gerarListaCategoriaParaCadastro(produto.getCategorias()));
        return produtoRepository.save(produto);
    }

    private List<Categoria> gerarListaCategoriaParaCadastro(List<Categoria> categorias) {
        List<Categoria> categoriasAdcionarNoProduto = new ArrayList<>();
        for (Categoria categoria : categorias) {
            categoriasAdcionarNoProduto.add(categoriaService.verificarCategoriaPorNome(categoria));
        }
        return categoriasAdcionarNoProduto;
    }

    public Iterable<Produto> obterTodosProduto() {
        return produtoRepository.findAll();
    }

    public Produto procurarProdutoPorId(Integer id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);

        if (optionalProduto.isEmpty()) {
            throw new ProdutoNaoExisteException("Não existe produto com id " + id);
        }

        return optionalProduto.get();
    }

    public Produto procurarProdutoPorNome(String nome) {
        Optional<Produto> optionalProduto = produtoRepository.findByNome(nome);

        if (optionalProduto.isEmpty()) {
            throw new ProdutoNaoExisteException("Não existe produto com nome " + nome);
        }

        return optionalProduto.get();
    }

    public Produto atualizarProduto(Produto produtoAtualizado) {
        Produto produto = procurarProdutoPorId(produtoAtualizado.getId());

        produto.setNome(produtoAtualizado.getNome());
        produto.setCategorias(
                gerarListaCategoriaParaCadastro(produtoAtualizado.getCategorias())
        );

        return produtoRepository.save(produto);
    }

    public Boolean verificarProdutoExistePorId(Integer id) {
        return produtoRepository.existsById(id);
    }

    public void deletarProdutoPorId(Integer id) {
        Produto produtoParaDeletar = procurarProdutoPorId(id);
        produtoRepository.delete(produtoParaDeletar);
    }
}
