package org.elva.elvaapp.model;

public class Question {

	private String question;
	private Questionaire questionaire;
	private int questionType; // 0 = Text, 1 = Radio, 2 = Seekbar
	private String answers;
	
	
	// Default text question
	public Question(Questionaire questionaire, String question){
		this.questionaire = questionaire;
		this.question = question;
		this.questionType = 0;
		questionaire.addQuestions(this);
	}
	
	// With type: 0 = Text, 1 = Radio, 2 = Seekbar
	public Question(Questionaire questionaire, String question, int questionType, String answers){
		this.questionaire = questionaire;
		this.question = question;
		this.questionType = questionType;
		this.answers = answers;
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
