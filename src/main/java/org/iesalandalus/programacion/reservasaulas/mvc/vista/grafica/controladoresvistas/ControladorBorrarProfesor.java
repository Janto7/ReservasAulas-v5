package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladoresvistas;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
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
		Reserva reserva = null;
		try {
			profesor = getProfesor();

			/*
			 * No tiene sentido permitir borrar un profesor que tiene asignada una o varias
			 * reservas, primero habria que borrar la reserva, y después el profesor. Si
			 * debemos permitir borrar aquellos profesores aún teniendo reservas asociadas
			 * en el registro del sistema, sean reservas ya se hayan consumado, y poder
			 * tener un registro de ellas.
			 */

			List<Reserva> reservas = controladorMVC.getReservasProfesor(profesor);
			for (Iterator<Reserva> it = reservas.iterator(); it.hasNext();) {
				reserva = it.next();
			}

			if (reservas.size() > 0 && reserva.getPermanencia().getDia().isAfter(LocalDate.now())) {
				Dialogos.mostrarDialogoError(BORRAR_PROFESOR,
						"No se puede borrar un profesor con reservas en curso. Debe cancelar primero la reserva.");
			} else {

				controladorMVC.borrarProfesor(profesor);
				Stage propietario = ((Stage) btBorrar.getScene().getWindow());
				Dialogos.mostrarDialogoInformacion(BORRAR_PROFESOR, "Profesor borrado satisfactoriamente", propietario);
			}
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
