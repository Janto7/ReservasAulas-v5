package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Reserva {
	// -----> profesor (0,1)
	private Profesor profesor;
	// -----> aula (0,1)
	private Aula aula;
	// -----> permanencia (0,1)
	private Permanencia permanencia;

	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {
		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);
	}

	public Reserva(Reserva r) {
		if (r == null) {
			throw new NullPointerException("ERROR: No se puede copiar una reserva nula.");
		}
		setProfesor(r.getProfesor());
		setAula(r.getAula());
		setPermanencia(r.getPermanencia());
	}

	private void setProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: La reserva debe estar a nombre de un profesor.");
		}
		this.profesor = new Profesor(profesor);
	}

	public Profesor getProfesor() {
		return profesor;
	}

	private void setAula(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: La reserva debe ser para un aula concreta.");
		}
		this.aula = new Aula(aula);
	}

	public Aula getAula() {
		return aula;
	}

	private void setPermanencia(Permanencia permanencia) {
		if (permanencia == null) {
			throw new NullPointerException("ERROR: La reserva se debe hacer para una permanencia concreta.");
		}
		// Con el operador instanceof comprobamos de que clase concreta es cada objeto
		if (permanencia instanceof PermanenciaPorTramo) {
			this.permanencia = new PermanenciaPorTramo((PermanenciaPorTramo) permanencia);
		} else if (permanencia instanceof PermanenciaPorHora) {
			this.permanencia = new PermanenciaPorHora((PermanenciaPorHora) permanencia);
		}
	}

	public Permanencia getPermanencia() {

		return permanencia;
	}

	/*
	 * A través de un aula y de una permanencia recibidas como parámetros, obtenemos
	 * un profesor ficticio y nos devuelve una reserva.
	 */
	public static Reserva getReservaFicticia(Aula aula, Permanencia permanencia) {
		return new Reserva(Profesor.getProfesorFicticio("Janto@gmail.com"), aula, permanencia);
	}

	// Devolvemos el número de puntos de la permanencia más el número de puntos del
	// aula.
	public float getPuntos() {
		return permanencia.getPuntos() + aula.getPuntos();
	}

	// Dos reservas seran iguales si tienen el misma aula y la misma permanencia.
	@Override
	public int hashCode() {
		return Objects.hash(aula, permanencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Reserva))
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(aula, other.aula) && Objects.equals(permanencia, other.permanencia);
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s, puntos=%.1f", profesor, aula, permanencia, getPuntos());
	}

}
