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

    public static ArrayList<Pokemon> loadPokemons() {
        ObjectMapper mapper = new ObjectMapper();
        if (!FILE.exists() || FILE.length() == 0) {
            return new ArrayList<>();
        }
        try {
            return mapper.readValue(FILE, new TypeReference<ArrayList<Pokemon>>() {}
            );

        } catch (IOException e) {
            System.out.println("Kunde inte läsa Pokemon-filen.");
            System.out.println("Filen innehåller ogiltig data: " + e.getMessage());

            return new ArrayList<>();
        }
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
            return new BattleStatistics(0, 0, null, null,null,null);
        }

        try {
            return mapper.readValue(BATTLEFILE, BattleStatistics.class);
        } catch (IOException e){
            System.out.println("Kunde ej läsa statistiken " + e);
            return new BattleStatistics(0, 0, null, null,null,null);
        }

    }


}