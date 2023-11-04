package com.cadastro.useCases.pedidosItens;
import com.cadastro.entities.Pedidos;
import com.cadastro.entities.Produtos;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensResponseDom;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface PedidosItensService {
    public List<PedidosItensResponseDom> carregarPedidosItens();
    PedidosItensResponseDom criarPedidosItens(PedidosItensRequestDom pedidosItensRequestDom, Produtos produtos, Pedidos pedidos) throws Exception;
    PedidosItensResponseDom atualizarPedidosItens(Long id, PedidosItensRequestDom pedidosItensRequestDom) throws SenacException;
    void deletarPedidosItens(Long id);
    PedidosItensResponseDom carregarPedidosItensById(Long id);
}
