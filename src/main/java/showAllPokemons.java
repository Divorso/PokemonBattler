import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class showAllPokemons {
    public static void readPokemons() throws IOException{
        String json = Files.readString(
                Path.of("/Users/michaelinma/Documents/skola/Jensen/pokedexInlamningUppgift1/pokedex/src/main/resources/Pokemons.json")
        );
        System.out.println(json);
    }
}
