package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public interface IModelo {

	void comenzar();

	void terminar();

	List<Aula> getAulas();

	int getNumAulas();

	List<String> representarAulas();

	Aula buscarAula(Aula aula);

	void insertarAula(Aula aula) throws OperationNotSupportedException;

	void borrarAula(Aula aula) throws OperationNotSupportedException;

	List<Profesor> getProfesores();

	int getNumProfesores();

	List<String> representarProfesores();

	Profesor buscarProfesor(Profesor profesor);

	void insertarProfesor(Profesor profesor) throws OperationNotSupportedException;

	void borrarProfesor(Profesor profesor) throws OperationNotSupportedException;

	List<Reserva> getReservas();

	int getNumReservas();

	List<String> representarReservas();

	Reserva buscarReserva(Reserva reserva);

	void realizarReserva(Reserva reserva) throws OperationNotSupportedException;

	void anularReserva(Reserva reserva) throws OperationNotSupportedException;

	List<Reserva> getReservasAulas(Aula aula);

	List<Reserva> getReservasProfesor(Profesor profesor);

	List<Reserva> getReservasPermanencia(Permanencia permanencia);

	boolean consultarDisponibilidad(Aula aula, Permanencia permanencia);

}