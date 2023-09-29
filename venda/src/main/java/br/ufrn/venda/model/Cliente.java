package br.ufrn.venda.model;

import lombok.Data;

@Data
public class Cliente {
    private Long id;
    private String cpf;
    private String nome;


}