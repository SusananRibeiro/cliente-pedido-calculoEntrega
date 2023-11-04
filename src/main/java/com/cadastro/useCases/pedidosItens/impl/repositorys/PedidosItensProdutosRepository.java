package com.cadastro.useCases.pedidosItens.impl.repositorys;
import com.cadastro.entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PedidosItensProdutosRepository extends JpaRepository<Produtos, Long> {

    @Query("SELECT pi FROM pedidos_itens pi WHERE pi.produtoId.id = :produtoId")
    public List<Produtos> carregarProdutosByPedidoItensId(@Param("produtoId") Long produtoId);

}
