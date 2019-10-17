package com.uds.sistema.pizzaria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uds.sistema.pizzaria.enums.SaborEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "sabor")
public class Sabor {

    @Id
    @SequenceGenerator(name="sabor_id_seq",
            sequenceName="sabor_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sabor_id_seq")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nome")
    @Enumerated(EnumType.STRING)
    private SaborEnum saborEnum;

    @NotNull
    @Column(name = "tempo_minuto")
    private Integer tempoMinuto;

    @NotNull
    @Column(name = "data_criacao")
    private Date dataCriacao;

    @JsonIgnore
    @OneToMany(mappedBy = "sabor", fetch = FetchType.LAZY)
    private List<Pedido> pedidos;

}
