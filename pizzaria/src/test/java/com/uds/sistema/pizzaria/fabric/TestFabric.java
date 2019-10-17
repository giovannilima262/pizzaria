package com.uds.sistema.pizzaria.fabric;

import com.uds.sistema.pizzaria.enums.AdicionalEnum;
import com.uds.sistema.pizzaria.enums.SaborEnum;
import com.uds.sistema.pizzaria.enums.TamanhoEnum;
import com.uds.sistema.pizzaria.model.Adicional;
import com.uds.sistema.pizzaria.model.Pedido;
import com.uds.sistema.pizzaria.model.Sabor;
import com.uds.sistema.pizzaria.model.Tamanho;
import com.uds.sistema.pizzaria.model.request.PedidoRequest;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestFabric {

    public static PedidoRequest umPedidoRequest() {
        return PedidoRequest.builder()
                .saborEnum(SaborEnum.PORTUGUESA)
                .tamanhoEnum(TamanhoEnum.MEDIA)
                .adicionalEnumList(Arrays.asList(AdicionalEnum.BORDA_RECHEADA, AdicionalEnum.SEM_CEBOLA, AdicionalEnum.EXTRA_BACON))
                .build();
    }

    private static Pedido umPedido() {
        Pedido pedido = new Pedido();
        Sabor sabor = new Sabor();
        Tamanho tamanho = new Tamanho();
        tamanho.setTamanhoEnum(TamanhoEnum.GRANDE);
        tamanho.setTempoMinuto(25);
        tamanho.setValor(new BigDecimal(40));
        sabor.setSaborEnum(SaborEnum.PORTUGUESA);
        sabor.setTempoMinuto(5);
        pedido.setSabor(sabor);
        pedido.setTamanho(tamanho);
        pedido.setDataCriacao(dataAtual());
        return pedido;
    }

    public static PedidoRequest umPedidoRequestSemAdicional() {
        return PedidoRequest.builder()
                .saborEnum(SaborEnum.PORTUGUESA)
                .tamanhoEnum(TamanhoEnum.MEDIA)
                .build();
    }

    public static List<Pedido> listaPedidosSemAdicional() {
        return Collections.singletonList(umPedido());
    }

    public static List<Pedido> listaPedidosComAdicional() {
        Pedido pedido = umPedido();
        pedido.setAdicionais(listaAdicionais().get());
        return Collections.singletonList(pedido);
    }

    public static Optional<List<Adicional>> listaAdicionais() {
        return Optional.of(Arrays.asList(
                umAdicional(AdicionalEnum.BORDA_RECHEADA, 5, new BigDecimal(5)),
                umAdicional(AdicionalEnum.EXTRA_BACON, 0, new BigDecimal(3)),
                umAdicional(AdicionalEnum.SEM_CEBOLA, 0, BigDecimal.ZERO)
        ));
    }

    public static Adicional umAdicional(AdicionalEnum adicionalEnum, Integer tempoMinuto, BigDecimal valor) {
        Adicional adicional = new Adicional();
        adicional.setAdicionalEnum(adicionalEnum);
        adicional.setTempoMinuto(tempoMinuto);
        adicional.setValor(valor);
        return adicional;
    }

    public static Date dataAtual() {
        Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = format.parse ( "2019-10-17 00:03:30" );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }

    public static Optional<List<Tamanho>> listaTamanhosPizza() {
        Tamanho tamanho = new Tamanho();
        tamanho.setTamanhoEnum(TamanhoEnum.GRANDE);
        tamanho.setTempoMinuto(25);
        tamanho.setValor(new BigDecimal(40));
        tamanho.setDataCriacao(dataAtual());
        return Optional.of(Collections.singletonList(tamanho));
    }

    public static Optional<List<Sabor>> listaSaborPizza() {
        Sabor sabor = new Sabor();
        sabor.setSaborEnum(SaborEnum.PORTUGUESA);
        sabor.setTempoMinuto(5);
        sabor.setDataCriacao(dataAtual());
        return Optional.of(Collections.singletonList(sabor));
    }

}
