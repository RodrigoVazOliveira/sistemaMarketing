package br.com.zup.sistemamarketing.repositories;

import br.com.zup.sistemamarketing.models.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
    Optional<Produto> findByNome(String nome);
    Boolean existsByNome(String nome);
}