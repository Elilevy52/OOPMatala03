package listeners;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.stage.Stage;
import model.MultilpeChoiceQuestion;
import model.MultipeChoiseAnswer;
import model.OpenQuestion;
import model.Question;

public interface MainEventListener {

	void addedOpenQuestion(OpenQuestion question);
	void addedMultipleChoiceQuestion(MultilpeChoiceQuestion quest);
	void addedMultipleChoiceAnswer(MultilpeChoiceQuestion quest);
	void deletedMultipleChoiceAnswer(MultipeChoiseAnswer answer, MultilpeChoiceQuestion mq);
	void importedBinaryFile(List<Question> allQuestions);
	void autoSavedToBinartFileOnExit(List<Question> allQuestions);
	void openAutoExamWindow(List<Question> autoExamArray, Stage stage);
	void sendFileListToView(List<File> exams);
	
	
	
	
	
	
	
	
	
	
}
