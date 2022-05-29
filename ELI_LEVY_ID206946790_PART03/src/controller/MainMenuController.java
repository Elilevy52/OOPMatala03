package controller;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

import javafx.stage.Stage;
import listeners.MainEventListener;
import listeners.MainUiListener;
import view.AbstractAnswersView;
import view.AbstractAutoExamView;
import view.AbstractCopyExamView;
import view.AbstractMainView;
import view.AbstractManualExamView;
import view.AnswersView;
import view.AutoExamView;
import view.CopyExamView;
import view.ManualExamView;


public class MainMenuController implements MainEventListener, MainUiListener {

	private MangerClass manModel;
	private AbstractMainView questView;
	private AbstractAnswersView answersView;
	private AbstractAutoExamView autoExamView;
	private AbstractManualExamView manualExamView;
	private AbstractCopyExamView copyExamView;
	
	public MainMenuController(MangerClass manager,AbstractMainView view) {
		this.manModel = manager;
		this.questView = view;
		
		manModel.registerMainMenuListener(this);
		questView.registerListener(this);
	} 
	
	public void MainMenuController(AbstractAnswersView ansView) {
		answersView = ansView;
		answersView.registerListener(this);
	}
	public void MainMenuController(AbstractAutoExamView autoView) {
		autoExamView = autoView;
		autoExamView.registerListener(this);
	}
	public void MainMenuController(AbstractManualExamView manView) {
		manualExamView = manView;
		manualExamView.registerListener(this);
	}
	public void MainMenuController(AbstractCopyExamView copyView) {
		copyExamView = copyView;
		copyExamView.registerListener(this);
	}
	
	public void addOpenQuestion(String question, String answer) {
		manModel.addOpenQuestion(question, answer);
	}

	@Override
	public void addedOpenQuestion(OpenQuestion question) {
		questView.addOpenQuestionToTable(question);
	}
	@Override
	public void addMultilpeChoiceQuestion(MultilpeChoiceQuestion question) {
		manModel.addMultilpeChoiceQuestion(question);

	}

	@Override
	public void addedMultipleChoiceQuestion(MultilpeChoiceQuestion quest) {
		questView.addMultilpeChoiceQuestionToTable(quest);

		
	}

	@Override
	public void addMultipleChoiceQuestionAnswer(MultilpeChoiceQuestion quest ,String choiceAnswer, boolean isTrue) {
		manModel.addMultipleChoiceAnswer(quest,choiceAnswer,isTrue);
	}


	@Override
	public void openAnswersWindow(MultilpeChoiceQuestion aQ, Stage parent) {	
		Stage newStage = new Stage();
		newStage.initOwner(parent);
		answersView = new AnswersView(newStage);
		MainMenuController(answersView);
		answersView.addMultipleChoiceAnswerToTable(aQ);
	}
	
	

	@Override
	public void addedMultipleChoiceAnswer(MultilpeChoiceQuestion quest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMultipleChoiceAnswer(MultipeChoiseAnswer answer, MultilpeChoiceQuestion mq) {
		manModel.deleteMultipleChoiceAnswer(answer, mq);
	}

	@Override
	public void deletedMultipleChoiceAnswer(MultipeChoiseAnswer answer, MultilpeChoiceQuestion mq) {
		answersView.deleteMultipleChoiceAnswerFromTable(answer,mq);
		
	}

	@Override
	public void importFromBinaryFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
		manModel.addQuestionsFromBinaryFile(fileName);
		
	}

	@Override
	public void importedBinaryFile(List<Question> allQuestions) {
		questView.addQuestionsFromBinaryFile(allQuestions);
		
	}

	@Override
	public void autoSaveTobinaryFileOnExit() throws FileAlreadyExistsException, FileNotFoundException, ClassNotFoundException, IOException {
		manModel.saveOnExitBinaryFile();
		
	}

	@Override
	public void autoSavedToBinartFileOnExit(List<Question> allQuestions) {
		
		
	}

	@Override
	public void autoCreateExamFromChoiceBox(int number, Stage stage) throws IOException {
		manModel.autoCreateExam(number, stage);
	}

	@Override
	public void openAutoExamWindow(List<Question> autoExamArray, Stage parent) {
		Stage newStage = new Stage();
		newStage.initOwner(parent);
		autoExamView = new AutoExamView(newStage);
		MainMenuController(autoExamView);
		autoExamView.loadExamIntoTable(autoExamArray);
	}

	@Override
	public void openManualExamWindow(Stage parent) {
		Stage newStage = new Stage();
		newStage.initOwner(parent);
		manualExamView = new ManualExamView(newStage);
		MainMenuController(manualExamView);
		for (int i = 0; i < manModel.getAllQuestionSize(); i++) {
			manualExamView.loadQuestionsExamIntoTable(manModel.getAllQuestion(i));
		}
	}

	@Override
	public void addOpenQuestToManualExam(OpenQuestion quest) {
		manModel.addOpenQuestionToManualExam(quest);
		
	}

	@Override
	public void addMultipleChoiceQuestToManualExam(MultilpeChoiceQuestion quest) {
		manModel.addMultipeChoiseQuestionToManualExam(quest);
	}

	@Override
	public void createManualExam() throws IOException {
		manModel.sortAndPrintManualExam();
	}

	@Override
	public void openExistingExams(Stage parentStage) {
		Stage newStage = new Stage();
		newStage.initOwner(parentStage);
		copyExamView = new CopyExamView(newStage);
		MainMenuController(copyExamView);
		copyExamView.loadFilesIntoTable(manModel.showAllExistingFilesInDir());
	}
	
	@Override
	public void copySelectedExam(File f) throws FileNotFoundException, IOException {
		manModel.makeCopyOfChosenFile(f);
	}

	@Override
	public void sendFileListToView(List<File> exams) {
		copyExamView.loadFilesIntoTable(exams);
		
	}

	@Override
	public void importPreMade() {
		manModel.questionList();
		
	}
	





}
