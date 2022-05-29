package view;

import java.io.IOException;
import java.util.List;

import listeners.MainUiListener;
import model.*;
public interface AbstractMainView {
	
	void registerListener(MainUiListener listener);
	void addOpenQuestionToTable(OpenQuestion question);
	void addMultilpeChoiceQuestionToTable(MultilpeChoiceQuestion question);
	void addQuestionsFromBinaryFile(List<Question> allQuestions);
	void autoSaveToBinaryFileOnExit();
	void autoCreateExamFromChoiceBox(int number) throws IOException;
	
	
	
	
	
	
	
	
	
	
	
}

