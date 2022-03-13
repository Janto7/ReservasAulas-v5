package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public interface IAulas {

	void comenzar();

	void terminar();

	List<Aula> getAulas();

	int getNumAulas();

	void insertar(Aula aula) throws OperationNotSupportedException;

	Aula buscar(Aula aula);

	void borrar(Aula aula) throws OperationNotSupportedException;

	List<String> representar();

}