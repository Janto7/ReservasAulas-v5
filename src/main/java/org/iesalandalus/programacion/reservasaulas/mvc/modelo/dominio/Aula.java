package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;

public class Aula implements Serializable {

	private static final float PUNTOS_POR_PUESTO = 0.5f;

	// El valor mínimo y máximo nos los proporciona el propio test.

	private static final int MIN_PUESTOS = 10;
	private static final int MAX_PUESTOS = 100;

	private String nombre;
	private int puestos;

	// Constructor
	public Aula(String nombre, int puestos) {
		setNombre(nombre);
		setPuestos(puestos);
	}

	// Constructor copia
	public Aula(Aula a) {
		if (a == null) {
			throw new NullPointerException("ERROR: No se puede copiar un aula nula.");
		}
		setNombre(a.getNombre());
		setPuestos(a.getPuestos());
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre del aula no puede ser nulo.");
		}
		if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El nombre del aula no puede estar vacío.");
		}
		this.nombre = nombre;
	}

	public int getPuestos() {
		return puestos;
	}

	private void setPuestos(int puestos) {
		if (puestos < MIN_PUESTOS || puestos > MAX_PUESTOS) {
			throw new IllegalArgumentException("ERROR: El número de puestos no es correcto.");
		}
		this.puestos = puestos;
	}

	public String getNombre() {
		return nombre;
	}

	public float getPuntos() {
		// Un aula restará 0,5 puntos por el número de puestos del aula.
		return puestos * PUNTOS_POR_PUESTO;
	}

	// Establezco un número de puestos ficticio con un valor válido(10-100)
	public static Aula getAulaFicticia(String nombre) {
		return new Aula(nombre, 10);
	}

	// Dos aulas serán iguales si tienen el mismo nombre
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Aula))
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return String.format("nombre=%s, puestos=%d", nombre, puestos);
	}

}
