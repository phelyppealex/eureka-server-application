package br.ufrn.cliente.controller;

import br.ufrn.cliente.model.Cliente;
import br.ufrn.cliente.service.ClienteService;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteControler {

    ClienteService service;
    ModelMapper mapper;

    public ClienteControler(ClienteService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public void salvar(@RequestBody Cliente cliente){
        var c = this.mapper.map(cliente, Cliente.DtoRequest.class);
        this.service.save(c);
    }

    @GetMapping
    public List<Cliente> listarTudo(){
        return this.service.findAll();
    }

    @GetMapping("{cpf}")
    public Cliente listarPorId(@PathVariable String cpf) {
        return this.service.findById(cpf);
    }

    @DeleteMapping("{cpf}")
    public void deletar(@PathVariable String id){
        this.service.deleteById(id);
    }
}
