package br.com.zup.sistemamarketing.repositories;

import br.com.zup.sistemamarketing.models.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
}