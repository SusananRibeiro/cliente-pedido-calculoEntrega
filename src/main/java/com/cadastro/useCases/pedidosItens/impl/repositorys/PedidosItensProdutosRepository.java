package com.cadastro.useCases.pedidosItens.impl.repositorys;
import com.cadastro.entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosItensProdutosRepository extends JpaRepository<Produtos, Long> {
}
