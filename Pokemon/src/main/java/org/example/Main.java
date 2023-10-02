package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.var;
import org.example.Repository.PokemonRepositoryImpl;
import org.example.Repository.PokemonRepositoy;
import org.example.Service.DatabaseManager;
import org.example.Util.Conversor;
import org.example.controllers.PokemonController;
import org.example.models.Pokemon;
import java.sql.SQLException;


import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

/**
 * https://json2csharp.com/code-converters/json-to-pojo
 * https://www.jsonschema2pojo.org/
 * https://plugins.jetbrains.com/plugin/8634-robopojogenerator
 * https://marketplace.visualstudio.com/items?itemName=quicktype.quicktype
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException,SQLException  {
        System.out.println("Hello Pokedex!");
//        var pokeController = PokemonController.getInstance();

//        pokeController.lista10Primeros();
//        pokeController.ultimosCinco();
//        Optional<Pokemon> pokemonEncontrado = pokeController.getPokemonPorNombre("Pikachu");
//        if (pokemonEncontrado.isPresent()) {
//            Pokemon pokemon = pokemonEncontrado.get();
//            System.out.println("Pokemon encontrado: " + pokemon);
//        } else {
//            System.out.println("No se encontró el Pokemon con nombre: " );
//        }
//        pokeController.pokemonTipo("Water");
//        pokeController.numeroPokemonsConUnaDebilidad();
//        pokeController.pokemonsConDebilidadAUnTipo("Water");
//        pokeController.pokemonConMasDebilidades();
//        pokeController.pokemonConMenosEvoluciones();
//        pokeController.pokemonMasPesado();
//        pokeController.pokemonMasAlto();
//        pokeController.pokemonNombreMasLargo();
//        pokeController.mediaPesoPokemons();
//        pokeController.mediaAlturaPokemons();
//        pokeController.mediaDebilidadesPokemons();
//        pokeController.pokemonsAgrupadosPorTipo();
//        pokeController.pokemonsAgrupadosPorDebilidad();
//        pokeController.mediaEvolucionPokemons();
//            pokeController.debilidadMasComun();
        Conversor c = new Conversor();
        DatabaseManager dbManager = DatabaseManager.getInstance();
        dbManager.executeScript("init.sql", true);

        PokemonRepositoy pokemons = PokemonRepositoryImpl.getInstance(dbManager);
        pokemons.CargarBase(c.leerFichero());

        System.out.println("Todos los Pokémon");
        pokemons.findAll().forEach(System.out::println);

    }


//    private static void printPokemon(Pokemon pokemon) {
//        System.out.println(pokemon);
//    }
//
//    private static void printPokemonJson(Pokemon pokemon) {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        System.out.println(gson.toJson(pokemon));
//    }
}
