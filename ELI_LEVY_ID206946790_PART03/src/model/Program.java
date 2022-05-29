package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import java.util.Scanner;

import controller.MainMenuController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.AbstractMainView;
import view.MainView;

public class Program extends Application implements MainMethods {
	Scanner input;
	MangerClass manage;
	private char chr;
	private Stage stage;
	private int choice;
	private int innerOP;
	private int questionNum;
	private int amountOfQuestions;
	private int updateChoice;
	private boolean flag = true;

	@Override
	public boolean importQuestions() throws ClassNotFoundException, IOException {
		System.out.println("Import questions from binary file ? \nType '1' for yes. \nType '2' for no.");
		int q = manage.intException(input);
		if (q == 2) {
			
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void mainMenu() throws FileNotFoundException, IOException, ClassNotFoundException, InputMismatchException {
		String menu = ("\n--------Exam creator--------\n" + "-----Select option:-----\n"
				+ "[1] - Show all questions/answers that exist.\n" + "[2] - Add a new question/answer.\n"
				+ "[3] - Update an existing question.\n" + "[4] - Update an existing answer.\n"
				+ "[5] - Delete an existing answer.\n" + "[6] - Create an exam manually.\n"
				+ "[7] - Create an exam automatically.\n" + "[8] - Sort Questions by answers length.\n"
				+ "[9] - Create copy of an existing exam.\n" 
				+ "\n[11] - Exit.\n" + "Enter your choice: ");

		do {
			System.out.println(menu);
			choice = manage.intException(input);
			switch (choice) {
			case 1:

				System.out.println("-----Show all questions menu-----");
				System.out.println("[1] - Show all questions.");
				System.out.println("[2] - Show only Multilpe Choice Questions (With their correct answers).");
				System.out.println("[3] - Show only Open-type questions.");
				System.out.println("[4] - Show available questions only.");
				System.out.println(
						"[-1] - Go back to main menu." + "\n(You can type '-1' at any time to go back to main menu).");
				do {
					innerOP = manage.intException(input);
					switch (innerOP) {
					case 1:
						manage.printAllQuestionAndAnswers();
						break;
					case 2:
						manage.printOnlyMultilpeChoiceQuestions();
						break;
					case 3:
						manage.printOnlyOpenQuestions();
						break;
					case 4:
						manage.printAllQuestions();
						break;
					}
				} while (innerOP != -1);
				break;

			case 2:

				System.out.println("----Add a question-----" + "\n[1] - Add an Multilpe Choice - type question."
						+ "\n[2] - Add an open - type question." + "\n[-1] - To go back to main menu."
						+ "\n(You can type '-1' at any time to go back to main menu).");
				do {
					innerOP = manage.intException(input);
					switch (innerOP) {
					case 1:
						System.out.println("Please enter your question below: ");
						String ameriQuestion = input.nextLine();
						MultilpeChoiceQuestion ameriC = new MultilpeChoiceQuestion(ameriQuestion);
						if (!manage.addMultilpeChoiceQuestion(ameriC)) {
							innerOP = -1;
							break;
						} else {
							System.out.println("How many answers do you want to add? (2 to 6) ");
							int answersAmount = manage.intException(input);
							if (answersAmount < 2 || answersAmount > 6) {
								System.out.println("Invalid input, Please enter a number between 2 to 6.");
								answersAmount = manage.intException(input);
							}
							for (int i = 0; i < answersAmount; i++) {
								System.out.println("Please enter answer number " + (i + 1) + ": ");
								String answer = input.nextLine();
								System.out.println("Is it a correct answer or not? (true/false): ");
								try {
									boolean isTrue = input.nextBoolean();
									input.nextLine();
									System.out.println(ameriC.addAnswer(new MultipeChoiseAnswer(answer, isTrue)));
									flag = false;
								} catch (InputMismatchException e) {
									System.out.println("Invaild input, Please enter True/False answers only.");
									i--;
								}
							}
							break;
						}

					case 2:
						System.out.println("Please enter your question below: ");
						String question = input.nextLine();
						System.out.println("Please enter an answer: ");
						String answer = input.nextLine();
						System.out.println(manage.addOpenQuestion(question, answer));
						break;
					}
				} while (innerOP != -1);
				break;

			case 3:

				do {
					System.out.println(
							"See the full list of questions & answers? y/Y" + "\nTo go back to main menu 'n/N.");
					char newChoice = input.next().charAt(0);
					if (newChoice == 'y' || newChoice == 'Y') {
						if (manage.isAllQuestionEmpty()) {
							innerOP = -1;
							break;
						} else if (!manage.isAllQuestionEmpty()) {
							manage.printAllQuestions();
						}
					} else if (newChoice == 'n' || newChoice == 'N') {
						innerOP = -1;
						break;
					}
					System.out.println("Please choose the question number you want to update: ");
					updateChoice = manage.intException(input);
					System.out.println("Enter new text below: ");
					String newQuestion = input.nextLine();
					System.out.println(manage.updateAnExistingQuestion(updateChoice, newQuestion));
					break;

				} while (innerOP != -1);
				break;

			case 4:

				System.out.println("Would you like to update an Multilpe Choice answer or Open answer?"
						+ "\nType [1] for Multilpe Choise, \nType [2] for Open." + "\n[-1] - To go back to main menu.");
				do {
					innerOP = manage.intException(input);
					switch (innerOP) {
					case 1:
						System.out.println(
								"See the full list of questions & answers? y/Y" + "\nTo go back to main menu 'n/N.");
						char newChoice1 = input.next().charAt(0);
						if (newChoice1 == 'y' || newChoice1 == 'Y') {
							if (manage.isAllQuestionEmpty()) {
								innerOP = -1;
								break;
							} else {
								manage.printOnlyMultilpeChoiceQuestions();
							}
						} else if (newChoice1 == 'n' || newChoice1 == 'N') {
							innerOP = -1;
							break;
						}
						if (!manage.isAllQuestionEmpty()) {
							System.out.println("Please select from which question you want to update an answer: ");
							questionNum = manage.intException(input);
							manage.getMultipeChoiseAnswer(questionNum);
							System.out.println("Please select which answer: ");
							int answerNum = manage.intException(input);
							System.out.println("Please enter the new answer: ");
							String newAnswer = input.nextLine();
							System.out.println(manage.updateAnAnswer(questionNum, answerNum, newAnswer));
							break;
						}
						break;

					case 2:

						System.out.println(
								"See the full list of questions & answers? y/Y" + "\nTo go back to main menu 'n/N.");
						char newChoice2 = input.next().charAt(0);
						if (newChoice2 == 'y' || newChoice2 == 'Y') {
							if (manage.isAllQuestionEmpty()) {
								innerOP = -1;
								break;
							} else {
								manage.printOnlyOpenQuestions();
							}
						} else if (newChoice2 == 'n' || newChoice2 == 'N') {
							innerOP = -1;
							break;
						}
						System.out.println("Please select from which question you want to update an answer: ");
						int questionNum1 = manage.intException(input);
						System.out.println("Please enter the new answer: ");
						String newAnswer1 = input.nextLine();
						System.out.println(manage.updateAnAnswer(questionNum1, questionNum, newAnswer1));
						break;
					}
				} while (innerOP != -1);
				break;

			case 5:

				do {
					System.out
							.println("See the full list of questions&answers? y/Y" + "\nTo go back to main menu 'n/N.");
					char newChoice2 = input.next().charAt(0);
					if (newChoice2 == 'y' || newChoice2 == 'Y') {
						if (manage.isAllQuestionEmpty()) {
							innerOP = -1;
							break;
						}
					} else if (newChoice2 == 'n' || newChoice2 == 'N') {
						innerOP = -1;
						break;
					}
					System.out.println("Can only delete from American-type questions.");
					manage.printOnlyMultilpeChoiceQuestions();
					System.out.println("Please select from which question you want to delete an answer: ");
					int questionNum1 = manage.intException(input);
					while (!manage.checkInstanceofQuestion(questionNum1)) {
						System.out.println("Cannot delete from an Open Question, select a different one: ");
						questionNum1 = manage.intException(input);
						input.nextLine();
					}
					manage.printSelectedMultipeChoiseAnswers(questionNum1);
					System.out.println("Please select which answer: ");
					int answerNum1 = manage.intException(input);
					System.out.println(manage.deleteAnExistingAnswer(questionNum1, answerNum1));
					break;
				} while (innerOP != -1);
				break;

			case 6:
				if (manage.isAllQuestionEmpty()) {
					break;
				} else {
					System.out.println("How many questions do you want in your exam? ");
					amountOfQuestions = manage.intException(input);
					manage.setSize(amountOfQuestions);
					System.out.println(amountOfQuestions);
					manage.printAllQuestions();
					int answersAmount = 0;
					List<Integer> answersArray = new ArrayList<>();
					System.out.println("\nPlease choose the question you wish to add: ");
					for (int i = 0; i < amountOfQuestions; i++) {
						questionNum = manage.intException(input);
						if (!manage.checkInstanceofQuestion(questionNum)) {
							manage.addQuestionToManualExam(questionNum, null);
						} else {
							System.out.println("Please choose the amount of answers you want for the question: ");
							answersAmount = manage.intException(input);
							System.out.println("These are the answers:");
							manage.getMultipeChoiseAnswer(questionNum);
							System.out.println("Please select the answers you want: ");
							for (int j = 0; j < answersAmount; j++) {
								answersArray.add(manage.intException(input));
							}
							manage.addQuestionToManualExam(questionNum, answersArray);
							if (i != answersArray.size() - 1) {
								System.out.println("Please enter the next question.");
							}
						}
					}
				}
				manage.printAndSaveManualExamToFile();
				break;

			case 7:
				if (manage.isAllQuestionEmpty()) {
					break;
				}
				int questA;
				System.out.println("How many questions would you like in your exam? ");
				System.out.println("There are only: " + manage.checkAllQuestionLength() + " Questions available.");
				questA = manage.intException(input);
				if (questA <= 0 || questA > manage.checkAllQuestionLength()) {
					questA = manage.intException(input);
				}
				manage.autoCreateExam(questA, stage);
				break;
				
			case 8:
				if (manage.isAllQuestionEmpty()) {
					break;
				}
				System.out.println("Sort Questions by answers length.");
				manage.sortQuestionsByAnswersLength();
				break;
			case 9:
				if (manage.isAllQuestionEmpty()) {
					break;
				}
				manage.createCopyOfAnExistingExam();
				System.out.println("\nPlease chose file number: ");
				do {
					try {
						int chosenFile = manage.intException(input);
//						manage.makeCopyOfChosenFile(chosenFile);
						flag = false;
					} catch (InputMismatchException e) {
						System.out.println("Chosen File must be 1 or higher, Please try again.");
					}
				} while (flag);
				break;
			case 10:
				
				break;
			default:
				if (choice != 11) {
					System.out.println("Invalid option, Please try again.");
				}

			}
		} while (choice != 11);

		manage.saveOnExitBinaryFile();

		System.out.println("Exiting program...Thank you!");
		input.close();

	}



	@Override
	public void mangerMethods() {
		this.input = new Scanner(System.in);
		this.manage = new MangerClass();
		this.chr = 0;
		this.choice = 0;
		this.innerOP = 0;
		this.amountOfQuestions = 0;
		this.questionNum = 0;
	}

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		Program op = new Program();
		op.mangerMethods();
		launch(args);
//		op.mainMenu();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		MangerClass manage2 = new MangerClass();
		AbstractMainView aqv = new MainView(primaryStage); 
		MainMenuController mmc = new MainMenuController(manage2, aqv);
		MainView aaa = new MainView(mmc); 
	}

	
}
