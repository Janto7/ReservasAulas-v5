package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IAulas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IProfesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IReservas;

public class Modelo implements IModelo {

	// -----> profesores (0,1)
	private IProfesores profesores;
	// -----> aulas (0,1)
	private IAulas aulas;
	// -----> reservas (0,1)
	private IReservas reservas;

	// Le pasamos al constructor una interfaz IFuenteDatos
	public Modelo(IFuenteDatos fuenteDatos) {
		profesores = fuenteDatos.crearProfesores();
		aulas = fuenteDatos.crearAulas();
		reservas = fuenteDatos.crearReservas();
	}
	
	@Override
	public void comenzar() {
		aulas.comenzar();
		profesores.comenzar();
		reservas.comenzar();
	}

	@Override
	public void terminar() {
		aulas.terminar();
		profesores.terminar();
		reservas.terminar();
	}

	@Override
	public List<Aula> getAulas() {

		return aulas.getAulas();
	}

	@Override
	public int getNumAulas() {

		return aulas.getNumAulas();

	}

	@Override
	public List<String> representarAulas() {

		return aulas.representar();
	}

	@Override
	public Aula buscarAula(Aula aula) {

		return aulas.buscar(aula);
	}

	@Override
	public void insertarAula(Aula aula) throws OperationNotSupportedException {

		aulas.insertar(aula);
	}

	@Override
	public void borrarAula(Aula aula) throws OperationNotSupportedException {

		aulas.borrar(aula);
	}

	@Override
	public List<Profesor> getProfesores() {

		return profesores.getProfesores();
	}

	@Override
	public int getNumProfesores() {

		return profesores.getNumProfesores();

	}

	@Override
	public List<String> representarProfesores() {

		return profesores.representar();
	}

	@Override
	public Profesor buscarProfesor(Profesor profesor) {

		return profesores.buscar(profesor);
	}

	@Override
	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {

		profesores.insertar(profesor);
	}

	@Override
	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {

		profesores.borrar(profesor);
	}

	@Override
	public List<Reserva> getReservas() {

		return reservas.getReservas();
	}

	@Override
	public int getNumReservas() {

		return reservas.getNumReservas();

	}

	@Override
	public List<String> representarReservas() {

		return reservas.representar();
	}

	@Override
	public Reserva buscarReserva(Reserva reserva) {

		return reservas.buscar(reserva);
	}

	@Override
	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {

		reservas.insertar(reserva);

	}

	@Override
	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {

		reservas.borrar(reserva);
	}

	@Override
	public List<Reserva> getReservasAulas(Aula aula) {

		return reservas.getReservasAula(aula);
	}

	@Override
	public List<Reserva> getReservasProfesor(Profesor profesor) {

		return reservas.getReservasProfesor(profesor);

	}

	@Override
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {

		return reservas.getReservasPermanencia(permanencia);

	}

	@Override
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {

		return reservas.consultarDisponibilidad(aula, permanencia);
	}

}
