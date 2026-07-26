module com.contactbook.contactbook {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.contactbook.contactbook to javafx.fxml;
    exports com.contactbook.contactbook;
}