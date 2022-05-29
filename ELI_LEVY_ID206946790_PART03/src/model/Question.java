package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Question implements Serializable {
	protected String question;
	private static int idGen = 1;
	private int id;

	public Question(String question) {
		this.question = question;
		id = idGen++;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setQuestionsId(List<Question> allQuestions) {
		idGen = allQuestions.size() + 1;
	}

	public int getId() {
		return id;
	}

	public String printQuestionNumber() {
		return "[" + id + "]" + toString();
	}

	@Override
	public String toString() {
		return "Question [" + question + "]" + "\n";
	}
}
