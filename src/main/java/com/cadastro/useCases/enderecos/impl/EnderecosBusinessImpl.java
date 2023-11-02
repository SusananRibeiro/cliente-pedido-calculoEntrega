package com.cadastro.useCases.enderecos.impl;
import com.cadastro.entities.Clientes;
import com.cadastro.entities.Enderecos;
import com.cadastro.frameWork.annotions.Business;
import com.cadastro.frameWork.utils.SenacException;
import com.cadastro.useCases.enderecos.EnderecosBusiness;
import com.cadastro.useCases.enderecos.domanis.EnderecosRequestDom;
import com.cadastro.useCases.enderecos.domanis.EnderecosResponseDom;
import com.cadastro.useCases.enderecos.impl.mappers.EnderecosMapper;
import com.cadastro.useCases.enderecos.impl.repositorys.EnderecosClientesRepository;
import com.cadastro.useCases.enderecos.impl.repositorys.EnderecosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
public class EnderecosBusinessImpl implements EnderecosBusiness {

    @Autowired
    private EnderecosRepository enderecosRepository;

    @Autowired
    private EnderecosClientesRepository enderecosClientesRepository;

    @Override
    public List<EnderecosResponseDom> carregarEnderecos() {
        List<Enderecos> enderecosList = enderecosRepository.findAll();

        List<EnderecosResponseDom> out = enderecosList.stream()
                .map(EnderecosMapper::enderecosToEnderecosResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public EnderecosResponseDom criarEndereco(EnderecosRequestDom endereco) throws SenacException {
        List<String> messages = this.validacaoManutencaoEndereco(endereco);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Clientes> cliente = enderecosClientesRepository.findById(endereco.getClienteId());
        if(!cliente.isPresent()){
            throw new SenacException("Cliente não encontrado");
        }

        Enderecos enderecoRetorno = enderecosRepository.save(EnderecosMapper.enderecosResquestDomToEnderecos(endereco, cliente.get()));

        return EnderecosMapper.enderecosToEnderecosResponseDom(enderecoRetorno);
    }

    @Override
    public EnderecosResponseDom atualizarEndereco(Long id, EnderecosRequestDom endereco) throws SenacException {
        List<String> messages = this.validacaoManutencaoEndereco(endereco);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Clientes> cliente = enderecosClientesRepository.findById(endereco.getClienteId());
        if(!cliente.isPresent()){
            throw new SenacException("Cliente não encontrado");
        }

        Optional<Enderecos> enderecoRetorno = enderecosRepository.findById(id).map(record -> {
            record.setRua(endereco.getRua());
            record.setBairro(endereco.getBairro());
            record.setCidade(endereco.getCidade());
            record.setEstado(endereco.getEstado());
            record.setCliente(cliente.get());
            return enderecosRepository.save(record);
        });

        if(enderecoRetorno.isPresent() == false){
            throw new SenacException("Endereço não encontrado");
        }

        EnderecosResponseDom out = EnderecosMapper.enderecosToEnderecosResponseDom(enderecoRetorno.get());

        return out;
    }

    @Override
    public void deletarEndereco(Long id) {
        enderecosRepository.deleteById(id);
    }

    private List<String> validacaoManutencaoEndereco(EnderecosRequestDom endereco){
        List<String> messages = new ArrayList<>();

        if(endereco.getBairro() == null || endereco.getBairro() == ""){
            messages.add("Bairro não informa!");
        }

        if(endereco.getEstado() == null || endereco.getEstado() == ""){
            messages.add("Estado não informa!");
        }
        if(endereco.getCidade() == null || endereco.getCidade() == ""){
            messages.add("Cidade não informa!");
        }
        if(endereco.getRua() == null || endereco.getRua() == ""){
            messages.add("Rua não informa!");
        }

        if(endereco.getClienteId() == null || endereco.getClienteId() < 1){
            messages.add("ClienteId não informa ou valor invalido!");
        }

        return messages;
    }
}
