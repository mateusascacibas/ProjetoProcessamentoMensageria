package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.dto.PedidoDTO;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	private final PedidoService pedidoService;
	
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@PostMapping
	public ResponseEntity<String> criar(@RequestBody PedidoDTO pedidoDTO){
		try {
			pedidoService.criarPedido(pedidoDTO);
			return ResponseEntity.ok("📤 Pedido enviado com sucesso!");
		} catch(Exception e) {
			   return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
		}
	}
}
