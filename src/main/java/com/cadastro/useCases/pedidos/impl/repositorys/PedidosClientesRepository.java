package com.cadastro.useCases.pedidos.impl.repositorys;
import com.cadastro.entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosClientesRepository extends JpaRepository<Clientes, Long> {
}
