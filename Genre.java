import java.util.ArrayList;
import java.text.DecimalFormat;

/**
* A collection of movies in a particular Genre.
* 
*/
public class Genre implements Comparable<Genre>{

	/**
	The name of the Genre.
	*/
	private String name;		
	
	/**
	A list of movies in the genre.
	*/
	private Movie[] movies;		
	
	/**
	The count of movies currently stored in the list.
	*/
	private int length;			

	/**
	* Creates an object representing a genre of movie.
	* @param name the name of the genre
	*/
	public Genre(String name)
	{
		this.name = name;
		this.length = 0;
		this.movies = new Movie[100];
	}

	/**
	* Returns the movie name.
	*
	* @return the name of the genre.
	*/
	public String getName()
	{
		return name;
	}

	/**
	* Returns the number of movies currently stored in the genre.
	* @return the number of movies currently stored in the genre.
	*/
	public int length()
	{
		return length;
	}

	/**
	* Checks to see if the given movie object is already contained in the
	* genre object. Compares based on movie names.
	* @param the movie to look for in the list
	* @return a boolean value: true if the movie is in the list; false otherwise.
	*/
	private boolean containsMovie(Movie movieToCheck)
	{
		for (int i = 0; i < length; i++)
		{
			if (movies[i].getName().equals(movieToCheck.getName()))
			{
				return true;
			}
		}

		return false;
	}

	/**
	* Tries to add the movie to the collection.
	*
	* @param m the movie to add to the collection.
	* @throws DuplicateMovieException an exception if a movie with the same name is already in the collection.
	*/
	public void addMovie(Movie m) throws DuplicateMovieException
	{
		if (containsMovie(m))
		{
			throw new DuplicateMovieException(m.getName()+
				" is already in the genre " + name);
		}

		else
		{
			//do we need more capacity
			if (movies.length <= length)
				addCapacity();

			movies[length] = m;
			length++;
		}
	}

	/**
	* Calculates the average rating of the movies in the genre.
	* @return the average rating of the movies in the genre.
	*/
	public double calcAvgRating()
	{
		double total  = 0.0;

		for (int i = 0; i < length; i++)
		{
			total += movies[i].getAvgRating();
		}

		return total / length;
	}

	/**
	* Returns the highest rated movie.
	* @return the highest rated Movie object in the genre.
	*/
	public Movie getHighestRated()
	{
		Movie highest = null;

		for (int i = 0; i < length; i++)
		{
			Movie m = movies[i];
			if (highest == null || highest.getAvgRating() < m.getAvgRating())
				highest = m;
		}

		return highest;
	}

	/**
	* Compares one genre to another based on its average rating.
	* @param other the other genre to compare against.
	* @return a positive value if this movie is "greater than" the other, negative if not.
	*/
	public int compareTo(Genre other)
	{
		return (int) ((calcAvgRating() - other.calcAvgRating()) * 100);
	}

	/**
	* Adds more capacity to this movie object;
	*/
	private void addCapacity()
	{
		Movie[] newArray = new Movie[movies.length * 2];

		for (int i = 0; i < movies.length; i++)
		{
			newArray[i] = movies[i];
		}

		movies = newArray;
	}

	public String toString()
	{
		DecimalFormat fmt = new DecimalFormat("0.00");

		return name + ", contains: "+ length + " movies, avg. rating: " + 
				fmt.format(calcAvgRating()) + "\n\t" + getHighestRated();
	}
}