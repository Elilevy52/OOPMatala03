package view;

import java.io.File;
import java.util.List;

import listeners.*;
import model.*;

public interface AbstractCopyExamView {
	void registerListener(MainUiListener listener);
	void loadFilesIntoTable(List<File> files);
}
