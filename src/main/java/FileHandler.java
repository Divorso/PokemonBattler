import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class FileHandler {

    private static final File FILE =
            new File("src/main/resources/Pokemons.json");


    // Loads Pokemon from JSON
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
}