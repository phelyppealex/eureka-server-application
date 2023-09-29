package br.ufrn.cliente.controller;

import br.ufrn.cliente.dto.ClienteDto;
import br.ufrn.cliente.service.ClienteService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteControler {

    @Autowired
    private ClienteService service;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> detalhar(@PathVariable @NotNull Long id) {
        ClienteDto dto  = service.getCliente(id);

        System.out.println(dto.getId());
        System.out.println(dto.getCpf());
        System.out.println(dto.getNome());

        return ResponseEntity.ok(dto);
    }
}
