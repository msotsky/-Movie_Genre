/**
* Represents a collection of movie genres.
*/
public class GenreSet
{
	/**
	A list of genres.
	*/
	private Genre[] genres;	
	
	/**
	The number of genres currently in the set.
	*/
	private int length;		

	/**
	* Creates a new GenreSet.
	*/
	public GenreSet()
	{
		genres = new Genre[10];
		length = 0;
	}

	/**
	* Returns the number of genres currently in the set.
	* @return the number of genres currently in the set
	*/
	public int length()
	{
		return length;
	}

	/**
	* Returns the genre at the given idex.
	* @param i the index of the genre to be returned.
	* @return the genre at the given index.
	*/
	public Genre get(int i)
	{
		return genres[i];
	}

	/**
	* Attempts to add a genre to the set if the genre is not already in the set.
	* Genres are compared based on their names, throws an exception if an object
	* with the same name is already in the set.
	* @param g A genre with a given name.
	* @throws DuplicateGenreException Throws an exception if a Genre with the name is already in the set.
	*/
	public void addGenre(Genre g) throws DuplicateGenreException
	{
		//if the genre is not in the set
		if (genreIndex(g) == -1)
		{
			if (length >= genres.length)
				addCapacity();
			
			genres[length] = g;
			length++;
		
		}
		else
		{
			throw new DuplicateGenreException("Already contains"+
					" the genre: "+g.getName());

		}
	}

	/**
	* Returns the index of the given Genre object.
	* @param g the index of the genre
	* @return the index of the given genre
	*/
	public int genreIndex(Genre g)
	{
		int index = -1;

		for (int i = 0; i < length; i++)
		{
			if (genres[i].getName().equals(g.getName()))
			{
				index = i;
				break;
			}
		}

		return index;
	}

	/**
	* Sorts the set of genres based on each genre's avg. rating.
	*/
	public void sort()
	{
      int min;
      Genre temp;

      for (int index = 0; index < length-1; index++)
      {
         min = index;
         for (int scan = index+1; scan < length; scan++)
            if (genres[scan].compareTo(genres[min]) < 0)
               min = scan;

         // Swap the values
         temp = genres[min];
         genres[min] = genres[index];
         genres[index] = temp;
      }
	}

	/**
	* Adds capacity to store more genres in the set.
	*/
	private void addCapacity()
	{
		Genre[] newArray = new Genre[genres.length * 2];

		for (int i = 0; i < genres.length; i++)
		{
			newArray[i] = genres[i];
		}

		genres = newArray;
	}

}