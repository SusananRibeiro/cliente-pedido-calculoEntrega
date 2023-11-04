package com.cadastro.useCases.pedidosItens.impl.repositorys;
import com.cadastro.entities.PedidosItens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosItensRepository extends JpaRepository<PedidosItens, Long> {
}
