package br.ufrn.estoque.repository;

import br.ufrn.estoque.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    Optional<Produto> findById(String id);
}
