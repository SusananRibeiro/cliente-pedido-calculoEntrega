package com.cadastro.useCases.pedidos.impl.repositorys;
import com.cadastro.entities.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
}
