/*
 * File: FlightPlannerServer.java
 * ---------------------
 * A server program that, when run, reads in information
 * about available flights from a data file, and then listens
 * for incoming network requests.  This program can respond to
 * two types of requests:
 * 
 * "getAllCities" -> we send back a list of all cities
 * "getDestinations" -> (needs parameter "city") we send back a
 *                      list of all cities reachable from the
 *                      provided city.
 */

import acm.program.*;
import acm.util.*;
import java.io.*;
import java.util.*;

public class FlightPlannerServer extends ConsoleProgram 
    implements SimpleServerListener {

    /* The port number where we listen for requests */
    private static final int PORT = 8080;
    
    /* The name of the file containing our flight data */
    private static final String FLIGHT_DATA_FILE = "flights.txt";
    
    /* The server object that we use to listen for requests */
    private SimpleServer server;
    
    /* A map from city names to cities you can fly to from there */
    private HashMap<String, ArrayList<String>> flights;

    public void run() {
        readFlightData(FLIGHT_DATA_FILE);
        server = new SimpleServer(this, PORT);
        server.start();
        println("Starting server...");
    }

    /* Called when we receive a request to respond to */
    public String requestMade(Request request) {
        String cmd = request.getCommand();
        
        // Send back a list of all city names
        if (cmd.equals("getAllCities")) {
            println("Received getAllCities Request");
            ArrayList<String> cities = new ArrayList<String>();
            for (String cityName : flights.keySet()) {
                cities.add(cityName);
            }
            return cities.toString();
        
        // Send back a list of cities reachable from the provided city
        } else if (cmd.equals("getDestinations")) {
            String city = request.getParam("city");
            println("Received getDestinations Request for " + city);
            ArrayList<String> destinations = flights.get(city);
            
            /* If that city is not in our map, we need to make an empty
             * list because we cannot call toString on null.
             */
            if (destinations == null) {
                destinations = new ArrayList<String>(); 
            }
            return destinations.toString();
        } else {
            return "Error, cannot process request: " + request;
        }
    }

    /**
     * Reads in the city information from the given file and stores the
     * information in the HashMap of flights.
     */
    private void readFlightData(String filename) {
        flights = new HashMap<String, ArrayList<String>>();
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.length() != 0) {
                    readFlightEntry(line);
                }
            }
            fileScanner.close();
        } catch (IOException ex) {
            throw new ErrorException(ex);
        }
    }

    /**
     * Reads a single flight entry from the line passed as an argument,
     * which should be in the form
     *
     * fromCity -> toCity
     *
     * Each new flight is recorded by adding a new destination city to
     * the ArrayList stored in our flights HashMap under the key for
     * the starting city.
     */
    private void readFlightEntry(String line) {
        int arrow = line.indexOf("->");
        if (arrow == -1) {
            throw new ErrorException("Illegal flight entry " + line);
        }

        // Note: trim() removes leading/ending spaces from a string
        String fromCity = line.substring(0, arrow).trim();
        String toCity = line.substring(arrow + 2).trim();
        defineCity(fromCity);
        defineCity(toCity);
        flights.get(fromCity).add(toCity);
    }


    /**
     * Defines a city if it has not already been defined. Defining
     * a city consists of entering an empty ArrayList in the flights
     * map to show that it has no destinations yet.
     */
    private void defineCity(String cityName) {
        if (!flights.containsKey(cityName)) {
            flights.put(cityName, new ArrayList<String>());
        }
    }
}

