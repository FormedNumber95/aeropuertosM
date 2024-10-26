package model;

import java.sql.Blob;
import java.util.Objects;

/**
 * Clase ModeloAeropuertoPrivado.
 */
public class ModeloAeropuertoPrivado extends ModeloAeropuerto {

	/** EL num socios. */
	int numSocios;

	/**
	 * Instantiates a new modelo aeropuerto privado.
	 *
	 * @param nombre the nombre
	 * @param anioInauguracion the anio inauguracion
	 * @param capacidad the capacidad
	 * @param direccion the direccion
	 * @param imagen the imagen
	 * @param numSocios the num socios
	 */
	public ModeloAeropuertoPrivado(String nombre, int anioInauguracion, int capacidad,
			ModeloDireccion direccion, Blob imagen, int numSocios) {
		super(nombre, anioInauguracion, capacidad, direccion, imagen);
		this.numSocios = numSocios;
	}

	/**
	 * Gets the num socios.
	 *
	 * @return the num socios
	 */
	public int getNumSocios() {
		return numSocios;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(numSocios);
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloAeropuertoPrivado other = (ModeloAeropuertoPrivado) obj;
		return anioInauguracion == other.anioInauguracion && capacidad == other.capacidad
				&& Objects.equals(direccion, other.direccion) && id == other.id && Objects.equals(imagen, other.imagen)
				&& Objects.equals(nombre, other.nombre)&&numSocios == other.numSocios;
	}
	
}
