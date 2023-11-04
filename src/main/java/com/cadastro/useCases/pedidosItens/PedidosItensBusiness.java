package com.cadastro.useCases.pedidosItens;
import com.cadastro.entities.Pedidos;
import com.cadastro.entities.PedidosItens;
import com.cadastro.entities.Produtos;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensResponseDom;
import java.util.List;

public interface PedidosItensBusiness {
    public List<PedidosItensResponseDom> carregarPedidosItens();
    PedidosItensResponseDom criarPedidosItens(PedidosItensRequestDom pedidosItensRequestDom,
                                              Produtos produtos, Pedidos pedidos) throws Exception;
    PedidosItensResponseDom atualizarPedidosItens(Long id, PedidosItensRequestDom pedidosItensRequestDom) throws SenacException;
    void deletarPedidosItens(Long id);
    PedidosItens carregarPedidosItensEntidade(Long id);
    PedidosItensResponseDom carregarPedidosItensById(Long id);


}
