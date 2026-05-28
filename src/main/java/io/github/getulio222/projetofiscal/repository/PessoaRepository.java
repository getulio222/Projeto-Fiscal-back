package io.github.getulio222.projetofiscal.repository;

import io.github.getulio222.projetofiscal.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    boolean existsByCpf(String cpf);
    Optional<Pessoa> findByCpf(String cpf);
    List<Pessoa> findByNomeContainingIgnoreCase(String nome);

    boolean existsByCpfAndIdNot(String cpf, Long id);
}
