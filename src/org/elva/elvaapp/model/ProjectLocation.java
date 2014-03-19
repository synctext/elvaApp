package org.elva.elvaapp.model;

import java.util.ArrayList;

public class ProjectLocation {

	private String name;
	private int xCoordinate;
	private int yCoordinate;
	private ArrayList<Project> projects;

	public ProjectLocation(String name) {
		this.name = name;
		projects = new ArrayList<Project>(); 
	}

	public ProjectLocation(String name, int xCoordinate, int yCoordinate) {
		this(name);
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<String> getPublicProjectNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (Project project : projects) {
			if(!project.isPrivate()){
				names.add(project.getName());
			}
		}
		return names;
	}
	
	public ArrayList<String> getPrivateProjectNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (Project project : projects) {
			if(project.isPrivate()){
				names.add(project.getName());
			}
		}
		return names;
	}
	
	public void addProject(Project project){
		projects.add(project);
	}

	public Project getProject(int index) {
		return projects.get(index);
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
