package com.cadastro.useCases.pedidos.domanis;
import com.cadastro.entities.Clientes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PedidosResponseDom {
    private Long id;

    private LocalDateTime dataCriacao;

    private LocalDate dataEntrega;

    private Integer valorDesconto;

    private Long clienteId;

    private Long enderecoId;

    private List<PedidosProdutosResponseDom> produtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Integer getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Integer valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    public List<PedidosProdutosResponseDom> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<PedidosProdutosResponseDom> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "PedidosResponseDom{" +
                "id=" + id +
                ", dataCriacao=" + dataCriacao +
                ", dataEntrega=" + dataEntrega +
                ", valorDesconto=" + valorDesconto +
                ", clienteId=" + clienteId +
                ", enderecoId=" + enderecoId +
                ", produtos=" + produtos +
                '}';
    }
}
