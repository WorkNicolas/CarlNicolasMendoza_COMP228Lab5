module ca.centennialcollege.lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.dotenv;
    requires com.oracle.database.jdbc;


    opens ca.centennialcollege.lab5 to javafx.fxml;
    exports ca.centennialcollege.lab5;
}