package io.github.getulio222.cadastramento.repository;

import io.github.getulio222.cadastramento.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    boolean existsByCpf(String cpf);
    Optional<Pessoa> findByCpf(String cpf);
    List<Pessoa> findByNomeContainingIgnoreCase(String nome);
}
