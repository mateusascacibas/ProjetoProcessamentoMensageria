package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core;

import java.util.List;

public class Pedido {
	private String cliente;
	private List<ItemPedido> itens;
	
	public Pedido(String cliente, List<ItemPedido> itens) {
		this.setCliente(cliente);
		this.setItens(itens);
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
}
