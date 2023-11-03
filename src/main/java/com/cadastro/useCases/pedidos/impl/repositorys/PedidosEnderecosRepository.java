package com.cadastro.useCases.pedidos.impl.repositorys;
import com.cadastro.entities.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosEnderecosRepository extends JpaRepository<Enderecos, Long> {
}
