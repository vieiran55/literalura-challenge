package com.alura.literatura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.alura.literatura.model.Dados;

public class ConverteDados {
    public Dados obterDados(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Dados.class);
    }
}
