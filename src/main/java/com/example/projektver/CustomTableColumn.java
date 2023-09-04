package com.example.projektver;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TableColumn;


// Change name of first TableColumn
class CustomTableColumn extends TableColumn<DataRow, String> {

    StringProperty columnName = new SimpleStringProperty("");

    public CustomTableColumn() {

        // Set the initial column name
        setText(columnName.get());

        // Listen for changes in the columnName property
        columnName.addListener((obs, oldName, newName) -> setText(newName));


    }
}
