package br.com.zup.sistemamarketing.repositories;

import br.com.zup.sistemamarketing.models.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
}