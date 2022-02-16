package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Aula {

	private static final float PUNTOS_POR_PUESTO = 0.5f;
	private static final int MIN_PUESTOS = 10;
	private static final int MAX_PUESTOS = 100;

	private String nombre;
	private int puestos;

	public Aula(String nombre, int puestos) {

		setNombre(nombre);
		setPuestos(puestos);
	}

	public Aula(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede copiar un aula nula.");
		}

		setNombre(aula.getNombre());
		setPuestos(aula.getPuestos());
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre del aula no puede ser nulo.");
		}

		if (nombre.trim().isEmpty()) {
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
		return puestos * PUNTOS_POR_PUESTO;
	}

	public static Aula getAulaFicticia(String nombre) {
		return new Aula(nombre, 7);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + ", puestos=" + puestos;
	}

}
