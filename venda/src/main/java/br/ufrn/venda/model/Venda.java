package br.ufrn.venda.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String cpf ;
    String codBarras;
    Integer quantidade;

    public Venda(String cpf, String codBarras, Integer quantidade){

    }

    public Venda(){

    }



}
