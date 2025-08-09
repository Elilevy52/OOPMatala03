package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class MultilpeChoiceQuestion extends Question implements Serializable {

        List<MultipeChoiseAnswer> answers = new ArrayList<MultipeChoiseAnswer>();
        private int answerNumber;

	public MultilpeChoiceQuestion(String question) {
		super(question);
		answerNumber = 0;
	}

        public String addAnswer(MultipeChoiseAnswer answer) {
                if (answers.contains(answer)) {
                        JOptionPane.showMessageDialog(null, answer + "Answer already exists");
                        return "Answer already exists";
                }
                answers.add(answer);
                answerNumber++;
                return "Answer Added";
        }

	public void deleteAnswer(int index) {
                if(answers.isEmpty()) {
                        System.out.println("No answers currently in database.");
                }
                answers.remove(index - 1);
                answerNumber--;
        }
        public boolean deleteAnswerObject(MultipeChoiseAnswer aN) {
                if(answers.remove(aN)) {
                        answerNumber--;
                        return true;
                } else {
                        return false;
                }
		
	}
	public void updateAnswer(int index, String Answer) {
                answers.get(index - 1).setAnswer(Answer);
        }

        public MultipeChoiseAnswer getAnswers(int index) {
                return answers.get(index);
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
                for (int i = 0; i < answers.size(); i++) {
                        if (answers.get(i) != null) {
                                sb.append("Answer number: [" + (i + 1) + "] " + answers.get(i).toString());
                                sb.append("[Correct or not]: " + answers.get(i).getIsTrue() + "\n");
                        }
                }
		return sb.toString();
	}
}
