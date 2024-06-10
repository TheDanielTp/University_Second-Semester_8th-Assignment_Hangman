module hangman {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires com.google.gson;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires org.json;
    requires com.microsoft.sqlserver.jdbc;
    requires org.postgresql.jdbc;

    opens hangman to javafx.fxml;
    exports hangman;
}