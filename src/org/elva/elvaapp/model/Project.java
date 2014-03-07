package org.elva.elvaapp.model;

import java.util.ArrayList;

public class Project {

	private String name;
	private String description;
	private ArrayList<Questionaire> questionaires;
	private ProjectLocation location;
	
	public Project(ProjectLocation location, String name, String description){
		this.name = name;
		this.description = description;
		this.location = location;
		questionaires = new ArrayList<Questionaire>();
		location.addProject(this);
	}
	
	public void addQuestionaire(Questionaire questionaire){
		questionaires.add(questionaire);
	}

	public Questionaire getQuestionnaire (int index) {
		return questionaires.get(index);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProjectLocation getLocation() {
		return location;
	}

	public void setLocation(ProjectLocation location) {
		this.location = location;
	}
	
	public String toString(){
		return name;		
	}

}