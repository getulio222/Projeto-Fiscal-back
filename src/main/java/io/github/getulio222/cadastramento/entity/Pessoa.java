package io.github.getulio222.cadastramento.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "pessoa", schema = "lono_dev_test1")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 50, nullable = false)
    private String cidade;

    @ManyToOne
    @JoinColumn(name = "cd_estado", nullable = false)
    private Estado estado;

}
