package com.taxiapp.desktop;

import java.util.function.Function;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;

public class TableHelper {
    public static  <T,E> TableColumn<E, String> createColumn(String columnName, Function<E, T> valueExtractor) {
        var column = new TableColumn<E, String>(columnName);
        column.setCellValueFactory(c -> new SimpleStringProperty(valueExtractor.apply(c.getValue()).toString()));
        return column;
    }

}
