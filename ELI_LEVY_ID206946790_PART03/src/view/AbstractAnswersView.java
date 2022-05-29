package view;

import java.util.List;

import controller.MainMenuController;
import listeners.MainUiListener;
import model.MultilpeChoiceQuestion;
import model.MultipeChoiseAnswer;
import model.Question;

public interface AbstractAnswersView {

	void registerListener(MainUiListener listener);
	void addMultipleChoiceAnswerToTable(MultilpeChoiceQuestion answer);
	void deleteMultipleChoiceAnswerFromTable(MultipeChoiseAnswer answer,MultilpeChoiceQuestion question);

}
