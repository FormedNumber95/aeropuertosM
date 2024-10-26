module es.aketzagonzalez {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
	requires javafx.base;
	requires javafx.graphics;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.ikonli.fontawesome5;
    opens ctrl to javafx.fxml;
    opens model to javafx.base;
    exports es.aketzagonzalez.aeropuertosM;
}