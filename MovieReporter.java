import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;
/**
 * Main class to test movie, genre, and genreSet classes
 * 
 * @author Maxime Sotsky 3637964
 */

/**
* Creates a report on movies.
*/

public class MovieReporter
{
	/**
	 * method that reads input file containing movies, their ratings, total points and genres
	 * @param file -the file where movie information is stored, to be read
	 * @exception FileNotFoundException   - thrown if errorLog.txt failed to creat or file is not found
	 * 									  - program cannot proceed without these files
	 * @exception InputMismatchException  -thrown when scanner receives unexpected data
	 * 									  -skips lines that deviate from expected format
	 * @exception DuplicateGenreException -thrown when duplicate genre is found
	 * 									  -handled by retrieving existing genre
	 * @exception DuplicateMovieException -thrown when duplicate movie is found
	 * 									  -handled by skiping move and printing to errorLog.txt
	 */
	public static void processMovieFile(File file){

		GenreSet genres = new GenreSet();
		int line = 1;//first line skipped
		String STRgenre;
		double totalPoints;
		int rating;
		String name;

		try{
			PrintWriter out = new PrintWriter("errorLog.txt");
			Scanner in = new Scanner(file);
			in.useDelimiter("~|\\n");

			while(in.hasNext()){
				try{
					in.nextLine();
					line++;

					STRgenre = in.next();
					totalPoints = in.nextDouble();
					rating = in.nextInt();
					name = in.next();
					
					Genre g = new Genre(STRgenre);

					try{
						genres.addGenre(g);
					}catch(DuplicateGenreException e){
						g = genres.get(genres.genreIndex(g));
					}
					try{
						g.addMovie(new Movie(name,totalPoints,rating));
					}catch(DuplicateMovieException e){
						out.println(e.getMessage());
					}
					
				}catch(InputMismatchException e){
					out.println("problem reading line: " + line);
				}
			}
			in.close();
			out.close();
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		showTopGenres(genres);
	}
	/**
	 * method that sorts the list of genres, calculates the average rating and
	 * displays them in desc order
	 * @param genres -the set of genres contructed by processMovieFile
	 */
	public static void showTopGenres(GenreSet genres){
		for(int i = 0; i < genres.length(); i++){
			genres.get(i).calcAvgRating();
		}
		genres.sort();
		for(int i = genres.length()-1; i >= 0; i--){
			System.out.println(genres.get(i).toString());
		}
	}
	/**
	 * main method 
	 * @param args -the arr of arguments for main method
	 */
	public static void main(String[] args)
	{
		File file = new File(args[0]);
		processMovieFile(file);
	}
}