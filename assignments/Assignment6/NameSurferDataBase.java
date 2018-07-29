/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.util.*;
import java.io.*;

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	HashMap<String, String> nameMap = new HashMap<String, String>();
	
	public NameSurferDataBase(String filename) {
		// You fill this in //
		try	{
			Scanner s = new Scanner(new File(filename));
			while(s.hasNextLine())	{
				String line = s.nextLine();
				int index = 0;
				for (int i = 0;i <line.length();i ++)
					if (Character.isWhitespace(line.charAt(i)))	{
						index = i;
						break;
					}
				String name = line.substring(0, index);
				nameMap.put(name, line);	
			}
			s.close();
		}	catch (IOException e)	{
				throw new RuntimeException(e);
				
		}
		
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		if (nameMap.get(name) != null)	{
			NameSurferEntry nse = new NameSurferEntry(nameMap.get(name));
			return nse;
		}
		else
			return null;		
	}
}

