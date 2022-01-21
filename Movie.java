import java.text.DecimalFormat;
/**
* A class that represents a movie, which has ratings.
*/
public class Movie
{
	/**
	The name of the movie.
	*/
	private String name;		
	
	/**
	The totla value of all ratings.
	*/
	private double totalPoints;	
	
	/**
	The total count of ratings.
	*/
	private int ratings;		

	/**
	* Creates a new Movie object.
	* @param name the name of the movie
	* @param totalPoints the total value of all ratings
	* @param ratings the count of ratings the movie has received
	*/
	public Movie(String name, double totalPoints, int ratings)
	{
		this.name = name;
		this.totalPoints = totalPoints;
		this.ratings = ratings;
	}

	/**
	* Return the movies names
	* @return the movies name
	*/
	public String getName()
	{
		return name;
	}

	/**
	* Calculates the average rating value for the movie
	* @return the movies average rating.
	*/
	public double getAvgRating()
	{
		return totalPoints / ratings;
	}

	/**
	* A String description of this movie.
	* @return A String description of this movie.
	*/
	public String toString()
	{
		DecimalFormat fmt = new DecimalFormat("0.00");
		return name + "; average rating: " + fmt.format(getAvgRating());
	}
}