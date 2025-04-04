package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core;

import java.util.List;

public class Validadores {

	public static List<ValidadorPedido> obterValidadores() {
		return List.of(pedido -> {
			if (pedido.getCliente() == null || pedido.getCliente().isBlank()) {
				throw new Exception("Cliente inválido");
			}
		}, pedido -> {
			if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
				throw new Exception("Pedido sem itens");
			}
		}, pedido -> {
			boolean possuiQuantidadeNegativa = pedido.getItens().stream().anyMatch(item -> item.getQuantidade() < 0);
			if (possuiQuantidadeNegativa) {
				throw new Exception("Item com quantidade inválida");
			}
		});

	}

}
