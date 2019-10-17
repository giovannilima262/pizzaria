package com.uds.sistema.pizzaria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uds.sistema.pizzaria.enums.AdicionalEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "adicional")
public class Adicional {

    @Id
    @SequenceGenerator(name="adicional_id_seq",
            sequenceName="adicional_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adicional_id_seq")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nome")
    @Enumerated(EnumType.STRING)
    private AdicionalEnum adicionalEnum;

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
    @ManyToMany(mappedBy = "adicionais")
    private List<Pedido> pedidos;

}

