package com.cadastro.useCases.pedidos.impl.repositorys;
import com.cadastro.entities.PedidosItens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidosPedidosItensRepository extends JpaRepository<PedidosItens, Long> {
    @Query("select pi from pedidos_itens pi where pi.pedidoId.id = :id")
    List<PedidosItens> carregarPedidosItensByPedidoId(@Param("id") Long id);
}
