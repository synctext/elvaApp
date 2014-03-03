package org.elva.elvaapp.model;

public class Question {

	private String question;
	private Questionaire questionaire;
	
	public Question(Questionaire questionaire, String question){
		this.questionaire = questionaire;
		this.question = question;
		questionaire.addQuestions(this);
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Questionaire getQuestionaire() {
		return questionaire;
	}

	public void setQuestionaire(Questionaire questionaire) {
		this.questionaire = questionaire;
	}
	
	public String toString(){
		return question;		
	}
	
}
