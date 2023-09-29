package br.ufrn.cliente.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Data
@Entity
public class Cliente {
    @Id
    private String cpf;
    @NotBlank
    private String nome;

    @Data
    public static class DtoRequest{
        String cpf;
        String nome;

        public static Cliente convertToEntity(Cliente.DtoRequest response, ModelMapper mapper){
            return mapper.map(response, Cliente.class);
        }
    }

    public static class DtoResponse{
        String cpf;
        String nome;

        public static Cliente.DtoResponse convertToDto(Cliente cliente, ModelMapper mapper){
            return mapper.map(cliente, Cliente.DtoResponse.class);
        }
    }
}
