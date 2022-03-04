package org.iesalandalus.programacion.reservasaulas;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.IModelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.IVista;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;

/*
 * @Author: José Antonio Del Rey Martínez, IES AL-ÁNDALUS, ALMERÍA
 * GitHub: Janto7
 * Tarea Online 6
 */
public class MainApp {

	public static void main(String[] args) {
		// Nos devuelve un IFuenteDatos del tipo memoria.

		IModelo modelo = new Modelo(FactoriaFuenteDatos.MEMORIA.crear());
		IVista vista = new Vista();
		IControlador controlador = new Controlador(modelo, vista);
		// Capturamos las posibles excepciones del controlador.
		try {
			controlador.comenzar();
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

}