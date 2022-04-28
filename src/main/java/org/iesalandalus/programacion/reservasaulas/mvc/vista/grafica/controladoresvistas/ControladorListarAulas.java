package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladoresvistas;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControladorListarAulas {

	private IControlador controladorMVC;

	@FXML
	TableColumn<String, String> tcNombre;
	@FXML
	TableColumn<String, String> tcPuestos;
	@FXML
	TableView<String> tvAulas;
	@FXML
	Button btAceptar;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		tvAulas.setItems(FXCollections.observableArrayList(controladorMVC.representarAulas()));
	}

	/**
	 * Al darme el método represartarAulas para poder listarlas una lista de cadenas,
	 * tengo que extraer esos datos buscando los indices entre las cadenas. Nos
	 * valemos para ello de los métodos indexOf() e lastIndexOf()
	 */
	@FXML
	private void initialize() {
		tcNombre.setCellValueFactory(nombre -> new SimpleStringProperty(nombre.getValue()
				.substring(nombre.getValue().indexOf("nombre=") + 7, nombre.getValue().indexOf(", puestos="))));
		tcPuestos.setCellValueFactory(nombre -> new SimpleStringProperty(
				nombre.getValue().substring(nombre.getValue().lastIndexOf(", puestos=") + 10)));

	}

	@FXML
	private void aceptar() {
		Stage escena = (Stage) btAceptar.getScene().getWindow();
		escena.close();
	}

}
