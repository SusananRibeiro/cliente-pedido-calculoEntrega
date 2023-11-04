package com.cadastro.useCases.pedidosItens.impl;
import com.cadastro.entities.Pedidos;
import com.cadastro.entities.Produtos;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.pedidosItens.PedidosItensService;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PedidosItensServiceImpl implements PedidosItensService {
    @Autowired
    private PedidosItensBusinessImpl pedidosItensBusiness;
    @Override
    public List<PedidosItensResponseDom> carregarPedidosItens() {

        return pedidosItensBusiness.carregarPedidosItens();
    }

    @Override
    public PedidosItensResponseDom criarPedidosItens(PedidosItensRequestDom pedidosItensRequestDom,
                                                     Produtos produtos, Pedidos pedidos) throws Exception {
        return pedidosItensBusiness.criarPedidosItens(pedidosItensRequestDom, produtos, pedidos);
    }

    @Override
    public PedidosItensResponseDom atualizarPedidosItens(Long id,
                                             PedidosItensRequestDom pedidosItensRequestDom) throws SenacException {
        return pedidosItensBusiness.atualizarPedidosItens(id, pedidosItensRequestDom);
    }

    @Override
    public void deletarPedidosItens(Long id) {
        pedidosItensBusiness.deletarPedidosItens(id);

    }

    @Override
    public PedidosItensResponseDom carregarPedidosItensById(Long id) {

        return pedidosItensBusiness.carregarPedidosItensById(id);
    }
}
