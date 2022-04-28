package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladoresvistas;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorAnadirProfesor {

	private static final String ER_OBLIGATORIO = ".+";
	private static final String ER_TELEFONO = "(6|9|7)[0-9]{8}";
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}";

	private IControlador controladorMVC;

	@FXML
	private TextField tfNombre;
	@FXML
	private TextField tfCorreo;
	@FXML
	private TextField tfTelefono;
	@FXML
	private Button btAnadir;
	@FXML
	private Button btCancelar;

	@FXML
	private void initialize() {
		tfNombre.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfNombre));
		tfCorreo.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_CORREO, tfCorreo));
		tfTelefono.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_TELEFONO, tfTelefono));
	}

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@FXML
	private void anadirProfesor() {
		Profesor profesor = null;
		try {
			profesor = getProfesor();
			controladorMVC.insertarProfesor(profesor);
			Stage propietario = ((Stage) btAnadir.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Profesor", "Profesor añadido satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Profesor", e.getMessage());
		}
	}

	@FXML
	private void cancelar() {
		((Stage) btCancelar.getScene().getWindow()).close();
	}

	public void inicializa() {
		tfNombre.setText("");
		compruebaCampoTexto(ER_OBLIGATORIO, tfNombre);
		tfCorreo.setText("");
		compruebaCampoTexto(ER_CORREO, tfCorreo);
		tfTelefono.setText("");
		compruebaCampoTexto(ER_TELEFONO, tfTelefono);
	}

	private void compruebaCampoTexto(String er, TextField campoTexto) {
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		} else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
	}

	// Obtenemos los datos de los respectivos TextField
	private Profesor getProfesor() {
		String nombre = tfNombre.getText();
		String correo = tfCorreo.getText();
		String telefono = tfTelefono.getText().isEmpty() ? null : tfTelefono.getText();
		return new Profesor(nombre, correo, telefono);
	}

}
