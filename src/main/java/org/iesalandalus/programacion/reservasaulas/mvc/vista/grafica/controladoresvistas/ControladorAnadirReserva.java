package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladoresvistas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
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

public class ControladorAnadirReserva {

	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}";

	private IControlador controladorMVC;
	private ObservableList<String> aulas = FXCollections.observableArrayList();
	private ObservableList<String> profesores = FXCollections.observableArrayList();

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
	Button btAceptar;
	@FXML
	Button btCancelar;
	@FXML
	private TextField tfCorreo;

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
		tfCorreo.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_CORREO, tfCorreo));
		lvAula.setItems(aulas);
		lvAula.setCellFactory(l -> new CeldaAula());
	}

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		aulas.setAll(FXCollections.observableArrayList(controladorMVC.representarAulas()));
		profesores.setAll(FXCollections.observableArrayList(controladorMVC.representarProfesores()));
		dpDia.setValue(null);
		rbHora.setSelected(false);
		rbTramo.setSelected(false);
		cbTramo.getSelectionModel().clearSelection();
		tfHora.setText("");
	}

	private void compruebaCampoTexto(String er, TextField campoTexto) {
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		} else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
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
	void realizarReserva(ActionEvent event) {
		String nombreAula = lvAula.getSelectionModel().getSelectedItem();
		Aula aula = null;
		Aula aulaRegistrada = null;
		try {
			aula = new Aula(Aula.getAulaFicticia(
					nombreAula.substring(nombreAula.indexOf("nombre=") + 7, nombreAula.lastIndexOf(", puestos="))));

			aulaRegistrada = controladorMVC.buscarAula(aula);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Realizar Reserva",
					"No ha seleccionado ningún aula. Debe seleccionar una de la lista.");
		}

		Profesor profesor = getProfesor();
		LocalDate dia = dpDia.getValue();
		RadioButton seleccionado = (RadioButton) tgHoraTramo.getSelectedToggle();
		try {

			if (profesor != null) {

				Permanencia permanencia;
				if (seleccionado == rbHora) {
					permanencia = new PermanenciaPorHora(dia,
							LocalTime.parse(tfHora.getText(), DateTimeFormatter.ofPattern("HH:mm")));
				} else {
					permanencia = new PermanenciaPorTramo(dia, cbTramo.getValue());
				}
				Reserva reserva = new Reserva(profesor, aulaRegistrada, permanencia);
				controladorMVC.realizarReserva(reserva);
				Stage propietario = ((Stage) btAceptar.getScene().getWindow());
				Dialogos.mostrarDialogoInformacion("Realizar Reserva", "Reserva realizada satisfactoriamente",
						propietario);
			} else {

				Dialogos.mostrarDialogoError("Profesor no encontrado", "No existe ningún profesor con ese correo");
			}

		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Realizar Reserva", e.getMessage());
		}
	}

	private Profesor getProfesor() {
		Profesor profesor = null;
		try {
			profesor = controladorMVC.buscarProfesor(Profesor.getProfesorFicticio(tfCorreo.getText()));

		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Correo no válido", e.getMessage());
		}

		return profesor;
	}

	@FXML
	void cancelar(ActionEvent event) {
		((Stage) btCancelar.getScene().getWindow()).close();
	}

}