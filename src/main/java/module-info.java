module com.taxiapp.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;
    requires javafx.web;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    requires lombok;

    opens com.taxiapp.desktop to javafx.fxml;
    exports com.taxiapp.desktop;
    exports com.taxiapp.desktop.entities;
    opens com.taxiapp.desktop.entities to javafx.fxml;
    exports com.taxiapp.desktop.net.controllers;
    opens com.taxiapp.desktop.net.controllers to javafx.fxml;
    exports com.taxiapp.desktop.net.responses;
    opens com.taxiapp.desktop.net.responses to javafx.fxml;
    exports com.taxiapp.desktop.net.requests;
    exports com.taxiapp.desktop.menupages.dashboard.infopages;
    opens com.taxiapp.desktop.menupages.dashboard.infopages to javafx.fxml;
    exports com.taxiapp.desktop.menupages;
    opens com.taxiapp.desktop.menupages to javafx.fxml;
    exports com.taxiapp.desktop.menupages.dashboard;
    opens com.taxiapp.desktop.menupages.dashboard to javafx.fxml;

}