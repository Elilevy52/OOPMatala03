package model;

import java.io.Serializable;
import java.util.Objects;

import javax.swing.JOptionPane;

public class MultipeChoiseAnswer implements Serializable {
	private String answer;
	private boolean isTrue;
	private String text;
	
	public MultipeChoiseAnswer(String answer, boolean isTrue) {
		setAnswer(answer);
		setTrue(isTrue);
		if (isTrue) {
			text = "true";
		}
		else {
			text = "false";
		}
	}

	public MultipeChoiseAnswer(MultipeChoiseAnswer other) {
		setAnswer(other.answer);
		setTrue(other.isTrue);
	}

	public boolean getIsTrue() {
		return isTrue;
	}

	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}
	
	public String getText() {
		return text;
	}

	public String getAnswer() {
		return answer;
	}

	public boolean setAnswer(String answer) {
		this.answer = answer;
		return true;
	}

	public String toString() {
		return answer;
	}
	public boolean trueOrFalseText(String booleanText) {
	if(booleanText.equalsIgnoreCase("true")) {
		setTrue(true);
		text = "true";
		return true;
	}
	if(booleanText.equalsIgnoreCase("false")) {
		setTrue(false);
		text = "false";
		return false;
	}
	else {
		String m = JOptionPane.showInputDialog("True/False");
		trueOrFalseText(m);
		return false;
	}
}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultipeChoiseAnswer other = (MultipeChoiseAnswer) obj;
		return Objects.equals(answer, other.answer) && isTrue == other.isTrue;
	}
}
