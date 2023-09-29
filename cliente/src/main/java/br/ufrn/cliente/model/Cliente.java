package br.ufrn.cliente.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Cliente {
    @Id
    private String cpf;
    @NotBlank
    private String nome;
}
