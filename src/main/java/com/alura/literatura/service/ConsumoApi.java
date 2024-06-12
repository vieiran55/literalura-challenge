package com.alura.literatura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    public String obterDados(String endereco) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String json = response.body();
                //System.out.println(json);
            } else {
                System.err.println("Erro na requisição: " + response.statusCode());
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro de IO ao fazer requisição", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Requisição interrompida", e);
        }

        String json = response.body();
        return json;
    }
}