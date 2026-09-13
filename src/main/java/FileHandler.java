import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileHandler {

    public static String readFile() throws IOException {

        InputStream input = FileHandler.class
                .getResourceAsStream("/Pokemons.json");
        System.out.println(input);

        String json = new String(
                input.readAllBytes(),
                StandardCharsets.UTF_8
        );
        System.out.println(json);

        return json;
    }
}