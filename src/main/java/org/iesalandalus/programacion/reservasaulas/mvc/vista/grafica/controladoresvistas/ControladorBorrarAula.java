package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladoresvistas;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.utilidades.Dialogos;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorBorrarAula {

	private static final String BORRAR_AULA = "Borrar Aula";
	private static final String ER_OBLIGATORIO = ".+";

	private IControlador controladorMVC;

	@FXML
	private TextField tfNombre;

	@FXML
	private Button btBorrar;
	@FXML
	private Button btCancelar;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@FXML
	private void initialize() {
		tfNombre.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfNombre));

	}

	@FXML
	private void borrarAula() {
		Reserva reserva = null;
		Aula aula = null;
		try {

			aula = getAula();
			/*
			 * No tiene sentido permitir borrar un aula que tiene asignada una o varias
			 * reservas, primero habria que borrar la reserva, y después el aula. Si debemos
			 * permitir borrar aquellas aulas aún teniendo reservas asociadas en el registro
			 * del sistema, sean reservas ya se hayan consumado, y poder tener un registro
			 * de ellas.
			 */
			List<Reserva> reservas = controladorMVC.getReservasAula(aula);
			for (Iterator<Reserva> it = reservas.iterator(); it.hasNext();) {
				reserva = it.next();
			}

			if (reservas.size() > 0 && reserva.getPermanencia().getDia().isAfter(LocalDate.now())) {
				Dialogos.mostrarDialogoError(BORRAR_AULA,
						"No se puede borrar un aula con reservas en curso. Debe cancelar primero la reserva.");
			} else {

				controladorMVC.borrarAula(aula);
				Stage propietario = ((Stage) btBorrar.getScene().getWindow());
				Dialogos.mostrarDialogoInformacion(BORRAR_AULA, "Aula borrada satisfactoriamente", propietario);
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(BORRAR_AULA, e.getMessage());
		}
	}

	@FXML
	private void cancelar() {
		((Stage) btCancelar.getScene().getWindow()).close();
	}

	public void inicializa() {
		tfNombre.setText("");
		compruebaCampoTexto(ER_OBLIGATORIO, tfNombre);

	}

	private void compruebaCampoTexto(String er, TextField campoTexto) {
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		} else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
	}

	private Aula getAula() {
		Aula aula = null;
		try {
			String nombre = tfNombre.getText();

			if (!nombre.equals("")) {

				aula = new Aula(Aula.getAulaFicticia(nombre));

			} else {
				Dialogos.mostrarDialogoError(BORRAR_AULA, "Debe introducir el nombre del aula");
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(BORRAR_AULA, e.getMessage());
		}
		return aula;
	}
}
