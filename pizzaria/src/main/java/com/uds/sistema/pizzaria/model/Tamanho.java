package com.uds.sistema.pizzaria.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uds.sistema.pizzaria.enums.TamanhoEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tamanho")
public class Tamanho {

    @Id
    @SequenceGenerator(name="tamanho_id_seq",
            sequenceName="tamanho_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tamanho_id_seq")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nome")
    @Enumerated(EnumType.STRING)
    private TamanhoEnum tamanhoEnum;

    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;

    @NotNull
    @Column(name = "tempo_minuto")
    private Integer tempoMinuto;

    @NotNull
    @Column(name = "data_criacao")
    private Date dataCriacao;

    @JsonIgnore
    @OneToMany(mappedBy = "tamanho", fetch = FetchType.LAZY)
    private List<Pedido> pedidos;

}
