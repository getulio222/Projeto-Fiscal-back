package io.github.getulio222.projetofiscal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pessoa")
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


}
