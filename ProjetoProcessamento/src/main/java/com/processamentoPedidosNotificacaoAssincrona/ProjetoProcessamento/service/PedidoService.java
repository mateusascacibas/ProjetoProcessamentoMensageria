package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core.ItemPedido;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core.Pedido;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core.ValidadorPedido;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core.Validadores;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.dto.PedidoDTO;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.queue.FilaMensagens;

@Service
public class PedidoService {
	
	public void criarPedido(PedidoDTO dto) throws Exception{
		List<ItemPedido> itens = dto.getItens().stream().map(i -> new ItemPedido(i.getNome(), i.getQuantidade())).toList();
		
		Pedido pedido = new Pedido(dto.getCliente(), itens);
		for(ValidadorPedido v : Validadores.obterValidadores()) {
			v.validar(pedido);
		}
		
		FilaMensagens.publicar(pedido);
	}

}
