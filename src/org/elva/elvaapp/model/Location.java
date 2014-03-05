package org.elva.elvaapp.model;

import java.util.ArrayList;

public class Location {

	private String name;
	private int xCoordinate;
	private int yCoordinate;
	private ArrayList<Project> projects;

	public Location(String name) {
		this.name = name;
		projects = new ArrayList<Project>(); 
	}

	public Location(String name, int xCoordinate, int yCoordinate) {
		this(name);
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<String> getProjectNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (Project project : projects) {
			names.add(project.getName());
		}
		return names;
	}
	
	public void addProject(Project project){
		projects.add(project);
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCoordinateX() {
		return xCoordinate;
	}

	public void setCoordinateX(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getCoordinateY() {
		return yCoordinate;
	}

	public void setCoordinateY(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	
	public String toString(){
		return name;
	}

}
