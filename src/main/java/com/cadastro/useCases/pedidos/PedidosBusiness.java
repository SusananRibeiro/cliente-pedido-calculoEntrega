package com.cadastro.useCases.pedidos;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.pedidos.domanis.PedidosRequestDom;
import com.cadastro.useCases.pedidos.domanis.PedidosResponseDom;
import com.cadastro.useCases.produtos.domanis.ProdutosResponseDom;

import java.util.List;

public interface PedidosBusiness {
    List<PedidosResponseDom> carregarPedidos();
    PedidosResponseDom criarPedido(PedidosRequestDom pedido) throws SenacException;
    PedidosResponseDom atualizarPedido(Long id, PedidosRequestDom pedido) throws SenacException;
    void deletarPedido(Long id);

    PedidosResponseDom carregarPedidoById(Long id) throws SenacException;
}
