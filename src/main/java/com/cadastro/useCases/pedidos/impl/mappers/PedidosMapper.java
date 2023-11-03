package com.cadastro.useCases.pedidos.impl.mappers;
import com.cadastro.entities.Clientes;
import com.cadastro.entities.Enderecos;
import com.cadastro.entities.Pedidos;
import com.cadastro.useCases.pedidos.domanis.PedidosRequestDom;
import com.cadastro.useCases.pedidos.domanis.PedidosResponseDom;

public class PedidosMapper {

    public static Pedidos pedidosResquestDomToPedidos(PedidosRequestDom pedidos, Clientes cliente, Enderecos enderecos){
        Pedidos out = new Pedidos();
        out.setDataCriacao(pedidos.getDataCriacao());
        out.setDataEntrega(pedidos.getDataEntrega());
        out.setValorDesconto(pedidos.getValorDesconto());
        out.setClienteId(cliente);
        out.setEnderecoId(enderecos);

        return out;
    }

    public static PedidosResponseDom pedidosToPedidosResponseDom(Pedidos pedidos){
        PedidosResponseDom out = new PedidosResponseDom();
        out.setId(pedidos.getId());
        out.setDataCriacao(pedidos.getDataCriacao());
        out.setDataEntrega(pedidos.getDataEntrega());
        out.setValorDesconto(pedidos.getValorDesconto());
        out.setClienteId(pedidos.getClienteId().getId());
        out.setEnderecoId(pedidos.getEnderecoId().getId());

        return out;
    }


}
