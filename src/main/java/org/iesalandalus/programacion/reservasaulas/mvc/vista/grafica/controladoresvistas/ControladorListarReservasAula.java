package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladoresvistas;

import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.utilidades.Dialogos;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControladorListarReservasAula {

	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private static final String RESERVAS_AULA = "Reservas Aula";

	private IControlador controladorMVC;

	private ObservableList<String> aulas = FXCollections.observableArrayList();

	@FXML
	private ListView<String> lvAulas;

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

	private class CeldaAula extends ListCell<String> {
		@Override
		public void updateItem(String string, boolean vacia) {
			super.updateItem(string, vacia);
			if (vacia) {
				setText("");
			} else {
				setText(string.substring(string.indexOf("nombre=") + 7, string.indexOf(", puestos=")));
			}
		}
	}

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@FXML
	private void initialize() {

		lvAulas.setItems(aulas);
		lvAulas.setCellFactory(l -> new CeldaAula());

	}

	@FXML
	private void listarReservasAula() {

		try {

			tvReservas.setItems(FXCollections.observableArrayList(controladorMVC.getReservasAula(getAula())));
			ObservableList<Reserva> list = tvReservas.getItems();

			// Si existen reservas para el aula establecemos el valor de sus columnas.

			if (list.size() > 0) {

				tcProfesorNombre.setCellValueFactory(
						reserva -> new SimpleStringProperty(reserva.getValue().getProfesor().getNombre()));
				tcProfesorCorreo.setCellValueFactory(
						reserva -> new SimpleStringProperty(reserva.getValue().getProfesor().getCorreo()));
				tcProfesorTelefono.setCellValueFactory(
						reserva -> new SimpleStringProperty(reserva.getValue().getProfesor().getTelefono()));
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

				// De lo contrario informamos por pantalla no existen reservas para ese aula.

				Dialogos.mostrarDialogoError(RESERVAS_AULA,
						"No existen reservas registrada en el sistema para el aula introducida.");

			}

		} catch (Exception e) {
			Dialogos.mostrarDialogoError(RESERVAS_AULA, e.getMessage());
		}
	}

	@FXML
	private void salir() {
		Stage escena = (Stage) btSalir.getScene().getWindow();
		escena.close();
	}

	public void inicializa() {
		aulas.setAll(FXCollections.observableArrayList(controladorMVC.representarAulas()));

	}

	private Aula getAula() {

		Aula aula = null;

		// Establecemos el aula de nuestra ListView y obtenemos su valor con el método
		// getSelectionModel()

		try {

			// Al listarnos una lista de cadenas debemos extraer su nombre extrayendo la
			// cadena exacta.

			String nombreAula = lvAulas.getSelectionModel().getSelectedItem();

			aula = new Aula(Aula.getAulaFicticia(
					nombreAula.substring(nombreAula.indexOf("nombre=") + 7, nombreAula.lastIndexOf(", puestos="))));
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(RESERVAS_AULA, "No ha seleccionado ningún aula");
		}
		return aula;
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
