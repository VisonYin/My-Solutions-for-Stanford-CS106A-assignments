/* 
 * File: FacePamphletServerTests.java
 * -----------------------
 * This program tests your server by sending a bunch of requests
 * and validating if the response is what it was expecting.
 */

import acm.program.*;
import java.io.*;
import acm.graphics.*;

public class FacePamphletServerTests extends ConsoleProgram {

	/* The internet address of the computer running the server */
	private static final String HOST = "http://localhost:8000/";

	/* Run all of the tests */
	public void run() {

		// a welcome message
		println("Running the FacePamphlet server tester");
		println("Make sure that the server has just been restarted");
		println("-------");
		println("");

		int passed = 0;
		int total = 0;

		println("=== PING ===\n");

		Request ping = new Request("ping");
		boolean success = runTest(ping, false, "Hello, internet");
		total++;
		if (success) {
			passed++;
		}

		println("=== ADD PROFILE ===\n");

		String[] testNames = {"Chris", "Mehran", "Julie", "Barbra Streisand", "Beyonce Knowles"};

		// Adding first time should be successful
		for (String name : testNames) {
			Request addRequest = new Request("addProfile");
			addRequest.addParam("name", name);
			success = runTest(addRequest, false, "success");
			total++;
			if (success) {
				passed++;
			}
		}

		// Adding a second time should give an error
		for (String name : testNames) {
			Request addRequest = new Request("addProfile");
			addRequest.addParam("name", name);
			success = runTest(addRequest, true, null);
			total++;
			if (success) {
				passed++;
			}
		}

		println("=== CONTAINS PROFILE ===\n");

		// All previously-added profiles should have response "true"
		for (String name : testNames) {
			Request containsRequest = new Request("containsProfile");
			containsRequest.addParam("name", name);
			success = runTest(containsRequest, false, "true");
			total++;
			if (success) {
				passed++;
			}
		}

		String[] notAddedNames = {"Voldemort", "Lord Business", "Darth Vader"};

		// All un-added profiles should have response "false"
		for (String name : notAddedNames) {
			Request containsRequest = new Request("containsProfile");
			containsRequest.addParam("name", name);
			success = runTest(containsRequest, false, "false");
			total++;
			if (success) {
				passed++;
			}
		}

		println("=== DELETE PROFILE ===\n");

		// Should receive error
		Request d1 = new Request("deleteProfile");
		d1.addParam("name", "Voldemort");
		success = runTest(d1, true, "");
		total++;
		if (success) {
			passed++;
		}

		// Should delete successfully
		Request d2 = new Request("deleteProfile");
		d2.addParam("name", "Beyonce Knowles");
		success = runTest(d2, false, "success");
		total++;
		if (success) {
			passed++;
		}

		// Should no longer contain profile
		Request d3 = new Request("containsProfile");
		d3.addParam("name", "Beyonce Knowles");
		success = runTest(d3, false, "false");
		total++;
		if (success) {
			passed++;
		}

		// Should receive error (cannot delete 2x)
		Request d4 = new Request("deleteProfile");
		d4.addParam("name", "Beyonce Knowles");
		success = runTest(d4, true, "success");
		total++;
		if (success) {
			passed++;
		}

		// Should be able to add again
		Request d5 = new Request("addProfile");
		d5.addParam("name", "Beyonce Knowles");
		success = runTest(d5, false, "success");
		total++;
		if (success) {
			passed++;
		}

		println("=== STATUS ===\n");

		// Chris's status is empty
		Request s1 = new Request("getStatus");
		s1.addParam("name", "Chris");
		success = runTest(s1, false, "");
		total++;
		if (success) {
			passed++;
		}

		// Set Chris's status
		Request s2 = new Request("setStatus");
		s2.addParam("name", "Chris");
		s2.addParam("status", "testing");
		success = runTest(s2, false, "success");
		total++;
		if (success) {
			passed++;
		}

		// Should now get new status back
		Request s3 = new Request("getStatus");
		s3.addParam("name", "Chris");
		success = runTest(s3, false, "testing");
		total++;
		if (success) {
			passed++;
		}

		// Should get error for unknown user
		Request s4 = new Request("setStatus");
		s4.addParam("name", "Voldemort");
		s4.addParam("status", "evil");
		success = runTest(s4, true, null);
		total++;
		if (success) {
			passed++;
		}

		// Should get error for unknown user
		Request s5 = new Request("getStatus");
		s5.addParam("name", "Voldemort");
		success = runTest(s5, true, null);
		total++;
		if (success) {
			passed++;
		}

		println("=== IMAGES ===\n");

		// Set and get Chris's image
		GImage image = new GImage("images/ChrisP.jpg");
		String imageString = SimpleServer.imageToString(image);

		Request i1 = new Request("setImage");
		i1.addParam("name", "Chris");
		i1.addParam("imageString", imageString);
		success = runTest(i1, false, "success");
		total++;
		if (success) {
			passed++;
		}

		Request i2 = new Request("getImage");
		i2.addParam("name", "Chris");
		success = runTest(i2, false, imageString);
		total++;
		if (success) {
			passed++;
		}

		// Set and get an invalid user's image
		Request i3 = new Request("setImage");
		i3.addParam("name", "Voldemort");
		i3.addParam("imageString", imageString);
		success = runTest(i3, true, null);
		total++;
		if (success) {
			passed++;
		}

		Request i4 = new Request("getImage");
		i4.addParam("name", "Voldemort");
		success = runTest(i4, true, null);
		total++;
		if (success) {
			passed++;
		}

		println("=== FRIENDS ===\n");

		// Chris initially has no friends :(
		Request f1 = new Request("getFriends");
		f1.addParam("name", "Chris");
		success = runTest(f1, false, "[]");
		total++;
		if (success) {
			passed++;
		}

		// Mehran and Chris become friends!
		Request f2 = new Request("addFriend");
		f2.addParam("name1", "Chris");
		f2.addParam("name2", "Mehran");
		success = runTest(f2, false, "success");
		total++;
		if (success) {
			passed++;
		}

		// Chris has Mehran as a friend <3
		Request f3 = new Request("getFriends");
		f3.addParam("name", "Chris");
		success = runTest(f3, false, "[Mehran]");
		total++;
		if (success) {
			passed++;
		}

		// Mehran also has Chris as a friend <3 (reciprocal friendships)
		Request f4 = new Request("getFriends");
		f4.addParam("name", "Mehran");
		success = runTest(f4, false, "[Chris]");
		total++;
		if (success) {
			passed++;
		}

		// Chris and Julie become friends!
		Request f5 = new Request("addFriend");
		f5.addParam("name1", "Chris");
		f5.addParam("name2", "Julie");
		success = runTest(f5, false, "success");
		total++;
		if (success) {
			passed++;
		}

		// Error: Chris and Julie are already friends
		Request f5b = new Request("addFriend");
		f5b.addParam("name1", "Chris");
		f5b.addParam("name2", "Julie");
		success = runTest(f5b, true, "");
		total++;
		if (success) {
			passed++;
		}

		// Chris is now friends with Mehran and Julie
		Request f6 = new Request("getFriends");
		f6.addParam("name", "Chris");
		success = runTest(f6, false, "[Mehran, Julie]");
		total++;
		if (success) {
			passed++;
		}

		// Deleting Julie's profile should remove her from her friends' friend lists
		Request f7 = new Request("deleteProfile");
		f7.addParam("name", "Julie");
		success = runTest(f7, false, "success");
		total++;
		if (success) {
			passed++;
		}

		// Chris should now just be friends with Mehran
		Request f8 = new Request("getFriends");
		f8.addParam("name", "Chris");
		success = runTest(f8, false, "[Mehran]");
		total++;
		if (success) {
			passed++;
		}

		// Error: Chris cannot be friends with nonexistent person
		Request f9 = new Request("addFriend");
		f9.addParam("name1", "Chris");
		f9.addParam("name2", "Voldemort");
		success = runTest(f9, true, "");
		total++;
		if (success) {
			passed++;
		}

		// Error: Chris cannot be friends with himself
		Request f10 = new Request("addFriend");
		f10.addParam("name1", "Chris");
		f10.addParam("name2", "Chris");
		success = runTest(f10, true, "");
		total++;
		if (success) {
			passed++;
		}

		println("=== INVALID COMMANDS ===\n");

		// and what if we send a bad command?
		Request bad = new Request("badCommand");
		success = runTest(bad, true, "");
		total++;
		if (success) {
			passed++;
		}

		println("Passed: " + passed + "/" + total);
	}

	/**
	 * Runs a request and checks if the result is what was expected (both whether we
	 * expect an error and otherwise what String response is expected)
	 */
	private boolean runTest(Request request, boolean expectError, String expectedSuccessOutput) {
		println(request.toString());
		try{
			String result = SimpleClient.makeRequest(HOST, request);
			if(expectError) {
				println("Test failed. Expected an error but didn't get one\n");
				return false;
			} else if(!result.equals(expectedSuccessOutput)) {
				println("Test failed.");
				println("Expected response: " + expectedSuccessOutput);
				println("Actual response:  " + result + "\n");
				return false;
			} else {
				println("Test passed.\n");
				return true;
			}
		} catch(IOException e) {
			if(expectError && e.getMessage().startsWith(SimpleClient.ERROR_KEY)) {
				println("Test passed.\n");
				return true;
			} else {
				println("Test failed. Received unknown error: " + e.getMessage() + "\n");
				return false;
			}
		}
	}
}
