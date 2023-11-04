package com.cadastro.useCases.pedidosItens.domanis;

import java.util.List;

public class PedidosItensResponseDom {
    private Long id;

    private int quantidade;

    private double valorUnitario;

    private Long produtoId;

    private Long pedidoId;

    private List<PedidosItensProdutosResponseDom> produtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public List<PedidosItensProdutosResponseDom> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<PedidosItensProdutosResponseDom> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "PedidosItensResponseDom{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", valorUnitario=" + valorUnitario +
                ", produtoId=" + produtoId +
                ", pedidoId=" + pedidoId +
                ", pedidosItensProdutos=" + produtos +
                '}';
    }
}
