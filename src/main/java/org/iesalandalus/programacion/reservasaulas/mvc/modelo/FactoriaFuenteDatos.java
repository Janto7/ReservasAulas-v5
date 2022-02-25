package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria.FactoriaFuenteDatosMemoria;

public enum FactoriaFuenteDatos {

	/*
	 * Nos devuelve una interfaz fuente de datos que es capaz de crear aulas,
	 * profesores y reservas de memoria.
	 */

	MEMORIA {
		public IFuenteDatos crear() {
			return new FactoriaFuenteDatosMemoria();
		}
	};

	public abstract IFuenteDatos crear();

}
