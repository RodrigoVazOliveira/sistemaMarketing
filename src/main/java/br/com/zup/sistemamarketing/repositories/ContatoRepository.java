package br.com.zup.sistemamarketing.repositories;

import br.com.zup.sistemamarketing.models.Contato;
import org.springframework.data.repository.CrudRepository;

public interface ContatoRepository extends CrudRepository<Contato, Integer> {
    Boolean existsByEmail(String email);
    Iterable<Contato> findByProdutosNome(String nome);
    Iterable<Contato> findByProdutosCategoriasNome(String nome);
}