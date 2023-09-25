package dev.joseluisgs.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dev.joseluisgs.Repositorio.Metodos;
import dev.joseluisgs.models.Pokedex;
import dev.joseluisgs.models.Pokemon;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PokemonController {
    private static PokemonController instance;
    private Pokedex pokedex;

    private PokemonController() {
        loadPokedex();
    }

    public static PokemonController getInstance() {
        if (instance == null) {
            instance = new PokemonController();
        }
        return instance;
    }

    private void loadPokedex() {
        Path currentRelativePath = Paths.get("");
        String ruta = currentRelativePath.toAbsolutePath().toString();
        String dir = ruta + File.separator + "data";
        String paisesFile = dir + File.separator + "pokemon.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Actualizar a try-with-resources
        try (Reader reader = Files.newBufferedReader(Paths.get(paisesFile))) {
            this.pokedex = gson.fromJson(reader, new TypeToken<Pokedex>() {
            }.getType());
            System.out.println("Pokedex loaded! There are: " + pokedex.pokemon.size());
        } catch (Exception e) {
            System.out.println("Error loading Pokedex!");
            System.out.println("Error: " + e.getMessage());
        }
    }


    public Pokemon getPokemon(int index) {
        return pokedex.pokemon.get(index);
    }

    public void lista10Primeros() {
        pokedex.pokemon.stream().limit(10).forEach(System.out::println);
    }

    public void ultimosCinco() {
        pokedex.pokemon.stream().skip(pokedex.pokemon.size() - 5).forEach(System.out::println);
    }

    public Optional<Pokemon> getPokemonPorNombre(String nombre) {//FALTA HACER EL FILTRO PARA QUE SI NO EXISTE MUESTRE ALGO

         Optional<Pokemon> a=pokedex.pokemon.stream().filter(c -> c.getName().toUpperCase().equalsIgnoreCase(nombre.toUpperCase())).findFirst();
            return a;
    }

    //    public void evolucionPokemon(String nombre){
//        pokedex.pokemon.stream().
//
//    }
    public void pokemonTipo(String tipo) {//FALTA EL FILTRO PARA QUE SI NO EXISTE MUESTRE ALGO APARTE DE QUE EL TIPO TIENE QUE ESTAR EXACTAMENTE IGUAL A COMO ESTA EN EL JSON SINO NO FUNCIONA

            pokedex.pokemon.stream().filter(c -> c.getType().contains(tipo)).forEach(System.out::println);

    }
    public void numeroPokemonsConUnaDebilidad(){
        var res = pokedex.pokemon.stream().filter(c->c.getWeaknesses().size()==1).count();
        System.out.println("El total de los pokemons que tienen una sola debilidad es :" +res);
    }
    public void pokemonsConDebilidadAUnTipo(String debilidad){
        //LISTA CON LOS DE DEBILIDAD AGUA
        List<Pokemon> debilesA =pokedex.pokemon.stream().filter(c->c.getWeaknesses().contains(debilidad)).collect(Collectors.toList());
        debilesA.forEach(System.out::println);
        System.out.println("El total de los Pokemons con esa debilidad al "+debilidad +" son :" +debilesA.size());
    }

    public void pokemonConMasDebilidades(){//NI PREGUNTAR COMO HA SALIDO PERO HA SALIDO
        System.out.println(pokedex.pokemon.stream().max(Comparator.comparing(pokemon -> pokemon.getWeaknesses().size())));
    }
    public void pokemonConMenosEvoluciones(){//NO ENTIENDO
        System.out.println(pokedex.pokemon.stream().filter(c -> c.getNext_evolution().size() == 1).findFirst());
    }
    public void pokemonMasPesado(){
        System.out.println(pokedex.pokemon.stream().max(Comparator.comparingDouble(value -> value.getWeight())));
    }
    public void pokemonMasAlto(){
        System.out.println(pokedex.pokemon.stream().max(Comparator.comparingDouble(value -> value.getHeight())));
    }
    public void pokemonNombreMasLargo(){//PROBLEMAS CON .LENGTH
        System.out.println(pokedex.pokemon.stream().max(Comparator.comparing(pokemon -> pokemon.getName().length())));
    }
    public void mediaEvolucionPokemons(){//ESTA MAL
        var a = pokedex.pokemon.stream().collect(Collectors.averagingLong(value -> value.next_evolution.size()));
        System.out.println("La media de evolucion de los pokemons es : "+a);
    }
    public void mediaPesoPokemons(){//NECESITA REDONDEAR A DOS DECIMALES
        var a = pokedex.pokemon.stream().collect(Collectors.averagingDouble(value -> value.getWeight()));
        System.out.println("La media de peso de los pokemons es : " +a);
    }
    public void mediaAlturaPokemons(){//NECESITA REDONDEAR A DOS DECIMALES
        var a = pokedex.pokemon.stream().collect(Collectors.averagingDouble(value -> value.getHeight()));
        System.out.println("La media de altura de los pokemons es : " +a);
    }
    public void mediaDebilidadesPokemons(){//Falta Redondear
        var a = pokedex.pokemon.stream().collect(Collectors.averagingDouble(value -> value.getWeaknesses().size()));
        System.out.println("La media de debilidades de los pokemons es : " +a);
    }
    public void pokemonsAgrupadosPorTipo(){//NO DEBERIA SER OBJECT PERO FUNCIONA AUNQUE LOS MUESSTRA EN UNA SOLA LINEA
        Map<List<String>, List<Pokemon>> agrupados=pokedex.pokemon.stream().collect(Collectors.groupingBy(Pokemon::getType));


        for (Map.Entry<List<String>,List<Pokemon>> pokemon:agrupados.entrySet()) {
            List<String> tipo = pokemon.getKey();
            List<Pokemon> ad=pokemon.getValue();
            System.out.println(tipo+"--------------");
            for (int i = 0; i < ad.size(); i++) {
                System.out.println(ad.get(i));
            }
        }
    }
    public void pokemonsAgrupadosPorDebilidad(){
        Map<List<String>, List<Pokemon>> agrupados=pokedex.pokemon.stream().collect(Collectors.groupingBy(Pokemon::getWeaknesses));

        for (Map.Entry<List<String>,List<Pokemon>> pokemon:agrupados.entrySet()) {
            List<String> tipo = pokemon.getKey();
            List<Pokemon> ad=pokemon.getValue();
            System.out.println(tipo+"--------------");
            for (int i = 0; i < ad.size(); i++) {
                System.out.println(ad.get(i));
            }
        }
    }
    public void debilidadMasComun(){//SUANDO "PREDICATE"O ALGO SIMILAR QUE HABIA EN LOS APUNTES DE GIT
        var agrupados=pokedex.pokemon.stream().collect(Collectors.groupingBy(Pokemon::getWeaknesses));


    }
}

