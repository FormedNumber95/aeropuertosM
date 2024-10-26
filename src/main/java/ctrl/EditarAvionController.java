package ctrl;
import dao.DaoAeropuerto;
import dao.DaoAvion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import model.ModeloAeropuerto;
import model.ModeloAvion;

/**
 * Clase EditarAvionController.
 */
public class EditarAvionController {

    /** EL toggleGruop activacion. */
    @FXML
    private ToggleGroup activacion;

    /** EL btn guardar. */
    @FXML
    private Button btnGuardar;

    /** EL btncancelar. */
    @FXML
    private Button btncancelar;

    /** EL cmb aeropuertos. */
    @FXML
    private ComboBox<ModeloAeropuerto> cmbAeropuertos;

    /** EL cmb aviones. */
    @FXML
    private ComboBox<ModeloAvion> cmbAviones;

    /** EL lbl titulo. */
    @FXML
    private Label lblTitulo;

    /** EL rad activado. */
    @FXML
    private RadioButton radActivado;

    /** EL rad desactivado. */
    @FXML
    private RadioButton radDesactivado;

    /**
     * Accion cancelar.
     *
     * @param event the event
     */
    @FXML
    void accionCancelar(ActionEvent event) {
    	ListadoAeropuertosController.getS().close();
    }

    /**
     * Accion guardar.
     *
     * @param event the event
     */
    @FXML
    void accionGuardar(ActionEvent event) {
    	Alert al=new Alert(AlertType.CONFIRMATION);
    	al.setHeaderText(null);
    	if(ListadoAeropuertosController.isEsBorrar()) {
    		al.setContentText("¿Estas seguro de que deseas eliminar el avion?");
    		al.showAndWait();
    		if(al.getResult().getButtonData().name().equals("OK_DONE")) {
    			DaoAvion.borrar(cmbAviones.getSelectionModel().getSelectedItem().getModelo(),cmbAviones.getSelectionModel().getSelectedItem().getIdAeropuerto());
    			cmbAviones.setItems(DaoAvion.listaAviones(cmbAeropuertos.getSelectionModel().getSelectedItem().getId()));
    		}
    		}else {
    			al.setAlertType(AlertType.INFORMATION);
    			DaoAvion.updateEstado(cmbAviones.getSelectionModel().getSelectedItem().getModelo(), cmbAviones.getSelectionModel().getSelectedItem().getIdAeropuerto(),
    				radActivado.isSelected());
    			al.setContentText("Avion modificado correctamente");
    			al.showAndWait();
    	}
    }
    
    /**
     * Actualizar aviones.
     *
     * @param event the event
     */
    @FXML
    void actualizarAviones(ActionEvent event) {
    	this.cmbAviones.setItems(DaoAvion.listaAviones(this.cmbAeropuertos.getSelectionModel().getSelectedItem().getId()));
    }
    
    /**
     * Initializa el cmbAeropuertos el titulo y los radioButtons.
     */
    @FXML
    private void initialize() {
    	this.cmbAeropuertos.setItems(DaoAeropuerto.listaTodas());
    	radActivado.setVisible(!ListadoAeropuertosController.isEsBorrar());
    	radDesactivado.setVisible(!ListadoAeropuertosController.isEsBorrar());
    	if(ListadoAeropuertosController.isEsBorrar()) {
    		lblTitulo.setText("BORRAR AVION");
    	}else {
    		lblTitulo.setText("ACTIVAR / DESACTIVAR AVION");
    	}
    }

}
