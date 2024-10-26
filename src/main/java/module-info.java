module com.backend.restaurant {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.gluonhq.attach.util;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.desktop;
    requires charm.glisten;

    opens com.backend.restaurant to javafx.fxml;
    exports com.backend.restaurant;

    opens com.backend.restaurant.controllers to javafx.fxml;
    exports com.backend.restaurant.controllers;

    exports com.backend.restaurant.models;

    opens com.backend.restaurant.services to javafx.fxml;
    exports com.backend.restaurant.services;

}