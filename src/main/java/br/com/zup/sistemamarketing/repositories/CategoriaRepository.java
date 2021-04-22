package br.com.zup.sistemamarketing.repositories;

import br.com.zup.sistemamarketing.models.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
    Optional<Categoria> findByNome(String nome);
    Iterable<Categoria> findAllByIdOrNome(Integer id, String nome);
}