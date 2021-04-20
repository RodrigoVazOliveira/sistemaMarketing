package br.com.zup.sistemamarketing.services;

import br.com.zup.sistemamarketing.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, CategoriaService categoriaService) {
        this.produtoRepository = produtoRepository;
        this.categoriaService = categoriaService;
    }
}
