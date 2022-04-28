package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladoresvistas;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControladorListarReservas {

	private IControlador controladorMVC;

	@FXML
	TableColumn<String, String> tcProfesorNombre;
	@FXML
	TableColumn<String, String> tcProfesorCorreo;
	@FXML
	TableColumn<String, String> tcProfesorTelefono;
	@FXML
	TableColumn<String, String> tcAulaNombre;
	@FXML
	TableColumn<String, String> tcAulaPuestos;
	@FXML
	TableColumn<String, String> tcPermanenciaDia;
	@FXML
	TableColumn<String, String> tcPermanenciaTramoOHora;
	@FXML
	TableColumn<String, String> tcPuntos;

	@FXML
	TableView<String> tvReservas;
	@FXML
	Button btAceptar;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		tvReservas.setItems(FXCollections.observableArrayList(controladorMVC.representarReservas()));

	}

	@FXML
	private void initialize() {

		/**
		 * Al darme el método represartarReservas para poder listarlas una lista de
		 * cadenas, tengo que extraer esos datos buscando los indices entre las cadenas.
		 * Nos valemos para ello de los métodos indexOf() e lastIndexOf()
		 */

		tcProfesorNombre.setCellValueFactory(nombre -> new SimpleStringProperty(nombre.getValue()
				.substring(nombre.getValue().indexOf("nombre=") + 7, nombre.getValue().lastIndexOf(", correo="))));

		tcProfesorCorreo.setCellValueFactory(correo -> {
			var aux = correo.getValue().toString();
			return aux.contains(", teléfono=")
					? new SimpleStringProperty(correo.getValue().substring(correo.getValue().indexOf("correo=") + 7,
							correo.getValue().lastIndexOf(", teléfono=")))
					: new SimpleStringProperty(correo.getValue().substring(correo.getValue().indexOf("correo=") + 7,
							correo.getValue().lastIndexOf(", nombre=")));

		});

		tcProfesorTelefono.setCellValueFactory(telefono -> {
			var aux = telefono.getValue().toString();
			return aux.contains(", teléfono=")
					? new SimpleStringProperty(telefono.getValue().substring(
							telefono.getValue().indexOf("teléfono=") + 9, telefono.getValue().lastIndexOf(", nombre=")))
					: null;

		});

		tcAulaNombre.setCellValueFactory(nombre -> new SimpleStringProperty(nombre.getValue()
				.substring(nombre.getValue().lastIndexOf("nombre=") + 7, nombre.getValue().lastIndexOf(", puestos="))));

		tcAulaPuestos.setCellValueFactory(puestos -> new SimpleStringProperty(puestos.getValue()
				.substring(puestos.getValue().indexOf(", puestos=") + 10, puestos.getValue().lastIndexOf(", día="))));

		tcPermanenciaDia.setCellValueFactory(permanenciaDia -> {
			var aux = permanenciaDia.getValue().toString();
			return aux.contains(", tramo=")
					? new SimpleStringProperty(
							permanenciaDia.getValue().substring(permanenciaDia.getValue().indexOf("día=") + 4,
									permanenciaDia.getValue().lastIndexOf(", tramo=")))
					: new SimpleStringProperty(
							permanenciaDia.getValue().substring(permanenciaDia.getValue().indexOf("día=") + 4,
									permanenciaDia.getValue().lastIndexOf(", hora=")));

		});

		tcPermanenciaTramoOHora.setCellValueFactory(permanenciaTramoOHora -> {
			var aux = permanenciaTramoOHora.getValue().toString();
			return aux.contains(", tramo=")
					? new SimpleStringProperty(permanenciaTramoOHora.getValue().substring(
							permanenciaTramoOHora.getValue().indexOf("tramo=") + 6,
							permanenciaTramoOHora.getValue().lastIndexOf(", puntos=")))
					: new SimpleStringProperty(permanenciaTramoOHora.getValue().substring(
							permanenciaTramoOHora.getValue().indexOf("hora=") + 5,
							permanenciaTramoOHora.getValue().lastIndexOf(", puntos=")));

		});

		tcPuntos.setCellValueFactory(puntos -> new SimpleStringProperty(
				puntos.getValue().substring(puntos.getValue().indexOf("puntos=") + 7)));
	}

	@FXML
	private void salir() {
		Stage escena = (Stage) btAceptar.getScene().getWindow();
		escena.close();
	}

}
