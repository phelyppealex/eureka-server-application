package br.ufrn.estoque.controller;

import br.ufrn.estoque.model.Produto;
import br.ufrn.estoque.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class ProdutoController {
    ProdutoService service;
    ModelMapper mapper;

    public ProdutoController(ProdutoService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<Produto.DtoResponse> listar(){
        return this.service.findAll();
    }

    @GetMapping("{codBarras}")
    public Produto.DtoResponse listarPorId(@PathVariable String codBarras){
        return this.service.findById(codBarras);
    }

    @PostMapping
    public void salvar(@RequestBody Produto.DtoRequest produto){
        service.save(produto);
    }

    @DeleteMapping("{codBarras}")
    public void deletar(@PathVariable String codBarras){
        this.service.deleteById(codBarras);
    }

    @DeleteMapping
    public void deletarTudo(){
        this.service.deleteAll();
    }
}
