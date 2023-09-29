package br.ufrn.cliente.controller;

import br.ufrn.cliente.dto.ClienteDto;
import br.ufrn.cliente.model.Cliente;
import br.ufrn.cliente.service.ClienteService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteControler {

    @Autowired
    private ClienteService service;

    @PostMapping
    public void salvar(@RequestBody Cliente cliente){
        this.service.save(cliente);
    }

    @GetMapping
    public List<Cliente> listarTudo(){
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public ClienteDto detalhar(@PathVariable @NotNull Long id) {
        return service.getCliente(id);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable Long id){
        this.service.deleteById(id);
    }
}
