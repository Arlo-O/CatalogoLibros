package com.arlo.catalogoLibros.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ConvierteDatos implements IConversorDatos{
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> List<T> obtenerDatos(String json, Class<T> clase) {
        List<T> resultados = new ArrayList<>();
        try {
            JsonNode rootNode = mapper.readTree(json);
            JsonNode rootResults = rootNode.path("results");

            if (rootResults.isArray()) {
                for (JsonNode result : rootResults) {
                    T item = mapper.readValue(result.toString(), clase);
                    resultados.add(item);
                }
            }
        } catch (JsonProcessingException e) {
            System.out.println("Error en obtener datos: " + e.getMessage());
        }
        return resultados;
    }
}
