package calendario;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Controller implements Initializable {

	private IntegerProperty year = new SimpleIntegerProperty();

	@FXML
	private List<MonthCalendar> mesesCalendarList;

	@FXML
	private BorderPane view;

	@FXML
	private Label yearLabel;

	public Controller() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Calendar.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		year.set(LocalDate.now().getYear());
		yearLabel.textProperty().bind(year.asString());

		// view.getStylesheets().add(getClass().getResource("/css/calenario.css").toExternalForm());

		int numMes = 0;
		for (MonthCalendar mes : mesesCalendarList) {
			mes.setMonthProperty(numMes + 1);
			mes.yearPropertyProperty().bind(year);
			numMes++;
		}
	}

	public BorderPane getView() {
		return view;
	}

	@FXML
	private void onPreviousButtonAction(ActionEvent e) {
		year.set(year.get() - 1);
	}

	@FXML
	private void onNextButtonAction(ActionEvent e) {
		year.set(year.get() + 1);
	}

}
