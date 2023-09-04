package com.example.projektver;

// Class for TableView tableViewParadigm
public class DataRow {

    private String column1;
    private String column2;
    private String column3;


    public DataRow(String column1, String column2, String column3) {
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
    }

    // Do not delete
    public String getColumn1() {
        return column1;
    }

    public String getColumn2() {
        return column2;
    }

    public String getColumn3() {
        return column3;
    }



    @Override
    public String toString() {
        return "DataRow{" +
                "column1='" + column1 + '\'' +
                ", column2='" + column2 + '\'' +
                ", column3='" + column3 + '\'' +
                '}';
    }
}

