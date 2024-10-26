package ctrl;
import dao.DaoAeropuerto;
import dao.DaoAvion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.ModeloAeropuerto;
import model.ModeloAvion;

/**
 * Clase AniadirAvionController.
 */
public class AniadirAvionController {

    /** EL toggleGroup acivacion. */
    @FXML
    private ToggleGroup acivacion;

    /** EL btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** EL btn guardar. */
    @FXML
    private Button btnGuardar;

    /** EL cmb aeropuertos. */
    @FXML
    private ComboBox<ModeloAeropuerto> cmbAeropuertos;

    /** EL rad activado. */
    @FXML
    private RadioButton radActivado;

    /** EL rad desactivado. */
    @FXML
    private RadioButton radDesactivado;

    /** EL txt asientos. */
    @FXML
    private TextField txtAsientos;

    /** EL txt modelo. */
    @FXML
    private TextField txtModelo;

    /** EL txt vel max. */
    @FXML
    private TextField txtVelMax;

    /**
     * Calncelar avion.
     *
     * @param event the event
     */
    @FXML
    void calncelarAvion(ActionEvent event) {
    	ListadoAeropuertosController.getS().close();
    }

    /**
     * Guardar avion.
     *
     * @param event the event
     */
    @FXML
    void guardarAvion(ActionEvent event) {
    	String error="";
    	String modelo=txtModelo.getText();
    	int numAsientos=-1;
    	int velMax=-1;
    	boolean existe=false;
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	if(txtModelo.getText().isEmpty()) {
    		error+="El campo modelo es obligatorio\n";
    	}
    	if(txtAsientos.getText().isEmpty()) {
    		error+="El campo asientos es obligatorio\n";
    	}else {
    		try {
    			numAsientos=Integer.parseInt(txtAsientos.getText());
    			if(numAsientos<=0) {
    				throw new Exception();
    			}
    		}catch(NumberFormatException e) {
    			error+="El numero de asientos debe ser un numero entero\n";
    		} catch (Exception e) {
				error+="El numero de asientos debe ser mayor que 0\n";
			}
    	}
    	if(txtVelMax.getText().isEmpty()) {
    		error+="La velocidad maxima es obligatoria\n";
    	}else {
    		try {
    			velMax=Integer.parseInt(txtVelMax.getText());
    			if(velMax<1) {
    				throw new Exception();
    			}
    		}catch(NumberFormatException e) {
    			error+="La velocidad maxima debe ser un numero entero\n";
    		} catch (Exception e) {
				error+="La velocidad maxima debe ser mayor que 0\n";
			}
    	}
    	for(ModeloAvion avion:DaoAvion.conseguirListaTodos()) {
    		if(avion.getModelo().equals(modelo)&&cmbAeropuertos.getSelectionModel().getSelectedItem().getId()==avion.getIdAeropuerto()) {
    			existe=true;
    		}
    	}
    	if(error.equals("")&&!existe) {
    		DaoAvion.aniadir(modelo, numAsientos, velMax, radActivado.isSelected(),cmbAeropuertos.getSelectionModel().getSelectedItem().getId());
    		al.setContentText("Avion añadido correctamente");
    	}else {
    		if(error.equals("")) {
    			al.setAlertType(AlertType.WARNING);
    			al.setContentText("Ya existe un avion de ese modelo en ese aeropuerto");
    		}else {
    			al.setAlertType(AlertType.ERROR);
    			al.setContentText(error);
    		}
    	}
    	al.showAndWait();
    }
    
    /**
     * Initializa el combo.
     */
    @FXML
    private void initialize() {
    	this.cmbAeropuertos.setItems(DaoAeropuerto.listaTodas());
    }

}
