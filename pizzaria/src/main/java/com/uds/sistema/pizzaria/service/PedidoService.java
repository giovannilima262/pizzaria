package com.uds.sistema.pizzaria.service;

import com.uds.sistema.pizzaria.enums.AdicionalEnum;
import com.uds.sistema.pizzaria.enums.SaborEnum;
import com.uds.sistema.pizzaria.enums.TamanhoEnum;
import com.uds.sistema.pizzaria.exceptions.AdicionalException;
import com.uds.sistema.pizzaria.exceptions.SaborException;
import com.uds.sistema.pizzaria.exceptions.TamanhoException;
import com.uds.sistema.pizzaria.exceptions.mensagemException.Mensagem;
import com.uds.sistema.pizzaria.model.Adicional;
import com.uds.sistema.pizzaria.model.Pedido;
import com.uds.sistema.pizzaria.model.Sabor;
import com.uds.sistema.pizzaria.model.Tamanho;
import com.uds.sistema.pizzaria.model.request.PedidoRequest;
import com.uds.sistema.pizzaria.model.response.PedidoResponse;
import com.uds.sistema.pizzaria.repository.AdicionalRepository;
import com.uds.sistema.pizzaria.repository.PedidoRepository;
import com.uds.sistema.pizzaria.repository.SaborRepository;
import com.uds.sistema.pizzaria.repository.TamanhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @Autowired
    private SaborRepository saborRepository;

    @Autowired
    private AdicionalRepository adicionalRepository;

    public PedidoService(PedidoRepository pedidoRepository, TamanhoRepository tamanhoRepository, SaborRepository saborRepository, AdicionalRepository adicionalRepository) {
        this.pedidoRepository = pedidoRepository;
        this.adicionalRepository = adicionalRepository;
        this.tamanhoRepository = tamanhoRepository;
        this.saborRepository = saborRepository;
    }

    public void salvar(PedidoRequest pedidoRequest, Date dataAtual) throws TamanhoException, SaborException, AdicionalException {
        Pedido pedido = new Pedido();
        pedido.setTamanho(buscarTamanho(pedidoRequest.getTamanhoEnum(), dataAtual));
        pedido.setSabor(buscarSabor(pedidoRequest.getSaborEnum(), dataAtual));
        pedido.setDataCriacao(dataAtual);
        pedido.setAdicionais(buscarAdicional(pedidoRequest.getAdicionalEnumList(), dataAtual));
        pedidoRepository.save(pedido);
    }

    public List<PedidoResponse> buscarPedidoResponse() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoResponse> pedidoResponses = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            PedidoResponse pedidoResponse = new PedidoResponse();
            pedidoResponse.setTamanho(pedido.getTamanho());
            pedidoResponse.setSabor(pedido.getSabor());
            pedidoResponse.setPersonalizacoes(pedido.getAdicionais());
            pedidoResponse.setTempoPreparo(calcularTempoPreparo(pedido));
            pedidoResponse.setValorTotal(calcularValorTotal(pedido));
            pedidoResponses.add(pedidoResponse);
        }
        return pedidoResponses;
    }

    private BigDecimal calcularValorTotal(Pedido pedido) {
        BigDecimal valorTotal = pedido.getTamanho().getValor();
        if(pedido.getAdicionais() != null) {
            Optional<BigDecimal> adicionaisValor = pedido.getAdicionais().stream().map(Adicional::getValor).reduce(BigDecimal::add);
            if (adicionaisValor.isPresent()) {
                valorTotal = valorTotal.add(adicionaisValor.get());
            }
        }
        return valorTotal;
    }

    private Integer calcularTempoPreparo(Pedido pedido) {
        Integer tempoSabor = pedido.getSabor().getTempoMinuto();
        Integer tempoTamanho = pedido.getTamanho().getTempoMinuto();
        int tempoAdicionais = 0;
        if(pedido.getAdicionais() != null) {
            tempoAdicionais = pedido.getAdicionais().stream().mapToInt(Adicional::getTempoMinuto).sum();
        }
        return tempoSabor + tempoTamanho + tempoAdicionais;
    }

    private Tamanho buscarTamanho(TamanhoEnum tamanhoEnum, Date date) throws TamanhoException {
        validarTamanho(tamanhoEnum);
        Optional<List<Tamanho>> tamanhoListOptional = tamanhoRepository.detalheUltimaData(tamanhoEnum, date);
        if(!tamanhoListOptional.isPresent()) {
            throw new TamanhoException(Mensagem.TAMANHO_NAO_ENCONTRADO.concat(tamanhoEnum.name()));
        }
        return tamanhoListOptional.get().get(0);
    }

    private void validarTamanho(TamanhoEnum tamanhoEnum) throws TamanhoException {
        if(tamanhoEnum == null) {
            throw new TamanhoException(Mensagem.TAMANHO_NAO_PODE_SER_VAZIO);
        }
    }

    private Sabor buscarSabor(SaborEnum saborEnum, Date date) throws SaborException {
        validarSabor(saborEnum);
        Optional<List<Sabor>> saborListOptional = saborRepository.detalheUltimaData(saborEnum, date);
        if(!saborListOptional.isPresent()) {
            throw new SaborException(Mensagem.SABOR_NAO_ENCONTRADO.concat(saborEnum.name()));
        }
        return saborListOptional.get().get(0);
    }

    private void validarSabor(SaborEnum saborEnum) throws SaborException {
        if(saborEnum == null) {
            throw new SaborException(Mensagem.SABOR_NAO_PODE_SER_VAZIO);
        }
    }

    private List<Adicional> buscarAdicional(List<AdicionalEnum> adicionalEnumList, Date date) throws AdicionalException {
        if(adicionalEnumList == null) {
            return null;
        }
        List<Adicional> adicionalList = new ArrayList<>();
        for (AdicionalEnum adicionalEnum : adicionalEnumList) {
            Optional<List<Adicional>> adicionalListOptional = adicionalRepository.detalheUltimaData(adicionalEnum, date);
            if(!adicionalListOptional.isPresent()) {
                throw new AdicionalException(Mensagem.ADICIONAL_NAO_ENCONTRADO.concat(adicionalEnum.name()));
            }
            adicionalList.add(adicionalListOptional.get().get(0));
        }
        return adicionalList;
    }

}
