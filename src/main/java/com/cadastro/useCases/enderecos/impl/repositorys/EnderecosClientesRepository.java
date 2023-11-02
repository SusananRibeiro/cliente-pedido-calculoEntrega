package com.cadastro.useCases.enderecos.impl.repositorys;
import com.cadastro.entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecosClientesRepository extends JpaRepository<Clientes, Long> {
}
