package org.example.Repository;

import lombok.var;
import org.example.Service.DatabaseManager;
import org.example.models.PokemonBD;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonRepositoryImpl implements  PokemonRepositoy{
    private static PokemonRepositoryImpl instance;
    // Base de datos
    private final DatabaseManager db;

    private PokemonRepositoryImpl(DatabaseManager db) {
        this.db = db;
    }
    public static PokemonRepositoryImpl getInstance(DatabaseManager db) {
        if (instance == null) {
            instance = new PokemonRepositoryImpl(db);
        }
        return instance;
    }
    public List<PokemonBD> findByNombre(String nombre) throws SQLException {
        // Vamos a usar Like para buscar por nombre
        var lista = new ArrayList<PokemonBD>();
        String query = "SELECT * FROM Pokemon WHERE name LIKE ?";
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query)
        ) {
            stmt.setString(1, "%" + nombre + "%");
            var rs = stmt.executeQuery();
            while (rs.next()) {
                // Creamos un objeto PokemonCorto sin Argumentos de constructor
                var pokemon = new PokemonBD();
                pokemon.setId(rs.getInt("id"));
                pokemon.setNum(rs.getString("num"));
                pokemon.setName(rs.getString("name"));
                pokemon.setHeight(rs.getString("height"));
                pokemon.setWeight(rs.getString("weight"));

                // Lo añadimos a la lista
                lista.add(pokemon);
            }
            rs.close();

        }
        return lista;
    }

    public void CargarBase(List<PokemonBD> lista) throws SQLException {

        String query = "INSERT INTO Pokemon (id, num, name, height, weight) VALUES (?, ?, ?, ?, ?)";
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query)
        ) {
            for (PokemonBD poke:lista) {
                stmt.setInt(1, poke.getId());
                stmt.setString(2,poke.getNum());
                stmt.setString(3,poke.getName());
                stmt.setString(4,poke.getHeight());
                stmt.setString(5,poke.getWeight());
                var res = stmt.executeUpdate();

            }
            }
        }

    @Override
    public List<PokemonBD> findAll() throws SQLException {
        String query = "SELECT * FROM Pokemon";
        var lista = new ArrayList<PokemonBD>();
        try (var connection = db.getConnection();
             var stmt = connection.prepareStatement(query)
        ) {
            var rs = stmt.executeQuery();
            while (rs.next()) {
                // Creamos un objeto PokemonCorto sin Argumentos de constructor
                var pokemon = new PokemonBD();
                pokemon.setId(rs.getInt("id"));
                pokemon.setNum(rs.getString("num"));
                pokemon.setName(rs.getString("name"));
                pokemon.setHeight(rs.getString("height"));
                pokemon.setWeight(rs.getString("weight"));

                // Lo añadimos a la lista
                lista.add(pokemon);
            }
            rs.close();

        }
        return lista;
    }
    }