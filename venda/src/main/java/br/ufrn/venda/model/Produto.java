package br.ufrn.venda.model;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class Produto {
    String codBarras;
    Integer quantidade;

    public static class DtoResponse{
        String codBarras;
        Integer quantidade;
        public static Cliente.DtoResponse convertToDto(Cliente c, ModelMapper mapper){
            return mapper.map(c, Cliente.DtoResponse.class);
        }
    }

    @Data
    public static class DtoRequest{
        String codBarras;
        Integer quantidade;
        public static Cliente convertToEntity(Cliente.DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Cliente.class);
        }
    }
}
