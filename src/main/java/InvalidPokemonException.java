public class InvalidPokemonException extends RuntimeException {

    public InvalidPokemonException() {
        super("Pokemon finns inte!");
    }
}