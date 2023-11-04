package com.cadastro.useCases.pedidosItens.impl.repositorys;
import com.cadastro.entities.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosItensPedidosRepository extends JpaRepository<Pedidos, Long> {
}
