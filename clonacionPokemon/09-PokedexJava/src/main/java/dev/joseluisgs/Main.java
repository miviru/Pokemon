package dev.joseluisgs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.joseluisgs.models.Pokemon;
import dev.joseluisgs.controllers.PokemonController;

import java.util.Optional;

/**
 * https://json2csharp.com/code-converters/json-to-pojo
 * https://www.jsonschema2pojo.org/
 * https://plugins.jetbrains.com/plugin/8634-robopojogenerator
 * https://marketplace.visualstudio.com/items?itemName=quicktype.quicktype
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Pokedex!");
        var pokeController = PokemonController.getInstance();
//        pokeController.lista10Primeros();
//        pokeController.ultimosCinco();
//        Optional<Pokemon> pokemonEncontrado = pokeController.getPokemonPorNombre("Pikachu"); }ESTE METODO EMPIEZA AQUI
//        if (pokemonEncontrado.isPresent()) {
//            Pokemon pokemon = pokemonEncontrado.get();
//            System.out.println("Pokemon encontrado: " + pokemon);
//        } else {
//            System.out.println("No se encontró el Pokemon con nombre: " );
//        }                                                                                     }ACABA AQUI
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
    }


    private static void printPokemon(Pokemon pokemon) {
        System.out.println(pokemon);
    }

    private static void printPokemonJson(Pokemon pokemon) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(pokemon));
    }
}
