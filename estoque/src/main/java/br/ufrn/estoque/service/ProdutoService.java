package br.ufrn.estoque.service;

import br.ufrn.estoque.model.Produto;
import br.ufrn.estoque.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {
    ProdutoRepository repository;
    ModelMapper mapper;

    public ProdutoService(ProdutoRepository repository, ModelMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public void save(Produto.DtoRequest produtoDto){
        var produto = Produto.DtoRequest.convertToEntity(produtoDto, this.mapper);
        this.repository.save(produto);
    }

    public void deleteById(String codBarras){
        if(this.repository.findById(codBarras).isPresent())
            this.repository.deleteById(codBarras);

        throw new EntityNotFoundException();
    }

    public void deleteAll(){
        this.repository.deleteAll();
    }

    public List<Produto.DtoResponse> findAll(){
        return this.repository.findAll().stream().map(
            produto -> {
                return Produto.DtoResponse.convertToDto(produto, this.mapper);
            }
        ).toList();
    }

    public Produto.DtoResponse findById(String codBarras){
        if(this.repository.findById(codBarras).isPresent())
            return Produto.DtoResponse.convertToDto(this.repository.findById(codBarras).get(), this.mapper);

        throw new EntityNotFoundException();
    }
}
