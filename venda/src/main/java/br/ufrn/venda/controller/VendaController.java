package br.ufrn.venda.controller;

import br.ufrn.venda.model.Venda;
import br.ufrn.venda.service.VendaService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    private VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    @PostMapping
    public void salvar(@RequestBody Venda venda) throws Exception {
        this.service.save(venda);
    }

    @GetMapping("{id}")
    public Venda getVendaById(@PathVariable String id) {
        return this.service.findById(id);
    }

    @GetMapping
    public List<Venda> listarVendas() {
        return this.service.findAll();
    }
}
