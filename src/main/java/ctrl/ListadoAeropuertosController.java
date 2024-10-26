package ctrl;

import java.io.IOException;
import java.sql.SQLException;

import dao.DaoAeropuerto;
import dao.DaoAeropuertoPrivado;
import dao.DaoAeropuertoPublico;
import dao.DaoAvion;
import dao.DaoDireccion;
import db.ConexionBBDD;
import es.aketzagonzalez.aeropuertosM.MainApp;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ModeloAeropuertoPrivado;
import model.ModeloAeropuertoPublico;
import model.ModeloAvion;

/**
 * Clase ListadoAeropuertosController.
 */
public class ListadoAeropuertosController {

    /** EL aniadir aeropuerto. */
    @FXML
    private MenuItem aniadirAeropuerto;

    /** EL aniadir avion. */
    @FXML
    private MenuItem aniadirAvion;

    /** EL borrar aeropuerto. */
    @FXML
    private MenuItem borrarAeropuerto;

    /** EL borrar avion. */
    @FXML
    private MenuItem borrarAvion;

    /** EL editar aeropuerto. */
    @FXML
    private MenuItem editarAeropuerto;

    /** EL id tabla privado. */
    @FXML
    private TableView<ModeloAeropuertoPrivado> idTablaPrivado;

    /** EL id tabla publico. */
    @FXML
    private TableView<ModeloAeropuertoPublico> idTablaPublico;

    /** EL mostar info aeropuerto. */
    @FXML
    private MenuItem mostarInfoAeropuerto;

    /** EL rad private. */
    @FXML
    private RadioButton radPrivate;

    /** EL rad public. */
    @FXML
    private RadioButton radPublic;

    /** EL tbl anio privado. */
    @FXML
    private TableColumn<ModeloAeropuertoPrivado, Integer> tblAnioPrivado;

    /** EL tbl anio publico. */
    @FXML
    private TableColumn<ModeloAeropuertoPublico, Integer> tblAnioPublico;

    /** EL tbl calle privado. */
    @FXML
    private TableColumn<ModeloAeropuertoPrivado, String> tblCallePrivado;

    /** EL tbl calle publico. */
    @FXML
    private TableColumn<ModeloAeropuertoPublico, String> tblCallePublico;

    /** EL tbl capacidad privado. */
    @FXML
    private TableColumn<ModeloAeropuertoPrivado, Integer> tblCapacidadPrivado;

    /** EL tbl capacidad publico. */
    @FXML
    private TableColumn<ModeloAeropuertoPublico, Integer> tblCapacidadPublico;

    /** EL tbl ciudad privado. */
    @FXML
    private TableColumn<ModeloAeropuertoPrivado, String> tblCiudadPrivado;

    /** EL tbl ciudad publico. */
    @FXML
    private TableColumn<ModeloAeropuertoPublico, String> tblCiudadPublico;

    /** EL tbl financiacion. */
    @FXML
    private TableColumn<ModeloAeropuertoPublico, Float> tblFinanciacion;

    /** EL tbl id privado. */
    @FXML
    private TableColumn<ModeloAeropuertoPrivado, Integer> tblIdPrivado;

    /** EL tbl id publico. */
    @FXML
    private TableColumn<ModeloAeropuertoPublico, Integer> tblIdPublico;

    /** EL tbl nombre privado. */
    @FXML
    private TableColumn<ModeloAeropuertoPrivado, String> tblNombrePrivado;

    /** EL tbl nombre publico. */
    @FXML
    private TableColumn<ModeloAeropuertoPublico, String> tblNombrePublico;

    /** EL tbl num socios. */
    @FXML
    private TableColumn<ModeloAeropuertoPrivado, Integer> tblNumSocios;

    /** EL tbl numero privado. */
    @FXML
    private TableColumn<ModeloAeropuertoPrivado, Integer> tblNumeroPrivado;

    /** EL tbl numero publico. */
    @FXML
    private TableColumn<ModeloAeropuertoPublico, Integer> tblNumeroPublico;

    /** EL tbl numero trabajadores. */
    @FXML
    private TableColumn<ModeloAeropuertoPublico, Integer> tblNumeroTrabajadores;

    /** EL tbl pais privado. */
    @FXML
    private TableColumn<ModeloAeropuertoPrivado, String> tblPaisPrivado;

    /** EL tbl pais publico. */
    @FXML
    private TableColumn<ModeloAeropuertoPublico, String> tblPaisPublico;

    /** EL toggle avion. */
    @FXML
    private MenuItem toggleAvion;

    /** EL txt filtro. */
    @FXML
    private TextField txtFiltro;

    /** EL toggleGruop visibilidad. */
    @FXML
    private ToggleGroup visibilidad;
    
    /** La lista todas privado. */
    private static ObservableList<ModeloAeropuertoPrivado> listaTodasPrivado;
    
    /** La lista todas publico. */
    private static ObservableList<ModeloAeropuertoPublico> listaTodasPublico;
    
    /** EL filtro privado. */
    private FilteredList<ModeloAeropuertoPrivado> filtroPrivado;
    
    /** EL filtro publico. */
    private FilteredList<ModeloAeropuertoPublico> filtroPublico;
    
    /** EL es aniadir. */
    private static boolean esAniadir;
    
    /** EL es publico. */
    private static boolean esPublico=true;
    
    /** EL es borrar. */
    private static boolean esBorrar=true;
    
    /** EL stage. */
    private static Stage s;
    
    private  ContextMenu contextMenu;

    /**
     * Toggle tipo aeropuerto.
     *
     * @param event the event
     */
    @FXML
    void toggleTipoAeropuerto(ActionEvent event) {
    	esPublico=radPublic.isSelected();
    	if(radPublic.isSelected()) {
    		idTablaPublico.setVisible(true);
    		idTablaPrivado.setVisible(false);
    	}else {
    		idTablaPublico.setVisible(false);
    		idTablaPrivado.setVisible(true);;
    	}
    }
    
    /**
     * Accion filtrar.
     */
    @FXML
    void accionFiltrar() {
    	String filtro = txtFiltro.getText().toLowerCase();

        if (filtro.isEmpty()) {
            filtroPrivado.setPredicate(null);
            filtroPublico.setPredicate(null);
            idTablaPrivado.setItems(listaTodasPrivado);
            idTablaPublico.setItems(listaTodasPublico);
        } else {
            filtroPrivado.setPredicate(aeropuerto -> aeropuerto.getNombre().toLowerCase().contains(filtro));
            filtroPublico.setPredicate(aeropuerto -> aeropuerto.getNombre().toLowerCase().contains(filtro));
            idTablaPrivado.setItems(filtroPrivado);
            idTablaPublico.setItems(filtroPublico);
        }
    }
    
    /**
     * Aniadir aeropuerto.
     *
     * @param event the event
     */
    @FXML
    void aniadirAeropuerto(ActionEvent event) {
    	esAniadir=true;
    	s=new Stage();
    	Scene scene;
		try {
			 FXMLLoader controlador = new FXMLLoader(MainApp.class.getResource("/fxml/aniadirAeropuerto.fxml"));
			scene = new Scene(controlador.load());
			s.setTitle("AVIONES - AÑADIR AEROPUERTO");
			s.setScene(scene);
			AniadirAeropuertoController controller = controlador.getController();
			controller.setIdTablaPrivado(idTablaPrivado);
			controller.setIdTablaPublico(idTablaPublico);
		} catch (IOException e) {
			e.printStackTrace();
		}
        s.setResizable(false);
        s.initOwner(MainApp.getStage());
        s.initModality(javafx.stage.Modality.WINDOW_MODAL);
        s.showAndWait();
        accionFiltrar();
        idTablaPrivado.refresh();
        idTablaPublico.refresh();
    }

    /**
     * Aniadir avion.
     *
     * @param event the event
     */
    @FXML
    void aniadirAvion(ActionEvent event) {
    	s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(MainApp.class.getResource("/fxml/aniadirAvion.fxml"));
			scene = new Scene(controlador.load());
			s.setTitle("AVIONES - AÑADIR AVION");
			s.setScene(scene);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
        s.initOwner(MainApp.getStage());
        s.initModality(javafx.stage.Modality.WINDOW_MODAL);
        s.showAndWait();
    }

    /**
     * Borrar aeropuerto.
     *
     * @param event the event
     */
    @FXML
    void borrarAeropuerto(ActionEvent event) {
    	if(idTablaPrivado.getSelectionModel().getSelectedItem()!=null||idTablaPublico.getSelectionModel().getSelectedItem()!=null) {
    		Alert al=new Alert(AlertType.CONFIRMATION);
        	al.setHeaderText(null);
        	if(idTablaPrivado.getSelectionModel().getSelectedItem()!=null||idTablaPublico.getSelectionModel().getSelectedItem()!=null) {
        		al.setContentText("¿Estas seguro de que quieres borrar ese aeropuerto?");
        		al.showAndWait();
    	    	if(al.getResult().getButtonData().name().equals("OK_DONE")) {
    	    		if(esPublico) {
    	    			DaoAeropuertoPublico.eliminar(idTablaPublico.getSelectionModel().getSelectedItem().getId());
    	    			DaoAeropuerto.eliminar(idTablaPublico.getSelectionModel().getSelectedItem().getId());
    	    			listaTodasPublico=DaoAeropuertoPublico.cargarListaAeropuertosPublicos();
    	    			accionFiltrar();
    	    			idTablaPublico.refresh();
    	    		}else {
    	    			DaoAeropuertoPrivado.eliminar(idTablaPrivado.getSelectionModel().getSelectedItem().getId());
    	    			DaoAeropuerto.eliminar(idTablaPublico.getSelectionModel().getSelectedItem().getId());
    	    			listaTodasPrivado=DaoAeropuertoPrivado.cargarListaAeropuertosPrivados();
    	    			accionFiltrar();
    	    			idTablaPrivado.refresh();
    	    		}
    	    	}
        	}
    	}else {
    		Alert al=new Alert(AlertType.ERROR);
        	al.setHeaderText(null);
        	al.setContentText("No hay ninguno seleccionado, asi que no se puede eliminar ninguno");
        	al.showAndWait();
    	}
    }

    /**
     * Borrar avion.
     *
     * @param event the event
     */
    @FXML
    void borrarAvion(ActionEvent event) {
    	esBorrar=true;
    	s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(MainApp.class.getResource("/fxml/editarAvion.fxml"));
			scene = new Scene(controlador.load());
			s.setTitle("ELIMINAR AVION");
			s.setScene(scene);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
        s.initOwner(MainApp.getStage());
        s.initModality(javafx.stage.Modality.WINDOW_MODAL);
        s.showAndWait();
    }

    /**
     * Editar aeropuerto.
     *
     * @param event the event
     */
    @FXML
    void editarAeropuerto(ActionEvent event) {
    	esAniadir=false;
    	if(idTablaPrivado.getSelectionModel().getSelectedItem()!=null||idTablaPublico.getSelectionModel().getSelectedItem()!=null) {
    		s=new Stage();
        	Scene scene;
    		try {
    			 FXMLLoader controlador = new FXMLLoader(MainApp.class.getResource("/fxml/aniadirAeropuerto.fxml"));
    			scene = new Scene(controlador.load());
    			s.setTitle("AVIONES - MODIFICAR AEROPUERTO");
    			s.setScene(scene);
    			AniadirAeropuertoController controller = controlador.getController();
    			controller.setIdTablaPrivado(idTablaPrivado);
    			controller.setIdTablaPublico(idTablaPublico);
    			if(esPublico) {
    				ModeloAeropuertoPublico modelo=idTablaPublico.getSelectionModel().getSelectedItem();
    				controller.setTxtAnioInauguracionText(modelo.getAnioInauguracion()+"") ;
    				controller.setTxtCalleText(modelo.getDireccion().getCalle());
    				controller.setTxtCapacidadText(modelo.getCapacidad()+"");
    				controller.setTxtCiudadText(modelo.getDireccion().getCiudad());
    				controller.setTxtFinanciacionText(modelo.getFinanciacion()+"");
    				controller.setTxtNombreText(modelo.getNombre());
    				controller.setTxtNumeroText(modelo.getDireccion().getNumero()+"");
    				controller.setTxtNumTrabajadoresText(modelo.getNumTrabajadores()+"");
    				controller.setTxtPaisText(modelo.getDireccion().getPais());
    			}else {
    				ModeloAeropuertoPrivado modelo=idTablaPrivado.getSelectionModel().getSelectedItem();
    				controller.setTxtAnioInauguracionText(modelo.getAnioInauguracion()+"") ;
    				controller.setTxtCalleText(modelo.getDireccion().getCalle());
    				controller.setTxtCapacidadText(modelo.getCapacidad()+"");
    				controller.setTxtCiudadText(modelo.getDireccion().getCiudad());
    				controller.setTxtNombreText(modelo.getNombre());
    				controller.setTxtNumeroText(modelo.getDireccion().getNumero()+"");
    				controller.setTxtNumSociosText(modelo.getNumSocios()+"");
    				controller.setTxtPaisText(modelo.getDireccion().getPais());
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
            s.setResizable(false);
            s.initOwner(MainApp.getStage());
            s.initModality(javafx.stage.Modality.WINDOW_MODAL);
            s.showAndWait();
            accionFiltrar();
            idTablaPrivado.refresh();
            idTablaPublico.refresh();
    	}else {
    		Alert al=new Alert(AlertType.ERROR);
        	al.setHeaderText(null);
        	al.setContentText("No hay ninguno seleccionado, asi que no se puede modificar ninguno");
        	al.showAndWait();
    	}
    }

    /**
     * Mostar datos aeropuerto.
     *
     * @param event the event
     */
    @FXML
    void mostarDatosAeropuerto(ActionEvent event) {
    	if(idTablaPrivado.getSelectionModel().getSelectedItem()!=null||idTablaPublico.getSelectionModel().getSelectedItem()!=null) {
	    	Alert al=new Alert(AlertType.INFORMATION);
	    	al.setHeaderText(null);
	    	String str="";
	    	if(idTablaPrivado.getSelectionModel().getSelectedItem()!=null) {
	    		ModeloAeropuertoPrivado modelo=idTablaPrivado.getSelectionModel().getSelectedItem();
	    		str+="Nombre: "+modelo.getNombre()+"\n";
	    		str+="Pais: "+modelo.getDireccion().getPais()+"\n";
	    		str+="Direccion: "+modelo.getDireccion().getCalle()+" "+modelo.getDireccion().getNumero()+","+modelo.getDireccion().getCiudad()+"\n";
	    		str+="Año de inauguracion: "+modelo.getAnioInauguracion()+"\n";
	    		str+="Capacidad: "+modelo.getCapacidad()+"\n";
	    		str+="Aviones:\n";
	    		for(ModeloAvion avion:DaoAvion.listaAviones(DaoAeropuerto.conseguirID(
	    				modelo.getNombre(),modelo.getAnioInauguracion(),modelo.getCapacidad(),
	    				DaoDireccion.conseguirID(modelo.getDireccion().getPais(),modelo.
	    						getDireccion().getCiudad(),modelo.getDireccion().getCalle(),
	    						modelo.getDireccion().getNumero()), modelo.getImagen()))) {
	    			str+="\tModelo: "+avion.getModelo()+"\n";
	    			str+="\tNúmero de asientos: "+avion.getNumAsientos()+"\n";
	    			str+="\tVelocidad máxima: "+avion.getVelMaxima()+"\n";
	    			if(avion.isActivado()) {
	    				str+="\tActivado\n";
	    			}else {
	    				str+="\tDesactivado\n";
	    			}
	    		}
	    		str+="Privado\n";
	    		str+="Nº de socios: "+modelo.getNumSocios();
	    		
	    	}else {
	    		ModeloAeropuertoPublico modelo=idTablaPublico.getSelectionModel().getSelectedItem();
	    		str+="Nombre: "+modelo.getNombre()+"\n";
	    		str+="Pais: "+modelo.getDireccion().getPais()+"\n";
	    		str+="Direccion: "+modelo.getDireccion().getCalle()+" "+modelo.getDireccion().getNumero()+","+modelo.getDireccion().getCiudad()+"\n";
	    		str+="Año de inauguracion: "+modelo.getAnioInauguracion()+"\n";
	    		str+="Capacidad: "+modelo.getCapacidad()+"\n";
	    		str+="Aviones:\n";
	    		for(ModeloAvion avion:DaoAvion.listaAviones(DaoAeropuerto.conseguirID(
	    				modelo.getNombre(),modelo.getAnioInauguracion(),modelo.getCapacidad(),
	    				DaoDireccion.conseguirID(modelo.getDireccion().getPais(),modelo.
	    						getDireccion().getCiudad(),modelo.getDireccion().getCalle(),
	    						modelo.getDireccion().getNumero()), modelo.getImagen()))) {
	    			str+="\tModelo: "+avion.getModelo()+"\n";
	    			str+="\tNúmero de asientos: "+avion.getNumAsientos()+"\n";
	    			str+="\tVelocidad máxima: "+avion.getVelMaxima()+"\n";
	    			if(avion.isActivado()) {
	    				str+="\tActivado\n";
	    			}else {
	    				str+="\tDesactivado\n";
	    			}
	    		}
	    		str+="Público\n";
	    		str+="Financiacion: "+modelo.getFinanciacion()+"\n";
	    		str+="Número de trabajadores: "+modelo.getNumTrabajadores();
	    	}
	    	al.setContentText(str);
	    	al.showAndWait();
    	}
    }
    
    /**
     * Toggle avion.
     *
     * @param event the event
     */
    @FXML
    void toggleAvion(ActionEvent event) {
    	esBorrar=false;
    	s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(MainApp.class.getResource("/fxml/editarAvion.fxml"));
			scene = new Scene(controlador.load());
			s.setTitle("ACTIVAR / DESACTIVAR AVION");
			s.setScene(scene);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
        s.initOwner(MainApp.getStage());
        s.initModality(javafx.stage.Modality.WINDOW_MODAL);
        s.showAndWait();
    }
    
    @FXML
    void mostrarMenuContextual(MouseEvent event) {
    	if(event.getButton()==MouseButton.SECONDARY) {
    		contextMenu.show(MainApp.getStage(), event.getScreenX(), event.getScreenY());
    	}else {
    		if(event.getButton()==MouseButton.PRIMARY&&event.getClickCount()==2) {
    			mostarDatosAeropuerto(null);
    		}
    	}
    }
    
    /**
     * Initializa todas las tablas y crea la conexion con la base de datos.
     */
    @FXML
    private void initialize() {
    	try {
			ConexionBBDD con=new ConexionBBDD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	contextMenu = new ContextMenu();
    	MenuItem item1 = new MenuItem("Modificar");
        MenuItem item2 = new MenuItem("Eliminar");
        item1.setOnAction(event -> editarAeropuerto(event));
        item2.setOnAction(event -> borrarAeropuerto(event));
        contextMenu.getItems().addAll(item1,item2);
    	txtFiltro.setOnKeyReleased(event -> accionFiltrar());
		//Tabla publico
    	tblAnioPublico.setCellValueFactory(new PropertyValueFactory<>("anioInauguracion"));
    	tblCallePublico.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getDireccion().getCalle()));
    	tblCapacidadPublico.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
    	tblCiudadPublico.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getDireccion().getCiudad()));
    	tblFinanciacion.setCellValueFactory(new PropertyValueFactory<>("financiacion"));
    	tblIdPublico.setCellValueFactory(new PropertyValueFactory<>("id"));
    	tblNombrePublico.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	tblNumeroPublico.setCellValueFactory(cellData -> 
        new SimpleIntegerProperty(cellData.getValue().getDireccion().getNumero()).asObject());
    	tblPaisPublico.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getDireccion().getPais()));
    	tblNumeroTrabajadores.setCellValueFactory(new PropertyValueFactory<>("numTrabajadores"));
    	listaTodasPublico=DaoAeropuertoPublico.cargarListaAeropuertosPublicos();
    	filtroPublico=new FilteredList<ModeloAeropuertoPublico>(listaTodasPublico);
    	idTablaPublico.setItems(listaTodasPublico);
    	//Tabla privado
    	tblAnioPrivado.setCellValueFactory(new PropertyValueFactory<>("anioInauguracion"));
    	tblCallePrivado.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getDireccion().getCalle()));
    	tblCapacidadPrivado.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
    	tblCiudadPrivado.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getDireccion().getCiudad()));
    	tblIdPrivado.setCellValueFactory(new PropertyValueFactory<>("id"));
    	tblNombrePrivado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	tblNumeroPrivado.setCellValueFactory(cellData -> 
        new SimpleIntegerProperty(cellData.getValue().getDireccion().getNumero()).asObject());
    	tblPaisPrivado.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getDireccion().getPais()));
    	tblNumSocios.setCellValueFactory(new PropertyValueFactory<>("numSocios"));
    	listaTodasPrivado=DaoAeropuertoPrivado.cargarListaAeropuertosPrivados();
    	filtroPrivado=new FilteredList<ModeloAeropuertoPrivado>(listaTodasPrivado);
    	idTablaPrivado.setItems(listaTodasPrivado);
    }
    
    
    
    /**
     * Gets the s.
     *
     * @return the s
     */
    public static Stage getS() {
		return s;
	}
    
    /**
     * Gets the lista todas privado.
     *
     * @return the lista todas privado
     */
    public static ObservableList<ModeloAeropuertoPrivado> getListaTodasPrivado() {
		return listaTodasPrivado;
	}
    
    /**
     * Gets the lista todas publico.
     *
     * @return the lista todas publico
     */
    public static ObservableList<ModeloAeropuertoPublico> getListaTodasPublico() {
		return listaTodasPublico;
	}
    
    /**
     * Checks if is es aniadir.
     *
     * @return true, if is es aniadir
     */
    public static boolean isEsAniadir() {
		return esAniadir;
	}
    
    /**
     * Sets the lista todas privado.
     *
     * @param listaTodasPrivado the new lista todas privado
     */
    public static void setListaTodasPrivado(ObservableList<ModeloAeropuertoPrivado> listaTodasPrivado) {
		ListadoAeropuertosController.listaTodasPrivado = listaTodasPrivado;
	}
    
    /**
     * Sets the lista todas publico.
     *
     * @param listaTodasPublico the new lista todas publico
     */
    public static void setListaTodasPublico(ObservableList<ModeloAeropuertoPublico> listaTodasPublico) {
		ListadoAeropuertosController.listaTodasPublico = listaTodasPublico;
	}
    
    /**
     * Checks if is es publico.
     *
     * @return true, if is es publico
     */
    public static boolean isEsPublico() {
		return esPublico;
	}
    
    /**
     * Checks if is es borrar.
     *
     * @return true, if is es borrar
     */
    public static boolean isEsBorrar() {
		return esBorrar;
	}

}
