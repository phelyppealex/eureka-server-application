package br.ufrn.venda.model;

import lombok.Data;

@Data
public class Produto {
    String id;
    String codBarras;
    Integer quantidade;
}
