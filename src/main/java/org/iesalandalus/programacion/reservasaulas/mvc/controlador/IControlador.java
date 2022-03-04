package org.iesalandalus.programacion.reservasaulas.mvc.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public interface IControlador {

	void comenzar();

	void terminar();

	void insertarAula(Aula aula) throws OperationNotSupportedException;

	void insertarProfesor(Profesor profesor) throws OperationNotSupportedException;

	void borrarAula(Aula aula) throws OperationNotSupportedException;

	void borrarProfesor(Profesor profesor) throws OperationNotSupportedException;

	Aula buscarAula(Aula aula);

	Profesor buscarProfesor(Profesor profesor);

	List<String> representarAulas();

	List<String> representarProfesores();

	List<String> representarReservas();

	void realizarReserva(Reserva reserva) throws OperationNotSupportedException;

	void anularReserva(Reserva reserva) throws OperationNotSupportedException;

	List<Reserva> getReservasAula(Aula aula);

	List<Reserva> getReservasProfesor(Profesor profesor);

	List<Reserva> getReservasPermanencia(Permanencia permanencia);

	boolean consultarDisponibilidad(Aula aula, Permanencia permanencia);

}