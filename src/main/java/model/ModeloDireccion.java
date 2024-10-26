package model;

/**
 * The Class ModeloDireccion.
 */
public class ModeloDireccion {

	/** EL pais. */
	String pais;
	
	/** La ciudad. */
	String ciudad;
	
	/** La calle. */
	String calle;
	
	/** EL numero. */
	int numero;
	
	/**
	 * Instantiates a new modelo direccion.
	 *
	 * @param pais the pais
	 * @param ciudad the ciudad
	 * @param calle the calle
	 * @param numero the numero
	 */
	public ModeloDireccion(String pais, String ciudad, String calle, int numero) {
		super();
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
	}

	/**
	 * Gets the pais.
	 *
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Gets the ciudad.
	 *
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Gets the calle.
	 *
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	
}
