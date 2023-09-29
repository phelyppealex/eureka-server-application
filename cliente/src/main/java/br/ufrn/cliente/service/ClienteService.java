package br.ufrn.cliente.service;

import br.ufrn.cliente.model.Cliente;
import br.ufrn.cliente.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {
    ClienteRepository repository;
    ModelMapper mapper;

    public ClienteService(ClienteRepository repository, ModelMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public void save(Cliente.DtoRequest clienteDto){
        var cliente = Cliente.DtoRequest.convertToEntity(clienteDto, this.mapper);
        this.repository.save(cliente);
    }

    public List<Cliente> findAll(){
        return this.repository.findAll();
    }

    public void deleteById(String id){
        this.repository.deleteById(id);
    }

    public Cliente findById(String cpf) {
        if(this.repository.findById(cpf).isPresent())
            return this.repository.findById(cpf).get();

        throw new EntityNotFoundException();
    }
}
