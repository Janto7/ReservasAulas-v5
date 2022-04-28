package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladoresvistas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.LocalizadorRecursos;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControladorComprobarDisponibilidad {

	private static final String COMPROBAR_DISPONIBILIDAD = "Comprobar Disponibilidad";

	private IControlador controladorMVC;
	private ObservableList<String> aulas = FXCollections.observableArrayList();

	@FXML
	private ListView<String> lvAulas;
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
	Button btSalir;
	@FXML
	Button btComprobar;
	@FXML
	private ImageView ivEstado;

	private ToggleGroup tgHoraTramo = new ToggleGroup();

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	private class CeldaAula extends ListCell<String> {
		@Override
		public void updateItem(String string, boolean vacia) {
			super.updateItem(string, vacia);
			if (vacia) {
				setText("");
			} else {
				setText(string.substring(string.indexOf('=') + 1, string.indexOf(',')));
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
		lvAulas.setItems(aulas);
		lvAulas.setCellFactory(l -> new CeldaAula());
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

	private Aula getAula() {
		Aula aula = null;
		try {
			String nombreAula = lvAulas.getSelectionModel().getSelectedItem();

			aula = new Aula(Aula.getAulaFicticia(
					nombreAula.substring(nombreAula.indexOf("nombre=") + 7, nombreAula.lastIndexOf(", puestos="))));

		} catch (Exception e) {
			Dialogos.mostrarDialogoError(COMPROBAR_DISPONIBILIDAD, "No ha seleccionado ningún aula");
		}
		return aula;
	}

	@FXML
	void comprobarDisponibilidad(ActionEvent event) {

		Aula aula = getAula();
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
			if (controladorMVC.consultarDisponibilidad(aula, permanencia)) {

				/**
				 * He creado un dialogo especifico para comprobar la disponibilidad que recibe
				 * por parametro una imagen, y según la condición mostraremos una u otra para
				 * informar del estado de las respectivas aulas.
				 */

				Image image = new Image(
						(LocalizadorRecursos.class.getResourceAsStream("imagenes/iconoDisponible.png")));
				Dialogos.mostrarDialogoDisponibilidad(COMPROBAR_DISPONIBILIDAD,
						"DISPONIBLE: El aula se encuentra disponible para su reserva", image);
			} else {
				Image image = new Image((LocalizadorRecursos.class.getResourceAsStream("imagenes/noDisponible.png")));
				Dialogos.mostrarDialogoDisponibilidad(COMPROBAR_DISPONIBILIDAD,
						"NO DISPONIBLE: El aula no se encuentra disponible para su reserva", image);
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Buscar Reserva", e.getMessage());
		}
	}

	@FXML
	void salir(ActionEvent event) {
		((Stage) btSalir.getScene().getWindow()).close();
	}

}
