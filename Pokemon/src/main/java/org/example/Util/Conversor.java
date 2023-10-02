package org.example.Util;

import lombok.var;
import org.example.controllers.PokemonController;
import org.example.models.Pokemon;
import org.example.models.PokemonBD;
import org.example.models.PokemonCorto;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Conversor {
    private String  nombreDelFichero="pokemonCsv.csv";
    List<PokemonCorto>pasarDatos(){
        var pokeController = PokemonController.getInstance();
        List<PokemonCorto> listaCorta=new ArrayList<>();
        List<Pokemon> listaCompleta=pokeController.globalPokemon();

        for (Pokemon pokemon:listaCompleta) {
            PokemonCorto poke=new PokemonCorto();
            poke.setId(pokemon.getId());
            poke.setNum(pokemon.getNum());
            poke.setName(pokemon.getName());
            poke.setHeight(pokemon.getHeight());
            poke.setWeight(pokemon.getWeight());
            listaCorta.add(poke);
        }
        return listaCorta;
    }
    void CrearCsv(){
        List<PokemonCorto> poke= pasarDatos();


        Path currentRelativePath = Paths.get("");
        String ruta = currentRelativePath.toAbsolutePath().toString();
        String dir = ruta + File.separator + "data";
        String paisesFile = dir + File.separator + nombreDelFichero;

        try {
            BufferedWriter escribir = new BufferedWriter(new FileWriter(paisesFile,false));
            for (PokemonCorto pokemon :poke) {
                escribir.write(pokemon.getId()+";"+pokemon.getNum()+";"+pokemon.getName()+";"+pokemon.getWeight()+" Kg ;"+pokemon.getWeight()+" Kg");
                escribir.newLine();
            }
            escribir.flush();
            escribir.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

   public List<PokemonBD> leerFichero(){
        List<PokemonBD> poke=new ArrayList<>();
        Path currentRelativePath = Paths.get("");
        String ruta = currentRelativePath.toAbsolutePath().toString();
        String dir = ruta + File.separator + "data";
        String paisesFile = dir + File.separator + nombreDelFichero;
        try {
            BufferedReader lector= new BufferedReader( new FileReader(paisesFile));

            while (lector.ready()){
                String linea= lector.readLine();
                String vs[]=linea.split(";");
                var pokemon = new PokemonBD();
                pokemon.setId(Integer.parseInt(vs[0]));
                pokemon.setNum(vs[1]);
                pokemon.setName(vs[2]);
                pokemon.setHeight(vs[3]);
                pokemon.setWeight(vs[4]);
                poke.add(pokemon);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return poke;
    }
}
