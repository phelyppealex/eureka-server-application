package br.ufrn.venda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class VendaApplication {
    public static void main(String[] args) {
        SpringApplication.run(VendaApplication.class, args);
    }
}