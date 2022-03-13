package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria.FactoriaFuenteDatosMemoria;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.ficheros.FactoriaFuenteDatosFicheros;

public enum FactoriaFuenteDatos {

	/*
	 * Nos devuelve una interfaz fuente de datos que es capaz de crear aulas,
	 * profesores y reservas de memoria.
	 */

	MEMORIA {
		public IFuenteDatos crear() {
			return new FactoriaFuenteDatosMemoria();
		}
	},

	/*
	 * Nos devuelve una interfaz fuente de datos que es capaz de crear aulas,
	 * profesores y reservas de ficheros.
	 */

	FICHEROS {
		public IFuenteDatos crear() {
			return new FactoriaFuenteDatosFicheros();
		}
	};

	public abstract IFuenteDatos crear();

}
