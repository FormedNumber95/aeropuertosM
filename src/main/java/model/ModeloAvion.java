package model;

/**
 * Clase ModeloAvion.
 */
public class ModeloAvion {

	/** EL modelo. */
	String modelo;
	
	/** EL num asientos. */
	int numAsientos;
	
	/** La vel maxima. */
	int velMaxima;
	
	/** EL activado. */
	boolean activado;//en la base de datos en int(1), 1 si es true
	
	/** EL id aeropuerto. */
	int idAeropuerto;
	
	/**
	 * Instantiates a new modelo avion.
	 *
	 * @param modelo the modelo
	 * @param numAsientos the num asientos
	 * @param velMaxima the vel maxima
	 * @param activado the activado
	 * @param idAeropuerto the id aeropuerto
	 */
	public ModeloAvion(String modelo, int numAsientos, int velMaxima, boolean activado, int idAeropuerto) {
		super();
		this.modelo = modelo;
		this.numAsientos = numAsientos;
		this.velMaxima = velMaxima;
		this.activado = activado;
		this.idAeropuerto = idAeropuerto;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.modelo;
	}
	
	/**
	 * Gets the modelo.
	 *
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * Gets the num asientos.
	 *
	 * @return the num asientos
	 */
	public int getNumAsientos() {
		return numAsientos;
	}

	/**
	 * Gets the vel maxima.
	 *
	 * @return the vel maxima
	 */
	public int getVelMaxima() {
		return velMaxima;
	}

	/**
	 * Checks if is activado.
	 *
	 * @return true, if is activado
	 */
	public boolean isActivado() {
		return activado;
	}
	
	/**
	 * Gets the id aeropuerto.
	 *
	 * @return the id aeropuerto
	 */
	public int getIdAeropuerto() {
		return idAeropuerto;
	}
	
}
