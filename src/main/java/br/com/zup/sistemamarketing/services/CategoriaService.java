package br.com.zup.sistemamarketing.services;

import br.com.zup.sistemamarketing.models.Categoria;
import br.com.zup.sistemamarketing.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Categoria procurarCategoriaPorId(Integer id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isEmpty()) {
            throw new RuntimeException("Categoria com id " + id + " não existe!");
        }

        return categoriaOptional.get();
    }

    public Categoria procurarCategoriaPorNome(String nome) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome(nome);

        if (categoriaOptional.isEmpty()) {
            throw new RuntimeException("Categoria com nome " + nome + " não existe!");
        }

        return categoriaOptional.get();
    }

    public Iterable<Categoria> procurarCategoriaPorIdOuNome(Integer id, String nome) {
        return categoriaRepository.findByIdOrNome(id, nome);
    }

    public Iterable<Categoria> obterTodasCategoria() {
        return categoriaRepository.findAll();
    }

    public Categoria atualizarCategoria(Categoria categoria) {
        Categoria categoriaAntiga = procurarCategoriaPorId(categoria.getId());
        categoriaAntiga.setNome(categoria.getNome());
        return categoriaRepository.save(categoriaAntiga);
    }

    public void excluirCategoriaPorId(Integer id) {
        Categoria categoria = procurarCategoriaPorId(id);
        categoriaRepository.delete(categoria);
    }
}
