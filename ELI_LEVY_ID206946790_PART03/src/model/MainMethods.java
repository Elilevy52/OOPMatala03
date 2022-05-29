package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface MainMethods {

	void mangerMethods();

	void mainMenu() throws FileNotFoundException, IOException, ClassNotFoundException;

	boolean importQuestions() throws ClassNotFoundException, IOException;
}
