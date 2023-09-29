package br.ufrn.estoque.model;

import lombok.Data;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "produtos")
public class Produto {
    @Id
    private String codBarras;
    private Integer quantidade;

    @Data
    public static class DtoRequest{
        String codBarras;
        Integer quantidade;

        public static Produto convertToEntity(Produto.DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Produto.class);
        }
    }

    @Data
    public static class DtoResponse{
        String codBarras;
        Integer quantidade;

        public static Produto.DtoResponse convertToDto(Produto produto, ModelMapper mapper){
            return mapper.map(produto, Produto.DtoResponse.class);
        }
    }
}