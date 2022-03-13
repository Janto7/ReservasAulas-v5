package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.ficheros;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IAulas;

public class Aulas implements IAulas {
	// Fichero aulas
	private static final String NOMBRE_FICHERO_AULAS = "datos/aulas.dat";
	// -----> coleccionAulas (0...*)
	private List<Aula> coleccionAulas;

	public Aulas() {
		coleccionAulas = new ArrayList<>();
	}

	public Aulas(IAulas aulas) {
		setAulas(aulas);
	}

	@Override
	public void comenzar() {
		leer();
	}

	private void leer() {
		File ficheroAulas = new File(NOMBRE_FICHERO_AULAS);
		// Indicamos que se trata de un flujo de entrada para leer objetos
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroAulas))) {
			Aula aula = null;
			do {
				// Casting
				aula = (Aula) entrada.readObject();
				insertar(aula);
			} while (aula != null);
			// Cerramos el flujo
			entrada.close();
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo abrir el fihero de aulas.");
		} catch (EOFException e) {
			System.out.println("Fichero aulas leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void terminar() {
		escribir();
	}

	private void escribir() {
		File ficheroAulas = new File(NOMBRE_FICHERO_AULAS);
		// Indicamos que se trata de un flujo de salida para escribir objetos
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroAulas))) {
			for (Aula aula : coleccionAulas) {
				salida.writeObject(aula);
			}
			System.out.println("Fichero aulas escrito satisfactoriamente.");
			// Cerramos el flujo
			salida.close();
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de aulas.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		}
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
		int indice = coleccionAulas.indexOf(aula);
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		} else {
			coleccionAulas.remove(indice);
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
