package org.elva.elvaapp.model;

import java.util.ArrayList;

public class Data {

	private static ArrayList<Location> locations;

	public Data(){
		locations = new ArrayList<Location>();
	}
	
	public static ArrayList<Location> getLocations(){
		return locations;		
	}
	
	public static ArrayList<String> getLocationNames(){
		ArrayList<String> locationNames= new ArrayList<String>(); 
		for(Location location:locations){
			locationNames.add(location.getName());
		}
		return locationNames;		
	}
	
	
	public static void addLocation(Location location){
		locations.add(location);
	}
	
	
}
