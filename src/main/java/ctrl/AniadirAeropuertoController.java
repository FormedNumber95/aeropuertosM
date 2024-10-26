package ctrl;

import dao.DaoAeropuerto;
import dao.DaoAeropuertoPrivado;
import dao.DaoAeropuertoPublico;
import dao.DaoDireccion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import model.ModeloAeropuertoPrivado;
import model.ModeloAeropuertoPublico;
import model.ModeloDireccion;

/**
 * Clase AniadirAeropuertoController.
 */
public class AniadirAeropuertoController {

    /** EL btn guardar. */
    @FXML
    private Button btnGuardar;

    /** EL cancelar aeropuerto. */
    @FXML
    private Button cancelarAeropuerto;

    /** EL grid privado. */
    @FXML
    private GridPane gridPrivado;

    /** EL grid publico. */
    @FXML
    private GridPane gridPublico;

    /** EL grupo tipo. */
    @FXML
    private ToggleGroup grupoTipo;

    /** EL rad privado. */
    @FXML
    private RadioButton radPrivado;

    /** EL rad publico. */
    @FXML
    private RadioButton radPublico;

    /** EL txt calle. */
    @FXML
    private TextField txtCalle;

    /** EL txt capacidad. */
    @FXML
    private TextField txtCapacidad;

    /** EL txt ciudad. */
    @FXML
    private TextField txtCiudad;

    /** EL txt financiacion. */
    @FXML
    private TextField txtFinanciacion;

    /** EL txt nombre. */
    @FXML
    private TextField txtNombre;

    /** EL txt num socios. */
    @FXML
    private TextField txtNumSocios;

    /** EL txt num trabajadores. */
    @FXML
    private TextField txtNumTrabajadores;

    /** EL txt numero. */
    @FXML
    private TextField txtNumero;

    /** EL txt pais. */
    @FXML
    private TextField txtPais;

    /** EL txt anio inauguracion. */
    @FXML
    private TextField txtAnioInauguracion;
    
    /** EL id tabla privado. */
    private TableView<ModeloAeropuertoPrivado> idTablaPrivado;
    
    /** EL id tabla publico. */
    private TableView<ModeloAeropuertoPublico> idTablaPublico;

    /**
     * Cancelar operacion.
     *
     * @param event the event
     */
    @FXML
    void cancelarOperacion(ActionEvent event) {
    	ListadoAeropuertosController.getS().close();
    }

    /**
     * Guardar aeropuerto.
     *
     * @param event the event
     */
    @FXML
    void guardarAeropuerto(ActionEvent event) {
    	String error="";
    	String nombre=txtNombre.getText();
    	String pais=txtPais.getText();
    	String ciudad=txtCiudad.getText();
    	String calle=txtCalle.getText();
    	int numero=-1;
    	int anioInauguracion=-1;
    	int capacidad=-1;
    	boolean esPublico=radPublico.isSelected();
    	float financiacion=-1;
    	int numTrabajadores=-1;
    	int numSocios=-1;
    	boolean existe=false;
    	//Validacion
    	error = validarStrings(error);
    	if(txtNumero.getText().isEmpty()) {
    		error+="El campo numero es obligatorio\n";
    	}else {
    		try {
    			numero=Integer.parseInt(txtNumero.getText());
    			if(numero<=0) {
    				throw new Exception();
    			}
    		}catch(NumberFormatException e) {
    			error+="El numero de la calle debe ser un numero\n";
    		} catch (Exception e) {
				error+="El numero de la calle no puede ser menor que 1\n";
			}
    	}
    	if(txtAnioInauguracion.getText().isEmpty()) {
    		error+="El campo año de inauguracion es obligatorio\n";
    	}else {
    		try {
    			anioInauguracion=Integer.parseInt(txtAnioInauguracion.getText());
    			if(anioInauguracion<1900) {
    				throw new Exception();
    			}
    		}catch(NumberFormatException e) {
    			error+="El año de inauguracion debe ser un numero\n";
    		} catch (Exception e) {
				error+="El año de inauguracion no puede ser menor al 1900\n";
			}
    	}
    	if(txtCapacidad.getText().isEmpty()) {
    		error+="El campo capacidad es obligatorio\n";
    	}else {
    		try {
    			capacidad=Integer.parseInt(txtCapacidad.getText());
    			if(capacidad<=0) {
    				throw new Exception();
    			}
    		}catch(NumberFormatException e) {
    			error+="La capacidad debe ser un numero\n";
    		} catch (Exception e) {
				error+="La capacidad no puede ser menor a 1\n";
			}
    	}
    	if(esPublico) {
	    	if(esPublico&&txtFinanciacion.getText().isEmpty()) {
	    		error+="El campo financiacion es obligatorio\n";
	    	}else {
	    		if(!txtFinanciacion.getText().matches("^\\d{1,10}(\\.\\d{1,2})?$")){
	    			error+="La financiacion puede tener como mucho 10 digitos antes del punto y 2 despues\n";
	    		}else {
					try {
		    			financiacion=Float.parseFloat(txtFinanciacion.getText());
		        		if(financiacion<=0) {
		        			throw new Exception();
		        		}
		    		}catch(NumberFormatException e) {
		    			error+="La financiacion debe ser un numero\n";
		    		} catch (Exception e) {
						error+="La financiacion no puede ser menor que 1\n";
					}
	    		}
	    	}
		}
    	if(esPublico) {
	    	if(txtNumTrabajadores.getText().isEmpty()) {
	    		error+="El campo numero de trabajadores es obligatorio\n";
	    	}else {
	    		try {
	    			numTrabajadores=Integer.parseInt(txtNumTrabajadores.getText());
	    			if(numTrabajadores<=0) {
	    				throw new Exception();
	    			}
	    		}catch(NumberFormatException e) {
	    			error+="El numero de trabajadores debe ser un numero\n";
	    		} catch (Exception e) {
					error+="El numero de trabajadores no puede ser menor que 1\n";
				}
	    	}
    	}
    	if(!esPublico) {
    		if(txtNumSocios.getText().isEmpty()) {
	    		error+="El campo Nº socios es obligatorio\n";
	    	}else {
	    		try {
	    			numSocios=Integer.parseInt(txtNumSocios.getText());
	    			if(numSocios<=0) {
	    				throw new Exception();
	    			}
	    		}catch(NumberFormatException e) {
	    			error+="El numero de socios debe ser un numero\n";
	    		} catch (Exception e) {
					error+="El numero de socios no puede ser menor que 1\n";
				}
	    	}
    	}
    	//Una vez validado
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	if(ListadoAeropuertosController.isEsAniadir()) {
	    	aniadirAeropuerto(error, nombre, pais, ciudad, calle, numero, anioInauguracion, capacidad, esPublico,
					financiacion, numTrabajadores, numSocios, existe, al);
    	}else {
    		modificarAeropuerto(error, nombre, pais, ciudad, calle, numero, anioInauguracion, capacidad, esPublico,
					financiacion, numTrabajadores, numSocios, existe, al);
    	}
    	al.showAndWait();
    	idTablaPrivado.getSelectionModel().clearSelection();
    	idTablaPublico.getSelectionModel().clearSelection();
    	ListadoAeropuertosController.getS().close();
    }

	/**
	 * Modificar aeropuerto.
	 *
	 * @param error the error
	 * @param nombre the nombre
	 * @param pais the pais
	 * @param ciudad the ciudad
	 * @param calle the calle
	 * @param numero the numero
	 * @param anioInauguracion the anio inauguracion
	 * @param capacidad the capacidad
	 * @param esPublico the es publico
	 * @param financiacion the financiacion
	 * @param numTrabajadores the num trabajadores
	 * @param numSocios the num socios
	 * @param existe the existe
	 * @param al the al
	 */
	void modificarAeropuerto(String error, String nombre, String pais, String ciudad, String calle, int numero,
			int anioInauguracion, int capacidad, boolean esPublico, float financiacion, int numTrabajadores,
			int numSocios, boolean existe, Alert al) {
		existe = validarExistencia(nombre, pais, ciudad, calle, numero, anioInauguracion, capacidad, esPublico,
				financiacion, numTrabajadores, numSocios, existe);
		if(!existe&&error.equals("")) {
			Integer idDireccion=DaoDireccion.conseguirID(pais, ciudad, calle, numero);
			if(idDireccion==null) {
				DaoDireccion.aniadir(pais, ciudad, calle, numero);
				idDireccion=DaoDireccion.conseguirID(pais, ciudad, calle, numero);
			}
			Integer idAeropuerto=DaoAeropuerto.conseguirID(nombre, anioInauguracion, capacidad, idDireccion, null);
			if(idAeropuerto==null) {
				if(esPublico) {
					DaoAeropuerto.modificarPorId(idTablaPublico.getSelectionModel().getSelectedItem().getId(), nombre, anioInauguracion, capacidad, idDireccion, null);
				}else {
					DaoAeropuerto.modificarPorId(idTablaPrivado.getSelectionModel().getSelectedItem().getId(), nombre, anioInauguracion, capacidad, idDireccion, null);
				}
				idAeropuerto=DaoAeropuerto.conseguirID(nombre, anioInauguracion, capacidad,idDireccion, null);
			}
			if(esPublico) {
				DaoAeropuertoPublico.modificarPorID(idAeropuerto, financiacion, numTrabajadores);
				ListadoAeropuertosController.setListaTodasPublico(DaoAeropuertoPublico.cargarListaAeropuertosPublicos());
				idTablaPublico.refresh();
			}else {
				DaoAeropuertoPrivado.modificarPorID(idAeropuerto, numSocios);
				ListadoAeropuertosController.setListaTodasPrivado(DaoAeropuertoPrivado.cargarListaAeropuertosPrivados());
				idTablaPrivado.refresh();
			}
			al.setContentText("Aeropuerto modificado correctamente");
		}else {
			if(error.equals("")) {
				al.setAlertType(AlertType.WARNING);
				error="La persona ya estaba en la lista";
			}else {
				al.setAlertType(AlertType.ERROR);
			}
			al.setContentText(error);
		}
	}

	/**
	 * Aniadir aeropuerto.
	 *
	 * @param error the error
	 * @param nombre the nombre
	 * @param pais the pais
	 * @param ciudad the ciudad
	 * @param calle the calle
	 * @param numero the numero
	 * @param anioInauguracion the anio inauguracion
	 * @param capacidad the capacidad
	 * @param esPublico the es publico
	 * @param financiacion the financiacion
	 * @param numTrabajadores the num trabajadores
	 * @param numSocios the num socios
	 * @param existe the existe
	 * @param al the al
	 */
	void aniadirAeropuerto(String error, String nombre, String pais, String ciudad, String calle, int numero,
			int anioInauguracion, int capacidad, boolean esPublico, float financiacion, int numTrabajadores,
			int numSocios, boolean existe, Alert al) {
		existe = validarExistencia(nombre, pais, ciudad, calle, numero, anioInauguracion, capacidad, esPublico,
				financiacion, numTrabajadores, numSocios, existe);
		if(error.equals("")&&!existe) {
			Integer idDireccion=DaoDireccion.conseguirID(pais, ciudad, calle, numero);
			if(idDireccion==null) {
				DaoDireccion.aniadir(pais, ciudad, calle, numero);
				idDireccion=DaoDireccion.conseguirID(pais, ciudad, calle, numero);
			}
			Integer idAeropuerto=DaoAeropuerto.conseguirID(nombre, anioInauguracion, capacidad, idDireccion, null);
			if(idAeropuerto==null) {
				DaoAeropuerto.aniadir(nombre, anioInauguracion, capacidad, idDireccion, null);
				idAeropuerto=DaoAeropuerto.conseguirID(nombre, anioInauguracion, capacidad,idDireccion, null);
			}
			if(esPublico) {
				DaoAeropuertoPublico.aniadir(idAeropuerto, financiacion, numTrabajadores);
				ListadoAeropuertosController.setListaTodasPublico(DaoAeropuertoPublico.cargarListaAeropuertosPublicos());
				idTablaPublico.refresh();
			}else {
				DaoAeropuertoPrivado.aniadir(idAeropuerto, numSocios);
				ListadoAeropuertosController.setListaTodasPrivado(DaoAeropuertoPrivado.cargarListaAeropuertosPrivados());
				idTablaPrivado.refresh();
			}
			al.setContentText("Aeropuerto añadido correctamente");
		}else {
			if(error.equals("")) {
				al.setAlertType(AlertType.WARNING);
				error="La persona ya estaba en la lista";
			}else {
				al.setAlertType(AlertType.ERROR);
			}
			al.setContentText(error);
		}
	}

	/**
	 * Validar existencia.
	 *
	 * @param nombre the nombre
	 * @param pais the pais
	 * @param ciudad the ciudad
	 * @param calle the calle
	 * @param numero the numero
	 * @param anioInauguracion the anio inauguracion
	 * @param capacidad the capacidad
	 * @param esPublico the es publico
	 * @param financiacion the financiacion
	 * @param numTrabajadores the num trabajadores
	 * @param numSocios the num socios
	 * @param existe the existe
	 * @return true, if successful
	 */
	boolean validarExistencia(String nombre, String pais, String ciudad, String calle, int numero, int anioInauguracion,
			int capacidad, boolean esPublico, float financiacion, int numTrabajadores, int numSocios, boolean existe) {
		if(esPublico) {
			ModeloAeropuertoPublico aeropuerto=new ModeloAeropuertoPublico(nombre, anioInauguracion, capacidad, new ModeloDireccion(pais, ciudad, calle, numero), null, financiacion, numTrabajadores);
			for(ModeloAeropuertoPublico airport:ListadoAeropuertosController.getListaTodasPublico()) {
				if(airport.equals(aeropuerto)) {
					existe=true;
				}
			}
		}else {
			ModeloAeropuertoPrivado aeropuerto=new ModeloAeropuertoPrivado(nombre, anioInauguracion, capacidad, new ModeloDireccion(pais, ciudad, calle, numero), null, numSocios);
			for(ModeloAeropuertoPrivado airport:ListadoAeropuertosController.getListaTodasPrivado()) {
				if(airport.equals(aeropuerto)) {
					existe=true;
				}
			}
		}
		return existe;
	}

	/**
	 * Validar strings.
	 *
	 * @param error the error
	 * @return the string
	 */
	String validarStrings(String error) {
		if(txtNombre.getText().isEmpty()) {
    		error+="El campo nombre es obligatorio\n";
    	}
    	if(txtPais.getText().isEmpty()) {
    		error+="El campo pais es obligatorio\n";
    	}
    	if(txtCiudad.getText().isEmpty()) {
    		error+="El campo ciudad es obligatorio\n";
    	}
    	if(txtCalle.getText().isEmpty()) {
    		error+="El campo calle es obligatorio\n";
    	}
		return error;
	}

    /**
     * Toggle tipo aeropuerto.
     *
     * @param event the event
     */
    @FXML
    void toggleTipoAeropuerto(ActionEvent event) {
    	if(radPrivado.isSelected()) {
    		gridPrivado.setVisible(true);
    		gridPublico.setVisible(false);
    	}else {
    		gridPrivado.setVisible(false);
    		gridPublico.setVisible(true);
    	}
    }
    
    /**
     * Initializa los radioButtons y los grid.
     */
    @FXML
    private void initialize() {
    	radPublico.setSelected(ListadoAeropuertosController.isEsPublico());
    	radPrivado.setSelected(!ListadoAeropuertosController.isEsPublico());
    	gridPrivado.setVisible(radPrivado.isSelected());
    	gridPublico.setVisible(radPublico.isSelected());
    	radPrivado.setDisable(!ListadoAeropuertosController.isEsAniadir());
    	radPublico.setDisable(!ListadoAeropuertosController.isEsAniadir());
    }
    
    /**
     * Sets the id tabla privado.
     *
     * @param idTablaPrivado the new id tabla privado
     */
    public void setIdTablaPrivado(TableView<ModeloAeropuertoPrivado> idTablaPrivado) {
		this.idTablaPrivado = idTablaPrivado;
	}
    
    /**
     * Sets the id tabla publico.
     *
     * @param idTablaPublico the new id tabla publico
     */
    public void setIdTablaPublico(TableView<ModeloAeropuertoPublico> idTablaPublico) {
		this.idTablaPublico = idTablaPublico;
	}

	/**
	 * Sets the txt calle text.
	 *
	 * @param txtCalle the new txt calle text
	 */
	public void setTxtCalleText(String txtCalle) {
		this.txtCalle.setText(txtCalle);
	}

	/**
	 * Sets the txt capacidad text.
	 *
	 * @param txtCapacidad the new txt capacidad text
	 */
	public void setTxtCapacidadText(String txtCapacidad) {
		this.txtCapacidad.setText(txtCapacidad);
	}

	/**
	 * Sets the txt ciudad text.
	 *
	 * @param txtCiudad the new txt ciudad text
	 */
	public void setTxtCiudadText(String txtCiudad) {
		this.txtCiudad.setText(txtCiudad);
	}

	/**
	 * Sets the txt financiacion text.
	 *
	 * @param txtFinanciacion the new txt financiacion text
	 */
	public void setTxtFinanciacionText(String txtFinanciacion) {
		this.txtFinanciacion.setText(txtFinanciacion);
	}

	/**
	 * Sets the txt nombre text.
	 *
	 * @param txtNombre the new txt nombre text
	 */
	public void setTxtNombreText(String txtNombre) {
		this.txtNombre.setText(txtNombre);
	}

	/**
	 * Sets the txt num socios text.
	 *
	 * @param txtNumSocios the new txt num socios text
	 */
	public void setTxtNumSociosText(String txtNumSocios) {
		this.txtNumSocios.setText(txtNumSocios);
	}

	/**
	 * Sets the txt num trabajadores text.
	 *
	 * @param txtNumTrabajadores the new txt num trabajadores text
	 */
	public void setTxtNumTrabajadoresText(String txtNumTrabajadores) {
		this.txtNumTrabajadores.setText(txtNumTrabajadores);
	}

	/**
	 * Sets the txt numero text.
	 *
	 * @param txtNumero the new txt numero text
	 */
	public void setTxtNumeroText(String txtNumero) {
		this.txtNumero.setText(txtNumero);
	}

	/**
	 * Sets the txt pais text.
	 *
	 * @param txtPais the new txt pais text
	 */
	public void setTxtPaisText(String txtPais) {
		this.txtPais.setText(txtPais);
	}

	/**
	 * Sets the txt anio inauguracion text.
	 *
	 * @param txtAnioInauguracion the new txt anio inauguracion text
	 */
	public void setTxtAnioInauguracionText(String txtAnioInauguracion) {
		this.txtAnioInauguracion.setText(txtAnioInauguracion);
	}

}
