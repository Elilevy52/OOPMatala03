package listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import javafx.stage.Stage;
import model.MultilpeChoiceQuestion;
import model.MultipeChoiseAnswer;
import model.OpenQuestion;

public interface MainUiListener {

	void addOpenQuestion(String question, String answer);
	void addMultilpeChoiceQuestion(MultilpeChoiceQuestion question);
	void addMultipleChoiceQuestionAnswer(MultilpeChoiceQuestion quest, String choiceAnswer, boolean isTrue);
	void openAnswersWindow(MultilpeChoiceQuestion aQ, Stage parent);
	void deleteMultipleChoiceAnswer(MultipeChoiseAnswer answer, MultilpeChoiceQuestion mq);
	void importFromBinaryFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException;
	void autoSaveTobinaryFileOnExit() throws FileAlreadyExistsException, FileNotFoundException, ClassNotFoundException, IOException;
	void autoCreateExamFromChoiceBox(int number, Stage stage) throws IOException;
	void openManualExamWindow(Stage stage);
	void addOpenQuestToManualExam(OpenQuestion quest);
	void addMultipleChoiceQuestToManualExam(MultilpeChoiceQuestion quest);
	void createManualExam() throws IOException;
	void openExistingExams(Stage stage);
	void copySelectedExam(File f) throws FileNotFoundException, IOException ;
	void importPreMade();
	
	
	
	
	
	
	
	
}
