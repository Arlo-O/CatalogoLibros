package com.arlo.catalogoLibros.service;

import java.util.List;

public interface IConversorDatos {
    <T> List<T> obtenerDatos(String json, Class<T> clase);
}
