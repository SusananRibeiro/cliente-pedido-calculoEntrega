package com.cadastro.useCases.pedidos.domanis;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PedidosRequestDom {

    private LocalDateTime dataCriacao;

    private LocalDate dataEntrega;

    private Integer valorDesconto;

    private Long clienteId;

    private Long enderecoId;

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
}
