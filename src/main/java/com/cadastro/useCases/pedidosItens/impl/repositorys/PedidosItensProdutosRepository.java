package com.cadastro.useCases.pedidosItens.impl.repositorys;
import com.cadastro.entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PedidosItensProdutosRepository extends JpaRepository<Produtos, Long> {
//    select p.nome from pedidos_itens pi2
//    join produtos p on p.id = pi2.produto_id
//    where pi2.produto_id = 1;
    @Query("select p from produtos p")
    public List<Produtos> carregarProdutoByPedidosItensId(@Param("id") Long id);

//    @Query("SELECT c FROM cliente c WHERE c.emailEnviado = false")
//    public List<Cliente> carregarClienteNaoEnviado();

//    @Query("select a from enderecos a where a.cliente.id = :id")
//    List<Enderecos> carregarEnderecosByClienteId(@Param("id") Long id);

//    @Query("SELECT e FROM endereco e WHERE e.clienteId.id = :clienteId")
//    public List<Endereco> carregarEnderecoByClienteId(@Param("clienteId") Long clienteId);

}
