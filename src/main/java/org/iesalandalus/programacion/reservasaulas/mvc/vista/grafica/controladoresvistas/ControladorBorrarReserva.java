package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladoresvistas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;

import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ControladorBorrarReserva {
	private IControlador controladorMVC;
	private ObservableList<String> aulas = FXCollections.observableArrayList();

	@FXML
	private ListView<String> lvAula;
	@FXML
	private ListView<String> lvProfesor;
	@FXML
	private DatePicker dpDia;
	@FXML
	private RadioButton rbTramo;
	@FXML
	private RadioButton rbHora;
	@FXML
	private ComboBox<Tramo> cbTramo;
	@FXML
	private TextField tfHora;
	@FXML
	Button btBorrar;
	@FXML
	Button btCancelar;

	private ToggleGroup tgHoraTramo = new ToggleGroup();

	private class CeldaAula extends ListCell<String> {
		@Override
		public void updateItem(String string, boolean vacia) {
			super.updateItem(string, vacia);
			if (vacia) {
				setText("");
			} else {
				setText(string.substring(string.indexOf("nombre=") + 7, string.lastIndexOf(", puestos=")));
			}
		}
	}

	@FXML
	private void initialize() {
		cbTramo.setItems(FXCollections.observableArrayList(Tramo.values()));
		rbTramo.setToggleGroup(tgHoraTramo);
		rbHora.setToggleGroup(tgHoraTramo);
		tgHoraTramo.selectedToggleProperty().addListener((observable, oldValue, newValue) -> habilitaSeleccion());
		tfHora.setDisable(true);
		cbTramo.setDisable(true);
		lvAula.setItems(aulas);
		lvAula.setCellFactory(l -> new CeldaAula());
	}

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		aulas.setAll(FXCollections.observableArrayList(controladorMVC.representarAulas()));
		dpDia.setValue(null);
		rbHora.setSelected(false);
		rbTramo.setSelected(false);
		cbTramo.getSelectionModel().clearSelection();
		tfHora.setText("");
	}

	private void habilitaSeleccion() {
		RadioButton seleccionado = (RadioButton) tgHoraTramo.getSelectedToggle();
		if (seleccionado == rbHora) {
			tfHora.setDisable(false);
			cbTramo.setDisable(true);
		} else {
			tfHora.setDisable(true);
			cbTramo.setDisable(false);
		}
	}

	@FXML
	void anularReserva(ActionEvent event) {
		String nombreAula = lvAula.getSelectionModel().getSelectedItem();
		Aula aula = null;

		try {
			aula = new Aula(Aula.getAulaFicticia(
					nombreAula.substring(nombreAula.indexOf("nombre=") + 7, nombreAula.lastIndexOf(", puestos="))));
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Realizar Reserva",
					"No ha seleccionado ningún aula. Debe seleccionar una de la lista.");
		}

		LocalDate dia = dpDia.getValue();
		RadioButton seleccionado = (RadioButton) tgHoraTramo.getSelectedToggle();
		try {

			Permanencia permanencia;
			if (seleccionado == rbHora) {
				permanencia = new PermanenciaPorHora(dia,
						LocalTime.parse(tfHora.getText(), DateTimeFormatter.ofPattern("HH:mm")));
			} else {
				permanencia = new PermanenciaPorTramo(dia, cbTramo.getValue());
			}
			Reserva reserva = new Reserva(Reserva.getReservaFicticia(aula, permanencia));
			controladorMVC.anularReserva(reserva);
			Stage propietario = ((Stage) btBorrar.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Borrar Reserva", "Reserva borrada satisfactoriamente", propietario);

		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Borrar Reserva", e.getMessage());
		}
	}

	@FXML
	void cancelar(ActionEvent event) {
		((Stage) btCancelar.getScene().getWindow()).close();
	}
}
