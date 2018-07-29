/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */


public class NameSurferEntry implements NameSurferConstants {
	
	public String name;
	public int[] rank = new int[NDECADES];
	

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		// You fill this in //
		//记录line中为空格的指标
		int[] index = new int[NDECADES];
		int cnt = 0;
		for (int i = 0; i <line.length(); i ++)	{
			if (Character.isWhitespace(line.charAt(i)))	{
				cnt++;
				index[cnt-1] = i;
			}
			if (cnt == NDECADES)
				break;
				
		}
		this.name = line.substring(0, index[0]);
		for (int i = 0; i < NDECADES; i ++)
			if (i != NDECADES-1)
				this.rank[i] = Integer.parseInt(line.substring(index[i]+1, index[i+1]));
			else
				this.rank[i] = Integer.parseInt(line.substring(index[i]+1));
		
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		return this.name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation //
		if (decade>=0 && decade<NDECADES)
			return this.rank[decade];
		else
			return -1;
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		String str = "";
		str += this.name + " " + "[";
		for (int i = 0;i < NDECADES; i ++)
			if(i != NDECADES-1)
				str += Integer.toString(rank[i]) + ", ";
			else
				str += Integer.toString(rank[i]);
		return str;
	}
}

