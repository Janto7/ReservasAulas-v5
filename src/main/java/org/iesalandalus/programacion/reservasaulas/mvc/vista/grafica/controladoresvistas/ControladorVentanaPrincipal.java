package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladoresvistas;


import java.io.IOException;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.utilidades.Dialogos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorVentanaPrincipal {

	private IControlador controladorMVC;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@FXML
	private MenuItem bSalir;
	@FXML
	private MenuItem bAcercaDe;

	@FXML
	private Button btListarAulas;
	@FXML
	private Button btAnadirAula;
	@FXML
	private Button btBorrarAula;
	@FXML
	private Button btListarReservasAula;
	@FXML
	private Button btComprobarDisponibilidad;

	private Stage listarAulas;
	private ControladorListarAulas cListarAulas;

	private Stage anadirAula;
	private ControladorAnadirAula cAnadirAula;

	private Stage borrarAula;
	private ControladorBorrarAula cBorrarAula;

	private Stage listarReservasAula;
	private ControladorListarReservasAula cListarReservasAula;

	private Stage comprobarDisponibilidad;
	private ControladorComprobarDisponibilidad cComprobarDisponibilidad;

	private Stage listarProfesores;
	private ControladorListarProfesores cListarProfesores;

	private Stage anadirProfesor;
	private ControladorAnadirProfesor cAnadirProfesor;

	private Stage borrarProfesor;
	private ControladorBorrarProfesor cBorrarProfesor;

	private Stage listarReservasProfesor;
	private ControladorListarReservasProfesor cListarReservasProfesor;

	private Stage listarReservas;
	private ControladorListarReservas cListarReservas;

	private Stage anadirReserva;
	private ControladorAnadirReserva cAnadirReserva;
	
	private Stage borrarReserva;
	private ControladorBorrarReserva cBorrarReserva;

	@FXML
	private void acercaDe(ActionEvent event) throws IOException {

		VBox contenido = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/AcercaDe.fxml"));

		Dialogos.mostrarDialogoInformacionPersonalizado("Reservas Aulas", contenido);
	}

	@FXML
	private void salir(ActionEvent event) {

		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro que desea salir de la aplicación?", null)) {

			controladorMVC.terminar();
			System.exit(0);
		}
	}

	@FXML
	private void listarAulas() throws IOException {
		crearListarAulas();
		listarAulas.showAndWait();
	}

	private void crearListarAulas() throws IOException {
		if (listarAulas == null) {
			listarAulas = new Stage();
			FXMLLoader cargadorListarAulas = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/ListarAulas.fxml"));
			VBox raizListarAulas = cargadorListarAulas.load();
			cListarAulas = cargadorListarAulas.getController();
			cListarAulas.setControladorMVC(controladorMVC);
			cListarAulas.inicializa();
			Scene escenaListarAulas = new Scene(raizListarAulas);
			listarAulas.setTitle("Listar Aulas");
			listarAulas.getIcons()
					.add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo-ies.png")));
			listarAulas.initModality(Modality.APPLICATION_MODAL);
			listarAulas.setScene(escenaListarAulas);
		} else {
			cListarAulas.inicializa();
		}

	}

	@FXML
	private void anadirAula() throws IOException {
		crearAnadirAula();
		anadirAula.showAndWait();
	}

	private void crearAnadirAula() throws IOException {
		if (anadirAula == null) {
			anadirAula = new Stage();
			FXMLLoader cargadorAnadirAula = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/AnadirAula.fxml"));
			VBox raizAnadirAula = cargadorAnadirAula.load();
			cAnadirAula = cargadorAnadirAula.getController();
			cAnadirAula.setControladorMVC(controladorMVC);
			cAnadirAula.inicializa();
			Scene escenaAnadirAula = new Scene(raizAnadirAula);
			anadirAula.setTitle("Añadir Aula");
			anadirAula.getIcons()
					.add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo-ies.png")));

			anadirAula.initModality(Modality.APPLICATION_MODAL);
			anadirAula.setScene(escenaAnadirAula);
			anadirAula.setResizable(false);
		} else {
			cAnadirAula.inicializa();
		}
	}

	@FXML
	private void borrarAula() throws IOException {
		crearBorrarAula();
		borrarAula.showAndWait();
	}

	private void crearBorrarAula() throws IOException {
		if (borrarAula == null) {
			borrarAula = new Stage();
			FXMLLoader cargadorBorrarAula = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/BorrarAula.fxml"));
			VBox raizBorrarAula = cargadorBorrarAula.load();
			cBorrarAula = cargadorBorrarAula.getController();
			cBorrarAula.setControladorMVC(controladorMVC);
			cBorrarAula.inicializa();
			Scene escenaBorrarAula = new Scene(raizBorrarAula);
			borrarAula.setTitle("Borrar Aula");
			borrarAula.getIcons()
					.add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo-ies.png")));

			borrarAula.initModality(Modality.APPLICATION_MODAL);
			borrarAula.setScene(escenaBorrarAula);
			borrarAula.setResizable(false);
		} else {
			cBorrarAula.inicializa();
		}
	}

	@FXML
	private void listarReservasAula() throws IOException {
		crearListarReservasAula();
		listarReservasAula.showAndWait();
	}

	private void crearListarReservasAula() throws IOException {
		if (listarReservasAula == null) {
			listarReservasAula = new Stage();
			FXMLLoader cargadorListarReservasAula = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/ReservasAula.fxml"));
			VBox raizListarReservasAula = cargadorListarReservasAula.load();
			cListarReservasAula = cargadorListarReservasAula.getController();
			cListarReservasAula.setControladorMVC(controladorMVC);
			cListarReservasAula.inicializa();
			Scene escenaListarReservasAula = new Scene(raizListarReservasAula);
			listarReservasAula.setTitle("Reservas Aula");
			listarReservasAula.getIcons()
					.add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo-ies.png")));
			listarReservasAula.initModality(Modality.APPLICATION_MODAL);
			listarReservasAula.setScene(escenaListarReservasAula);
		} else {
			cListarReservasAula.inicializa();
		}
	}

	@FXML
	private void comprobarDisponibilidad() throws IOException {
		crearComprobarDisponibilidad();
		comprobarDisponibilidad.showAndWait();
	}

	private void crearComprobarDisponibilidad() throws IOException {
		if (comprobarDisponibilidad == null) {
			comprobarDisponibilidad = new Stage();
			FXMLLoader cargadorComprobarDisponibilidad = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/ComprobarDisponibilidad.fxml"));
			VBox raizComprobarDisponibilidad = cargadorComprobarDisponibilidad.load();
			cComprobarDisponibilidad = cargadorComprobarDisponibilidad.getController();
			cComprobarDisponibilidad.setControladorMVC(controladorMVC);
			cComprobarDisponibilidad.inicializa();
			Scene escenaComprobarDisponibilidad = new Scene(raizComprobarDisponibilidad);
			comprobarDisponibilidad.setTitle("Comprobar Disponibilidad");
			comprobarDisponibilidad.getIcons()
					.add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo-ies.png")));

			comprobarDisponibilidad.initModality(Modality.APPLICATION_MODAL);
			comprobarDisponibilidad.setScene(escenaComprobarDisponibilidad);
			comprobarDisponibilidad.setResizable(false);
		} else {
			cComprobarDisponibilidad.inicializa();
		}
	}

	@FXML
	private void listarProfesores() throws IOException {
		crearListarProfesores();
		listarProfesores.showAndWait();
	}

	private void crearListarProfesores() throws IOException {
		if (listarProfesores == null) {
			listarProfesores = new Stage();
			FXMLLoader cargadorListarProfesores = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/ListarProfesores.fxml"));
			VBox raizListarProfesores = cargadorListarProfesores.load();
			cListarProfesores = cargadorListarProfesores.getController();
			cListarProfesores.setControladorMVC(controladorMVC);
			cListarProfesores.inicializa();
			Scene escenaListarProfesores = new Scene(raizListarProfesores);
			listarProfesores.setTitle("Listar Profesores");
			listarProfesores.getIcons()
					.add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo-ies.png")));
			listarProfesores.initModality(Modality.APPLICATION_MODAL);
			listarProfesores.setScene(escenaListarProfesores);
		} else {
			cListarProfesores.inicializa();
		}

	}

	@FXML
	private void anadirProfesor() throws IOException {
		crearAnadirProfesor();
		anadirProfesor.showAndWait();
	}

	private void crearAnadirProfesor() throws IOException {
		if (anadirProfesor == null) {
			anadirProfesor = new Stage();
			FXMLLoader cargadorAnadirProfesor = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/AnadirProfesor.fxml"));
			VBox raizAnadirProfesor = cargadorAnadirProfesor.load();
			cAnadirProfesor = cargadorAnadirProfesor.getController();
			cAnadirProfesor.setControladorMVC(controladorMVC);
			cAnadirProfesor.inicializa();
			Scene escenaAnadirProfesor = new Scene(raizAnadirProfesor);
			anadirProfesor.setTitle("Añadir Profesor");
			anadirProfesor.getIcons()
					.add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo-ies.png")));

			anadirProfesor.initModality(Modality.APPLICATION_MODAL);
			anadirProfesor.setScene(escenaAnadirProfesor);
			anadirProfesor.setResizable(false);
		} else {
			cAnadirProfesor.inicializa();
		}
	}

	@FXML
	private void borrarProfesor() throws IOException {
		crearBorrarProfesor();
		borrarProfesor.showAndWait();
	}

	private void crearBorrarProfesor() throws IOException {
		if (borrarProfesor == null) {
			borrarProfesor = new Stage();
			FXMLLoader cargadorBorrarProfesor = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/BorrarProfesor.fxml"));
			VBox raizBorrarProfesor = cargadorBorrarProfesor.load();
			cBorrarProfesor = cargadorBorrarProfesor.getController();
			cBorrarProfesor.setControladorMVC(controladorMVC);
			cBorrarProfesor.inicializa();
			Scene escenaBorrarProfesor = new Scene(raizBorrarProfesor);
			borrarProfesor.setTitle("Borrar Profesor");
			borrarProfesor.getIcons()
					.add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo-ies.png")));

			borrarProfesor.initModality(Modality.APPLICATION_MODAL);
			borrarProfesor.setScene(escenaBorrarProfesor);
			borrarProfesor.setResizable(false);
		} else {
			cBorrarProfesor.inicializa();
		}
	}

	@FXML
	private void listarReservasProfesor() throws IOException {
		crearListarReservasProfesor();
		listarReservasProfesor.showAndWait();
	}

	private void crearListarReservasProfesor() throws IOException {
		if (listarReservasProfesor == null) {
			listarReservasProfesor = new Stage();
			FXMLLoader cargadorListarReservasProfesor = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/ReservasProfesor.fxml"));
			VBox raizListarReservasProfesor = cargadorListarReservasProfesor.load();
			cListarReservasProfesor = cargadorListarReservasProfesor.getController();
			cListarReservasProfesor.setControladorMVC(controladorMVC);
			cListarReservasProfesor.inicializa();
			Scene escenaListarReservasProfesor = new Scene(raizListarReservasProfesor);
			listarReservasProfesor.setTitle("Reservas Profesor");
			listarReservasProfesor.getIcons()
					.add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo-ies.png")));

			listarReservasProfesor.initModality(Modality.APPLICATION_MODAL);
			listarReservasProfesor.setScene(escenaListarReservasProfesor);
		} else {
			cListarReservasProfesor.inicializa();
		}
	}

	@FXML
	private void listarReservas() throws IOException {
		crearListarReservas();
		listarReservas.showAndWait();
	}

	private void crearListarReservas() throws IOException {
		if (listarReservas == null) {
			listarReservas = new Stage();
			FXMLLoader cargadorListarReservas = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/ListarReservas.fxml"));
			VBox raizListarReservas = cargadorListarReservas.load();
			cListarReservas = cargadorListarReservas.getController();
			cListarReservas.setControladorMVC(controladorMVC);
			cListarReservas.inicializa();
			Scene escenaListarReservas = new Scene(raizListarReservas);
			listarReservas.setTitle("Listar Reservas");
			listarReservas.getIcons()
					.add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo-ies.png")));
			listarReservas.initModality(Modality.APPLICATION_MODAL);
			listarReservas.setScene(escenaListarReservas);
		} else {
			cListarReservas.inicializa();
		}

	}

	@FXML
	private void anadirReserva() throws IOException {
		crearAnadirReserva();
		anadirReserva.showAndWait();
	}

	private void crearAnadirReserva() throws IOException {
		if (anadirReserva == null) {
			anadirReserva = new Stage();
			FXMLLoader cargadorAnadirReserva = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/AnadirReserva.fxml"));
			VBox raizAnadirReserva = cargadorAnadirReserva.load();
			cAnadirReserva = cargadorAnadirReserva.getController();
			cAnadirReserva.setControladorMVC(controladorMVC);
			cAnadirReserva.inicializa();
			Scene escenaAnadirAula = new Scene(raizAnadirReserva);
			anadirReserva.setTitle("Añadir Reserva");
			anadirReserva.initModality(Modality.APPLICATION_MODAL);
			anadirReserva.getIcons()
					.add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo-ies.png")));
			anadirReserva.setScene(escenaAnadirAula);
			anadirReserva.setResizable(false);
		} else {
			cAnadirReserva.inicializa();
		}
	}
	
	@FXML
	private void borrarReserva() throws IOException {
		crearBorrarReserva();
		borrarReserva.showAndWait();
	}

	private void crearBorrarReserva() throws IOException {
		if (borrarReserva == null) {
			borrarReserva = new Stage();
			FXMLLoader cargadorBorrarReserva = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/BorrarReserva.fxml"));
			VBox raizBorrarReserva = cargadorBorrarReserva.load();
			cBorrarReserva = cargadorBorrarReserva.getController();
			cBorrarReserva.setControladorMVC(controladorMVC);
			cBorrarReserva.inicializa();
			Scene escenaBorrarReserva = new Scene(raizBorrarReserva);
			borrarReserva.setTitle("Borrar Reserva");
			borrarReserva.getIcons()
					.add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo-ies.png")));

			borrarReserva.initModality(Modality.APPLICATION_MODAL);
			borrarReserva.setScene(escenaBorrarReserva);
			borrarReserva.setResizable(false);
		} else {
			cBorrarReserva.inicializa();
		}
	}

}
