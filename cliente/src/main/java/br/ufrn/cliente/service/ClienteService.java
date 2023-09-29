package br.ufrn.cliente.service;

import br.ufrn.cliente.dto.ClienteDto;
import br.ufrn.cliente.model.Cliente;
import br.ufrn.cliente.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public void save(Cliente cliente){
        this.repository.save(cliente);
    }

    public List<Cliente> findAll(){
        return this.repository.findAll();
    }

    public void deleteById(String cpf){
        this.repository.deleteById(cpf);
    }

    public ClienteDto getCliente(String cpf) {
        Cliente cliente = repository.findById(cpf).orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(cliente, ClienteDto.class);
    }
}
