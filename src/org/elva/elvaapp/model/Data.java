package org.elva.elvaapp.model;

import java.util.ArrayList;

public class Data {

	private static ArrayList<Location> locations;

	public Data() {
		locations = new ArrayList<Location>();
	}

	public static ArrayList<String> getLocationNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (Location location : locations) {
			names.add(location.getName());
		}
		return names;
	}

	public static void addLocation(Location location) {
		locations.add(location);
	}

	public static Location getLocation(int index) {
		return locations.get(index);
	}
}