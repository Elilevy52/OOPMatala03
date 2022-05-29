package model;

import java.util.Comparator;

public class CompareQuestion implements Comparator<Question> {

	@Override
	public int compare(Question o1, Question o2) {
		int o1Length = 0;
		int o2Length = 0;
		if (o1 != null) {
			if (o1 instanceof OpenQuestion) {
				OpenQuestion open = (OpenQuestion) o1;
				o1Length += open.getOpenAnswer().length();

			}
			if (o1 instanceof MultilpeChoiceQuestion) {
				MultilpeChoiceQuestion mQuestion = (MultilpeChoiceQuestion) o1;
				for (int i = 0; i < mQuestion.getAnswerNumber(); i++) {
					if (mQuestion.getAnswers(i).getAnswer() != null) {
						o1Length += mQuestion.getAnswers(i).getAnswer().length();
					}
				}
			}
		}
		if (o2 != null) {
			if (o2 instanceof OpenQuestion) {
				OpenQuestion open = (OpenQuestion) o2;
				o2Length += open.getOpenAnswer().length();
			}
			if (o2 instanceof MultilpeChoiceQuestion) {
				MultilpeChoiceQuestion mQuestion = (MultilpeChoiceQuestion) o2;
				for (int i = 0; i < mQuestion.getAnswerNumber(); i++) {
					if (mQuestion.getAnswers(i).getAnswer() != null) {
						o2Length += mQuestion.getAnswers(i).getAnswer().length();
					}
				}
			}
		}
		if (o1Length > o2Length) {
			return 1;
		} else if (o2Length > o1Length) {
			return -1;
		} else {
			return 0;
		}
	}

}
