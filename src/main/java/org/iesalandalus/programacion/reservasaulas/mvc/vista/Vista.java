package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.util.Iterator;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Vista implements IVista {

	private static final String ERROR = "ERROR: ";
	private static final String NOMBRE_VALIDO = "Nombre válido, el profesor está registrado en el sistema.";
	private static final String CORREO_VALIDO = "Correo válido, el correo está registrado en el sistema.";

	private IControlador controlador;

	public Vista() {
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

	@Override
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

	@Override
	public void borrarAula() {
		Consola.mostrarCabecera("Borrar Aula");
		try {
			controlador.borrarAula(Consola.leerAulaFicticia());
			System.out.println("Aula borrada correctamente.");
			/*
			 * Capturamos las excepciones de la clase Aula y las del método borrar.
			 */
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
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

	@Override
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
			System.out.println(ERROR + "No hay aulas que listar.Debe insertar primero un aula en el sistema.");
		}

	}

	@Override
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

	@Override
	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor");
		try {
			controlador.borrarProfesor(Consola.leerProfesorFicticio());
			System.out.println("Profesor borrado correctamente.");
			/*
			 * Capturamos las excepciones de la clase Profesor y las del método insertar.
			 */
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
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

	@Override
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

	@Override
	public void realizarReserva() {

		Consola.mostrarCabecera("Realizar Reserva");

		Reserva reserva = null;
		Profesor profesorConTelefono = null;
		Profesor profesorSinTelefono = null;

		List<String> profesores = controlador.representarProfesores();
		List<String> aulas = controlador.representarAulas();
		String datosProfesores = null;
		String datosAulas = null;
		String datosProfesor = null;
		String nombreProfesor = null;

		String telefonoProfesor = null;

		boolean aulaRegistrada = false;
		boolean profesorRegistrado = false;
		boolean telefonoRegistrado = false;

		try {

			reserva = Consola.leerReserva();

			for (Iterator<String> it = profesores.iterator(); it.hasNext();) {
				datosProfesores = it.next();

				/*
				 * Comparo el correo introducido por teclado, con el toString de profesores,
				 * valiendome de los métodos indexof(), que me extraen la cadena exacta necesito
				 * comparar.
				 */
				if (datosProfesores.contains(reserva.getProfesor().getCorreo())) {
					profesorRegistrado = true;
					datosProfesor = datosProfesores;

					/*
					 * Obtengo el nombre del profesor valiendome de los métodos indexOf() y
					 * lastIndexOf(), que me extraen la cadena exacta del nombre del profesor, y lo
					 * utilizamos para reemplazar el nombre del profesor ficticio, pues el enunciado
					 * nos indica el meétodo leerReserva() debe devolver la reserva a partir de un
					 * profesor ficticio, pero de esa forma siempre obtenemos el mismo nombre al
					 * listar reservas.
					 *
					 */
					nombreProfesor = reserva.getProfesor().getNombre().replace("José Antonio Del Rey Martínez",
							datosProfesor.substring(datosProfesor.indexOf('=') + 1, datosProfesor.indexOf(',')));

				}

			}

			if (datosProfesor != null) {

				if (datosProfesor.contains("teléfono=")) {
					telefonoRegistrado = true;

				}

				if (telefonoRegistrado) {

					/*
					 * Obtengo el teléfono del profesor valiendome del método lastIndexOf, que me
					 * extraen la cadena exacta del telefono del profesor.
					 *
					 */

					telefonoProfesor = datosProfesor.substring(datosProfesor.lastIndexOf('=') + 1);
					/*
					 * Si el profesor tiene un teléfono asignado usamos el constructor
					 * Profesor(nombre, correo, teléfono).
					 */
					profesorConTelefono = new Profesor(nombreProfesor, reserva.getProfesor().getCorreo(),
							telefonoProfesor);

					reserva = new Reserva(profesorConTelefono, reserva.getAula(), reserva.getPermanencia());

				} else {
					/*
					 * Si el profesor no tiene un teléfono asignado usamos el constructor
					 * Profesor(nombre, correo).
					 */

					profesorSinTelefono = new Profesor(nombreProfesor, reserva.getProfesor().getCorreo());
					reserva = new Reserva(profesorSinTelefono, reserva.getAula(), reserva.getPermanencia());

				}
			}

			/*
			 * Repito el mismo proceso para validar el aula existe en el sistema, esta vez
			 * valiendome del método replace() para quedarme con la cadena deseada.
			 */
			for (Iterator<String> it = aulas.iterator(); it.hasNext();) {
				datosAulas = it.next();
				if (reserva.getAula().getNombre().equalsIgnoreCase(
						datosAulas.substring(datosAulas.indexOf('=') + 1, datosAulas.lastIndexOf(',')))) {
					aulaRegistrada = true;

				}
			}

			if (!profesorRegistrado) {

				System.out.println(ERROR + "No se ha podido encontrar el profesor en el sistema.");
			}

			if (!aulaRegistrada) {
				System.out.println(ERROR + "No se ha podido encontrar el aula en el sistema.");

			} else if (profesorRegistrado && aulaRegistrada) {

				controlador.realizarReserva(reserva);
				System.out.println("Reserva realizada correctamente.\n" + NOMBRE_VALIDO + "\n" + CORREO_VALIDO);

			}

		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
	public void consultarDisponibilidad() {

		Consola.mostrarCabecera("Consultar Disponibilidad");
		Aula nombreAula;
		String nombresAulas;
		List<String> aulas = controlador.representarAulas();

		boolean aulaRegistrada = false;

		try {

			nombreAula = Consola.leerAulaFicticia();

			for (Iterator<String> it = aulas.iterator(); it.hasNext();) {
				nombresAulas = it.next();
				/*
				 * Comparo el nombre del aula este registrado en el sistema, utilizo el método
				 * replace para conseguir la cadena deseada.
				 */
				if (nombreAula.getNombre().equalsIgnoreCase(
						nombresAulas.substring(nombresAulas.indexOf('=') + 1, nombresAulas.lastIndexOf(',')))) {
					aulaRegistrada = true;

					Aula aulaAConsultar = new Aula(nombreAula);

					Permanencia permanencia = Consola.leerPermanencia();

					if (controlador.consultarDisponibilidad(aulaAConsultar, permanencia) == true) {
						System.out.println("Disponible el aula " + nombreAula + " para la permanencia " + permanencia);
					} else {
						System.out
								.println("No Disponible el aula " + nombreAula + " para la permanencia " + permanencia);
					}

				}
			}

			if (!aulaRegistrada) {
				System.out.println(ERROR + "No está registrada el aula " + nombreAula + " en el sistema.");

			}

		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}

	}
}
