package com.uds.sistema.pizzaria.model.response;

import com.uds.sistema.pizzaria.model.Adicional;
import com.uds.sistema.pizzaria.model.Sabor;
import com.uds.sistema.pizzaria.model.Tamanho;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PedidoResponse {

    private Tamanho tamanho;
    private Sabor sabor;
    private List<Adicional> personalizacoes;
    private BigDecimal valorTotal;
    private Integer tempoPreparo;

}
