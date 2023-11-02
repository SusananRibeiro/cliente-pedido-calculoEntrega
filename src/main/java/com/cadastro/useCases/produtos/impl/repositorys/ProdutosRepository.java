package com.cadastro.useCases.produtos.impl.repositorys;
import com.cadastro.entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
}
