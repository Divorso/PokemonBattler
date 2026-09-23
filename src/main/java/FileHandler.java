import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import com.fasterxml.jackson.core.type.TypeReference;


public class FileHandler {

    public static ArrayList<Pokemon> loadPokemons() throws IOException {

        String json = readFile();

        ObjectMapper mapper = new ObjectMapper();

        ArrayList<Pokemon> pokemons = mapper.readValue(
                json,
                new TypeReference<ArrayList<Pokemon>>() {}
        );

        return pokemons;
    }

    public static String readFile() throws IOException {

        InputStream input = FileHandler.class
                .getResourceAsStream("/Pokemons.json");
        String json = new String(
                input.readAllBytes(),
                StandardCharsets.UTF_8
        );
        return json;
    }
}
