package com.cadastro.useCases.clientes;
import com.cadastro.entities.Clientes;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.clientes.domanis.ClientesRequestDom;
import com.cadastro.useCases.clientes.domanis.ClientesResponseDom;
import java.util.List;

public interface ClientesBusiness {
    public List<ClientesResponseDom> carregarClientes();
    ClientesResponseDom criarCliente(ClientesRequestDom clientesRequestDom) throws Exception;
    ClientesResponseDom atualizarCliente(Long id, ClientesRequestDom clientesRequestDom) throws SenacException;
    void deletarCliente(Long id);
    Clientes carregarClienteEntidade(Long id);
    ClientesResponseDom carregarClienteById(Long id) throws SenacException;
}
