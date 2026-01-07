package io.github.getulio222.cadastramento.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "estado", schema = "lono_dev_test1")
@Data
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;
}
