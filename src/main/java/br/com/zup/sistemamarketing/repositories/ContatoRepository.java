package br.com.zup.sistemamarketing.repositories;

import br.com.zup.sistemamarketing.models.Contato;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContatoRepository extends CrudRepository<Contato, Integer> {
    Boolean existsByEmail(String email);
    Iterable<Contato> findByProdutosNome(String nome);
}