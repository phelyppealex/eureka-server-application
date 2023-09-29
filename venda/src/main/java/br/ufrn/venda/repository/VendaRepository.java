package br.ufrn.venda.repository;

import br.ufrn.venda.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendaRepository extends JpaRepository<Venda, String> {

    Optional<Venda> findById(String id);

}
