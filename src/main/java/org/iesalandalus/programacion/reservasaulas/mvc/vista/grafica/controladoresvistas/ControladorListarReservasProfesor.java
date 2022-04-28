package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladoresvistas;

import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.utilidades.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControladorListarReservasProfesor {

	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private static final String RESERVAS_PROFESOR = "Reservas Profesor";
	private static final String ER_OBLIGATORIO = ".+";

	private IControlador controladorMVC;

	@FXML
	private TextField tfCorreo;

	@FXML
	TableColumn<Reserva, String> tcProfesorNombre;
	@FXML
	TableColumn<Reserva, String> tcProfesorCorreo;
	@FXML
	TableColumn<Reserva, String> tcProfesorTelefono;
	@FXML
	TableColumn<Reserva, String> tcAulaNombre;
	@FXML
	TableColumn<Reserva, String> tcAulaPuestos;
	@FXML
	TableColumn<Reserva, String> tcPermanenciaDia;
	@FXML
	TableColumn<Reserva, String> tcPermanenciaTramo;
	@FXML
	TableColumn<Reserva, String> tcPuntos;
	@FXML
	TableView<Reserva> tvReservas;

	@FXML
	Button btBuscar;
	@FXML
	Button btSalir;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@FXML
	private void initialize() {

		tfCorreo.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfCorreo));

	}

	@FXML
	private void listarReservasProfesor() {

		try {

			Profesor profesor = getProfesor();
			if (profesor != null) {

				tvReservas
						.setItems(FXCollections.observableArrayList(controladorMVC.getReservasProfesor(getProfesor())));
				ObservableList<Reserva> list = tvReservas.getItems();

				if (list.size() > 0) {

					tcProfesorNombre.setCellValueFactory(reserva -> new SimpleStringProperty(
							reserva.getValue().getProfesor().getNombre()));
					tcProfesorCorreo.setCellValueFactory(reserva -> new SimpleStringProperty(
							reserva.getValue().getProfesor().getCorreo()));
					tcProfesorTelefono.setCellValueFactory(reserva -> new SimpleStringProperty(
							reserva.getValue().getProfesor().getTelefono()));
					tcAulaNombre.setCellValueFactory(
							reserva -> new SimpleStringProperty(reserva.getValue().getAula().getNombre()));
					tcAulaPuestos.setCellValueFactory(reserva -> new SimpleStringProperty(
							Integer.toString(reserva.getValue().getAula().getPuestos())));
					tcPermanenciaDia.setCellValueFactory(reserva -> new SimpleStringProperty(
							FORMATO_FECHA.format(reserva.getValue().getPermanencia().getDia())));
					tcPermanenciaTramo.setCellValueFactory(
							reserva -> new SimpleStringProperty(getPermanenciaString(reserva.getValue())));
					tcPuntos.setCellValueFactory(new PropertyValueFactory<>("puntos"));

				} else {

					Dialogos.mostrarDialogoError(RESERVAS_PROFESOR,
							"No existen reservas registradas en el sistema para el profesor introducido.");

				}

			} else {

				Dialogos.mostrarDialogoError("Profesor no encontrado", "No existe ningún profesor con ese correo");

			}

		} catch (Exception e) {

			Dialogos.mostrarDialogoError(RESERVAS_PROFESOR, e.getMessage());
		}
	}

	@FXML
	private void salir() {
		Stage escena = (Stage) btSalir.getScene().getWindow();
		escena.close();
	}

	public void inicializa() {

		tfCorreo.setText("");
		compruebaCampoTexto(ER_OBLIGATORIO, tfCorreo);

	}

	private void compruebaCampoTexto(String er, TextField campoTexto) {

		String texto = campoTexto.getText();

		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		} else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
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

	private String getPermanenciaString(Reserva reserva) {
		String horaOTramo;
		Permanencia permanencia = reserva.getPermanencia();
		if (permanencia instanceof PermanenciaPorHora) {
			horaOTramo = ((PermanenciaPorHora) permanencia).getHora().toString();
		} else {
			horaOTramo = ((PermanenciaPorTramo) permanencia).getTramo().toString();
		}
		return horaOTramo;
	}

}
