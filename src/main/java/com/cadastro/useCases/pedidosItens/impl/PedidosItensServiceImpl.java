package com.cadastro.useCases.pedidosItens.impl;
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
    public PedidosItensResponseDom criarPedidoItens(PedidosItensRequestDom pedidoItens) throws SenacException {
        return pedidosItensBusiness.criarPedidoItens(pedidoItens);
    }

    @Override
    public PedidosItensResponseDom atualizarPedidoItens(Long id, PedidosItensRequestDom pedidoItens) throws SenacException {
        return pedidosItensBusiness.atualizarPedidoItens(id, pedidoItens);
    }

    @Override
    public void deletarPedidoItens(Long id) {
        pedidosItensBusiness.deletarPedidoItens(id);

    }

    @Override
    public PedidosItensResponseDom carregarPedidoItensById(Long id) throws SenacException {
        return pedidosItensBusiness.carregarPedidoItensById(id);
    }
}
