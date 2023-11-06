package com.cadastro.useCases.clientes.impl;
import com.cadastro.entities.Clientes;
import com.cadastro.entities.Enderecos;
import com.cadastro.frameWork.annotions.Business;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.frameWork.utils.StringUtil;
import com.cadastro.useCases.clientes.ClientesBusiness;
import com.cadastro.useCases.clientes.domanis.ClientesRequestDom;
import com.cadastro.useCases.clientes.domanis.ClientesResponseDom;
import com.cadastro.useCases.clientes.impl.mappers.ClientesMapper;
import com.cadastro.useCases.clientes.impl.repositorys.ClientesEnderecosRespository;
import com.cadastro.useCases.clientes.impl.repositorys.ClientesRespository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
public class ClientesBusinessImpl implements ClientesBusiness {

    @Autowired
    private ClientesRespository clientesRepository;

    @Autowired
    private ClientesEnderecosRespository clientesEnderecosRespository;

    @Override
    public List<ClientesResponseDom> carregarClientes() {
        List<Clientes> clientesList = clientesRepository.findAll();

        List<ClientesResponseDom> out = clientesList
                .stream()
                .map(ClientesMapper :: clientesToClientesResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public ClientesResponseDom criarCliente(ClientesRequestDom clientesRequestDom) throws SenacException {
        List<String> messages = this.validacaoManutencaoCliente(clientesRequestDom);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Clientes clientes = ClientesMapper.clientesRequestDomToClientes(clientesRequestDom);

        Clientes resultClientes = clientesRepository.save(clientes);

        ClientesResponseDom out = ClientesMapper.clientesToClientesResponseDom(resultClientes);

        return out;
    }

    @Override
    public ClientesResponseDom atualizarCliente(Long id, ClientesRequestDom clientesRequestDom) throws SenacException {
        List<String> messages = this.validacaoManutencaoCliente(clientesRequestDom);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Clientes> clientes = clientesRepository.findById(id).map(record -> {
           record.setSobreNome(clientesRequestDom.getSobrenome());
           record.setNome(clientesRequestDom.getNome());
           record.setEmail(clientesRequestDom.getEmail());
           record.setTelefone(clientesRequestDom.getTelefone());
           record.setDataNascimento(clientesRequestDom.getDataNascimento());
           return clientesRepository.save(record);
        });

        if(!clientes.isPresent()){
            throw new SenacException("Cliente informando não existe!");
        }

        ClientesResponseDom out =
                ClientesMapper.clientesToClientesResponseDom(clientes.get());

        return out;
    }

    @Override
    public void deletarCliente(Long id) {
        clientesRepository.deleteById(id);
    }

    @Override
    public Clientes carregarClienteEntidade(Long id) {

        Clientes cliente = clientesRepository.findById(id).get();

        return cliente;
    }

    @Override
    public ClientesResponseDom carregarClienteById(Long id) {
        Clientes cliente = clientesRepository.findById(id).get();

        List<Enderecos> enderecos = clientesEnderecosRespository.carregarEnderecosByClienteId(id);

        ClientesResponseDom out = ClientesMapper.clientesToClientesResponseDom(cliente, enderecos);

        return out;
    }

    private List<String> validacaoManutencaoCliente(ClientesRequestDom cliente){
        List<String> messages = new ArrayList<>();

        if(StringUtil.validarString(cliente.getNome())){
            messages.add("Cliente informado não possui nome!");
        }

        if(StringUtil.validarString(cliente.getSobrenome())){
            messages.add("Cliente informado não possui sobrenome!");
        }

        if(StringUtil.validarString(cliente.getEmail())){
            messages.add("Cliente informado não possui email!");
        }

        return messages;
    }

}
