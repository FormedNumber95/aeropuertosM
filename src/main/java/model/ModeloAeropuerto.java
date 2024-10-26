package model;

import java.sql.Blob;
import java.util.Objects;

/**
 * Clase ModeloAeropuerto.
 */
public class ModeloAeropuerto {

	/** EL id. */
	int id;
	
	/** EL nombre. */
	String nombre;
	
	/** EL anio inauguracion. */
	int anioInauguracion;
	
	/** La capacidad. */
	int capacidad;
	
	/** La direccion. */
	ModeloDireccion direccion;
	
	/** La imagen. */
	Blob imagen;
	
	/**
	 * Instantiates a new modelo aeropuerto.
	 *
	 * @param nombre the nombre
	 * @param anioInauguracion the anio inauguracion
	 * @param capacidad the capacidad
	 * @param direccion the direccion
	 * @param imagen the imagen
	 */
	public ModeloAeropuerto(String nombre, int anioInauguracion, int capacidad, ModeloDireccion direccion,
			Blob imagen) {
		super();
		this.nombre = nombre;
		this.anioInauguracion = anioInauguracion;
		this.capacidad = capacidad;
		this.direccion = direccion;
		this.imagen = imagen;
	}
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Gets the anio inauguracion.
	 *
	 * @return the anio inauguracion
	 */
	public int getAnioInauguracion() {
		return anioInauguracion;
	}

	/**
	 * Gets the capacidad.
	 *
	 * @return the capacidad
	 */
	public int getCapacidad() {
		return capacidad;
	}

	/**
	 * Gets the direccion.
	 *
	 * @return the direccion
	 */
	public ModeloDireccion getDireccion() {
		return direccion;
	}

	/**
	 * Gets the imagen.
	 *
	 * @return the imagen
	 */
	public Blob getImagen() {
		return imagen;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(anioInauguracion, capacidad, direccion, id, imagen, nombre);
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloAeropuerto other = (ModeloAeropuerto) obj;
		return anioInauguracion == other.anioInauguracion && capacidad == other.capacidad
				&& Objects.equals(direccion, other.direccion) && id == other.id && Objects.equals(imagen, other.imagen)
				&& Objects.equals(nombre, other.nombre);
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.nombre;
	}
	
}
