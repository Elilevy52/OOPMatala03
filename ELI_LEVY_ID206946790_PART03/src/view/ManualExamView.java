package view;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import listeners.MainUiListener;
import model.MangerClass;
import model.MultilpeChoiceQuestion;
import model.MultipeChoiseAnswer;
import model.OpenQuestion;
import model.Question;

public class ManualExamView implements AbstractManualExamView {

	private Vector<MainUiListener> uiListener = new Vector<MainUiListener>();
	private MangerClass manModel;

	private GridPane root;
	private Scene scene;

	private TableView<OpenQuestion> questionTable;
	private TableView<MultilpeChoiceQuestion> multipleChoiceQuestionsTable;

	private TableColumn<OpenQuestion, String> openQuestionColumn;
	private TableColumn<MultilpeChoiceQuestion, String> multipleChoiceQColumn;

	public ManualExamView(Stage primaryStage) {
		this.root = new GridPane();
		this.scene = new Scene(root, 625, 500);
		Image ima = new Image("robot.png");
		primaryStage.getIcons().add(ima);
		root.setHgap(5);
		root.setVgap(5);
		root.setPadding(new Insets(10));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Exam creator program");

		tabelView();

		gridPane();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void gridPane() {
		root.add(questionTable, 0, 0);
		root.add(multipleChoiceQuestionsTable, 1, 0);
		root.add(addOpenQuestionToManualExamButton(), 0, 1);
		root.add(addMultipleQuestionToManualExamButton(), 1, 1);
		root.add(createTheManualExamButton(), 0, 2);
	}

	public void tabelView() {
		this.questionTable = new TableView<OpenQuestion>();
		questionTable.setMinSize(300, 300);

		this.multipleChoiceQuestionsTable = new TableView<MultilpeChoiceQuestion>();
		multipleChoiceQuestionsTable.setMinSize(300, 300);

		this.openQuestionColumn = new TableColumn<OpenQuestion, String>("Question");
		openQuestionColumn.setCellValueFactory(new PropertyValueFactory<OpenQuestion, String>("question"));
		openQuestionColumn.setEditable(false);

		this.multipleChoiceQColumn = new TableColumn<MultilpeChoiceQuestion, String>("Multiple choice question");
		multipleChoiceQColumn.setCellValueFactory(new PropertyValueFactory<MultilpeChoiceQuestion, String>("question"));

		openQuestionColumn.setMinWidth(300);
		openQuestionColumn.setMaxWidth(300);

		multipleChoiceQColumn.setMinWidth(300);
		multipleChoiceQColumn.setMaxWidth(300);

		questionTable.getColumns().add(openQuestionColumn);
		multipleChoiceQuestionsTable.getColumns().add(multipleChoiceQColumn);
	}

	public Button addOpenQuestionToManualExamButton() {
		Button addOpenQuestionButton = new Button("Add open question to manual exam");
		addOpenQuestionButton.setMinSize(25, 25);
		addOpenQuestionButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				if (questionTable.getSelectionModel().getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "No question selected" + "\nPlease select a question first.");
				} else {
					OpenQuestion oq = questionTable.getSelectionModel().getSelectedItem();
					for (MainUiListener l : uiListener) {
						l.addOpenQuestToManualExam(oq);
					}
				}

			}
		});
		return addOpenQuestionButton;
	}

	public Button addMultipleQuestionToManualExamButton() {
		Button addMultipleQuestionButton = new Button("Add multiple choice question to manual exam");
		addMultipleQuestionButton.setMinSize(25, 25);
		addMultipleQuestionButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				if (multipleChoiceQuestionsTable.getSelectionModel().getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "No question selected" + "\nPlease select a question first.");
				} else {
					MultilpeChoiceQuestion mq = multipleChoiceQuestionsTable.getSelectionModel().getSelectedItem();
					for (MainUiListener l : uiListener) {
						l.addMultipleChoiceQuestToManualExam(mq);
					}
				}

			}
		});
		return addMultipleQuestionButton;
	}

	public Button createTheManualExamButton() {
		Button createManualExam = new Button("Create manual exam");
		createManualExam.setMinSize(25, 25);
		createManualExam.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				for (MainUiListener l : uiListener) {
					try {
						l.createManualExam();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		});
		return createManualExam;
	}

	@Override
	public void registerListener(MainUiListener listener) {
		uiListener.add(listener);
	}

	@Override
	public void loadQuestionsExamIntoTable(Question q) {
		if (q instanceof OpenQuestion) {
			OpenQuestion openQ = (OpenQuestion) q;
			questionTable.getItems().add(openQ);
		}
		if (q instanceof MultilpeChoiceQuestion) {
			MultilpeChoiceQuestion aQ = (MultilpeChoiceQuestion) q;
			multipleChoiceQuestionsTable.getItems().add(aQ);
		}
	}
}
