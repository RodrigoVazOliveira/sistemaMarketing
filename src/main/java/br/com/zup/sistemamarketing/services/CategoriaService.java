package br.com.zup.sistemamarketing.services;

import br.com.zup.sistemamarketing.models.Categoria;
import br.com.zup.sistemamarketing.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria cadastrarNovaCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}
