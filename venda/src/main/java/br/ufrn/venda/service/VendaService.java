package br.ufrn.venda.service;

import br.ufrn.venda.model.Cliente;
import br.ufrn.venda.model.Produto;
import br.ufrn.venda.model.Venda;
import br.ufrn.venda.repository.VendaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private RestTemplate restTemplate;

    VendaRepository repository;

    @Autowired
    public VendaService(RestTemplate restTemplate, VendaRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    public void save(Venda venda){
        String estoqueUrl = "http://localhost:8081/estoque/" + venda.getCodBarras();
        String clienteUrl = "http://localhost:8082/cliente/" + venda.getCpf();

        Optional<Produto> produto = Optional.ofNullable(restTemplate.getForObject(estoqueUrl, Produto.class));
        Optional<Cliente> cliente = Optional.ofNullable(restTemplate.getForObject(clienteUrl, Cliente.class));

        if(produto.isPresent() && cliente.isPresent())
            repository.save(venda);
        throw new EntityNotFoundException();
    }

    public Venda findById(String id){
        Optional<Venda> venda = this.repository.findById(id);
        if(venda.isPresent()){
            return venda.get();
        }
        throw new EntityNotFoundException();
    }

    public List<Venda> findAll(){
        return this.repository.findAll();
    }


}
