package com.cadastro.useCases.pedidos.impl.repositorys;

import com.cadastro.entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PedidosProdutosRepository extends JpaRepository<Produtos, Long> {
    @Query("select p from produtos p")
    public List<Produtos> carregarProdutoByPedidosId(@Param("id") Long id);
}
