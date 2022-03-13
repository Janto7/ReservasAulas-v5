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
 * Tarea Online 8
 */
public class MainApp {

	public static void main(String[] args) {

		IModelo modelo = null;

		for (String argumento : args) {
			if (argumento.equalsIgnoreCase("-fdficheros")) {
				modelo = new Modelo(FactoriaFuenteDatos.FICHEROS.crear());
			} else if (argumento.equalsIgnoreCase("-fdmemoria")) {
				modelo = new Modelo(FactoriaFuenteDatos.MEMORIA.crear());

			}
		}
		IVista vista = new Vista();
		IControlador controlador = new Controlador(modelo, vista);
		controlador.comenzar();

	}
}