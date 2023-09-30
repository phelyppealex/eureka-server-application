package br.ufrn.venda.service;

import br.ufrn.venda.model.Venda;
import br.ufrn.venda.repository.VendaRepository;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class VendaService {

    VendaRepository repository;

    public VendaService(VendaRepository repository) {
        this.repository = repository;
    }

    public void save(Venda venda) throws IOException, InterruptedException {
        HttpClient clientCliente = HttpClient.newHttpClient();
        HttpRequest requestCliente = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:53792/cliente"))
                .build();

        HttpResponse<String> statusRespostaCliente = clientCliente.send(requestCliente, HttpResponse.BodyHandlers.ofString());

        HttpClient clientEstoque = HttpClient.newHttpClient();
        HttpRequest requestEstoque = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:53811/estoque"))
                .build();

        HttpResponse<String> statusRespostaEstoque = clientEstoque.send(requestEstoque, HttpResponse.BodyHandlers.ofString());

        var statusCliente = statusRespostaCliente.toString().split(" ");
        var statusEstoque = statusRespostaEstoque.toString().split(" ");

        if(statusCliente[2].equals("200") && statusEstoque[2].equals("200")){
            this.repository.save(venda);
        }
    }
}