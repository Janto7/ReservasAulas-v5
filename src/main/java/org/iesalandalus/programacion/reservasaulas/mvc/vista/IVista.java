package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;

public interface IVista {

	void setControlador(IControlador controlador);

	void comenzar();

	void salir();

	void insertarAula();

	void borrarAula();

	void buscarAula();

	void listarAulas();

	void insertarProfesor();

	void borrarProfesor();

	void buscarProfesor();

	void listarProfesores();

	void realizarReserva();

	void anularReserva();

	void listarReservas();

	void listarReservasAula();

	void listarReservasProfesor();

	void consultarDisponibilidad();

}