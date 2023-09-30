package br.ufrn.venda.model;

import org.modelmapper.ModelMapper;
import lombok.Data;

@Data
public class Cliente {
    private String cpf;
    private String nome;

    public static class DtoResponse{
        String cpf;
        Integer nome;
        public static Cliente.DtoResponse convertToDto(Cliente c, ModelMapper mapper){
            return mapper.map(c, Cliente.DtoResponse.class);
        }
    }

    @Data
    public static class DtoRequest{
        String cpf;
        Integer nome;
        public static Cliente convertToEntity(Cliente.DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Cliente.class);
        }
    }
}