package view;

import java.util.List;
import java.util.Vector;

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
import model.Question;

public class AutoExamView implements AbstractAutoExamView{

	private Vector<MainUiListener> uiListener = new Vector<MainUiListener>();
	
	private GridPane root;
	private Scene scene;

	
	private TableView<Question> questionTable;
	private TableColumn<Question, String> questionColumn;
	
	public AutoExamView(Stage primaryStage)  {
		this.root = new GridPane();
		this.scene = new Scene(root, 320, 400);
		Image ima = new Image("robot.png");
		primaryStage.getIcons().add(ima);
		root.setHgap(5);
		root.setVgap(5);
		root.setPadding(new Insets(10));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Exam Creator Program");
		
		tabelView();
		
		gridPane();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void gridPane() {
		root.add(questionTable, 0, 0);
		
	}
	
	public void tabelView() {
		
		this.questionTable = new TableView<Question>();
		this.questionColumn = new TableColumn<Question,String>("Question");
		questionColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
		questionColumn.setEditable(false);
		
		questionTable.setMinSize(300,300);
		
		questionColumn.setMinWidth(300);
		questionColumn.setMaxWidth(300);

		questionTable.getColumns().add(questionColumn);
	}
	@Override
	public void registerListener(MainUiListener listener) {
		uiListener.add(listener);
	}
	@Override
	public void loadExamIntoTable(List<Question> autoExamArray) {
		questionTable.getItems().addAll(autoExamArray);
	}
}
