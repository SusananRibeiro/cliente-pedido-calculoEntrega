package com.cadastro.useCases.pedidos.impl;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.pedidos.PedidosService;
import com.cadastro.useCases.pedidos.domanis.PedidosRequestDom;
import com.cadastro.useCases.pedidos.domanis.PedidosResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PedidosServiceImpl implements PedidosService {
    @Autowired
    private PedidosBusinessImpl pedidosBusiness;
    @Override
    public List<PedidosResponseDom> carregarPedidos() {

        return pedidosBusiness.carregarPedidos();
    }

    @Override
    public PedidosResponseDom criarPedido(PedidosRequestDom pedido) throws SenacException {
        return pedidosBusiness.criarPedido(pedido);
    }

    @Override
    public PedidosResponseDom atualizarPedido(Long id, PedidosRequestDom pedido) throws SenacException {
        return pedidosBusiness.atualizarPedido(id, pedido);
    }

    @Override
    public void deletarPedido(Long id) {
        pedidosBusiness.deletarPedido(id);
    }

    @Override
    public PedidosResponseDom carregarPedidoById(Long id) throws SenacException {
        return pedidosBusiness.carregarPedidoById(id);
    }
}
