/**
 * exception class for GenreSet class
 * thrown when duplicate genre is found in the array
 * 
 * @author Maxime Sotsky 3637964
 */
public class DuplicateGenreException extends Exception {
    /**
     * @param message -String representing message when exception is thrown.
     */
    public DuplicateGenreException(String message) {
        super(message);
    }
}
