package br.ufrn.estoque.service;

import br.ufrn.estoque.model.Produto;
import br.ufrn.estoque.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
        if(encontrou(codBarras))
            this.repository.deleteById(codBarras);
        else
            throw new EntityNotFoundException();
    }

    public void deleteAll(){
        this.repository.deleteAll();
    }

    public List<Produto> findAll(){
        return this.repository.findAll();
    }

    public Produto findById(String codBarras){
        var produto = this.repository.findById(codBarras);
        if(produto.isPresent())
            return produto.get();
        throw new EntityNotFoundException();
    }

    public boolean encontrou(String codBarras){
        Optional<Produto> produto = Optional.ofNullable(findById(codBarras));
        return produto.isPresent();
    }

    /*public void update(Produto produto){
        if (encontrou(produto.getId())){

        }
    }*/
}
