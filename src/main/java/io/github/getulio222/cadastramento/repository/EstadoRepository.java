package io.github.getulio222.cadastramento.repository;

import io.github.getulio222.cadastramento.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
