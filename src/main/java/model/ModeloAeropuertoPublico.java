package model;

import java.sql.Blob;
import java.util.Objects;

/**
 * Clase ModeloAeropuertoPublico.
 */
public class ModeloAeropuertoPublico extends ModeloAeropuerto {

	/** La financiacion. */
	float financiacion;
	
	/** EL num trabajadores. */
	int numTrabajadores;
	
	/**
	 * Instantiates a new modelo aeropuerto publico.
	 *
	 * @param nombre the nombre
	 * @param anioInauguracion the anio inauguracion
	 * @param capacidad the capacidad
	 * @param direccion the direccion
	 * @param imagen the imagen
	 * @param financiacion the financiacion
	 * @param numTrabajadores the num trabajadores
	 */
	public ModeloAeropuertoPublico(String nombre, int anioInauguracion, int capacidad,
			ModeloDireccion direccion, Blob imagen, float financiacion, int numTrabajadores) {
		super(nombre, anioInauguracion, capacidad, direccion, imagen);
		this.financiacion = financiacion;
		this.numTrabajadores = numTrabajadores;
	}
	
	/**
	 * Gets the financiacion.
	 *
	 * @return the financiacion
	 */
	public float getFinanciacion() {
		return financiacion;
	}
	
	/**
	 * Gets the num trabajadores.
	 *
	 * @return the num trabajadores
	 */
	public int getNumTrabajadores() {
		return numTrabajadores;
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
		result = prime * result + Objects.hash(financiacion, numTrabajadores);
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
		ModeloAeropuertoPublico other = (ModeloAeropuertoPublico) obj;
		return Float.floatToIntBits(financiacion) == Float.floatToIntBits(other.financiacion)
				&& numTrabajadores == other.numTrabajadores&&anioInauguracion == other.anioInauguracion && capacidad == other.capacidad
						&& Objects.equals(direccion, other.direccion) && id == other.id && Objects.equals(imagen, other.imagen)
						&& Objects.equals(nombre, other.nombre);
	}
	
}
