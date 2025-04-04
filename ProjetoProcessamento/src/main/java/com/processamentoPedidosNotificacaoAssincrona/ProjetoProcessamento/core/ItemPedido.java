package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.core;

public class ItemPedido {
	private String nome;
	private int quantidade;
	
	public ItemPedido(String nome, int quantidade) {
		this.setNome(nome);
		this.setQuantidade(quantidade);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
