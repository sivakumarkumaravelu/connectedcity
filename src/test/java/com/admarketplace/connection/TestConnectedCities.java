package com.admarketplace.connection;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Test class is used to find the connection between two cities.
 * 
 */

public class TestConnectedCities {

	@Test
	public void test_getCityToCitiesConnection_size() throws Exception {
		Integer expectedValue = 15;
		Map<String, Set<String>> cityToCitiesMap = ConnectedCities.parseFileIntoConnections("connections_test.txt");
		Integer actualValue = cityToCitiesMap.size();
		assertTrue(actualValue.equals(expectedValue));
	}

	@Test
	public void test_getCityToCitiesConnection_data() throws Exception {
		String expectedValue = "Chicago";
		Map<String, Set<String>> cityToCitiesMap = ConnectedCities.parseFileIntoConnections("connections_test.txt");
		String actualValue = cityToCitiesMap.get("New York").iterator().next();
		assertTrue(expectedValue.equals(actualValue));
	}

	@Test
	public void test_isCityConnected() throws Exception {
		Map<String, Set<String>> cityToCitiesMap = new HashMap<>();
		cityToCitiesMap.put("New York", new HashSet<>(Arrays.asList("Chicago")));
		cityToCitiesMap.put("Kansas City", new HashSet<>(Arrays.asList("Denver", "Nashville", "Tampa")));
		cityToCitiesMap.put("Charlotte", new HashSet<>(Arrays.asList("Omaha", "Columbus")));
		cityToCitiesMap.put("Seattle", new HashSet<>(Arrays.asList("Salt Lake City")));
		cityToCitiesMap.put("Chicago", new HashSet<>(Arrays.asList("New York", "Los Angeles", "Cleveland")));
		cityToCitiesMap.put("Nashville", new HashSet<>(Arrays.asList("Kansas City", "Tampa")));
		cityToCitiesMap.put("Cleveland", new HashSet<>(Arrays.asList("Chicago", "Phoenix")));
		cityToCitiesMap.put("Omaha", new HashSet<>(Arrays.asList("Charlotte", "Columbus", "Salt Lake City")));
		cityToCitiesMap.put("Columbus", new HashSet<>(Arrays.asList("Omaha", "Charlotte")));
		cityToCitiesMap.put("Phoenix", new HashSet<>(Arrays.asList("Los Angeles", "Cleveland")));
		cityToCitiesMap.put("Denver", new HashSet<>(Arrays.asList("Kansas City", "Houston")));
		cityToCitiesMap.put("Salt Lake City", new HashSet<>(Arrays.asList("Omaha", "Seattle")));
		cityToCitiesMap.put("Los Angeles", new HashSet<>(Arrays.asList("Chicago", "Phoenix")));
		cityToCitiesMap.put("Tampa", new HashSet<>(Arrays.asList("Kansas City", "Nashville", "Houston")));
		cityToCitiesMap.put("Houston", new HashSet<>(Arrays.asList("Denver", "Tampa")));

		assertTrue(Boolean.valueOf(ConnectedCities.isCityConnected(cityToCitiesMap, "New York", "Los Angeles"))
				.equals(true));
		assertTrue(Boolean.valueOf(ConnectedCities.isCityConnected(cityToCitiesMap, "Los Angeles", "Nashville"))
				.equals(false));
	}
}
