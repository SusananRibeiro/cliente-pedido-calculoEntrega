package com.cadastro.useCases.pedidosItens.impl;

import com.cadastro.entities.*;
import com.cadastro.frameWork.annotions.Business;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.frameWork.utils.StringUtil;
import com.cadastro.useCases.clientes.domanis.ClientesRequestDom;
import com.cadastro.useCases.clientes.domanis.ClientesResponseDom;
import com.cadastro.useCases.clientes.impl.mappers.ClientesMapper;
import com.cadastro.useCases.pedidosItens.PedidosItensBusiness;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import com.cadastro.useCases.pedidosItens.domanis.PedidosItensResponseDom;
import com.cadastro.useCases.pedidosItens.impl.mappers.PedidosItensMapper;
import com.cadastro.useCases.pedidosItens.impl.repositorys.PedidosItensPedidosRepository;
import com.cadastro.useCases.pedidosItens.impl.repositorys.PedidosItensProdutosRepository;
import com.cadastro.useCases.pedidosItens.impl.repositorys.PedidosItensRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
public class PedidosItensBusinessImpl implements PedidosItensBusiness {
    @Autowired
    private PedidosItensRepository pedidosItensRepository;
    @Autowired
    private PedidosItensProdutosRepository pedidosItensProdutosRepository;

    @Autowired
    private PedidosItensPedidosRepository pedidosItensPedidosRepository;
    @Override
    public List<PedidosItensResponseDom> carregarPedidosItens() {
        List<PedidosItens> pedidosItensList = pedidosItensRepository.findAll();

        List<PedidosItensResponseDom> out = pedidosItensList
                .stream()
                .map(PedidosItensMapper::pedidosItensToPedidosItensResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public PedidosItensResponseDom criarPedidosItens(PedidosItensRequestDom pedidosItensRequestDom,
                                                     Produtos produtos, Pedidos pedidos) throws Exception {
        List<String> messages = this.validacaoManutencaoPedidosItens(pedidosItensRequestDom);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        PedidosItens pedidosItens = PedidosItensMapper
                .pedidosItensRequestDomToPedidosItens(pedidosItensRequestDom, produtos, pedidos);

        PedidosItens resultPedidosItens = pedidosItensRepository.save(pedidosItens);

        PedidosItensResponseDom out = PedidosItensMapper.pedidosItensToPedidosItensResponseDom(resultPedidosItens);

        return out;
    }

    @Override
    public PedidosItensResponseDom atualizarPedidosItens(Long id,
                                              PedidosItensRequestDom pedidosItensRequestDom) throws SenacException {
        List<String> messages = this.validacaoManutencaoPedidosItens(pedidosItensRequestDom);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Produtos> produto = pedidosItensProdutosRepository.findById(pedidosItensRequestDom.getProdutoId());
        if(!produto.isPresent()){
            throw new SenacException("Produto não encontrado");
        }

        Optional<Pedidos> pedido = pedidosItensPedidosRepository.findById(pedidosItensRequestDom.getPedidoId());
        if(!pedido.isPresent()){
            throw new SenacException("Pedido não encontrado");
        }

        Optional<PedidosItens> pedidosItens = pedidosItensRepository.findById(id).map(record -> {
            record.setQuantidade(pedidosItensRequestDom.getQuantidade());
            record.setValorUnitario(pedidosItensRequestDom.getValorUnitario());
            record.setProdutoId(produto.get());
            record.setPedidoId(pedido.get());
            return pedidosItensRepository.save(record);
        });

        if(!pedidosItens.isPresent()){
            throw new SenacException("Pedido informando não existe!");
        }

        PedidosItensResponseDom out =
                PedidosItensMapper.pedidosItensToPedidosItensResponseDom(pedidosItens.get());

        return out;
    }

    @Override
    public void deletarPedidosItens(Long id) {
        pedidosItensRepository.deleteById(id);

    }

    @Override
    public PedidosItens carregarPedidosItensEntidade(Long id) {
        PedidosItens pedidosItens = pedidosItensRepository.findById(id).get();

        return pedidosItens;
    }

    @Override
    public PedidosItensResponseDom carregarPedidosItensById(Long id) {
        PedidosItens pedidosItens = pedidosItensRepository.findById(id).get();

        List<Produtos> produtos = pedidosItensProdutosRepository.carregarProdutosByPedidoItensId(id);

        PedidosItensResponseDom out = PedidosItensMapper.pedidosItensToPedidosItensResponseDom(pedidosItens, produtos);

        return out;
    }

    private List<String> validacaoManutencaoPedidosItens(PedidosItensRequestDom pedidosItens){
        List<String> messages = new ArrayList<>();

        if(StringUtil.validarString(Integer.toString(pedidosItens.getQuantidade()))){
            messages.add("Quantidade não informada!");
        }

        if(StringUtil.validarString(Double.toString(pedidosItens.getValorUnitario()))){
            messages.add("Valor do pedido não informado!");
        }

        if(StringUtil.validarString(Double.toString(pedidosItens.getProdutoId()))){
            messages.add("Código do produto não informado!");
        }

        if(StringUtil.validarString(Double.toString(pedidosItens.getPedidoId()))){
            messages.add("Código do pedido não informado!");
        }

        return messages;
    }

}
