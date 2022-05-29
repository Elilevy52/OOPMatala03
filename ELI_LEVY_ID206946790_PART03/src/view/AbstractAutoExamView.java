package view;

import java.util.List;

import listeners.MainUiListener;
import model.Question;

public interface AbstractAutoExamView {
	void registerListener(MainUiListener listener);

	void loadExamIntoTable(List<Question> autoExamArray);
}
