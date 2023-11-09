package com.cadastro.useCases.pedidosItens.impl.repositorys;
import com.cadastro.entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PedidosItensProdutosRepository extends JpaRepository<Produtos, Long> {

    @Query("SELECT p.nome FROM pedidos_itens pi2 \n" +
            "JOIN produtos p ON p.id = pi2.produtoId.id\n" +
            "WHERE pi2.produtoId = :id")
    public List<Produtos> carregarProdutoByPedidosItensId(@Param("id") Long id);


}
