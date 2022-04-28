package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladoresvistas;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControladorListarProfesores {

	private IControlador controladorMVC;

	@FXML
	TableColumn<String, String> tcNombre;
	@FXML
	TableColumn<String, String> tcCorreo;
	@FXML
	TableColumn<String, String> tcTelefono;

	@FXML
	TableView<String> tvProfesores;
	@FXML
	Button btAceptar;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		tvProfesores.setItems(FXCollections.observableArrayList(controladorMVC.representarProfesores()));

	}

	@FXML
	private void initialize() {

		/**
		 * Al darme el método represartarProfesores para poder listarlos un lista de
		 * cadenas, tengo que extraer esos datos buscando los indices entre las cadenas.
		 * Nos valemos para ello de los métodos indexOf() e lastIndexOf()
		 */

		tcNombre.setCellValueFactory(nombre -> new SimpleStringProperty(
				nombre.getValue().substring(nombre.getValue().indexOf('=') + 1, nombre.getValue().indexOf(','))));

		/**
		 * Debemos controlar que contienen las cadenas de las que vamos a extrar los
		 * datos, si contienen "teléfono" los indices de extraccion de los respectivos
		 * correos no pueden ser los mismos.
		 */

		tcCorreo.setCellValueFactory(correo -> {
			var aux = correo.getValue().toString();
			return aux.contains("teléfono")
					? new SimpleStringProperty(correo.getValue().substring(correo.getValue().indexOf("correo=") + 7,
							correo.getValue().lastIndexOf(", teléfono=")))
					: new SimpleStringProperty(correo.getValue().substring(correo.getValue().indexOf("correo=") + 7));

		});

		tcTelefono.setCellValueFactory(telefono -> {
			var aux = telefono.getValue().toString();
			return aux.contains("teléfono")
					? new SimpleStringProperty(
							telefono.getValue().substring(telefono.getValue().lastIndexOf("teléfono=") + 9))
					: null;

		});

	}

	@FXML
	private void aceptar() {
		Stage escena = (Stage) btAceptar.getScene().getWindow();
		escena.close();
	}

}
