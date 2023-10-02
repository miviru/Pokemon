package org.example.Repository;

import org.example.models.PokemonBD;

import java.sql.SQLException;
import java.util.List;

public interface PokemonRepositoy extends CrudRepository<PokemonBD, String>{
    List<PokemonBD> findByNombre(String nombre) throws SQLException;
}
