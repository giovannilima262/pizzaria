package com.uds.sistema.pizzaria.service;

import com.uds.sistema.pizzaria.enums.AdicionalEnum;
import com.uds.sistema.pizzaria.exceptions.AdicionalException;
import com.uds.sistema.pizzaria.exceptions.SaborException;
import com.uds.sistema.pizzaria.exceptions.TamanhoException;
import com.uds.sistema.pizzaria.fabric.TestFabric;
import com.uds.sistema.pizzaria.model.request.PedidoRequest;
import com.uds.sistema.pizzaria.model.response.PedidoResponse;
import com.uds.sistema.pizzaria.repository.AdicionalRepository;
import com.uds.sistema.pizzaria.repository.PedidoRepository;
import com.uds.sistema.pizzaria.repository.SaborRepository;
import com.uds.sistema.pizzaria.repository.TamanhoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;

public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private TamanhoRepository tamanhoRepository;

    @Mock
    private SaborRepository saborRepository;

    @Mock
    private AdicionalRepository adicionalRepository;

    private PedidoService pedidoService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        pedidoService = new PedidoService(pedidoRepository, tamanhoRepository, saborRepository, adicionalRepository);
    }

    @Test
    public void criarPedidoSemAdicional() throws SaborException, TamanhoException, AdicionalException {
        PedidoRequest pedido = TestFabric.umPedidoRequestSemAdicional();
        when(tamanhoRepository.detalheUltimaData(pedido.getTamanhoEnum(), TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaTamanhosPizza());
        when(saborRepository.detalheUltimaData(pedido.getSaborEnum(), TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaSaborPizza());

        pedidoService.salvar(pedido, TestFabric.dataAtual());
    }

    @Test(expected = TamanhoException.class)
    public void criarPedidoSemAdicionalETamanho() throws SaborException, TamanhoException, AdicionalException {
        PedidoRequest pedido = TestFabric.umPedidoRequest();

        when(saborRepository.detalheUltimaData(pedido.getSaborEnum(), TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaSaborPizza());

        pedidoService.salvar(pedido, TestFabric.dataAtual());
    }

    @Test(expected = SaborException.class)
    public void criarPedidoSemAdicionalESabor() throws SaborException, TamanhoException, AdicionalException {
        PedidoRequest pedido = TestFabric.umPedidoRequest();

        when(tamanhoRepository.detalheUltimaData(pedido.getTamanhoEnum(), TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaTamanhosPizza());

        pedidoService.salvar(pedido, TestFabric.dataAtual());
    }

    @Test(expected = AdicionalException.class)
    public void criarPedidoComAdicionalNaoEncontrado() throws SaborException, TamanhoException, AdicionalException {
        PedidoRequest pedido = TestFabric.umPedidoRequest();

        when(tamanhoRepository.detalheUltimaData(pedido.getTamanhoEnum(), TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaTamanhosPizza());

        when(saborRepository.detalheUltimaData(pedido.getSaborEnum(), TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaSaborPizza());

        when(adicionalRepository.detalheUltimaData(AdicionalEnum.BORDA_RECHEADA, TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaAdicionais());

        pedidoService.salvar(pedido, TestFabric.dataAtual());
    }

    @Test
    public void criarPedidoComAdicionalEncontrado() throws SaborException, TamanhoException, AdicionalException {
        PedidoRequest pedido = TestFabric.umPedidoRequest();

        when(tamanhoRepository.detalheUltimaData(pedido.getTamanhoEnum(), TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaTamanhosPizza());

        when(saborRepository.detalheUltimaData(pedido.getSaborEnum(), TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaSaborPizza());

        when(adicionalRepository.detalheUltimaData(AdicionalEnum.BORDA_RECHEADA, TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaAdicionais());

        when(adicionalRepository.detalheUltimaData(AdicionalEnum.SEM_CEBOLA, TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaAdicionais());

        when(adicionalRepository.detalheUltimaData(AdicionalEnum.EXTRA_BACON, TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaAdicionais());

        pedidoService.salvar(pedido, TestFabric.dataAtual());
    }

    @Test(expected = TamanhoException.class)
    public void criarPedidoSemAdicionalETamanhoNull() throws SaborException, TamanhoException, AdicionalException {
        PedidoRequest pedido = TestFabric.umPedidoRequestSemAdicional();
        pedido.setTamanhoEnum(null);
        when(tamanhoRepository.detalheUltimaData(pedido.getTamanhoEnum(), TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaTamanhosPizza());
        when(saborRepository.detalheUltimaData(pedido.getSaborEnum(), TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaSaborPizza());

        pedidoService.salvar(pedido, TestFabric.dataAtual());
    }


    @Test(expected = SaborException.class)
    public void criarPedidoSemAdicionalESaborNull() throws SaborException, TamanhoException, AdicionalException {
        PedidoRequest pedido = TestFabric.umPedidoRequestSemAdicional();
        pedido.setSaborEnum(null);
        when(tamanhoRepository.detalheUltimaData(pedido.getTamanhoEnum(), TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaTamanhosPizza());
        when(saborRepository.detalheUltimaData(pedido.getSaborEnum(), TestFabric.dataAtual()))
                .thenReturn(TestFabric.listaSaborPizza());

        pedidoService.salvar(pedido, TestFabric.dataAtual());
    }


    @Test
    public void buscarPedidoCalculoTempoSemAdicional() {

        when(pedidoRepository.findAll()).thenReturn(TestFabric.listaPedidosSemAdicional());

        List<PedidoResponse> pedidoResponseList = pedidoService.buscarPedidoResponse();

        PedidoResponse pedidoResponse = pedidoResponseList.get(0);
        Assert.assertEquals(pedidoResponse.getTempoPreparo().intValue(), 30);
    }

    @Test
    public void buscarPedidoCalculoValorSemAdicional() {

        when(pedidoRepository.findAll()).thenReturn(TestFabric.listaPedidosSemAdicional());

        List<PedidoResponse> pedidoResponseList = pedidoService.buscarPedidoResponse();

        PedidoResponse pedidoResponse = pedidoResponseList.get(0);
        Assert.assertEquals(pedidoResponse.getValorTotal(), new BigDecimal(40));
    }

    @Test
    public void buscarPedidoCalculoTempoComAdicional() {

        when(pedidoRepository.findAll()).thenReturn(TestFabric.listaPedidosComAdicional());

        List<PedidoResponse> pedidoResponseList = pedidoService.buscarPedidoResponse();

        PedidoResponse pedidoResponse = pedidoResponseList.get(0);
        Assert.assertEquals(pedidoResponse.getTempoPreparo().intValue(), 35);
    }

    @Test
    public void buscarPedidoCalculoValorComAdicional() {

        when(pedidoRepository.findAll()).thenReturn(TestFabric.listaPedidosComAdicional());

        List<PedidoResponse> pedidoResponseList = pedidoService.buscarPedidoResponse();

        PedidoResponse pedidoResponse = pedidoResponseList.get(0);
        Assert.assertEquals(pedidoResponse.getValorTotal(), new BigDecimal(48));
    }


}
