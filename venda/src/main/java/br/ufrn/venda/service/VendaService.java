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
    public VendaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Venda criarVenda(String codBarras, String cpf, int quantidade){
        String estoqueUrl = "http://localhost:8761/estoque" + codBarras;
        Produto produto = restTemplate.getForObject(estoqueUrl, Produto.class);

        String clienteUrl = "http://localhost:8761/cliente" + cpf;
        Cliente cliente = restTemplate.getForObject(clienteUrl, Cliente.class);

        Venda venda = new Venda();
        venda.setCodBarras(codBarras);
        venda.setCpf(cpf);
        venda.setQuantidade(quantidade);

        repository.save(venda);

        return venda;
    }

    public void save(Venda venda){
        this.repository.save(venda);
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
