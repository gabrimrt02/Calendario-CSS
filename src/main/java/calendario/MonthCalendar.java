package calendario;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MonthCalendar extends GridPane implements Initializable {

	private IntegerProperty monthProperty = new SimpleIntegerProperty();
	private IntegerProperty yearProperty = new SimpleIntegerProperty();

	@FXML
	private Label mesLabel;

	@FXML
	private List<Label> diasLabelList;

	public MonthCalendar() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MonthCalendar.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
			getStylesheets().add(getClass().getResource("/css/MonthCalendar.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.monthProperty.addListener((o, ov, nv) -> onCambioFecha(o, ov, nv));
		this.yearProperty.addListener((o, ov, nv) -> onCambioFecha(o, ov, nv));
	}

	private void onCambioFecha(ObservableValue<? extends Number> o, Number ov, Number nv) {

		int dia1 = primerDia(yearProperty.get(), monthProperty.get()) - 1;
		int diaFinal = ultimoDia(yearProperty.get(), monthProperty.get());

		for (int i = 0; i < dia1; i++) {
			diasLabelList.get(i).setText("");
		}

		for (int i = dia1, j = 1; i < dia1 + diaFinal; i++, j++) {
			diasLabelList.get(i).setText("" + j);
		}

		for (int i = dia1 + diaFinal; i < diasLabelList.size(); i++) {
			diasLabelList.get(i).setText("");
		}
		
		mesLabel.setText(obtenerMes(monthProperty.get()));
	}

	private int primerDia(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		return (weekday == 1) ? 7 : weekday - 1;
	}

	private int ultimoDia(int year, int month) {
		LocalDate first = LocalDate.of(year, month, 1);
		return first.plusMonths(1).minusDays(1).getDayOfMonth();
	}

	private String obtenerMes(int month) {
		String nombreMes = "";
		switch (month) {
		case 1:
			nombreMes = "Enero";
			break;
		case 2:
			nombreMes = "Febrero";
			break;
		case 3:
			nombreMes = "Marzo";
			break;
		case 4:
			nombreMes = "Abril";
			break;
		case 5:
			nombreMes = "Mayo";
			break;
		case 6:
			nombreMes = "Junio";
			break;
		case 7:
			nombreMes = "Julio";
			break;
		case 8:
			nombreMes = "Agosto";
			break;
		case 9:
			nombreMes = "Septiembre";
			break;
		case 10:
			nombreMes = "Octubre";
			break;
		case 11:
			nombreMes = "Noviembre";
			break;
		case 12:
			nombreMes = "Diciembre";
			break;
		}
		return nombreMes;
	}

	public final IntegerProperty monthPropertyProperty() {
		return this.monthProperty;
	}

	public final int getMonthProperty() {
		return this.monthPropertyProperty().get();
	}

	public final void setMonthProperty(final int monthProperty) {
		this.monthPropertyProperty().set(monthProperty);
	}

	public final IntegerProperty yearPropertyProperty() {
		return this.yearProperty;
	}

	public final int getYearProperty() {
		return this.yearPropertyProperty().get();
	}

	public final void setYearProperty(final int yearProperty) {
		this.yearPropertyProperty().set(yearProperty);
	}

}
