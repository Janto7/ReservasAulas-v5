package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IAulas;

public class Aulas implements IAulas {
	// -----> coleccionAulas (0...*)
	private List<Aula> coleccionAulas;

	public Aulas() {
		coleccionAulas = new ArrayList<>();
	}

	public Aulas(IAulas aulas) {
		setAulas(aulas);
	}

	private void setAulas(IAulas aulas) {
		if (aulas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar aulas nulas.");
		}
		this.coleccionAulas = copiaProfundaAulas(aulas.getAulas());
	}

	// Comparamos las aulas con el método getNombre() y las retornamos ordenadas.

	@Override
	public List<Aula> getAulas() {
		List<Aula> aulasOrdenadas = copiaProfundaAulas(coleccionAulas);
		aulasOrdenadas.sort(Comparator.comparing(Aula::getNombre));
		return aulasOrdenadas;
	}

	private List<Aula> copiaProfundaAulas(List<Aula> aulas) {

		List<Aula> otrasAulas = new ArrayList<>();
		// Recorremos mientras queden elementos
		for (Iterator<Aula> it = aulas.iterator(); it.hasNext();) {

			Aula aula = it.next();
			otrasAulas.add(new Aula(aula));

		}

		return otrasAulas;

	}

	@Override
	public int getNumAulas() {
		return coleccionAulas.size();
	}

	@Override
	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}
		// Si la colección no contiene el aula a introducir
		if (!coleccionAulas.contains(aula)) {
			// la insertamos
			coleccionAulas.add(new Aula(aula));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
		}

	}

	@Override
	public Aula buscar(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		/*
		 * Devuelve el índice de la primera aparición del elemento especificado en esta
		 * lista.
		 */
		int indice = coleccionAulas.indexOf(aula);
		// Devuelve -1 si el elemento especificado no está presente en esta lista.
		if (indice == -1) {
			return null;
		} else {
			return new Aula(coleccionAulas.get(indice));
		}
	}

	@Override
	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		// Si la colección contiene el aula
		if (coleccionAulas.contains(aula)) {
			// La eliminamos
			coleccionAulas.remove(aula);

		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}
	}

	@Override
	public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		// Uso el método getAulas me devuelve la lista ordenada.
		for (Iterator<Aula> it = getAulas().iterator(); it.hasNext();) {

			representacion.add(it.next().toString());
		}
		return representacion;
	}

}
