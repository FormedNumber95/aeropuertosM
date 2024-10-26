package ctrl;

import java.io.IOException;
import java.util.Properties;

import db.ConexionBBDD;
import es.aketzagonzalez.aeropuertosM.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Clase LoginController.
 */
public class LoginController {

    /** EL btn login. */
    @FXML
    private Button btnLogin;

    /** EL txt password. */
    @FXML
    private PasswordField txtPassword;

    /** EL txt user. */
    @FXML
    private TextField txtUser;
    
    /**
     * Validar usuario.
     *
     * @param event the event
     */
    @FXML
    void validarUsuario(ActionEvent event) {
    	Properties config=ConexionBBDD.loadProperties();
    	if(txtUser.getText().equals(config.getProperty("user"))&&txtPassword.getText().equals(config.getProperty("password"))) {
    		try {
				MainApp.setRoot("listadoAeropuertos", "AVIONES - AEROPUERTOS");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }

}
