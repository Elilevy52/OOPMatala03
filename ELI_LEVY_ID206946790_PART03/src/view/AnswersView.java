package view;

import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import controller.MainMenuController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import listeners.MainUiListener;
import model.MultilpeChoiceQuestion;
import model.MultipeChoiseAnswer;
import model.OpenQuestion;
import model.Question;

public class AnswersView implements AbstractAnswersView{
	
	private Vector<MainUiListener> uiListener = new Vector<MainUiListener>();
	private GridPane root;
	private Scene scene;
	private MultilpeChoiceQuestion mq;
	public TableColumn<MultipeChoiseAnswer, String> multipleAnswerCol;
	public TableColumn<MultipeChoiseAnswer, String> multipleTrueOrFalseCol;
	
	public TableView<MultipeChoiseAnswer> mcAnswerView;
	

	
	@Override
	public void registerListener(MainUiListener listener) {
		uiListener.add(listener);
	}
	
	public AnswersView(Stage primaryStage)  {
		this.root = new GridPane();
		this.scene = new Scene(root, 380, 500);
		Image ima = new Image("robot.png");
		primaryStage.getIcons().add(ima);
		root.setHgap(5);
		root.setVgap(5);
		root.setPadding(new Insets(10));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Exam Creator Program");
		mcAnswerView = new TableView<MultipeChoiseAnswer>();
		tabelView();
		deleteButton();
		
		gridPane();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void gridPane() {
		root.add(mcAnswerView, 0, 0);
		root.add(deleteButton(), 0, 5);
	}
	public Button deleteButton() {
		Button del = new Button("Delete");
		del.setMinSize(25, 35);
		del.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				for(MainUiListener l : uiListener) {
					try {
						l.deleteMultipleChoiceAnswer(mcAnswerView.getSelectionModel().getSelectedItem(),mq);
					}catch (Exception s) {
						JOptionPane.showMessageDialog(null, "Please select an asnwer first.");
					}
					
				} 
				
			}
		});
		return del;
	}
	public void tabelView() {
		this.multipleAnswerCol = new TableColumn<MultipeChoiseAnswer,String>("Answers");
		this.multipleTrueOrFalseCol = new TableColumn<MultipeChoiseAnswer, String>("True/False");
		
		multipleAnswerCol.setCellValueFactory(new PropertyValueFactory<MultipeChoiseAnswer, String>("answer"));
		multipleTrueOrFalseCol.setCellValueFactory(new PropertyValueFactory<MultipeChoiseAnswer, String>("text"));
		mcAnswerView.setEditable(true);
		multipleAnswerCol.setEditable(true);
		multipleTrueOrFalseCol.setEditable(true);
		multipleAnswerCol.setCellFactory(TextFieldTableCell.forTableColumn());
		multipleAnswerCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MultipeChoiseAnswer,String>>() {
			
			@Override
			public void handle(CellEditEvent<MultipeChoiseAnswer, String> e) {
				e.getTableView().getItems().get(e.getTablePosition().getRow()).setAnswer(e.getNewValue());
			}
		});
		
		multipleTrueOrFalseCol.setCellFactory(TextFieldTableCell.forTableColumn());
		multipleTrueOrFalseCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MultipeChoiseAnswer,String>>() {

			@Override
			public void handle(CellEditEvent<MultipeChoiseAnswer, String> e) {
				e.getTableView().getItems().get(e.getTablePosition().getRow()).trueOrFalseText(e.getNewValue());
			}
		});
		
		multipleAnswerCol.setMinWidth(180);
		multipleTrueOrFalseCol.setMinWidth(180);
		
		mcAnswerView.getColumns().addAll(multipleAnswerCol,multipleTrueOrFalseCol);
	}
	@Override
	public void addMultipleChoiceAnswerToTable(MultilpeChoiceQuestion aq) {
		for (int i = 0; i < aq.getAnswerNumber(); i++) {
			mcAnswerView.getItems().add(aq.getAnswers(i));
		}
		mq = aq;
	}

	@Override
	public void deleteMultipleChoiceAnswerFromTable(MultipeChoiseAnswer answer,MultilpeChoiceQuestion question) {
		mcAnswerView.getItems().remove(answer);
	}

}
