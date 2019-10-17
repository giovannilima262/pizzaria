package com.uds.sistema.pizzaria.controller;

import com.uds.sistema.pizzaria.exceptions.AdicionalException;
import com.uds.sistema.pizzaria.exceptions.SaborException;
import com.uds.sistema.pizzaria.exceptions.TamanhoException;
import com.uds.sistema.pizzaria.model.request.PedidoRequest;
import com.uds.sistema.pizzaria.model.response.PedidoResponse;
import com.uds.sistema.pizzaria.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/")
    public ResponseEntity salvar(@RequestBody PedidoRequest pedidoRequest) {
        try {
            pedidoService.salvar(pedidoRequest, new Date());
        } catch (TamanhoException | SaborException | AdicionalException e) {
            return  ResponseEntity.status(HttpStatus.BAD_GATEWAY)
            .body(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PedidoResponse>> buscarPedidoResponse() {
        return ResponseEntity.ok(pedidoService.buscarPedidoResponse());
    }


}
