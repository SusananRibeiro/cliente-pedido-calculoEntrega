package com.cadastro.useCases.pedidosItens;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensResponseDom;

import java.util.List;

public interface PedidosItensBusiness {
    List<PedidosItensResponseDom> carregarPedidosItens();
    PedidosItensResponseDom criarPedidoItens(PedidosItensRequestDom pedidoItens) throws SenacException;
    PedidosItensResponseDom atualizarPedidoItens(Long id, PedidosItensRequestDom pedidoItens) throws SenacException;
    void deletarPedidoItens(Long id);
    PedidosItensResponseDom carregarPedidoItensById(Long id) throws SenacException;
}
