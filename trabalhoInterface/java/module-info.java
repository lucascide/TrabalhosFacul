module com.example.sistemadeecommerce {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sistemadeecommerce to javafx.fxml;
    exports com.example.sistemadeecommerce;
}