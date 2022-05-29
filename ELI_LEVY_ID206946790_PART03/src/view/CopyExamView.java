package view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
import listeners.*;
import model.*;
public class CopyExamView implements AbstractCopyExamView {
	private Vector<MainUiListener> uiListener = new Vector<MainUiListener>();
	private GridPane root;
	private Scene scene;
	
	private TableView<File> filesTableView;
	public TableColumn<File, String> fileNameColumn;
	
	@Override
	public void registerListener(MainUiListener listener) {
		uiListener.add(listener);
	}
	public CopyExamView(Stage primaryStage)  {
		this.root = new GridPane();
		this.scene = new Scene(root, 270, 500);
		Image ima = new Image("robot.png");
		primaryStage.getIcons().add(ima);
		root.setHgap(5);
		root.setVgap(5);
		root.setPadding(new Insets(10));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Exam Creator Program");
		this.filesTableView = new TableView<File>();
		tabelView();

		gridPane();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	private void tabelView() {
		
		this.fileNameColumn = new TableColumn<File, String>("File name.");
		
		fileNameColumn.setCellValueFactory(new PropertyValueFactory<File, String>("name"));
		fileNameColumn.setMinWidth(200);
		
		filesTableView.getColumns().add(fileNameColumn);
	}
	private void gridPane() {
		root.add(filesTableView, 0, 0);
		root.add(copyButton(), 0, 2);
	}
	private Button copyButton() {
		Button copy = new Button("Make copy of existing exams");
		copy.setMinSize(25,	25);
		copy.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				File f = filesTableView.getSelectionModel().getSelectedItem();

				for (MainUiListener l : uiListener) {
					try {
						l.copySelectedExam(f);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Please select file first.");
					}
				}
				
			}
		});
		
	return copy;
	}
	@Override
	public void loadFilesIntoTable(List<File> files) {
		filesTableView.getItems().addAll(files);
		
	}
}
