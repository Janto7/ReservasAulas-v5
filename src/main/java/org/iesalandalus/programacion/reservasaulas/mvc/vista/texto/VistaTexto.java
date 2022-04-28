package org.iesalandalus.programacion.reservasaulas.mvc.vista.texto;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.IVista;

public class VistaTexto implements IVista {

	private static final String ERROR = "ERROR: ";
	private static final String NOMBRE_VALIDO = "Nombre válido, el nombre está registrado en el sistema.";
	private static final String CORREO_VALIDO = "Correo válido, el correo está registrado en el sistema.";

	private IControlador controlador;

	public VistaTexto() {
		Opcion.setVista(this);
	}

	@Override
	public void setControlador(IControlador controlador) {
		this.controlador = controlador;
	}

	@Override
	public void comenzar() {
		Consola.mostrarCabecera("Gestión de las Reservas de Aulas del IES Al-Ándalus");
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOridnal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	@Override
	public void salir() {
		controlador.terminar();
	}

	public void insertarAula() {
		Consola.mostrarCabecera("Insertar Aula");
		try {
			controlador.insertarAula(Consola.leerAula());
			System.out.println("Aula insertada correctamente.");
			/*
			 * Capturamos las excepciones de la clase Aula y las del método insertar.
			 */
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAula() {
		Consola.mostrarCabecera("Borrar Aula");
		Reserva reserva = null;

		try {

			Aula aula = Consola.leerAulaFicticia();
			/*
			 * No tiene sentido permitir borrar un aula que tiene asignada una o varias
			 * reservas, primero habria que borrar la reserva, y después el aula. Si debemos
			 * permitir borrar aquellas aulas aún teniendo reservas asociadas en el registro
			 * del sistema, sean reservas ya se hayan consumado, y poder tener un registro
			 * de ellas.
			 */
			List<Reserva> reservas = controlador.getReservasAula(aula);
			for (Iterator<Reserva> it = reservas.iterator(); it.hasNext();) {
				reserva = it.next();
			}

			if (reservas.size() > 0 && reserva.getPermanencia().getDia().isAfter(LocalDate.now())) {
				System.out.println("No se puede borrar un aula con reservas en curso.");
			} else {

				controlador.borrarAula(aula);
				System.out.println("Aula borrada correctamente.");
			}

		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarAula() {
		Consola.mostrarCabecera("Buscar Aula");
		Aula aula;
		try {
			aula = controlador.buscarAula(Consola.leerAulaFicticia());
			String mensaje = (aula != null) ? aula.toString() : "Aula no registrada en el sistema.";
			System.out.println(mensaje);
			/*
			 * Capturamos las excepciones de la clase Aula y la del método buscar.
			 */
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAulas() {

		Consola.mostrarCabecera("Listado de Aulas");

		List<String> aulas = controlador.representarAulas();
		// Comprobamos hay elementos en nuestra lista comprobando su tamaño
		if (aulas.size() > 0) {
			for (Iterator<String> it = aulas.iterator(); it.hasNext();) {
				String aula = it.next();
				System.out.println(aula);
			}

		} else {
			System.out.println(ERROR + "No hay aulas que listar. Debe insertar primero un aula en el sistema.");
		}

	}

	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar Profesor");
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor insertado correctamente.");
			/*
			 * Capturamos las excepciones de la clase Profesor y las del método insertar.
			 */
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor");
		Reserva reserva = null;
		try {

			Profesor profesor = Consola.leerProfesorFicticio();
			/*
			 * No tiene sentido permitir borrar un profesor que tiene asignada una o varias
			 * reservas, primero habria que borrar la reserva, y después el profesor. Si
			 * debemos permitir borrar aquellos profesores aún teniendo reservas asociadas
			 * en el registro del sistema, sean reservas ya se hayan consumado, y poder
			 * tener un registro de ellas.
			 */
			List<Reserva> reservas = controlador.getReservasProfesor(profesor);
			for (Iterator<Reserva> it = reservas.iterator(); it.hasNext();) {
				reserva = it.next();
			}

			if (reservas.size() > 0 && reserva.getPermanencia().getDia().isAfter(LocalDate.now())) {
				System.out.println("No se puede borrar un profesor con reservas en curso.");
			} else {

				controlador.borrarProfesor(profesor);
				System.out.println("Profesor borrado correctamente.");
			}

		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar Profesor");
		Profesor profesor;
		try {
			profesor = controlador.buscarProfesor(Consola.leerProfesorFicticio());
			String mensaje = (profesor != null) ? profesor.toString()
					: ERROR + "El profesor no está registrado en el sistema.";
			System.out.println(mensaje);
			/*
			 * Capturamos las excepciones de la clase Profesor y la del método buscar.
			 */
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarProfesores() {
		Consola.mostrarCabecera("Listado de Profesores");

		List<String> profesores = controlador.representarProfesores();
		// Comprobamos hay elementos en nuestra lista comprobando su tamaño
		if (profesores.size() > 0) {
			for (Iterator<String> it = profesores.iterator(); it.hasNext();) {
				String profesor = it.next();
				System.out.println(profesor);
			}
		} else {
			System.out
					.println(ERROR + "No hay profesores que listar. Debe insertar primero un profesor en el sistema.");
		}
	}

	public void realizarReserva() {

		Consola.mostrarCabecera("Realizar Reserva");

		try {
			Profesor profesor = Consola.leerProfesorFicticio();
			Profesor profesorRegistrado = controlador.buscarProfesor(profesor);

			if (profesorRegistrado != null) {

				Aula aula = Consola.leerAulaFicticia();
				Aula aulaRegistrada = controlador.buscarAula(aula);

				if (aulaRegistrada != null) {

					Permanencia permanencia = Consola.leerPermanencia();
					Reserva reserva = new Reserva(profesorRegistrado, aulaRegistrada, permanencia);

					controlador.realizarReserva(reserva);

					System.out.println("Reserva realizada correctamente.\n" + NOMBRE_VALIDO + "\n" + CORREO_VALIDO);
				} else {
					System.out.println(ERROR + "El aula " + aula.getNombre() + ", no está registrada en el sistema.");
				}
			} else {
				System.out.println(ERROR + "El correo " + profesor.getCorreo() + ", no está registrado en el sistema.");
			}

		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void anularReserva() {

		Consola.mostrarCabecera("Anular Reserva");

		try {

			controlador.anularReserva(Consola.leerReservaFicticia());
			System.out.println("Reserva anulada correctamente.");
			// Capturamos todas las posibles excepciones al anunlar la reserva
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarReservas() {

		Consola.mostrarCabecera("Listado de Reservas");

		List<String> reservas = controlador.representarReservas();
		// Comprobamos hay elementos en nuestra lista comprobando su tamaño
		if (reservas.size() > 0) {
			for (Iterator<String> it = reservas.iterator(); it.hasNext();) {
				String reserva = it.next();
				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas que listar. Debe insertar primero una reserva.");
		}
	}

	public void listarReservasAula() {
		Consola.mostrarCabecera("Listado de Reservas por Aula");
		List<Reserva> reservas = controlador.getReservasAula(Consola.leerAulaFicticia());
		if (reservas.size() > 0) {
			for (Iterator<Reserva> it = reservas.iterator(); it.hasNext();) {
				Reserva reserva = it.next();

				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas, para dicha aula.");
		}
	}

	public void listarReservasProfesor() {
		Consola.mostrarCabecera("Listado de Reservas por Profesor");
		List<Reserva> reservas = controlador.getReservasProfesor(Consola.leerProfesorFicticio());
		if (reservas.size() > 0) {
			for (Iterator<Reserva> it = reservas.iterator(); it.hasNext();) {
				Reserva reserva = it.next();

				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas, para dicho profesor.");
		}
	}

	public void consultarDisponibilidad() {

		try {
			Aula aula = Consola.leerAulaFicticia();
			Aula aulaRegistrada = controlador.buscarAula(aula);

			if (aulaRegistrada != null) {

				Permanencia permanencia = Consola.leerPermanencia();

				if (controlador.consultarDisponibilidad(aula, permanencia)) {
					System.out
							.println("Disponible el aula " + aula.getNombre() + ", para la permanencia " + permanencia);

				} else {
					System.out.println(
							"No disponible el aula " + aula.getNombre() + ", para la permanencia " + permanencia);
				}
			} else {
				System.out.println(ERROR + "El aula " + aula.getNombre() + " no está registrada en el sistema.");
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}

	}

}
