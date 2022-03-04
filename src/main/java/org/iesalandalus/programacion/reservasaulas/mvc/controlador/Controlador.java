package org.iesalandalus.programacion.reservasaulas.mvc.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.IModelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.IVista;

public class Controlador implements IControlador {
	// -----> vista (0,1)
	private IVista vista;
	// -----> modelo (0,1)
	private IModelo modelo;

	public Controlador(IModelo modelo, IVista vista) {
		if (modelo == null) {
			throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
		}
		if (vista == null) {
			throw new NullPointerException("ERROR: La vista no puede ser nula.");
		}
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
	}

	@Override
	public void comenzar() {
		vista.comenzar();
	}

	@Override
	public void terminar() {
		System.out.println("Hasta luego!!!");
	}

	@Override
	public void insertarAula(Aula aula) throws OperationNotSupportedException {
		modelo.insertar(aula);
	}

	@Override
	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.insertar(profesor);
	}

	@Override
	public void borrarAula(Aula aula) throws OperationNotSupportedException {
		modelo.borrar(aula);
	}

	@Override
	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.borrar(profesor);
	}

	@Override
	public Aula buscarAula(Aula aula) {
		return modelo.buscar(aula);
	}

	@Override
	public Profesor buscarProfesor(Profesor profesor) {
		return modelo.buscar(profesor);
	}

	@Override
	public List<String> representarAulas() {
		return modelo.representarAulas();
	}

	@Override
	public List<String> representarProfesores() {
		return modelo.representarProfesores();
	}

	@Override
	public List<String> representarReservas() {
		return modelo.representarReservas();
	}

	@Override
	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		modelo.realizarReserva(reserva);
	}

	@Override
	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
		modelo.anularReserva(reserva);
	}

	@Override
	public List<Reserva> getReservasAula(Aula aula) {
		return modelo.getReservasAulas(aula);
	}

	@Override
	public List<Reserva> getReservasProfesor(Profesor profesor) {
		return modelo.getReservasProfesor(profesor);
	}

	@Override
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		return modelo.getReservasPermanencia(permanencia);
	}

	@Override
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return modelo.consultarDisponibilidad(aula, permanencia);
	}
}
