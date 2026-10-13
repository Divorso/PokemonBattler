import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class FileHandler {

    private static final File FILE =
            new File("src/main/resources/Pokemons.json");
    private static final File BATTLEFILE =
            new File("src/main/resources/BattleStatistic.json");

    public static ArrayList<Pokemon> loadPokemons() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        if (!FILE.exists() || FILE.length() == 0) {
            return new ArrayList<>();
        }

        ArrayList<Pokemon> pokemons = mapper.readValue(
                FILE,
                new TypeReference<ArrayList<Pokemon>>() {}
        );

        return pokemons;
    }

    public static void savePokemons(ArrayList<Pokemon> pokemonArray) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(FILE, pokemonArray);
    }

    public static void saveBattleStatistics(BattleStatistics statistics){
        ObjectMapper mapper = new ObjectMapper();

        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(BATTLEFILE, statistics);
            System.out.println("statistiken är nu sparad!");

        } catch (IOException e){
            System.out.println("kunde ej spara" + e);
        }
    }

    public static BattleStatistics loadBattleStatistics(){
        ObjectMapper mapper = new ObjectMapper();
        if(!BATTLEFILE.exists() || BATTLEFILE.length() == 0){
            return new BattleStatistics(0,0);
        }

        try {
            return mapper.readValue(BATTLEFILE, BattleStatistics.class);
        } catch (IOException e){
            System.out.println("Kunde ej läsa statistiken " + e);
            return new BattleStatistics(0,0);
        }

    }
}