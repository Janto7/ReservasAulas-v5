package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladoresvistas;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorBorrarProfesor {

	private static final String BORRAR_PROFESOR = "Borrar Profesor";
	private static final String ER_OBLIGATORIO = ".+";
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}";

	private IControlador controladorMVC;

	@FXML
	private TextField tfCorreo;

	@FXML
	private Button btBorrar;
	@FXML
	private Button btCancelar;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@FXML
	private void initialize() {
		tfCorreo.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfCorreo));

	}

	@FXML
	private void borrarProfesor() {
		Profesor profesor = null;

		try {
			profesor = getProfesor();

			controladorMVC.borrarProfesor(profesor);
			Stage propietario = ((Stage) btBorrar.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion(BORRAR_PROFESOR, "Profesor borrado satisfactoriamente", propietario);

		} catch (Exception e) {
			Dialogos.mostrarDialogoError(BORRAR_PROFESOR, e.getMessage());
		}
	}

	@FXML
	private void cancelar() {
		((Stage) btCancelar.getScene().getWindow()).close();
	}

	public void inicializa() {
		tfCorreo.setText("");
		compruebaCampoTexto(ER_OBLIGATORIO, tfCorreo);
		compruebaCampoTexto(ER_CORREO, tfCorreo);

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
			String correo = tfCorreo.getText();

			profesor = new Profesor(Profesor.getProfesorFicticio(correo));
		} catch (NumberFormatException e) {
			Dialogos.mostrarDialogoError(BORRAR_PROFESOR, e.getMessage());
		}
		return profesor;
	}
}
