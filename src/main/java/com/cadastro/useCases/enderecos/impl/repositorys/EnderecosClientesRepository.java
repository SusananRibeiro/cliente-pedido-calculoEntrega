package com.cadastro.useCases.enderecos.impl.repositorys;
import com.cadastro.entitys.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecosClientesRepository extends JpaRepository<Clientes, Long> {
}
