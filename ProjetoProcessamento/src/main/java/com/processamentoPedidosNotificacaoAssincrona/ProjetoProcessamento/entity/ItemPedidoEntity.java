package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "itens_pedido")
public class ItemPedidoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private int quantidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id")
	private PedidoEntity pedido;

	public ItemPedidoEntity() {
	}

	public ItemPedidoEntity(String nome, int quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public PedidoEntity getPedido() {
		return pedido;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setPedido(PedidoEntity pedido) {
		this.pedido = pedido;
	}
}
