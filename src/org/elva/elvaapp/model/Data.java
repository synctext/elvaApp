package org.elva.elvaapp.model;

import java.util.ArrayList;

public class Data {

	private static ArrayList<ProjectLocation> locations;
	
	public static ArrayList<String> getLocationNames() {
		if(locations == null){
			Data.loadTestData();
		}
		ArrayList<String> names = new ArrayList<String>();
		for (ProjectLocation location : locations) {
			names.add(location.getName());
		}
		return names;
	}

	public static void addLocation(ProjectLocation location) {
		if(locations == null){
			Data.loadTestData();
		}
		locations.add(location);
	}

	public static ProjectLocation getLocation(int index) {
		if(locations == null){
			Data.loadTestData();
		}
		return locations.get(index);
	}
	
	public static void loadTestData() {
		locations = new ArrayList<ProjectLocation>();
		ProjectLocation location = new ProjectLocation("test location");
		Project project = new Project(location, "test project", "test desciption");
		Questionaire questionaire = new Questionaire(project, "test questionaire");
		Question question = new Question(questionaire, "test question");
		
		ProjectLocation location2 = new ProjectLocation("test location2");
		Project project2 = new Project(location2, "test project2", "test desciption2");
		Questionaire questionaire2 = new Questionaire(project2, "test questionaire2");
		Question question2 = new Question(questionaire2, "test question2");
		
		new Data();
		Data.addLocation(location);
		Data.addLocation(location2);
	}
	
}