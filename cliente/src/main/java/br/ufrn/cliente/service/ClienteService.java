package br.ufrn.cliente.service;

import br.ufrn.cliente.dto.ClienteDto;
import br.ufrn.cliente.model.Cliente;
import br.ufrn.cliente.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public ClienteDto getCliente(Long id) {
        Cliente cliente = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(cliente, ClienteDto.class);
    }
}
