package view;
import model.*;
import java.util.List;

import listeners.MainUiListener;
import model.Question;

public interface AbstractManualExamView {

	void registerListener(MainUiListener listener);

	void loadQuestionsExamIntoTable(Question q);

}
