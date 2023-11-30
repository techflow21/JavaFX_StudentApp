module com.sobtech.crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.sobtech.crud to javafx.fxml;
    exports com.sobtech.crud;
    exports com.sobtech.crud.data;
    exports com.sobtech.crud.model;
}
