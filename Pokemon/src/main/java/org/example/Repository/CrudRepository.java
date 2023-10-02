package org.example.Repository;

import org.example.models.PokemonBD;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository <T,String>{


    // Buscar por ID
    List<PokemonBD> findByNombre(String id) throws SQLException;

    // Buscar todos
    List<T> findAll() throws SQLException;

    void CargarBase(List<PokemonBD> poke)throws SQLException;
}
