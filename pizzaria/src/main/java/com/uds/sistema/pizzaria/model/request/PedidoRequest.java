package com.uds.sistema.pizzaria.model.request;

import com.uds.sistema.pizzaria.enums.AdicionalEnum;
import com.uds.sistema.pizzaria.enums.SaborEnum;
import com.uds.sistema.pizzaria.enums.TamanhoEnum;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class PedidoRequest {

    private TamanhoEnum tamanhoEnum;

    private SaborEnum saborEnum;

    private List<AdicionalEnum> adicionalEnumList;

}
