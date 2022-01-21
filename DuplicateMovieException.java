/**
 * exception class for Genre class
 * thrown when duplicate movie is found in the array
 * 
 * @author Maxime Sotsky 3637964
 */
public class DuplicateMovieException extends Exception {
    /**
     * 
     * @param message - String representing message when exception is thrown.
     */
    public DuplicateMovieException(String message){
        super(message);
    }
}
