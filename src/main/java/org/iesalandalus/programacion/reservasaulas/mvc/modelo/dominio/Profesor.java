package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringTokenizer;

public class Profesor implements Serializable {

	private static final String ER_TELEFONO = "(6|9|7)[0-9]{8}";
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}";

	private String nombre;
	private String correo;
	private String telefono;

	public Profesor(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
	}

	public Profesor(String nombre, String correo, String telefono) {
		setNombre(nombre);
		setCorreo(correo);
		setTelefono(telefono);
	}

	public Profesor(Profesor p) {
		if (p == null) {
			throw new NullPointerException("ERROR: No se puede copiar un profesor nulo.");
		}
		setNombre(p.getNombre());
		setCorreo(p.getCorreo());
		setTelefono(p.getTelefono());
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
		}
		if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El nombre del profesor no puede estar vacío.");
		}
		// Utilizamos el método formateaNombre para establecer el nombre
		this.nombre = formateaNombre(nombre);
	}

	private String formateaNombre(String nombre) {

		// Paso 1 :Paso: Creamos un objeto del método StringTokenizer
		StringTokenizer st = new StringTokenizer(nombre);
		/*
		 * Paso 2: Contamos la cantidad de palabras que tiene la variable que contenga
		 * el texto y la guardamos en una variable entera.
		 */

		int cantidadPalabras = st.countTokens();
		// Paso 3: Creamos un ciclo for que corra por cada una de las palabras.
		String nombreCompleto = " ";
		for (int i = 0; i < cantidadPalabras; i++) {
			String PalabraIndividual = st.nextToken();
			/*
			 * Nombre formateado (+=) primera letra de cada palabra en mayuscula, mas resto
			 * de letras de cada palabra en miniscu
			 */

			nombreCompleto += PalabraIndividual.substring(0, 1).toUpperCase()
					+ PalabraIndividual.substring(1).toLowerCase() + " ";
		}
		nombre = nombreCompleto.trim();
		return nombre;
	}

	public void setCorreo(String correo) {
		if (correo == null) {
			throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");
		}
		if (!correo.matches(ER_CORREO)) {
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		}
		this.correo = correo;
	}

	public void setTelefono(String telefono) {
		if (telefono != null && !telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
		}
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public String getTelefono() {
		return telefono;
	}

	// Devuelve un profesor ficticio de un correo pasado por parámetro
	public static Profesor getProfesorFicticio(String correo) {
		return new Profesor("José Antonio Del Rey Martínez", correo);
	}

	// Dos profesores serán iguales si tienen el mismo correo
	@Override
	public int hashCode() {
		return Objects.hash(correo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Profesor))
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(correo, other.correo);
	}

	@Override
	public String toString() {
		String cadenaTelefono = (telefono == null) ? "" : ", teléfono=" + telefono;
		return "nombre=" + getNombre() + ", correo=" + getCorreo() + cadenaTelefono;
	}

}
