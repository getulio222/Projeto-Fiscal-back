package io.github.getulio222.projetofiscal.cliente.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cliente")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String nomeRazaoSocial;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;

    @Column(length = 14, nullable = false, unique = true)
    private String cpfCnpj;

    @Column(length = 100, nullable = false)
    private String Pais;

    @Column(length = 2, nullable = false)
    private String Uf;

    @Column(length = 100, nullable = false)
    private String Cidade;

    @Column(length = 300, nullable = false)
    private String Endereco;

    @Column(length = 11)
    private String Telefone;
}
