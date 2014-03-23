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
				
		Question question3 = new Question(questionaire, "Wat is het probleem?", 1, new String [] {"Cha cha", "Vodka", "Pils"});
		Question question5 = new Question(questionaire, "Hoeveel problemen heb je?", 2, new String []{"0", "100", "99"});
		Question question6 = new Question(questionaire, "Voeg een foto toe", 3, new String []{""});
		Question question4 = new Question(questionaire, "Wie is het probleem?");
		Question question7 = new Question(questionaire, "Voeg een 2e foto toe", 3, new String []{""});



				
		ProjectLocation location2 = new ProjectLocation("test location2");
		Project project2 = new Project(location2, "test project2", "test desciption2");
		Project project3 = new Project(location2, "test project3", "test desciption3");
		Project project4 = new Project(location2, "test project4", "test desciption4");
		
		project4.setPrivate();
		
		Questionaire questionaire2 = new Questionaire(project2, "test questionaire2");
		Question question2 = new Question(questionaire2, "test question2");
		
		new Data();
		Data.addLocation(location);
		Data.addLocation(location2);
	}
	
}