package io.github.getulio222.projetofiscal.cliente.infra;

import io.github.getulio222.projetofiscal.cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByCpfCnpj(String cpfCnpj);

    Optional<Cliente> findByCpfCnpj(String cpfCnpj);

    List<Cliente> findByNomeRazaoSocialContainingIgnoreCase(String nomeRazaoSocial);

    boolean existsByCpfCnpjAndIdNot(String cpfCnpj, Long id);
}