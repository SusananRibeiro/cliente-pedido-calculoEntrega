package com.cadastro.useCases.pedidosItens.impl;
import com.cadastro.entities.*;
import com.cadastro.frameWork.annotions.Business;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.clientes.domanis.ClientesResponseDom;
import com.cadastro.useCases.clientes.impl.mappers.ClientesMapper;
import com.cadastro.useCases.pedidos.domanis.PedidosResponseDom;
import com.cadastro.useCases.pedidos.impl.mappers.PedidosMapper;
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

        List<PedidosItensResponseDom> out = pedidosItensList.stream()
                .map(PedidosItensMapper::pedidosItensToPedidosItensResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public PedidosItensResponseDom criarPedidoItens(PedidosItensRequestDom pedidoItens) throws SenacException {
        List<String> messages = this.validacaoManutencaoPedidoItens(pedidoItens);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Produtos> produto = pedidosItensProdutosRepository.findById(pedidoItens.getProdutoId());
        if(!produto.isPresent()){
            throw new SenacException("Produto não encontrado");
        }

        Optional<Pedidos> pedido = pedidosItensPedidosRepository.findById(pedidoItens.getPedidoId());
        if(!pedido.isPresent()){
            throw new SenacException("Pedido não encontrado");
        }

        PedidosItens pedidoItensRetorno = pedidosItensRepository.save(PedidosItensMapper
                .pedidosItensResquestDomToPedidosItens(pedidoItens, produto.get(), pedido.get()));

        return PedidosItensMapper.pedidosItensToPedidosItensResponseDom(pedidoItensRetorno);
    }

    @Override
    public PedidosItensResponseDom atualizarPedidoItens(Long id, PedidosItensRequestDom pedidoItens) throws SenacException {
        List<String> messages = this.validacaoManutencaoPedidoItens(pedidoItens);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Produtos> produto = pedidosItensProdutosRepository.findById(pedidoItens.getProdutoId());
        if(!produto.isPresent()){
            throw new SenacException("Produto não encontrado");
        }

        Optional<Pedidos> pedido = pedidosItensPedidosRepository.findById(pedidoItens.getPedidoId());
        if(!pedido.isPresent()){
            throw new SenacException("Pedido não encontrado");
        }

        Optional<PedidosItens> pedidosItensRetorno = pedidosItensRepository.findById(id).map(record -> {
            record.setQuantidade(pedidoItens.getQuantidade());
            record.setValorUnitario(pedidoItens.getValorUnitario());
            record.setProdutoId(produto.get());
            record.setPedidoId(pedido.get());
            return pedidosItensRepository.save(record);
        });

        if(pedidosItensRetorno.isPresent() == false){
            throw new SenacException("Item do Pedido não encontrado");
        }

        PedidosItensResponseDom out = PedidosItensMapper.pedidosItensToPedidosItensResponseDom(pedidosItensRetorno.get());

        return out;
    }

    @Override
    public void deletarPedidoItens(Long id) {
        pedidosItensRepository.deleteById(id);

    }

    @Override
    public PedidosItensResponseDom carregarPedidoItensById(Long id) throws SenacException {

        Optional<PedidosItens> optionalPedidosItens = pedidosItensRepository.findById(id);
        if(!optionalPedidosItens.isPresent()) {
            throw new SenacException("Item do Pedido não encontrado");
        }
        PedidosItens pedidosItens = pedidosItensRepository.findById(id).get();
        PedidosItensResponseDom out = PedidosItensMapper.pedidosItensToPedidosItensResponseDom(pedidosItens);
        return out;

    }

    @Override
    public PedidosItens carregarPedidoItensEntidade(Long id) {
        PedidosItens pedidosItens = pedidosItensRepository.findById(id).get();

        return pedidosItens;
    }

    private List<String> validacaoManutencaoPedidoItens(PedidosItensRequestDom pedidosItensRequestDom){
        List<String> messages = new ArrayList<>();

        if(pedidosItensRequestDom.getQuantidade() == null || pedidosItensRequestDom.getQuantidade() < 1){
            messages.add("Quantidade não informada ou inválida!");
        }

        if(pedidosItensRequestDom.getValorUnitario() == null || pedidosItensRequestDom.getValorUnitario() < 1){
            messages.add("Valor unitário não informado ou inválido!");
        }

        if(pedidosItensRequestDom.getProdutoId() == null || pedidosItensRequestDom.getProdutoId() < 1){
            messages.add("Código do produto não informado ou inválido!");
        }

        if(pedidosItensRequestDom.getPedidoId() == null || pedidosItensRequestDom.getPedidoId() < 1){
            messages.add("Código do pedido não informado ou inválido!");
        }

        return messages;
    }
}
