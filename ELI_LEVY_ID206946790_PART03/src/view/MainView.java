package view;

import java.util.Vector;

import javax.swing.JOptionPane;

import model.*;
import controller.MainMenuController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import listeners.MainUiListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Observable;

public class MainView implements AbstractMainView {
	private Stage stage;
	private Vector<MainUiListener> uiListener = new Vector<MainUiListener>();
	public MainMenuController qController;
	private GridPane root;
	private Scene scene;
	private int selectedIndex;
	
	private TextField openQuestionTextFiled;
	private TextField openQuestionAnswerTextFiled;
	private TextField multipeChoiceQuestionTextField;
	private TextField multipeChoiseAnswerTextField;
	private TextField importTextField;

	public TableView<OpenQuestion> openQuestionView;
	public TableView<OpenQuestion> openAnswerView;
	public TableView<MultilpeChoiceQuestion> mcQuestionView;

	private TableColumn<OpenQuestion, String> openQuestionCol;
	private TableColumn<OpenQuestion, String> openQAnswerCol;
	private TableColumn<MultilpeChoiceQuestion, String> multipleQuestionCol;

	private Button addOpenQuestionButton;

	
	private RadioButton trueButton;
	private RadioButton falseButton;
	
	private Label programLabel;
	private Label autoExamAmountLabel;
	
	private TilePane tp;
	private ToggleGroup tg;
	private TilePane tp2;
	
	public MainView(MainMenuController questionController) {
		this.qController = questionController;
	}

	public MainView(Stage primaryStage) throws Exception {
		stage = primaryStage;
		this.root = new GridPane();
		this.scene = new Scene(root, 1000, 620);
		root.setHgap(7);
		root.setVgap(7);
		root.setPadding(new Insets(10));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Exam creator program");
		Image ima = new Image("robot.png");
		primaryStage.getIcons().add(ima);
		openQuestionView = new TableView<OpenQuestion>();
		openAnswerView = new TableView<OpenQuestion>();
		mcQuestionView = new TableView<MultilpeChoiceQuestion>();
		openAnswerView.setEditable(true);
		openQuestionView.setEditable(true);
		mcQuestionView.setEditable(true);

		labels();
		textFileds();
		bikiniButton();
		createTable();
		radioButtons();

		gridPane();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void createTable() {
		this.openQuestionCol = new TableColumn<OpenQuestion, String>("Open question");
		this.openQAnswerCol = new TableColumn<OpenQuestion, String>("Open answer");
		this.multipleQuestionCol = new TableColumn<MultilpeChoiceQuestion, String>("Multilpe choice question");

		openQuestionCol.setCellValueFactory(new PropertyValueFactory<OpenQuestion, String>("question"));
		openQAnswerCol.setCellValueFactory(new PropertyValueFactory<OpenQuestion, String>("openAnswer"));
		multipleQuestionCol.setCellValueFactory(new PropertyValueFactory<MultilpeChoiceQuestion, String>("question"));

		openQuestionView.setMinSize(400, 300);
		mcQuestionView.setMinSize(300, 300);
		
		openQuestionView.resizeColumn(openQuestionCol, 120);
		openQuestionView.resizeColumn(openQAnswerCol, 120);
		mcQuestionView.resizeColumn(multipleQuestionCol, 220);
		openQuestionCol.setResizable(false);
		openQAnswerCol.setResizable(false);
		multipleQuestionCol.setResizable(false);

		openQuestionCol.setEditable(true);
		openQAnswerCol.setEditable(true);
		multipleQuestionCol.setEditable(true);

		openQuestionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		openQuestionCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<OpenQuestion, String>>() {

			@Override
			public void handle(CellEditEvent<OpenQuestion, String> e) {
				e.getTableView().getItems().get(e.getTablePosition().getRow()).setQuestion(e.getNewValue());
				
			}
		});
		openQAnswerCol.setCellFactory(TextFieldTableCell.forTableColumn());
		openQAnswerCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<OpenQuestion, String>>() {

			@Override
			public void handle(CellEditEvent<OpenQuestion, String> e) {
				e.getTableView().getItems().get(e.getTablePosition().getRow()).setOpenAnswer(e.getNewValue());
			}
		});
		multipleQuestionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		multipleQuestionCol
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MultilpeChoiceQuestion, String>>() {

					@Override
					public void handle(CellEditEvent<MultilpeChoiceQuestion, String> e) {
						e.getTableView().getItems().get(e.getTablePosition().getRow()).setQuestion(e.getNewValue());
					}
				});
		
		openQuestionView.getColumns().addAll(openQuestionCol, openQAnswerCol);
		mcQuestionView.getColumns().addAll(multipleQuestionCol);
	}

	public void gridPane() {
		//Questions
		root.add(programLabel, 0, 0);
		root.add(openQuestionTextFiled, 0, 1);
		root.add(openQuestionAnswerTextFiled, 1, 1);
		root.add(addOpenQuestionButton, 2, 1);
		root.add(multipeChoiceQuestionTextField, 0, 2);
		root.add(addMultipleQuestionToButton(), 2, 2);
		root.add(multipeChoiseAnswerTextField, 0, 3);
		root.add(addAnswerToMultipleChoiceQuestionButton(), 2, 3);
		root.add(tp, 1, 3);
		//exms
		root.add(autoExamAmountLabel,0 ,4);
		root.add(addChoiceBox(), 1, 4);
		root.add(manualExamButton(), 2, 4);
		root.add(showExistingExamFilesButton(stage),2,5);
		//import
		root.add(importTextField, 0, 5);
		root.add(importFromBinaryFileButton(), 1, 5);
		root.add(importPreMade() , 0, 6);
		//tables
		root.add(openNewScene(), 1, 9);
		root.add(openQuestionView, 0, 8);
		root.add(mcQuestionView, 1, 8);

		
		//exit
		root.add(exitButton(), 3, 10);
	}

	public TilePane addChoiceBox() {
		tp2 = new TilePane();
		Button autoEButton = new Button("Create auto exam");
		tp2.setPrefWidth(80);
		autoEButton.setMinSize(25, 25);
		ChoiceBox <String> choiceBox = new ChoiceBox<>();
		choiceBox.getItems().add("1");
		choiceBox.getItems().add("2");
		choiceBox.getItems().add("3");
		choiceBox.getItems().add("4");
		choiceBox.getItems().add("5");
		choiceBox.getItems().add("6");
		choiceBox.getItems().add("7");
		choiceBox.getItems().add("8");
		choiceBox.getItems().add("9");
		choiceBox.getItems().add("10");
		
		choiceBox.setOnAction((event) -> {
			selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
				
		});
		
		autoEButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
			for(MainUiListener l : uiListener) {
				try {
					l.autoCreateExamFromChoiceBox(selectedIndex + 1, stage);
				} catch (IOException e1) {
					errorMessage(e1.getMessage());
				}
			}
			}
		});
		tp2.getChildren().add(choiceBox);
		tp2.getChildren().add(autoEButton);
		return tp2;
	}
	

	public void radioButtons() {
		tp = new TilePane();
		tg = new ToggleGroup();
		this.trueButton = new RadioButton("True");
		this.falseButton = new RadioButton("False");
		trueButton.setToggleGroup(tg);
		falseButton.setToggleGroup(tg);
		tp.getChildren().add(trueButton);
		tp.getChildren().add(falseButton);
	}

	public void labels() {
		this.programLabel = new Label("Add questions and Answers");
		programLabel.setFont(new Font("Ariel", 14));
		programLabel.setMinWidth(200);
		
		
		this.autoExamAmountLabel = new Label("Create auto exam - Please chose amount of questions ->");
		autoExamAmountLabel.setFont(new Font("Ariel", 12));
		
	}

	public void textFileds() {
		this.openQuestionTextFiled = new TextField();
		openQuestionTextFiled.setPromptText("Enter question");
		openQuestionTextFiled.setMinSize(25, 25);
		
		this.openQuestionAnswerTextFiled = new TextField();
		openQuestionAnswerTextFiled.setPromptText("Enter answer");
		openQuestionAnswerTextFiled.setMinSize(25, 25);
		
		this.multipeChoiceQuestionTextField = new TextField();
		multipeChoiceQuestionTextField.setPromptText("Enter question");
		multipeChoiceQuestionTextField.setMinSize(25, 25);
		
		this.multipeChoiseAnswerTextField = new TextField();
		multipeChoiseAnswerTextField.setPromptText("Enter answer");
		multipeChoiseAnswerTextField.setMinSize(25, 25);
		
		this.importTextField = new TextField();
		importTextField.setEditable(false);
		importTextField.setText("Exam.dat");
		
		
	}

	public void bikiniButton() throws Exception {
		this.addOpenQuestionButton = new Button("Add open question");
		addOpenQuestionButton.setMinSize(50, 25);
		addOpenQuestionButton.setOnAction(e -> addOpenQuestionButtonAction());

	}

	private Button openNewScene() {
		Button openAnswersTable = new Button("Show all added multiple choice answers");
		openAnswersTable.setMinSize(210, 25);
		openAnswersTable.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				for (MainUiListener l : uiListener) {
					try {
					MultilpeChoiceQuestion op = mcQuestionView.getSelectionModel().getSelectedItem();
					l.openAnswersWindow(op, stage);
					} catch(Exception e) {
						JOptionPane.showMessageDialog(null, "Please add multiple choice question and answer first.");
					}
				}
			}
		});
		return openAnswersTable;
	}
	public Button importPreMade() {
		Button importPreMadeButton = new Button("Import pre-made questions");
		importPreMadeButton.setMinSize(210, 25);
		importPreMadeButton.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				for (MainUiListener l : uiListener) {
					l.importPreMade();
				}
			}
		});
		return importPreMadeButton;
	}
	public Button exitButton() {
		Button exitB = new Button("Exit");
		exitB.setMinWidth(30);
		exitB.setMinHeight(30);
		exitB.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				int exit = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
				if (exit == 0) {
					if (!mcQuestionView.getItems().isEmpty()
							|| (!openQuestionView.getItems().isEmpty() && openAnswerView.getItems().isEmpty())) {
						for (MainUiListener l : uiListener) {
							try {
								l.autoSaveTobinaryFileOnExit();
							} catch (FileAlreadyExistsException e1) {
								errorMessage(e1.getMessage());
							} catch (FileNotFoundException e1) {
								errorMessage(e1.getMessage());
							} catch (ClassNotFoundException e1) {
								errorMessage(e1.getMessage());
							} catch (IOException e1) {
								errorMessage(e1.getMessage());
							}
							JOptionPane.showMessageDialog(null, "Exiting and saveing to new binary file.");
							Platform.exit();
						}
					} else {
						JOptionPane.showMessageDialog(null, "No question added, no binary file was created." );
						Platform.exit();
					}

				}
			}

		});
		return exitB;
	}

	private Button importFromBinaryFileButton() {
		Button importButton = new Button("Import binary file");
		importButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				if (importTextField.getText().isEmpty() || importTextField.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "No file name entered." + "\nPlease enter file name first.");
				} else {
					for (MainUiListener l : uiListener) {
						try {
							l.importFromBinaryFile(importTextField.getText());
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "File Not found.");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						} catch (ClassNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "Class not found.");
							e1.printStackTrace();
						}
					}
				}

			}
		});

		return importButton;
	}

	private Button addMultipleQuestionToButton() {
		Button addQuestion = new Button("Add multiple choice question");
		addQuestion.setMinSize(210, 25);
		addQuestion.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (multipeChoiceQuestionTextField.getText().isEmpty()
						|| multipeChoiceQuestionTextField.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "No question added." + "\nPlease add a question first.");
				} else {
					for (MainUiListener l : uiListener) {
						MultilpeChoiceQuestion aw = new MultilpeChoiceQuestion(
								multipeChoiceQuestionTextField.getText());
						l.addMultilpeChoiceQuestion(aw);
					}
				}
			}
		});
		return addQuestion;
	}

	private Button addAnswerToMultipleChoiceQuestionButton() {
		Button addAnswer = new Button("Add multiple choice answer");
		addAnswer.setMinSize(210, 25);
		addAnswer.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				boolean togleB = false;
				if (trueButton.isSelected()) {
					togleB = true;
				} else {
					togleB = false;
				}
				if (mcQuestionView.getSelectionModel().getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "No question selected." + "\nPlease select a question first.");
				} else {
					MultilpeChoiceQuestion mq = mcQuestionView.getSelectionModel().getSelectedItem();
					for (MainUiListener l : uiListener) {
						l.addMultipleChoiceQuestionAnswer(mq, multipeChoiseAnswerTextField.getText(), togleB);
					}
				}

			}
		});
		return addAnswer;
	}
	private Button manualExamButton() {
		Button openManualExamButton = new Button("Create manual exam");
		openManualExamButton.setMinSize(210, 25);
		openManualExamButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				for (MainUiListener l : uiListener) {
					l.openManualExamWindow(stage);
				}
				
			}
		});
		return openManualExamButton;
	}
	private Button showExistingExamFilesButton(Stage stage) {
		Button openFilesWindow = new Button("Exam files");
		openFilesWindow.setMinSize(210, 25);
		openFilesWindow.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				for (MainUiListener l : uiListener) {
					l.openExistingExams(stage);
				}
				
			}
		});
		return openFilesWindow;
	}
	private void addOpenQuestionButtonAction() {
		if (openQuestionTextFiled.getText().isEmpty() || openQuestionTextFiled.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "No question added." + "\nPlease add a question first.");
		}
		if (openQuestionAnswerTextFiled.getText().isEmpty() || openQuestionAnswerTextFiled.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "No answer added." + "\nPlease add an answer first.");
		} else {
			for (MainUiListener l : uiListener) {
				l.addOpenQuestion(openQuestionTextFiled.getText(), openQuestionAnswerTextFiled.getText());
			}
		}
		openQuestionAnswerTextFiled.clear();
		openQuestionTextFiled.clear();
	}
	
	@Override
	public void registerListener(MainUiListener listener) {
		uiListener.add(listener);

	}

	@Override
	public void addOpenQuestionToTable(OpenQuestion question) {
		openQuestionView.getItems().addAll(question);
	}

	@Override
	public void addMultilpeChoiceQuestionToTable(MultilpeChoiceQuestion question) {
		mcQuestionView.getItems().add(question);

	}

	@Override
	public void addQuestionsFromBinaryFile(List<Question> allQuestions) {
		for (int i = 0; i < allQuestions.size(); i++) {
			if (allQuestions.get(i) instanceof OpenQuestion) {
				OpenQuestion openQ = (OpenQuestion) allQuestions.get(i);
				openQuestionView.getItems().add(openQ);
			}
			if (allQuestions.get(i) instanceof MultilpeChoiceQuestion) {
				MultilpeChoiceQuestion mQuestion = (MultilpeChoiceQuestion) allQuestions.get(i);
				mcQuestionView.getItems().add(mQuestion);
			}
		}

	}

	@Override
	public void autoSaveToBinaryFileOnExit() {
		
	}
	
	public void errorMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	@Override
	public void autoCreateExamFromChoiceBox(int number) throws IOException {
	
		
	}
}
