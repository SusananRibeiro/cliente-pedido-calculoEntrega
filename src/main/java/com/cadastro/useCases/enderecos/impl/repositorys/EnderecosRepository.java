package com.cadastro.useCases.enderecos.impl.repositorys;
import com.cadastro.entities.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecosRepository extends JpaRepository<Enderecos, Long> {
}
