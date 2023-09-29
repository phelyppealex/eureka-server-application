package br.ufrn.venda.controller;

import br.ufrn.venda.model.Venda;
import br.ufrn.venda.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    VendaService service;
    @Autowired

    public VendaController(VendaService service) {
        this.service = service;
    }

    @PostMapping("/criar")
    public Venda criarVenda(@RequestBody Venda request) {
        String codBarras = request.getCodBarras();
        String cpf = request.getCpf();
        int quantidade = request.getQuantidade();

        return service.criarVenda(codBarras, cpf, quantidade);
    }

    @GetMapping("/{id}")
    public Venda getVendaById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Venda> listarVendas() {
        return service.findAll();
    }



}
