package br.ufrn.estoque.controller;

import br.ufrn.estoque.model.Produto;
import br.ufrn.estoque.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
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
        List<Produto.DtoResponse> response = this.service.findAll().stream().map(
                elemento -> {
                    System.out.printf(elemento.toString());
                    return Produto.DtoResponse.convertToDto(elemento, this.mapper);
                }
        ).toList();

        return response;
    }

    @GetMapping("{id}")
    public Produto.DtoResponse listarPorId(@PathVariable String id){
        var produto = Produto.DtoResponse.convertToDto(
                this.service.findById(id),
                this.mapper
        );
        return produto;
    }

    @PostMapping
    public void salvar(@RequestBody Produto.DtoRequest produto){
        service.save(produto);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable String id){
        this.service.deleteById(id);
    }

    @DeleteMapping
    public void deletarTudo(){
        this.service.deleteAll();
    }
}
