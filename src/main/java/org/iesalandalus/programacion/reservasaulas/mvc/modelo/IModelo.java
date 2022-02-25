package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public interface IModelo {

	List<Aula> getAulas();

	int getNumAulas();

	List<String> representarAulas();

	Aula buscar(Aula aula);

	void insertar(Aula aula) throws OperationNotSupportedException;

	void borrar(Aula aula) throws OperationNotSupportedException;

	List<Profesor> getProfesores();

	int getNumProfesores();

	List<String> representarProfesores();

	Profesor buscar(Profesor profesor);

	void insertar(Profesor profesor) throws OperationNotSupportedException;

	void borrar(Profesor profesor) throws OperationNotSupportedException;

	List<Reserva> getReservas();

	int getNumReservas();

	List<String> representarReservas();

	Reserva buscar(Reserva reserva);

	void realizarReserva(Reserva reserva) throws OperationNotSupportedException;

	void anularReserva(Reserva reserva) throws OperationNotSupportedException;

	List<Reserva> getReservasAulas(Aula aula);

	List<Reserva> getReservasProfesor(Profesor profesor);

	List<Reserva> getReservasPermanencia(Permanencia permanencia);

	boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) throws OperationNotSupportedException;

}