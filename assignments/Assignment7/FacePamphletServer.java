/*
 * File: FacePamphletServer.java
 * ------------------------------
 * When it is finished, this program will implement a basic
 * social network management server.  Remember to update this comment!
 */

import acm.program.*;
import java.util.*;
import acm.graphics.*;

public class FacePamphletServer extends ConsoleProgram 
					implements SimpleServerListener {
	
	/* The internet port to listen to requests on */
	private static final int PORT = 8000;
	
	/* The server object. All you need to do is start it */
	private SimpleServer server = new SimpleServer(this, PORT);

	/**
	 * Starts the server running so that when a program sends
	 * a request to this computer, the method requestMade is
	 * called.
	 */
	public void run() {
		println("Starting server on port " + PORT);
		server.start();
	}

	/**
	 * When a request is sent to this computer, this method is
	 * called. It must return a String.
	 */
	public String requestMade(Request request) {
		String cmd = request.getCommand();
		println(request.toString());
		
		// your code here. 
		String result = "Error: Unknown command " + cmd + ".";
		
		if (cmd.equals("ping"))
			result = "Hello, internet";
		
		if (cmd.equals("addProfile"))	{
			String param = request.getParam("name");
			if (!isConstained(param))	{
				listOfProfiles.add(new FacePamphletProfile(param));
				result = "success";
			}
			else
				result = "Error: Database already contains a profile with name " + param;		
		}
		
		if (cmd.equals("containsProfile"))	{
			String param = request.getParam("name");
			if (isConstained(param))
				result = "true";
			else
				result = "false";
		}
		
		if (cmd.equals("deleteProfile"))	{
			String param = request.getParam("name");
			if (isConstained(param))	{
				remove(param);
				//将其从所有好友列表移除
				removedFromAll(param);
				result = "success";
			}
			else
				result = "Error: Database does not contain a profile with name " + param;
		}
		
		if (cmd.equals("setStatus"))	{
			String name = request.getParam("name");
			String status = request.getParam("status");
			if (isConstained(name))	{
				listOfProfiles.get(index(name)).setStatus(status);
				result = "success";
				}
			else
				result = "Error: Database does not contain a profile with name " + name;
		}
		
		if (cmd.equals("getStatus"))	{
			String name = request.getParam("name");
			if (isConstained(name))	
				result = listOfProfiles.get(index(name)).getStatus();
			else
				result = "Error: Database does not contain a profile with name " + name;	
		}
		
		if (cmd.equals("setImage"))	{
			String name = request.getParam("name");
			String imageStr = request.getParam("imageString");
			if (isConstained(name))	{
				GImage image = SimpleServer.stringToImage(imageStr);
				listOfProfiles.get(index(name)).setImage(image);
				result = "success";
			}
			else
				result = "Error: Database does not contain a profile with name " + name;
		}
		
		if (cmd.equals("getImage"))	{
			String name = request.getParam("name");
			if (isConstained(name))	{
				GImage image = listOfProfiles.get(index(name)).getImage();		
				String imageString = SimpleServer.imageToString(image);
				result = imageString;
			}
			else
				result = "Error: Database does not contain a profile with name " + name;
		}
		
		if (cmd.equals("addFriend"))	{
			String name1 = request.getParam("name1");
			String name2 = request.getParam("name2");
			if (isConstained(name1) && isConstained(name2))	{
				if (name1.equals(name2))
					result = "Error: " + name1 + " and " + name2 + " is the same person";
				else if (isFriends(name1, name2))
					result = "Error: " + name1 + " and " + name2 + " have already been friends";
				else	{
					listOfProfiles.get(index(name1)).addFriend(name2);
					listOfProfiles.get(index(name2)).addFriend(name1);
					result = "success";
				}
				
			}
			else
				result = "Error: " + "Either " + name1 + " or " + name2 + " does not exit";
		}
		
		if (cmd.equals("getFriends"))	{
			String name = request.getParam("name");
			if (isConstained(name))	{
				String stringOfFriends = (listOfProfiles.get(index(name)).getFriends()).toString();
				result = stringOfFriends;
			}
			else
				result = "Error: Database does not contain a profile with name " + name;
		}
			
		
		println(" => " + result);
		return result;
		
		//return "Error: Unknown command " + cmd + ".";
	}
	
	//判断listOfProfiles中是否存在名字为name的对象
	private boolean isConstained(String name)	{
		boolean result = false;
		for (int i = 0; i < listOfProfiles.size(); i ++)	{
			String nameOfAProfile = listOfProfiles.get(i).getName();
			if (name.equals(nameOfAProfile))	{
				result = true;
				break;
			}	
		}
		return result;
	}
	
	//移除listOfProfiles中名字为name的对象
	private void remove(String name) {
		for (FacePamphletProfile profile : listOfProfiles)	{
			if (name.equals(profile.getName()))	{
				listOfProfiles.remove(profile);
				break;
			}		
		}
	}
	
	//返回listOfProfiles中名字为name的对象的index
	private int index(String name)	{
		for (int i = 0; i < listOfProfiles.size(); i ++)	{
			if (name.equals(listOfProfiles.get(i).getName()))	{
				return i;
			}
		}
		return -1;
	}
	
	//判断listOfProfiles中名字为name1的对象和名字为name2的对象是否为朋友(仅单方向判断即可)
	private boolean isFriends(String name1, String name2)	{
		FacePamphletProfile profile1 = listOfProfiles.get(index(name1));
		if ((profile1.getFriends()).contains(name2))
			return true;
		else
			return false;
	}
	
	//将名字为name的对象从他所有朋友的朋友列表移除
	private void removedFromAll (String name)	{
		for (FacePamphletProfile profile : listOfProfiles)	{
			if ((profile.getFriends()).contains(name))
				(profile.getFriends()).remove(name);
		}
	}
	
	private ArrayList<FacePamphletProfile> listOfProfiles = new ArrayList<FacePamphletProfile>();

}
