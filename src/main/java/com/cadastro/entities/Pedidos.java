package com.cadastro.entities;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "pedidos")
@SQLDelete(sql = "UPDATE pedidos SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
public class Pedidos {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime dataCriacao;
    @Column(nullable = false)
    private LocalDate dataEntrega;
    @Column(nullable = false)
    private Integer valorDesconto;
    @Column
    private LocalDateTime deleted_at;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Clientes clienteId;
    @OneToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    private Enderecos enderecoId;

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

    public LocalDateTime getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(LocalDateTime deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Clientes getClienteId() {
        return clienteId;
    }

    public void setClienteId(Clientes clienteId) {
        this.clienteId = clienteId;
    }

    public Enderecos getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Enderecos enderecoId) {
        this.enderecoId = enderecoId;
    }
}
