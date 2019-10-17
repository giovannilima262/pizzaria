package com.uds.sistema.pizzaria.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @SequenceGenerator(name="pedido_id_seq",
            sequenceName="pedido_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_id_seq")
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tamanho")
    private Tamanho tamanho;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_sabor")
    private Sabor sabor;

    @NotNull
    @Column(name = "data_criacao")
    private Date dataCriacao;

    @ManyToMany
    @JoinTable(
            name = "pedido_adicional",
            joinColumns = @JoinColumn(name = "id_pedido"),
            inverseJoinColumns = @JoinColumn(name = "id_adicional"))
    private List<Adicional> adicionais;

}
