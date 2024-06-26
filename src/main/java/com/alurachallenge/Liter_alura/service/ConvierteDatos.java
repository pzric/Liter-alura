package com.alurachallenge.Liter_alura.service;

import com.alurachallenge.Liter_alura.model.DatosAutor;
import com.alurachallenge.Liter_alura.model.DatosLibros;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        T resultado = null;
        try {
            if (clase == DatosLibros.class) {
                JsonNode node = mapper.readTree(json);
                var s = node.get("results").get(0);
                resultado = mapper.treeToValue(s, clase);
            }else if (clase == DatosAutor.class) {
                JsonNode node = mapper.readTree(json);
                var s = node.get("results").get(0).get("authors").get(0);
                resultado = mapper.treeToValue(s, clase);
            }else {
                resultado = mapper.readValue(json, clase);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
}

