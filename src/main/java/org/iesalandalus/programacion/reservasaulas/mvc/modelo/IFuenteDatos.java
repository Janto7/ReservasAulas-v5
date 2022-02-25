package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IAulas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IProfesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IReservas;

public interface IFuenteDatos {
	// Devuelve una instacia de la interfaz Aulas.
	IAulas crearAulas();

	// Devuelve una instancia de la interfaz Profesores.
	IProfesores crearProfesores();

	// Devuelve una instancia de la interfaz Reservas
	IReservas crearReservas();

}