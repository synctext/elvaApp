package org.elva.elvaapp.model;
import java.util.ArrayList;

public class Questionaire {
	private String name;
	private Project project;
	private ArrayList<Question> questions;
	
	public Questionaire(Project project, String name){
			this.name = name;
			questions =  new ArrayList<Question>();
			this.project = project;
			project.addQuestionaire(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void addQuestions(Question question) {
		questions.add(question);
	}
	
	public String toString(){
		return name;		
	}
	
}
