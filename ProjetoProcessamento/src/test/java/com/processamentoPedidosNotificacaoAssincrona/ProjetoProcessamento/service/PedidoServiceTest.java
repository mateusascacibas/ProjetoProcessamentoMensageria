package com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.dto.ItemDTO;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.dto.PedidoDTO;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.entity.PedidoEntity;
import com.processamentoPedidosNotificacaoAssincrona.ProjetoProcessamento.repository.PedidoRepository;

class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoPublisher pedidoPublisher;

    @Mock
    private PedidoRepository pedidoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarPedidoValidoComSucesso() throws Exception {
        ItemDTO item1 = new ItemDTO("Produto Teste", 2);
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setCliente("Mateus");
        pedidoDTO.setItens(List.of(item1));
        pedidoService.criarPedido(pedidoDTO);
        verify(pedidoRepository, times(1)).save(any(PedidoEntity.class));
        verify(pedidoPublisher, times(1)).publicar(any());
    }
}
