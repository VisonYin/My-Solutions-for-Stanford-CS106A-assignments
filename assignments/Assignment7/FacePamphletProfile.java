/*
 * File: FacePamphletProfile.java
 * ------------------------------
 * When it is finished, this class will define a new variable type
 * that represents a single FacePamphlet profile. Remember to update this comment!
 */

import java.util.*;
import acm.graphics.*;

public class FacePamphletProfile {

	// your code here
	private String name;
	private String status = "";
	private GImage image = null;
	private ArrayList<String> friends = new ArrayList<String>();

	/** 
	 * Constructor
	 * This method takes care of any initialization needed for
	 * the profile.
	 */
	public FacePamphletProfile(String name) {
		// your code here
		this.name = name;
	}

	/** This method returns the name associated with the profile. */ 
	public String getName() {
		// You fill this in.  Currently always returns the empty string.
		return this.name;
	}


	public GImage getImage() {
		// You fill this in.  Currently always returns null.
		return this.image;
	}

	/** This method sets the image associated with the profile. */ 
	public void setImage(GImage image) {
		// You fill this in.
		this.image = image;
	}

	/** 
	 * This method returns the status associated with the profile.
	 * If there is no status associated with the profile, the method
	 * returns the empty string ("").
	 */ 
	public String getStatus() {
		// You fill this in.  Currently always returns the empty string.
		return this.status;
	}

	/** This method sets the status associated with the profile. */ 
	public void setStatus(String status) {
		// You fill this in. 
		this.status = status;
	}

	/** 
	 * This method adds the named friend to this profile's list of 
	 * friends.  It returns true if the friend's name was not already
	 * in the list of friends for this profile (and the name is added 
	 * to the list).  The method returns false if the given friend name
	 * was already in the list of friends for this profile (in which 
	 * case, the given friend name is not added to the list of friends 
	 * a second time.)
	 */
	public boolean addFriend(String friend) {
		// You fill this in.  Currently always returns false.
		if (this.friends.contains(friend)) 
			return false;
		else	{
			this.friends.add(friend);
			return true;
		}
	}

	/** 
	 * This method removes the named friend from this profile's list
	 * of friends.  It returns true if the friend's name was in the 
	 * list of friends for this profile (and the name was removed from
	 * the list).  The method returns false if the given friend name 
	 * was not in the list of friends for this profile (in which case,
	 * the given friend name could not be removed.)
	 */
	public boolean removeFriend(String friend) {
		// You fill this in.  Currently always returns false.
		if (this.friends.contains(friend))	{
			this.friends.remove(friend);
			return true;
		}
		else
			return false;
	}

	/** 
	 * This method returns an ArrayList of friends 
	 * associated with the profile.
	 */ 
	public ArrayList<String> getFriends() {
		// You fill this in.  Currently always returns null.
		return this.friends;
	}

}
