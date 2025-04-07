package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.entity.PedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
