package br.com.zup.sistemamarketing.repositories;

import br.com.zup.sistemamarketing.models.Contato;
import org.springframework.data.repository.CrudRepository;

public interface ContatoRepository extends CrudRepository<Contato, Integer> {
    Boolean existsByEmail(String email);
    Iterable<Contato> findAllByProdutosNome(String nome);
    Iterable<Contato> findAllByProdutosCategoriasNome(String nome);
}