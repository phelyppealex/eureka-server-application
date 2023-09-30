package br.ufrn.venda.service;

import br.ufrn.venda.model.RequestResponse;
import br.ufrn.venda.model.Venda;
import br.ufrn.venda.repository.VendaRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class VendaService {

    VendaRepository repository;
    private final WebClient.Builder webClientBuilder;

    public VendaService(VendaRepository repository, WebClient.Builder webClientBuilder) {
        this.repository = repository;
        this.webClientBuilder = webClientBuilder;
    }

    public Venda save(Venda venda) throws Exception {
        RequestResponse clienteExiste = new RequestResponse();
        RequestResponse estoqueExiste = new RequestResponse();

        webClientBuilder.build()
                .get()
                .uri("http://CLIENTE/cliente")
                .retrieve()
                .onStatus(
                        HttpStatusCode::is2xxSuccessful,
                        status -> {
                            clienteExiste.setExiste(true);
                            return Mono.empty();
                        }
                )
                .bodyToMono(String.class)
                .block();

        webClientBuilder.build()
                .get()
                .uri("http://ESTOQUE/estoque")
                .retrieve()
                .onStatus(
                        HttpStatusCode::is2xxSuccessful,
                        status -> {
                            estoqueExiste.setExiste(true);
                            return Mono.empty();
                        }
                )
                .bodyToMono(String.class)
                .block();

        if(clienteExiste.getExiste() && estoqueExiste.getExiste()){
            return this.repository.save(venda);
        }else{
            throw new Exception("Um dos parametros passados esta incorreto!!!");
        }
    }
}