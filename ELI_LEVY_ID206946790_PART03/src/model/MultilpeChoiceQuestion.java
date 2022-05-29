package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.JOptionPane;

public class MultilpeChoiceQuestion extends Question implements Serializable {

	NewSet<MultipeChoiseAnswer> Answers = new NewSet<MultipeChoiseAnswer>();
	private int answerNumber;

	public MultilpeChoiceQuestion(String question) {
		super(question);
		answerNumber = 0;
	}

	public String addAnswer(MultipeChoiseAnswer answer) {
		for (int i = 0; i <= answerNumber; i++) {
			if (Answers.contains(answer)) {
				JOptionPane.showMessageDialog(null, answer + "Answer already exists");
				return "Answer already exists";
			}
		}
		answerNumber++;
		if (answerNumber >= Answers.size()) {
			Answers.add(answer);
		}
		return "Answer Added";
	}

	public void deleteAnswer(int index) {
		if(Answers.isEmpty()) {
			System.out.println("No answers currently in database.");
		}
		Answers.remove(Answers.get(index - 1));
		answerNumber--;
	}
	public boolean deleteAnswerObject(MultipeChoiseAnswer aN) {
		if(Answers.remove(aN)) {
			answerNumber--;
			return true;
		} else {
			return false;
		}
		
	}
	public void updateAnswer(int index, String Answer) {
		Answers.get(index - 1).setAnswer(Answer);
	}

	public MultipeChoiseAnswer getAnswers(int index) {
		return Answers.get(index);
	}

	public int getAnswerNumber() {
		return answerNumber;
	}

	public String getQuestion() {
		return question;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[" + getId() + "] Multipe Choise Question: \n" + getQuestion() + "\n");
		for (int i = 0; i < Answers.size(); i++) {
			if (Answers.get(i) != null) {
				sb.append("Answer number: [" + (i + 1) + "] " + Answers.get(i).toString());
				sb.append("[Correct or not]: " + Answers.get(i).getIsTrue() + "\n");
			}
		}
		return sb.toString();
	}
}
