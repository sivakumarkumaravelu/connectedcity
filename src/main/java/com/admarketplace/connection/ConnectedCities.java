package com.admarketplace.connection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * The class is used to find the connection between two cities.
 * 
 */

public class ConnectedCities {

	public static final String DELIMITER = ",";

	public static void main(String[] args) {
		if (args == null || args.length != 3) {
			System.out.println("USAGE: Returns CONNECTED or NOT CONNECTED, if two cities are connected");
			System.out.println("java ConnectedCities <fromCity> <toCity> <fileName");
			System.out.println("Example: java ConnectedCities \"New York\" \"Los Angeles\" connections.txt");

		} else {
			String fromCity = args[0];
			String toCity = args[1];
			String filename = args[2];
			try {
				Map<String, Set<String>> cityToCitiesMap = parseFileIntoConnections(filename);
				boolean isConnected = isCityConnected(cityToCitiesMap, fromCity, toCity);
				if (isConnected) {
					System.out.println("CONNECTED");
				} else {
					System.out.println("NOT CONNECTED");
				}
			} catch (Exception e) {
				System.out.println("Error occured while processing!" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method parses the file and creates a map which contains the city name
	 * and a set of all the cities connected.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Set<String>> parseFileIntoConnections(String fileName) throws IOException {
		Map<String, Set<String>> cityToCitiesMap = new HashMap<String, Set<String>>();

		try (BufferedReader in = new BufferedReader(
				new FileReader(new File(".").getCanonicalPath() + "/" + fileName))) {

			String line = in.readLine();

			while (line != null && !line.isEmpty()) {
				String[] cities = line.split(DELIMITER);
				String fromCity = cities[0].trim();
				String toCity = cities[1].trim();

				Set<String> fromCityConnections = getCityToCitiesConnection(cityToCitiesMap, fromCity);
				Set<String> toCityConnections = getCityToCitiesConnection(cityToCitiesMap, toCity);

				fromCityConnections.add(toCity);
				toCityConnections.add(fromCity);

				line = in.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error occured while processing file!" + e.getMessage());
			e.printStackTrace();
		}

		return cityToCitiesMap;
	}

	/**
	 * Returns the Set of cities that are connected to the requested city.
	 * 
	 * @param map
	 * @param city
	 * @return
	 */
	public static Set<String> getCityToCitiesConnection(Map<String, Set<String>> map, String city) {
		if (!map.containsKey(city)) {
			map.put(city, new HashSet<String>());
		}
		return map.get(city);
	}

	/**
	 * Method that finds if the cities are connected or not. BFS implementation.
	 * 
	 * @param cityToCitiesMap
	 * @param fromCity
	 * @param toCity
	 * @return
	 */
	public static boolean isCityConnected(Map<String, Set<String>> cityToCitiesMap, String fromCity, String toCity) {
		boolean isConnectionFound = fromCity.equals(toCity);
		if (cityToCitiesMap.containsKey(fromCity) && cityToCitiesMap.containsKey(toCity)) {
			// BFS requires a Queue
			Queue<String> citiesToVisit = new LinkedList<String>();
			// Maintain the set of visited cities to avoid looping in BFS
			Set<String> citiesVisited = new HashSet<String>();
			citiesToVisit.add(fromCity);

			while (!citiesToVisit.isEmpty() && !isConnectionFound) {
				String city = citiesToVisit.poll();
				isConnectionFound = city.equals(toCity);
				// Get the city from map and check for the connected city
				Set<String> possibleConnections = cityToCitiesMap.get(city);
				possibleConnections.stream().forEach(connectedCity -> {
					if (!citiesVisited.contains(connectedCity)) {
						citiesToVisit.add(connectedCity);
						citiesVisited.add(connectedCity);
					}
				});
			}
		}

		return isConnectionFound;
	}
}
