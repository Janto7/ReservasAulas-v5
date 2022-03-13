package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Clase abstracta

public abstract class Permanencia implements Serializable {

	private LocalDate dia;

	protected static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Permanencia(LocalDate dia) {
		setDia(dia);
	}

	public Permanencia(Permanencia p) {
		if (p == null) {
			throw new NullPointerException("ERROR: No se puede copiar una permanencia nula.");
		}

		setDia(p.getDia());
	}

	public LocalDate getDia() {
		return dia;
	}

	private void setDia(LocalDate dia) {
		if (dia == null) {
			throw new NullPointerException("ERROR: El día de una permanencia no puede ser nulo.");
		}
		this.dia = dia;
	}

	/*
	 * El modificador abstract sirve para indicar que un método es abstracto, un
	 * método sin implementación (no hay cuerpo, solo la declaración terminada en
	 * punto y coma, en lugar de las llaves con el cuerpo).
	 */

	public abstract int getPuntos();

	public abstract int hashCode();

	public abstract boolean equals(Object obj);

	@Override
	public String toString() {
		return "día=" + getDia().format(FORMATO_DIA);
	}

}
