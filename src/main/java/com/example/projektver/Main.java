package com.example.projektver;


import java.awt.image.BufferedImage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.imageio.ImageIO;


public class Main extends Application {

    private MenuBar menuBar = new MenuBar();

    private ComboBox<Integer> comboBoxVerseNr;
    private Button upButton;
    private Button downButton;
    private Button saveButton;
    private Label outputSaveLabel = new Label();
    private TextArea paliVerseTextArea;
    private TextArea paliVerseGERTextArea;
    private TextArea analysisTextArea;
    private TextArea notesTextArea = new TextArea();
    private TabPane tabPaneAnalysis = new TabPane();
    private Tab nounPronounTab = new Tab();
    private Tab adjectiveTabMasculine = new Tab();
    private Tab adjectiveTabFeminine = new Tab();
    private Tab adjectiveTabNeuter = new Tab();
    private Tab yaTabMasculine = new Tab();
    private Tab yaTabFeminine = new Tab();
    private Tab yaTabNeuter = new Tab();
    private Tab presentTab = new Tab();
    private Tab aoristTab = new Tab();
    private Tab futureTab = new Tab();
    private String os = System.getProperty("os.name").toLowerCase();

    TableView<DataRow> tableViewParadigmNounPronoun = new TableView<>();
    TableView<DataRow> tableViewParadigmAdjectiveMasculine = new TableView<>();
    TableView<DataRow> tableViewParadigmAdjectiveFeminine = new TableView<>();
    TableView<DataRow> tableViewParadigmAdjectiveNeuter = new TableView<>();
    TableView<DataRow> tableViewParadigmYaMasculine = new TableView<>();
    TableView<DataRow> tableViewParadigmYaFeminine = new TableView<>();
    TableView<DataRow> tableViewParadigmYaNeuter = new TableView<>();
    TableView<DataRow> tableViewPresent = new TableView<>();
    TableView<DataRow> tableViewAorist = new TableView<>();
    TableView<DataRow> tableViewFuture = new TableView<>();
    Stage searchResultsStage = new Stage();
    Stage searchStage = new Stage();

    private static final int MAX_TEXT_LENGTH = 500;


    private Connection connection;
    private String partOfSpeech = "";
    private String displayVerbAnalysis = "";
    private String displayVerbAnalysisAbsolutive = "";

    // Variables defined as class variables because used in methods
    private String paliWordNominativeSg = "";
    private String paliWordAccusativeSg = "";
    private String paliWordInstrumentalSg = "";
    private String paliWordDativeSg = "";
    private String paliWordAblativeSg = "";
    private String paliWordGenitiveSg = "";
    private String paliWordLocativeSg = "";
    private String paliWordVocativeSg = "";
    private String paliWordNominativePl = "";
    private String paliWordAccusativePl = "";
    private String paliWordInstrumentalPl = "";
    private String paliWordDativePl = "";
    private String paliWordAblativePl = "";
    private String paliWordGenitivePl = "";
    private String paliWordLocativePl = "";
    private String paliWordVocativePl = "";
    private String paliWordNominativeSgAdjFem = "";
    private String paliWordAccusativeSgAdjFem = "";
    private String paliWordInstrumentalSgAdjFem = "";
    private String paliWordDativeSgAdjFem = "";
    private String paliWordAblativeSgAdjFem = "";
    private String paliWordGenitiveSgAdjFem = "";
    private String paliWordLocativeSgAdjFem = "";
    private String paliWordVocativeSgAdjFem = "";
    private String paliWordNominativePlAdjFem = "";
    private String paliWordAccusativePlAdjFem = "";
    private String paliWordInstrumentalPlAdjFem = "";
    private String paliWordDativePlAdjFem = "";
    private String paliWordAblativePlAdjFem = "";
    private String paliWordGenitivePlAdjFem = "";
    private String paliWordLocativePlAdjFem = "";
    private String paliWordVocativePlAdjFem = "";
    private String paliWordNominativeSgAdjNeut = "";
    private String paliWordAccusativeSgAdjNeut = "";
    private String paliWordInstrumentalSgAdjNeut = "";
    private String paliWordDativeSgAdjNeut = "";
    private String paliWordAblativeSgAdjNeut = "";
    private String paliWordGenitiveSgAdjNeut = "";
    private String paliWordLocativeSgAdjNeut = "";
    private String paliWordVocativeSgAdjNeut = "";
    private String paliWordNominativePlAdjNeut = "";
    private String paliWordAccusativePlAdjNeut = "";
    private String paliWordInstrumentalPlAdjNeut = "";
    private String paliWordDativePlAdjNeut = "";
    private String paliWordAblativePlAdjNeut = "";
    private String paliWordGenitivePlAdjNeut = "";
    private String paliWordLocativePlAdjNeut = "";
    private String paliWordVocativePlAdjNeut = "";
    private String paliWordNominativeSgYaFem = "";
    private String paliWordAccusativeSgYaFem = "";
    private String paliWordInstrumentalSgYaFem = "";
    private String paliWordDativeSgYaFem = "";
    private String paliWordAblativeSgYaFem = "";
    private String paliWordGenitiveSgYaFem = "";
    private String paliWordLocativeSgYaFem = "";
    private String paliWordNominativePlYaFem = "";
    private String paliWordAccusativePlYaFem = "";
    private String paliWordInstrumentalPlYaFem = "";
    private String paliWordDativePlYaFem = "";
    private String paliWordAblativePlYaFem = "";
    private String paliWordGenitivePlYaFem = "";
    private String paliWordLocativePlYaFem = "";
    private String paliWordNominativeSgYaNeut = "";
    private String paliWordAccusativeSgYaNeut = "";
    private String paliWordInstrumentalSgYaNeut = "";
    private String paliWordDativeSgYaNeut = "";
    private String paliWordAblativeSgYaNeut = "";
    private String paliWordGenitiveSgYaNeut = "";
    private String paliWordLocativeSgYaNeut = "";
    private String paliWordNominativePlYaNeut = "";
    private String paliWordAccusativePlYaNeut = "";
    private String paliWordInstrumentalPlYaNeut = "";
    private String paliWordDativePlYaNeut = "";
    private String paliWordAblativePlYaNeut = "";
    private String paliWordGenitivePlYaNeut = "";
    private String paliWordLocativePlYaNeut = "";
    private String paliWordNominativeSgRoot2 = "";
    private String paliWordAccusativeSgRoot2 = "";
    private String paliWordInstrumentalSgRoot2 = "";
    private String paliWordDativeSgRoot2 = "";
    private String paliWordAblativeSgRoot2 = "";
    private String paliWordGenitiveSgRoot2 = "";
    private String paliWordLocativeSgRoot2 = "";
    private String paliWordVocativeSgRoot2 = "";
    private String paliWordNominativePlRoot2 = "";
    private String paliWordAccusativePlRoot2 = "";
    private String paliWordInstrumentalPlRoot2 = "";
    private String paliWordDativePlRoot2 = "";
    private String paliWordAblativePlRoot2 = "";
    private String paliWordGenitivePlRoot2 = "";
    private String paliWordLocativePlRoot2 = "";
    private String paliWord1PSgPresent = "";
    private String paliWord1PPlPresent = "";
    private String paliWord2PSgPresent = "";
    private String paliWord2PPlPresent = "";
    private String paliWord3PSgPresent = "";
    private String paliWord3PPlPresent = "";
    private String paliWord1PSgFuture = "";
    private String paliWord1PPlFuture = "";
    private String paliWord2PSgFuture = "";
    private String paliWord2PPlFuture = "";
    private String paliWord3PSgFuture = "";
    private String paliWord3PPlFuture = "";
    private String stringPrefixA = "a";
    private String root = "";
    private String paliWord1PSgAorist = "";
    private String paliWord2PSgAorist = "";
    private String paliWord3PSgAorist = "";
    private String paliWord1PPlAorist = "";
    private String paliWord2PPlAorist = "";
    private String paliWord3PPlAorist = "";
    private String root2 = "";
    private String paliWord1PSgRoot2Present = "";
    private String paliWord2PSgRoot2Present = "";
    private String paliWord3PSgRoot2Present = "";
    private String paliWord1PPlRoot2Present = "";
    private String paliWord2PPlRoot2Present = "";
    private String paliWord3PPlRoot2Present = "";
    private String paliWord1PSgAoristRoot2NoPrefix = "";
    private String paliWord2PSgAoristRoot2NoPrefix = "";
    private String paliWord3PSgAoristRoot2NoPrefix = "";
    private String paliWord1PPlAoristRoot2NoPrefix = "";
    private String paliWord2PPlAoristRoot2NoPrefix = "";
    private String paliWord3PPlAoristRoot2NoPrefix = "";
    private String paliWord1PSgFutureRoot2 = "";
    private String paliWord2PSgFutureRoot2 = "";
    private String paliWord3PSgFutureRoot2 = "";
    private String paliWord1PPlFutureRoot2 = "";
    private String paliWord2PPlFutureRoot2 = "";
    private String paliWord3PPlFutureRoot2 = "";
    private String root3 = "";
    private String paliWord1PSgAoristKr = "";
    private String paliWord2PSgAoristKr = "";
    private String paliWord3PSgAoristKr = "";
    private String paliWord1PPlAoristKr = "";
    private String paliWord2PPlAoristKr = "";
    private String paliWord3PPlAoristKr = "";
    private String paliWord1PSgFutureRoot3 = "";
    private String paliWord2PSgFutureRoot3 = "";
    private String paliWord3PSgFutureRoot3 = "";
    private String paliWord1PPlFutureRoot3 = "";
    private String paliWord2PPlFutureRoot3 = "";
    private String paliWord3PPlFutureRoot3 = "";
    private String paliWord1PSgAoristRoot2PrefixA = "";
    private String paliWord2PSgAoristRoot2PrefixA = "";
    private String paliWord3PSgAoristRoot2PrefixA = "";
    private String paliWord1PPlAoristRoot2PrefixA = "";
    private String paliWord2PPlAoristRoot2PrefixA = "";
    private String paliWord3PPlAoristRoot2PrefixA = "";
    private int[] minMaxVerseArray = new int[]{0};


    public static void main(String[] args) {
        launch(args);


    }


    @Override
    public void start(Stage stage) throws SQLException, IOException {

        // Connect to DB
        connection = DatabaseManager.connectToDB();

        DatabaseManager databaseManager = new DatabaseManager();


        // Create backup of DB

        if (os.contains("win")) {

            DatabaseManager.createDatabaseBackup(databaseManager.getDB_URLCreateDatabaseBackupSourceWindows(),
                    databaseManager.getDB_URL_BackupCreateDatabaseBackupTargetWindows());
        } else {

            DatabaseManager.createDatabaseBackup(databaseManager.getDB_URLCreateDatabaseBackupSourceLinux(),
                    databaseManager.getDB_URL_BackupCreateDatabaseBackupTargetLinux());
        }

        // Create instances
        // Instance must be created after connection is established
        WordAnalysis instanceOfWordAnalysis = new WordAnalysis(connection);
        Paradigm instanceOfParadigm = new Paradigm(connection);


        // Query of min/max verse to enable/disable up- and down arrow button
        minMaxVerseArray = instanceOfParadigm.getMinMaxVerse(connection);


        // Empty placeholder for TableView
//        tableViewParadigmNounPronoun.setPlaceholder(new Label(""));

        // Hide column header
        tableViewParadigmNounPronoun.getStyleClass().add("noheader");
        tableViewParadigmAdjectiveMasculine.getStyleClass().add("noheader");
        tableViewParadigmAdjectiveFeminine.getStyleClass().add("noheader");
        tableViewParadigmAdjectiveNeuter.getStyleClass().add("noheader");
        tableViewParadigmYaMasculine.getStyleClass().add("noheader");
        tableViewParadigmYaFeminine.getStyleClass().add("noheader");
        tableViewParadigmYaNeuter.getStyleClass().add("noheader");
        tableViewPresent.getStyleClass().add("noheader");
        tableViewAorist.getStyleClass().add("noheader");
        tableViewFuture.getStyleClass().add("noheader");


        // Create the TableView and columns

        // Instantiate column1 with the updated columnName
        // Custom table names

        // NounPronoun Paradigm
        CustomTableColumn column1NounPronounAdjective = new CustomTableColumn();
        CustomTableColumn column2NounPronounAdjective = new CustomTableColumn();
        CustomTableColumn column3NounPronounAdjective = new CustomTableColumn();

        // Adjective masculine

        CustomTableColumn column1AdjectiveMasculine = new CustomTableColumn();
        CustomTableColumn column2AdjectiveMasculine = new CustomTableColumn();
        CustomTableColumn column3AdjectiveMasculine = new CustomTableColumn();

        // Adjective feminine

        CustomTableColumn column1AdjectiveFeminine = new CustomTableColumn();
        CustomTableColumn column2AdjectiveFeminine = new CustomTableColumn();
        CustomTableColumn column3AdjectiveFeminine = new CustomTableColumn();

        // Adjective neuter

        CustomTableColumn column1AdjectiveNeuter = new CustomTableColumn();
        CustomTableColumn column2AdjectiveNeuter = new CustomTableColumn();
        CustomTableColumn column3AdjectiveNeuter = new CustomTableColumn();

        // Ya masc

        CustomTableColumn column1YaMasculine = new CustomTableColumn();
        CustomTableColumn column2YaMasculine = new CustomTableColumn();
        CustomTableColumn column3YaMasculine = new CustomTableColumn();

        // Ya fem

        CustomTableColumn column1YaFeminine = new CustomTableColumn();
        CustomTableColumn column2YaFeminine = new CustomTableColumn();
        CustomTableColumn column3YaFeminine = new CustomTableColumn();

        // Ya neut

        CustomTableColumn column1YaNeuter = new CustomTableColumn();
        CustomTableColumn column2YaNeuter = new CustomTableColumn();
        CustomTableColumn column3YaNeuter = new CustomTableColumn();

        // Verbs present

        CustomTableColumn column1Present = new CustomTableColumn();
        CustomTableColumn column2Present = new CustomTableColumn();
        CustomTableColumn column3Present = new CustomTableColumn();

        // Verbs aorist

        CustomTableColumn column1Aorist = new CustomTableColumn();
        CustomTableColumn column2Aorist = new CustomTableColumn();
        CustomTableColumn column3Aorist = new CustomTableColumn();

        // Verbs future
        CustomTableColumn column1Future = new CustomTableColumn();
        CustomTableColumn column2Future = new CustomTableColumn();
        CustomTableColumn column3Future = new CustomTableColumn();

        // Set the data sources for the columns
        column1NounPronounAdjective.setCellValueFactory(new PropertyValueFactory<>("column1"));
        column2NounPronounAdjective.setCellValueFactory(new PropertyValueFactory<>("column2"));
        column3NounPronounAdjective.setCellValueFactory(new PropertyValueFactory<>("column3"));

        column1AdjectiveMasculine.setCellValueFactory(new PropertyValueFactory<>("column1"));
        column2AdjectiveMasculine.setCellValueFactory(new PropertyValueFactory<>("column2"));
        column3AdjectiveMasculine.setCellValueFactory(new PropertyValueFactory<>("column3"));

        column1AdjectiveFeminine.setCellValueFactory(new PropertyValueFactory<>("column1"));
        column2AdjectiveFeminine.setCellValueFactory(new PropertyValueFactory<>("column2"));
        column3AdjectiveFeminine.setCellValueFactory(new PropertyValueFactory<>("column3"));

        column1AdjectiveNeuter.setCellValueFactory(new PropertyValueFactory<>("column1"));
        column2AdjectiveNeuter.setCellValueFactory(new PropertyValueFactory<>("column2"));
        column3AdjectiveNeuter.setCellValueFactory(new PropertyValueFactory<>("column3"));

        column1YaMasculine.setCellValueFactory(new PropertyValueFactory<>("column1"));
        column2YaMasculine.setCellValueFactory(new PropertyValueFactory<>("column2"));
        column3YaMasculine.setCellValueFactory(new PropertyValueFactory<>("column3"));

        column1YaFeminine.setCellValueFactory(new PropertyValueFactory<>("column1"));
        column2YaFeminine.setCellValueFactory(new PropertyValueFactory<>("column2"));
        column3YaFeminine.setCellValueFactory(new PropertyValueFactory<>("column3"));

        column1YaNeuter.setCellValueFactory(new PropertyValueFactory<>("column1"));
        column2YaNeuter.setCellValueFactory(new PropertyValueFactory<>("column2"));
        column3YaNeuter.setCellValueFactory(new PropertyValueFactory<>("column3"));

        column1Present.setCellValueFactory(new PropertyValueFactory<>("column1"));
        column2Present.setCellValueFactory(new PropertyValueFactory<>("column2"));
        column3Present.setCellValueFactory(new PropertyValueFactory<>("column3"));

        column1Aorist.setCellValueFactory(new PropertyValueFactory<>("column1"));
        column2Aorist.setCellValueFactory(new PropertyValueFactory<>("column2"));
        column3Aorist.setCellValueFactory(new PropertyValueFactory<>("column3"));

        column1Future.setCellValueFactory(new PropertyValueFactory<>("column1"));
        column2Future.setCellValueFactory(new PropertyValueFactory<>("column2"));
        column3Future.setCellValueFactory(new PropertyValueFactory<>("column3"));

        // Add the columns to the TableView
        tableViewParadigmNounPronoun.getColumns().add(column1NounPronounAdjective);
        tableViewParadigmNounPronoun.getColumns().add(column2NounPronounAdjective);
        tableViewParadigmNounPronoun.getColumns().add(column3NounPronounAdjective);

        tableViewParadigmAdjectiveMasculine.getColumns().add(column1AdjectiveMasculine);
        tableViewParadigmAdjectiveMasculine.getColumns().add(column2AdjectiveMasculine);
        tableViewParadigmAdjectiveMasculine.getColumns().add(column3AdjectiveMasculine);

        tableViewParadigmAdjectiveFeminine.getColumns().add(column1AdjectiveFeminine);
        tableViewParadigmAdjectiveFeminine.getColumns().add(column2AdjectiveFeminine);
        tableViewParadigmAdjectiveFeminine.getColumns().add(column3AdjectiveFeminine);

        tableViewParadigmAdjectiveNeuter.getColumns().add(column1AdjectiveNeuter);
        tableViewParadigmAdjectiveNeuter.getColumns().add(column2AdjectiveNeuter);
        tableViewParadigmAdjectiveNeuter.getColumns().add(column3AdjectiveNeuter);

        tableViewParadigmYaMasculine.getColumns().add(column1YaMasculine);
        tableViewParadigmYaMasculine.getColumns().add(column2YaMasculine);
        tableViewParadigmYaMasculine.getColumns().add(column3YaMasculine);

        tableViewParadigmYaFeminine.getColumns().add(column1YaFeminine);
        tableViewParadigmYaFeminine.getColumns().add(column2YaFeminine);
        tableViewParadigmYaFeminine.getColumns().add(column3YaFeminine);

        tableViewParadigmYaNeuter.getColumns().add(column1YaNeuter);
        tableViewParadigmYaNeuter.getColumns().add(column2YaNeuter);
        tableViewParadigmYaNeuter.getColumns().add(column3YaNeuter);

        tableViewPresent.getColumns().add(column1Present);
        tableViewPresent.getColumns().add(column2Present);
        tableViewPresent.getColumns().add(column3Present);

        tableViewAorist.getColumns().add(column1Aorist);
        tableViewAorist.getColumns().add(column2Aorist);
        tableViewAorist.getColumns().add(column3Aorist);

        tableViewFuture.getColumns().add(column1Future);
        tableViewFuture.getColumns().add(column2Future);
        tableViewFuture.getColumns().add(column3Future);


        // Resize table according to size of content
        tableViewParadigmNounPronoun.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewParadigmAdjectiveMasculine.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewParadigmAdjectiveFeminine.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewParadigmAdjectiveNeuter.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewParadigmYaMasculine.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewParadigmYaFeminine.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewParadigmYaNeuter.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewPresent.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewAorist.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewFuture.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Make first row and column of table bold
        column1NounPronounAdjective.setStyle("-fx-font-weight: bold;");
        styleFirstRow(tableViewParadigmNounPronoun);
        column1AdjectiveMasculine.setStyle("-fx-font-weight: bold;");
        styleFirstRow(tableViewParadigmAdjectiveMasculine);
        column1AdjectiveFeminine.setStyle("-fx-font-weight: bold;");
        styleFirstRow(tableViewParadigmAdjectiveFeminine);
        column1AdjectiveNeuter.setStyle("-fx-font-weight: bold;");
        styleFirstRow(tableViewParadigmAdjectiveNeuter);
        column1YaMasculine.setStyle("-fx-font-weight: bold;");
        styleFirstRow(tableViewParadigmYaMasculine);
        column1YaFeminine.setStyle("-fx-font-weight: bold;");
        styleFirstRow(tableViewParadigmYaFeminine);
        column1YaNeuter.setStyle("-fx-font-weight: bold;");
        styleFirstRow(tableViewParadigmYaNeuter);
        column1Present.setStyle("-fx-font-weight: bold;");
        styleFirstRow(tableViewPresent);
        column1Aorist.setStyle("-fx-font-weight: bold;");
        styleFirstRow(tableViewAorist);
        column1Future.setStyle("-fx-font-weight: bold;");
        styleFirstRow(tableViewFuture);


        // Create ComboBox
        comboBoxVerseNr = new ComboBox<>();
        comboBoxVerseNr.getStyleClass().add("combo-box");


        // Set Buttons
        upButton = new Button("\u2191");
        upButton.setFont(Font.font(19));
        upButton.setDisable(true);
        upButton.getStyleClass().add("plastic-button");


        downButton = new Button("\u2193");
        downButton.setFont(Font.font(19));
        downButton.getStyleClass().add("plastic-button");

        // Create TextAreas
        paliVerseTextArea = new TextArea();
        paliVerseGERTextArea = new TextArea();
        analysisTextArea = new TextArea();

        // TextArea is not editable
        paliVerseTextArea.setEditable(false);
        paliVerseGERTextArea.setEditable(false);
        analysisTextArea.setEditable(false);

        // Activate line break
        paliVerseTextArea.setWrapText(true);
        paliVerseGERTextArea.setWrapText(true);
        analysisTextArea.setWrapText(true);

        // Set TextArea background color
        paliVerseTextArea.setStyle("-fx-control-inner-background: lightgrey;");
        paliVerseGERTextArea.setStyle("-fx-control-inner-background: lightgrey;");
        analysisTextArea.setStyle("-fx-control-inner-background: lightgrey;");
        notesTextArea.setStyle("-fx-control-inner-background: white;");

        notesTextArea.setWrapText(true);

        // Restrict max. number of characters to 500 of notes text area
        notesTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > MAX_TEXT_LENGTH) {
                notesTextArea.setText(newValue.substring(0, MAX_TEXT_LENGTH));
                // outputSaveLabel.setTextFill(Paint.valueOf("blue"));
                outputSaveLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-padding: 8 0 0 0;");
                outputSaveLabel.setText("Maximale Anzahl von 500 Zeichen erreicht.");
            } else {
                outputSaveLabel.setText("");

            }
        });


        saveButton = new Button("Speichern");
        saveButton.getStyleClass().add("plastic-buttonSave");

        saveButton.setOnAction(e -> {
            saveTextToDatabase();
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);


        Menu fileMenu = new Menu("Datei");
        Menu editMenu = new Menu("Bearbeiten");
        Menu searchMenu = new Menu("Suchen");
        Menu verseMenu = new Menu("Vers");
        Menu helpMenu = new Menu("Hilfe");


        // Create menuItem "Beenden"
        MenuItem exitMenuItem = new MenuItem("Beenden");
        // Set close request event
        stage.setOnCloseRequest(event -> {
            event.consume(); // Prevent automatic window closing
            showConfirmationDialog(stage);
        });


        exitMenuItem.setOnAction(event -> showConfirmationDialog(stage));


        menuBar.setStyle("-fx-background-color: linear-gradient(to bottom, #B0C4DE, #F8F8FF);" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0);" +
                "-fx-padding: 4;");

        menuBar.getMenus().addAll(fileMenu, editMenu, verseMenu, searchMenu, helpMenu);
        Menu importMenu = new Menu("Daten importieren");
        Menu exportMenu = new Menu("Daten exportieren");
        MenuItem PaliVerseImport = new MenuItem("Dhammapada-Verse (Pali) importieren");
        MenuItem PaliVerseGERImport = new MenuItem("Dt. Übersetzung von Dhammapada-Versen importieren");
        MenuItem translationPaliWordGERImport = new MenuItem("Dt. Wörterbucheinträge zu Paliwörtern importieren");
        MenuItem exportPaliVerseMenuItem = new MenuItem("Paliverse exportieren");
        MenuItem exportPaliVerseGERMenuItem = new MenuItem("Dt. Übersetzung von Dhammapada-Versen exportieren");
        MenuItem exportDatabaseCompleteMenuItem = new MenuItem("Gesamtdatenbank exportieren");
        MenuItem searchMenuItem = new MenuItem("In Paliversen suchen");
        MenuItem searchGERMenuItem = new MenuItem("In dt. Übersetzung von Paliversen suchen");
        MenuItem ruckgangigMenuItem = new MenuItem("Rückgängig");
        MenuItem vorwartsMenuItem = new MenuItem("Vorwärts");
        MenuItem decompositionMenuItem = new MenuItem("Dekomposition Pali");
        MenuItem engTranslationMenuItem = new MenuItem("Eng. Übersetzung von Palivers anzeigen");
        MenuItem gerTranslationMenuItem = new MenuItem("Dt. Übersetzung von Palivers anzeigen");
        MenuItem aboutMenuItem = new MenuItem("Über Dhammapada Reader");

        PaliVerseImport.setOnAction(e -> showImportDialog("Paliverse"));
        PaliVerseGERImport.setOnAction(e -> showImportDialog("PaliverseGER"));
        translationPaliWordGERImport.setOnAction(e -> showImportDialog("TranslationPaliWordGER"));
        exportPaliVerseMenuItem.setOnAction(event -> exportPaliVerse(stage));
        exportPaliVerseGERMenuItem.setOnAction(event -> exportPaliVerseGER(stage));
        exportDatabaseCompleteMenuItem.setOnAction(event -> exportDatabaseComplete(stage));

        ruckgangigMenuItem.setOnAction(e -> {
            notesTextArea.undo();
        });

        vorwartsMenuItem.setOnAction(e -> {
            notesTextArea.redo();
        });


        importMenu.getItems().add(PaliVerseImport);
        importMenu.getItems().add(PaliVerseGERImport);
        importMenu.getItems().add(translationPaliWordGERImport);

        exportMenu.getItems().add(exportPaliVerseMenuItem);
        exportMenu.getItems().add(exportPaliVerseGERMenuItem);
        exportMenu.getItems().add(exportDatabaseCompleteMenuItem);


        fileMenu.getItems().add(importMenu);
        fileMenu.getItems().add(exportMenu);
        fileMenu.getItems().add(exitMenuItem);

        searchMenu.getItems().add(searchMenuItem);
        searchMenu.getItems().add(searchGERMenuItem);

        editMenu.getItems().addAll(ruckgangigMenuItem, vorwartsMenuItem);

        verseMenu.getItems().add(decompositionMenuItem);
        verseMenu.getItems().add(engTranslationMenuItem);
        verseMenu.getItems().add(gerTranslationMenuItem);

        helpMenu.getItems().add(aboutMenuItem);

        searchMenuItem.setOnAction(event -> searchVerse("Pali"));
        searchGERMenuItem.setOnAction(event -> searchVerse("Deutsch"));

        aboutMenuItem.setOnAction(event -> aboutDhammapadaReader());


        decompositionMenuItem.setOnAction(event -> {
            try {
                if (comboBoxVerseNr.getValue() == null) {
                    Stage Stage = null;
                    showInformationDialogPaliVerseEN(Stage);
                    return;
                }
                loadTextFromPaliTextDecomposed();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        engTranslationMenuItem.setOnAction(event -> {
            try {
                loadTextFromPaliVerseEN();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        gerTranslationMenuItem.setOnAction(event -> {
            try {
                if (comboBoxVerseNr.getValue() == null) {
                    Stage Stage = null;
                    showInformationDialogPaliVerseEN(Stage);
                    return;
                }

                loadTextFromPaliVerseGER();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


        VBox vbox = new VBox(menuBar, gridPane);


        HBox comboBoxArrowButtons = new HBox(comboBoxVerseNr, upButton, downButton);
        comboBoxArrowButtons.setSpacing(10);


        Label verseNrLabel = new Label("Vers Nummer:");
        verseNrLabel.setStyle("-fx-text-fill: #800080;");
        gridPane.add(verseNrLabel, 0, 0);
        gridPane.add(comboBoxArrowButtons, 1, 0);

        Label palitextLabel = new Label("Pali:");
        palitextLabel.setStyle("-fx-text-fill: #800080;");
        palitextLabel.setMinWidth(100);

        gridPane.add(palitextLabel, 0, 1);


        gridPane.add(paliVerseTextArea, 1, 1);

        Label translationGERLabel = new Label("Deutsch:");
        translationGERLabel.setStyle("-fx-text-fill: #800080;");
        translationGERLabel.setMinWidth(150);


        gridPane.add(translationGERLabel, 0, 2);

        gridPane.add(paliVerseGERTextArea, 1, 2);
        Label analysisLabel = new Label("Wortanalyse:");
        analysisLabel.setStyle("-fx-text-fill: #800080;");
        gridPane.add(analysisLabel, 2, 0);
        gridPane.add(analysisTextArea, 2, 1);


        gridPane.add(tabPaneAnalysis, 2, 2, 1, 2);

        Label notesLabel = new Label("Notizen:");
        notesLabel.setStyle("-fx-text-fill: #800080;");

        notesTextArea.setEditable(true);
        // Show notesTextArea and saveButton only if Verse is selected

        notesTextArea.setVisible(false);
        saveButton.setVisible(false);


        notesTextArea.setPromptText("Notizen hinzufügen...");


        HBox saveButtonOutputSaveLabel = new HBox(saveButton, outputSaveLabel);
        saveButtonOutputSaveLabel.setSpacing(10);

        gridPane.add(notesLabel, 0, 3);
        gridPane.add(notesTextArea, 1, 3);
        gridPane.add(saveButtonOutputSaveLabel, 1, 4);


        try {
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("Buddha_verkleinert_hälfte_hellblau.png");
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            WritableImage image = convertToJavaFXImage(bufferedImage);

            ImageView imageView = new ImageView(image);
            gridPane.add(imageView, 2, 4);

            // Set alignment of ImageView right justified
            GridPane.setHalignment(imageView, HPos.RIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Show notesTextArea and saveButton only if verse is selected
        comboBoxVerseNr.valueProperty().addListener((observable, oldValue, newValue) -> {

            boolean isVisible = newValue != null;
            notesTextArea.setVisible(isVisible);
            saveButton.setVisible(isVisible);

        });


        nounPronounTab.setContent(tableViewParadigmNounPronoun);
        adjectiveTabMasculine.setContent(tableViewParadigmAdjectiveMasculine);
        adjectiveTabFeminine.setContent(tableViewParadigmAdjectiveFeminine);
        adjectiveTabNeuter.setContent(tableViewParadigmAdjectiveNeuter);
        yaTabMasculine.setContent(tableViewParadigmYaMasculine);
        yaTabFeminine.setContent(tableViewParadigmYaFeminine);
        yaTabNeuter.setContent(tableViewParadigmYaNeuter);
        presentTab.setContent(tableViewPresent);
        aoristTab.setContent(tableViewAorist);
        futureTab.setContent(tableViewFuture);

        // Fill combobox automatically
        fillComboBox();


        // Load text automatically based on chosen number
        comboBoxVerseNr.setOnAction(event -> {
            try {
                if (connection.isClosed())
                    connection = DatabaseManager.connectToDB();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                loadTextFromPaliText();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                loadTextFromPaliVerseGER();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                loadNotesFromPaliVerse();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        });

        upButton.setOnAction(event -> {
            Integer selectedNumber = comboBoxVerseNr.getValue();
            if (selectedNumber == null) {
                return;
            }
            int currentIndex = comboBoxVerseNr.getItems().indexOf(selectedNumber);
            if (currentIndex > 0) {
                comboBoxVerseNr.getSelectionModel().select(currentIndex - 1);
                try {
                    loadTextFromPaliText();
                    loadTextFromPaliVerseGER();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            clearAnalysisTextArea();


        });

        downButton.setOnAction(event -> {

            Integer selectedNumber = comboBoxVerseNr.getValue();
            int currentIndex = selectedNumber != null ? comboBoxVerseNr.getItems().indexOf(selectedNumber) : -1;
            int maxIndex = comboBoxVerseNr.getItems().size() - 1;
            if (currentIndex < maxIndex) {
                if (currentIndex == -1) {
                    comboBoxVerseNr.getSelectionModel().selectFirst();
                } else {
                    comboBoxVerseNr.getSelectionModel().select(currentIndex + 1);
                }
                try {
                    loadTextFromPaliText();
                    loadTextFromPaliVerseGER();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            clearAnalysisTextArea();

        });


        //  Listener which spots marking changes
        paliVerseTextArea.setOnMouseClicked(event -> {


            if (!paliVerseTextArea.getSelectedText().isEmpty()) {
                String selectedText = paliVerseTextArea.getSelectedText();


                // Pass parameters to methods to check DB for selected text and show respective data
                // Access by instance "wordAnalysis"

                String gender = instanceOfWordAnalysis.genderAnalysis(selectedText);
                String translation = instanceOfWordAnalysis.translationGERAnalysis(selectedText);
                root = instanceOfWordAnalysis.rootAnalysis(selectedText);
                root2 = instanceOfWordAnalysis.root2Analysis(selectedText);
                root3 = instanceOfWordAnalysis.root3Analysis(selectedText);
                String root4 = instanceOfWordAnalysis.root4Analysis(selectedText);
                partOfSpeech = instanceOfParadigm.queryPartOfSpeech(selectedText);
                String mood = instanceOfParadigm.queryMood(selectedText);
                String tense = instanceOfParadigm.queryTense(selectedText);


                // Query respective case ending of Pali word and put into respective String

                // Nouns, Pronouns, Adjectives

                // Query Case, Number and Person


                String case1 = instanceOfWordAnalysis.queryCase(selectedText);
                String person = instanceOfWordAnalysis.queryPerson(selectedText);
                String number = instanceOfWordAnalysis.queryNumber(selectedText);
                Integer compound = instanceOfParadigm.queryCompound(selectedText);


                // Query Case endings and save in String Array
                String[] caseEndings = instanceOfParadigm.queryCaseEndings(selectedText);

                // Query Root / Root2 of Pali word adjectives


                String[] caseEndingsRoot = instanceOfParadigm.queryCaseEndings(root);
                String[] caseEndingsRoot2 = instanceOfParadigm.queryCaseEndings(root2);

                // Output of String Array of methods caseEndings to Strings
                // Singular


                String nominativeSgEnding = caseEndings[0];
                String accusativeSgEnding = caseEndings[1];
                String instrumentalSgEnding = caseEndings[2];
                String dativeSgEnding = caseEndings[3];
                String ablativeSgEnding = caseEndings[4];
                String genitiveSgEnding = caseEndings[5];
                String locativeSgEnding = caseEndings[6];
                String vocativeSgEnding = caseEndings[7];

                // Plural
                String nominativePlEnding = caseEndings[8];
                String accusativePlEnding = caseEndings[9];
                String instrumentalPlEnding = caseEndings[10];
                String dativePlEnding = caseEndings[11];
                String ablativePlEnding = caseEndings[12];
                String genitivePlEnding = caseEndings[13];
                String locativePlEnding = caseEndings[14];
                String vocativePlEnding = caseEndings[15];

                // Adjective Fem


                // Singular

                String nominativeSgEndingAdjFem = caseEndingsRoot2[0];
                String accusativeSgEndingAdjFem = caseEndingsRoot2[1];
                String instrumentalSgEndingAdjFem = caseEndingsRoot2[2];
                String dativeSgEndingAdjFem = caseEndingsRoot2[3];
                String ablativeSgEndingAdjFem = caseEndingsRoot2[4];
                String genitiveSgEndingAdjFem = caseEndingsRoot2[5];
                String locativeSgEndingAdjFem = caseEndingsRoot2[6];
                String vocativeSgEndingAdjFem = caseEndingsRoot2[7];

                // Plural

                String nominativePlEndingAdjFem = caseEndingsRoot2[8];
                String accusativePlEndingAdjFem = caseEndingsRoot2[9];
                String instrumentalPlEndingAdjFem = caseEndingsRoot2[10];
                String dativePlEndingAdjFem = caseEndingsRoot2[11];
                String ablativePlEndingAdjFem = caseEndingsRoot2[12];
                String genitivePlEndingAdjFem = caseEndingsRoot2[13];
                String locativePlEndingAdjFem = caseEndingsRoot2[14];
                String vocativePlEndingAdjFem = caseEndingsRoot2[15];

                // Adjective Net

                // Singular

                String nominativeSgEndingAdjNeut = caseEndingsRoot[0];
                String accusativeSgEndingAdjNeut = caseEndingsRoot[1];
                String instrumentalSgEndingAdjNeut = caseEndingsRoot[2];
                String dativeSgEndingAdjNeut = caseEndingsRoot[3];
                String ablativeSgEndingAdjNeut = caseEndingsRoot[4];
                String genitiveSgEndingAdjNeut = caseEndingsRoot[5];
                String locativeSgEndingAdjNeut = caseEndingsRoot[6];
                String vocativeSgEndingAdjNeut = caseEndingsRoot[7];

                // Plural

                String nominativePlEndingAdjNeut = caseEndingsRoot[8];
                String accusativePlEndingAdjNeut = caseEndingsRoot[9];
                String instrumentalPlEndingAdjNeut = caseEndingsRoot[10];
                String dativePlEndingAdjNeut = caseEndingsRoot[11];
                String ablativePlEndingAdjNeut = caseEndingsRoot[12];
                String genitivePlEndingAdjNeut = caseEndingsRoot[13];
                String locativePlEndingAdjNeut = caseEndingsRoot[14];
                String vocativePlEndingAdjNeut = caseEndingsRoot[15];

                // Ya Fem

                String nominativeSgEndingYaFem = caseEndingsRoot[0];
                String accusativeSgEndingYaFem = caseEndingsRoot[1];
                String instrumentalSgEndingYaFem = caseEndingsRoot[2];
                String dativeSgEndingYaFem = caseEndingsRoot[3];
                String ablativeSgEndingYaFem = caseEndingsRoot[4];
                String genitiveSgEndingYaFem = caseEndingsRoot[5];
                String locativeSgEndingYaFem = caseEndingsRoot[6];

                // Plural
                String nominativePlEndingYaFem = caseEndingsRoot[8];
                String accusativePlEndingYaFem = caseEndingsRoot[9];
                String instrumentalPlEndingYaFem = caseEndingsRoot[10];
                String dativePlEndingYaFem = caseEndingsRoot[11];
                String ablativePlEndingYaFem = caseEndingsRoot[12];
                String genitivePlEndingYaFem = caseEndingsRoot[13];
                String locativePlEndingYaFem = caseEndingsRoot[14];

                // Ya Neut

                String nominativeSgEndingYaNeut = caseEndingsRoot2[0];
                String accusativeSgEndingYaNeut = caseEndingsRoot2[1];
                String instrumentalSgEndingYaNeut = caseEndingsRoot2[2];
                String dativeSgEndingYaNeut = caseEndingsRoot2[3];
                String ablativeSgEndingYaNeut = caseEndingsRoot2[4];
                String genitiveSgEndingYaNeut = caseEndingsRoot2[5];
                String locativeSgEndingYaNeut = caseEndingsRoot2[6];

                // Plural
                String nominativePlEndingYaNeut = caseEndingsRoot2[8];
                String accusativePlEndingYaNeut = caseEndingsRoot2[9];
                String instrumentalPlEndingYaNeut = caseEndingsRoot2[10];
                String dativePlEndingYaNeut = caseEndingsRoot2[11];
                String ablativePlEndingYaNeut = caseEndingsRoot2[12];
                String genitivePlEndingYaNeut = caseEndingsRoot2[13];
                String locativePlEndingYaNeut = caseEndingsRoot2[14];


                // Verbs endings

                // Query verb endings present and save in String Array

                String[] personPresentEndings = instanceOfParadigm.queryPersonPresentEndings(selectedText);


                // Present
                String firstPSgEnding = personPresentEndings[0];
                String secondPSgEnding = personPresentEndings[1];
                String thirdPSgEnding = personPresentEndings[2];
                String firstPPlEnding = personPresentEndings[3];
                String secondPPlEnding = personPresentEndings[4];
                String thirdPPlEnding = personPresentEndings[5];


                // Aorist

                // Query verb endings aorist and save in String Array

                String[] personAoristEndings = instanceOfParadigm.queryPersonAoristEndings(selectedText);

                String firstPSgAoristEnding = personAoristEndings[0];
                String secondPSgAoristEnding = personAoristEndings[1];
                String thirdPSgAoristEnding = personAoristEndings[2];
                String firstPPlAoristEnding = personAoristEndings[3];
                String secondPPlAoristEnding = personAoristEndings[4];
                String thirdPPlAoristEnding = personAoristEndings[5];

                // Future

                // Query verb endings future and save in String Array

                String[] personFutureEndings = instanceOfParadigm.queryPersonFutureEndings(selectedText);

                String firstPSgFutureEnding = personFutureEndings[0];
                String secondPSgFutureEnding = personFutureEndings[1];
                String thirdPSgFutureEnding = personFutureEndings[2];
                String firstPPlFutureEnding = personFutureEndings[3];
                String secondPPlFutureEnding = personFutureEndings[4];
                String thirdPPlFutureEnding = personFutureEndings[5];


                // Remove last letter of nominal root

                String nominalRootWithoutLastLetter = "";
                String nominalRoot2WithoutLastLetter = "";
                if (root.length() > 1) {
                    nominalRootWithoutLastLetter = root.substring(0, root.length() - 1);
                }

                // Remove last letter of nominal root2 (stem changes)
                // If condition necessary because of String Index Out Of Bounds Exception
                // avoid number of letters < 0

                if (!Objects.equals(root2, "")) {
                    if (root2.length() > 1) {
                        nominalRoot2WithoutLastLetter = root2.substring(0, root2.length() - 1);
                    }
                }


                // Combine nominal root without last letter with respective case ending

                // Singular
                paliWordNominativeSg = nominalRootWithoutLastLetter + nominativeSgEnding;
                paliWordAccusativeSg = nominalRootWithoutLastLetter + accusativeSgEnding;
                paliWordInstrumentalSg = nominalRootWithoutLastLetter + instrumentalSgEnding;
                paliWordDativeSg = nominalRootWithoutLastLetter + dativeSgEnding;
                paliWordAblativeSg = nominalRootWithoutLastLetter + ablativeSgEnding;
                paliWordGenitiveSg = nominalRootWithoutLastLetter + genitiveSgEnding;
                paliWordLocativeSg = nominalRootWithoutLastLetter + locativeSgEnding;
                paliWordVocativeSg = nominalRootWithoutLastLetter + vocativeSgEnding;

                // Plural
                paliWordNominativePl = nominalRootWithoutLastLetter + nominativePlEnding;
                paliWordAccusativePl = nominalRootWithoutLastLetter + accusativePlEnding;
                paliWordInstrumentalPl = nominalRootWithoutLastLetter + instrumentalPlEnding;
                paliWordDativePl = nominalRootWithoutLastLetter + dativePlEnding;
                paliWordAblativePl = nominalRootWithoutLastLetter + ablativePlEnding;
                paliWordGenitivePl = nominalRootWithoutLastLetter + genitivePlEnding;
                paliWordLocativePl = nominalRootWithoutLastLetter + locativePlEnding;
                paliWordVocativePl = nominalRootWithoutLastLetter + vocativePlEnding;

                // Singular Adj fem

                paliWordNominativeSgAdjFem = nominalRootWithoutLastLetter + nominativeSgEndingAdjFem;
                paliWordAccusativeSgAdjFem = nominalRootWithoutLastLetter + accusativeSgEndingAdjFem;
                paliWordInstrumentalSgAdjFem = nominalRootWithoutLastLetter + instrumentalSgEndingAdjFem;
                paliWordDativeSgAdjFem = nominalRootWithoutLastLetter + dativeSgEndingAdjFem;
                paliWordAblativeSgAdjFem = nominalRootWithoutLastLetter + ablativeSgEndingAdjFem;
                paliWordGenitiveSgAdjFem = nominalRootWithoutLastLetter + genitiveSgEndingAdjFem;
                paliWordLocativeSgAdjFem = nominalRootWithoutLastLetter + locativeSgEndingAdjFem;
                paliWordVocativeSgAdjFem = nominalRootWithoutLastLetter + vocativeSgEndingAdjFem;

                // Plural Adj fem

                paliWordNominativePlAdjFem = nominalRootWithoutLastLetter + nominativePlEndingAdjFem;
                paliWordAccusativePlAdjFem = nominalRootWithoutLastLetter + accusativePlEndingAdjFem;
                paliWordInstrumentalPlAdjFem = nominalRootWithoutLastLetter + instrumentalPlEndingAdjFem;
                paliWordDativePlAdjFem = nominalRootWithoutLastLetter + dativePlEndingAdjFem;
                paliWordAblativePlAdjFem = nominalRootWithoutLastLetter + ablativePlEndingAdjFem;
                paliWordGenitivePlAdjFem = nominalRootWithoutLastLetter + genitivePlEndingAdjFem;
                paliWordLocativePlAdjFem = nominalRootWithoutLastLetter + locativePlEndingAdjFem;
                paliWordVocativePlAdjFem = nominalRootWithoutLastLetter + vocativePlEndingAdjFem;

                // Singular Adj neut

                paliWordNominativeSgAdjNeut = nominalRootWithoutLastLetter + nominativeSgEndingAdjNeut;
                paliWordAccusativeSgAdjNeut = nominalRootWithoutLastLetter + accusativeSgEndingAdjNeut;
                paliWordInstrumentalSgAdjNeut = nominalRootWithoutLastLetter + instrumentalSgEndingAdjNeut;
                paliWordDativeSgAdjNeut = nominalRootWithoutLastLetter + dativeSgEndingAdjNeut;
                paliWordAblativeSgAdjNeut = nominalRootWithoutLastLetter + ablativeSgEndingAdjNeut;
                paliWordGenitiveSgAdjNeut = nominalRootWithoutLastLetter + genitiveSgEndingAdjNeut;
                paliWordLocativeSgAdjNeut = nominalRootWithoutLastLetter + locativeSgEndingAdjNeut;
                paliWordVocativeSgAdjNeut = nominalRootWithoutLastLetter + vocativeSgEndingAdjNeut;

                // Plural Adj neut

                paliWordNominativePlAdjNeut = nominalRootWithoutLastLetter + nominativePlEndingAdjNeut;
                paliWordAccusativePlAdjNeut = nominalRootWithoutLastLetter + accusativePlEndingAdjNeut;
                paliWordInstrumentalPlAdjNeut = nominalRootWithoutLastLetter + instrumentalPlEndingAdjNeut;
                paliWordDativePlAdjNeut = nominalRootWithoutLastLetter + dativePlEndingAdjNeut;
                paliWordAblativePlAdjNeut = nominalRootWithoutLastLetter + ablativePlEndingAdjNeut;
                paliWordGenitivePlAdjNeut = nominalRootWithoutLastLetter + genitivePlEndingAdjNeut;
                paliWordLocativePlAdjNeut = nominalRootWithoutLastLetter + locativePlEndingAdjNeut;
                paliWordVocativePlAdjNeut = nominalRootWithoutLastLetter + vocativePlEndingAdjNeut;

                // Sg Ya fem

                paliWordNominativeSgYaFem = nominalRootWithoutLastLetter + nominativeSgEndingYaFem;
                paliWordAccusativeSgYaFem = nominalRootWithoutLastLetter + accusativeSgEndingYaFem;
                paliWordInstrumentalSgYaFem = nominalRootWithoutLastLetter + instrumentalSgEndingYaFem;
                paliWordDativeSgYaFem = nominalRootWithoutLastLetter + dativeSgEndingYaFem;
                paliWordAblativeSgYaFem = nominalRootWithoutLastLetter + ablativeSgEndingYaFem;
                paliWordGenitiveSgYaFem = nominalRootWithoutLastLetter + genitiveSgEndingYaFem;
                paliWordLocativeSgYaFem = nominalRootWithoutLastLetter + locativeSgEndingYaFem;

                // Pl Ya fem

                paliWordNominativePlYaFem = nominalRootWithoutLastLetter + nominativePlEndingYaFem;
                paliWordAccusativePlYaFem = nominalRootWithoutLastLetter + accusativePlEndingYaFem;
                paliWordInstrumentalPlYaFem = nominalRootWithoutLastLetter + instrumentalPlEndingYaFem;
                paliWordDativePlYaFem = nominalRootWithoutLastLetter + dativePlEndingYaFem;
                paliWordAblativePlYaFem = nominalRootWithoutLastLetter + ablativePlEndingYaFem;
                paliWordGenitivePlYaFem = nominalRootWithoutLastLetter + genitivePlEndingYaFem;
                paliWordLocativePlYaFem = nominalRootWithoutLastLetter + locativePlEndingYaFem;

                // Sg Ya Neut

                paliWordNominativeSgYaNeut = nominalRootWithoutLastLetter + nominativeSgEndingYaNeut;
                paliWordAccusativeSgYaNeut = nominalRootWithoutLastLetter + accusativeSgEndingYaNeut;
                paliWordInstrumentalSgYaNeut = nominalRootWithoutLastLetter + instrumentalSgEndingYaNeut;
                paliWordDativeSgYaNeut = nominalRootWithoutLastLetter + dativeSgEndingYaNeut;
                paliWordAblativeSgYaNeut = nominalRootWithoutLastLetter + ablativeSgEndingYaNeut;
                paliWordGenitiveSgYaNeut = nominalRootWithoutLastLetter + genitiveSgEndingYaNeut;
                paliWordLocativeSgYaNeut = nominalRootWithoutLastLetter + locativeSgEndingYaNeut;

                // Pl Ya Neut

                paliWordNominativePlYaNeut = nominalRootWithoutLastLetter + nominativePlEndingYaNeut;
                paliWordAccusativePlYaNeut = nominalRootWithoutLastLetter + accusativePlEndingYaNeut;
                paliWordInstrumentalPlYaNeut = nominalRootWithoutLastLetter + instrumentalPlEndingYaNeut;
                paliWordDativePlYaNeut = nominalRootWithoutLastLetter + dativePlEndingYaNeut;
                paliWordAblativePlYaNeut = nominalRootWithoutLastLetter + ablativePlEndingYaNeut;
                paliWordGenitivePlYaNeut = nominalRootWithoutLastLetter + genitivePlEndingYaNeut;
                paliWordLocativePlYaNeut = nominalRootWithoutLastLetter + locativePlEndingYaNeut;

                // Singular, different stem

                paliWordNominativeSgRoot2 = nominalRoot2WithoutLastLetter + nominativeSgEnding;
                paliWordAccusativeSgRoot2 = nominalRoot2WithoutLastLetter + accusativeSgEnding;
                paliWordInstrumentalSgRoot2 = nominalRoot2WithoutLastLetter + instrumentalSgEnding;
                paliWordDativeSgRoot2 = nominalRoot2WithoutLastLetter + dativeSgEnding;
                paliWordAblativeSgRoot2 = nominalRoot2WithoutLastLetter + ablativeSgEnding;
                paliWordGenitiveSgRoot2 = nominalRoot2WithoutLastLetter + genitiveSgEnding;
                paliWordLocativeSgRoot2 = nominalRoot2WithoutLastLetter + locativeSgEnding;
                paliWordVocativeSgRoot2 = nominalRoot2WithoutLastLetter + vocativeSgEnding;

                // Plural, different stem

                paliWordNominativePlRoot2 = nominalRoot2WithoutLastLetter + nominativePlEnding;
                paliWordAccusativePlRoot2 = nominalRoot2WithoutLastLetter + accusativePlEnding;
                paliWordInstrumentalPlRoot2 = nominalRoot2WithoutLastLetter + instrumentalPlEnding;
                paliWordDativePlRoot2 = nominalRoot2WithoutLastLetter + dativePlEnding;
                paliWordAblativePlRoot2 = nominalRoot2WithoutLastLetter + ablativePlEnding;
                paliWordGenitivePlRoot2 = nominalRoot2WithoutLastLetter + genitivePlEnding;
                paliWordLocativePlRoot2 = nominalRoot2WithoutLastLetter + locativePlEnding;


                // Query Paradigm type

                String paradigmType = instanceOfParadigm.queryParadigmType(selectedText);

                // Combine verbal root with respective verbal root endings
                paliWord1PSgPresent = root + firstPSgEnding;
                paliWord2PSgPresent = root + secondPSgEnding;
                paliWord3PSgPresent = root + thirdPSgEnding;
                paliWord1PPlPresent = root + firstPPlEnding;
                paliWord2PPlPresent = root + secondPPlEnding;
                paliWord3PPlPresent = root + thirdPPlEnding;

                // Combine root2 with respective verbal root endings
                // root2 is used because root can change when inflected

                paliWord1PSgRoot2Present = root2 + firstPSgEnding;
                paliWord2PSgRoot2Present = root2 + secondPSgEnding;
                paliWord3PSgRoot2Present = root2 + thirdPSgEnding;
                paliWord1PPlRoot2Present = root2 + firstPPlEnding;
                paliWord2PPlRoot2Present = root2 + secondPPlEnding;
                paliWord3PPlRoot2Present = root2 + thirdPPlEnding;


                // Aorist

                // Combine verbal root with respective verbal root endings for aorist


                paliWord1PSgAorist = stringPrefixA + root + firstPSgAoristEnding;
                paliWord2PSgAorist = stringPrefixA + root + secondPSgAoristEnding;
                paliWord3PSgAorist = stringPrefixA + root + thirdPSgAoristEnding;
                paliWord1PPlAorist = stringPrefixA + root + firstPPlAoristEnding;
                paliWord2PPlAorist = stringPrefixA + root + secondPPlAoristEnding;
                paliWord3PPlAorist = stringPrefixA + root + thirdPPlAoristEnding;

                // Combine verbal root2 with respective verbal root endings for aorist without prefix a
                paliWord1PSgAoristRoot2PrefixA = stringPrefixA + root2 + firstPSgAoristEnding;
                paliWord2PSgAoristRoot2PrefixA = stringPrefixA + root2 + secondPSgAoristEnding;
                paliWord3PSgAoristRoot2PrefixA = stringPrefixA + root2 + thirdPSgAoristEnding;
                paliWord1PPlAoristRoot2PrefixA = stringPrefixA + root2 + firstPPlAoristEnding;
                paliWord2PPlAoristRoot2PrefixA = stringPrefixA + root2 + secondPPlAoristEnding;
                paliWord3PPlAoristRoot2PrefixA = stringPrefixA + root2 + thirdPPlAoristEnding;

                // Combine verbal root2 with respective verbal root endings for aorist without prefix a
                paliWord1PSgAoristRoot2NoPrefix = root2 + firstPSgAoristEnding;
                paliWord2PSgAoristRoot2NoPrefix = root2 + secondPSgAoristEnding;
                paliWord3PSgAoristRoot2NoPrefix = root2 + thirdPSgAoristEnding;
                paliWord1PPlAoristRoot2NoPrefix = root2 + firstPPlAoristEnding;
                paliWord2PPlAoristRoot2NoPrefix = root2 + secondPPlAoristEnding;
                paliWord3PPlAoristRoot2NoPrefix = root2 + thirdPPlAoristEnding;

                // Combine verbal root with respective verbal root endings for aorist without prefix a and root


                // Combine verbal root with respective verbal root endings for aorists like kṛ
                paliWord1PSgAoristKr = stringPrefixA + root3 + firstPSgAoristEnding;
                paliWord2PSgAoristKr = stringPrefixA + root3 + secondPSgAoristEnding;
                paliWord3PSgAoristKr = stringPrefixA + root3 + thirdPSgAoristEnding;
                paliWord1PPlAoristKr = stringPrefixA + root4 + firstPPlAoristEnding;
                paliWord2PPlAoristKr = stringPrefixA + root4 + secondPPlAoristEnding;
                paliWord3PPlAoristKr = stringPrefixA + root3 + thirdPPlAoristEnding;

                // Future

                // Combine verbal root with respective verbal root endings for future
                paliWord1PSgFuture = root + firstPSgFutureEnding;
                paliWord2PSgFuture = root + secondPSgFutureEnding;
                paliWord3PSgFuture = root + thirdPSgFutureEnding;
                paliWord1PPlFuture = root + firstPPlFutureEnding;
                paliWord2PPlFuture = root + secondPPlFutureEnding;
                paliWord3PPlFuture = root + thirdPPlFutureEnding;

                // Combine verbal root2 with respective verbal root endings for future
                paliWord1PSgFutureRoot2 = root2 + firstPSgFutureEnding;
                paliWord2PSgFutureRoot2 = root2 + secondPSgFutureEnding;
                paliWord3PSgFutureRoot2 = root2 + thirdPSgFutureEnding;
                paliWord1PPlFutureRoot2 = root2 + firstPPlFutureEnding;
                paliWord2PPlFutureRoot2 = root2 + secondPPlFutureEnding;
                paliWord3PPlFutureRoot2 = root2 + thirdPPlFutureEnding;

                // Combine verbal root3 with respective verbal root endings for future
                paliWord1PSgFutureRoot3 = root3 + firstPSgFutureEnding;
                paliWord2PSgFutureRoot3 = root3 + secondPSgFutureEnding;
                paliWord3PSgFutureRoot3 = root3 + thirdPSgFutureEnding;
                paliWord1PPlFutureRoot3 = root3 + firstPPlFutureEnding;
                paliWord2PPlFutureRoot3 = root3 + secondPPlFutureEnding;
                paliWord3PPlFutureRoot3 = root3 + thirdPPlFutureEnding;

                String displayNounPronounAnalysis = translation + "\n\nStamm: " + root +
                        "\nWortart: " + partOfSpeech +
                        "\nFall, Zahl, Geschlecht: " + case1 + " " +
                        number + " " + gender;

                String displayNounPronounAnalysisCompoundCaseNumberGender = translation + "\n\nStamm: " + root +
                        "\nWortart: " + partOfSpeech +
                        "\nFall, Zahl, Geschlecht: " + case1 + " " +
                        number + " " + gender + "\nSonstiges: Kompositum";

                String displayNounPronounAnalysisCompound = translation + "\n\nStamm: " + root +
                        "\nWortart: " + partOfSpeech + "\nSonstiges: Kompositum";

                String displayNounPronounAnalysisTadEtad = translation + "\n\nStamm: " + root3 +
                        "\nWortart: " + partOfSpeech +
                        "\nFall, Zahl, Geschlecht: " + case1 + " " +
                        number + " " + gender;

                // Check whether verb has a tense
                if (!Objects.equals(tense, "unknown")) {
                    displayVerbAnalysis = translation + "\n\nStamm: " + root + "\nWortart: " + partOfSpeech +
                            "\nPerson, Zahl: " + person + " Person " + number + "\nModus, Zeit: " + mood + ", " + tense;
                } else {
                    displayVerbAnalysis = translation + "\n\nStamm: " + root + "\nWortart: " + partOfSpeech +
                            "\nPerson, Zahl: " + person + " Person " + number + "\nModus: " + mood;
                }


                displayVerbAnalysisAbsolutive = translation + "\n\nStamm: " + root + "\nWortart: " + partOfSpeech;


                if ((Objects.equals(partOfSpeech, "Nomen") && (compound == 1)) && (Objects.equals(root, "sukha"))) {

                    analysisTextArea.setText(displayNounPronounAnalysisCompoundCaseNumberGender);


                } else if ((Objects.equals(partOfSpeech, "Nomen") && (compound == 1))) {

                    analysisTextArea.setText(displayNounPronounAnalysisCompound);


                } else if (Objects.equals(partOfSpeech, "Nomen")) {


                    analysisTextArea.setText(displayNounPronounAnalysis);

                    // Add and set tabs
                    tabPaneAnalysis.getTabs().add(nounPronounTab);

                    nounPronounTab.setText("Deklination");


                    ObservableList<DataRow> data = FXCollections.observableArrayList(
                            new DataRow("", "Singular", "Plural"),
                            new DataRow("Nominativ", paliWordNominativeSg, paliWordNominativePl),
                            new DataRow("Akkusativ", paliWordAccusativeSg, paliWordAccusativePl),
                            new DataRow("Instrumental", paliWordInstrumentalSg, paliWordInstrumentalPl),
                            new DataRow("Dativ", paliWordDativeSg, paliWordDativePl),
                            new DataRow("Ablativ", paliWordAblativeSg, paliWordAblativePl),
                            new DataRow("Genitiv", paliWordGenitiveSg, paliWordGenitivePl),
                            new DataRow("Lokativ", paliWordLocativeSg, paliWordLocativePl),
                            new DataRow("Vokativ", paliWordVocativeSg, paliWordVocativePl)

                    );

                    tableViewParadigmNounPronoun.setItems(data);

                } else if (Objects.equals(partOfSpeech, "Adjektiv")) {

                    analysisTextArea.setText(displayNounPronounAnalysis);

                    // Add and set tabs
                    tabPaneAnalysis.getTabs().add(adjectiveTabMasculine);
                    tabPaneAnalysis.getTabs().add(adjectiveTabFeminine);
                    tabPaneAnalysis.getTabs().add(adjectiveTabNeuter);

                    adjectiveTabMasculine.setText("Maskulin");
                    adjectiveTabFeminine.setText("Feminin");
                    adjectiveTabNeuter.setText("Neutrum");

                    fillTableViewAdj();

                } else if (Objects.equals(partOfSpeech, "Pronomen") && Objects.equals(paradigmType, "attan")) {

                    analysisTextArea.setText(displayNounPronounAnalysis);

                    // Add and set tabs
                    tabPaneAnalysis.getTabs().add(nounPronounTab);

                    nounPronounTab.setText("Deklination");

                    ObservableList<DataRow> data = FXCollections.observableArrayList(
                            new DataRow("", "Singular", ""),
                            new DataRow("Nominativ", paliWordNominativeSgRoot2, ""),
                            new DataRow("Akkusativ", paliWordAccusativeSgRoot2, ""),
                            new DataRow("Instrumental", paliWordInstrumentalSgRoot2, ""),
                            new DataRow("Dativ", paliWordDativeSgRoot2, ""),
                            new DataRow("Ablativ", paliWordAblativeSgRoot2, ""),
                            new DataRow("Genitiv", paliWordGenitiveSgRoot2, ""),
                            new DataRow("Lokativ", paliWordLocativeSgRoot2, ""),
                            new DataRow("Vokativ", paliWordVocativeSgRoot2, "")
                    );

                    tableViewParadigmNounPronoun.setItems(data);

                    nounPronounTab.setContent(tableViewParadigmNounPronoun);


                } else if (Objects.equals(partOfSpeech, "Pronomen") && Objects.equals(selectedText, "yo")) {

                    analysisTextArea.setText(displayNounPronounAnalysis);

                    // Add and set tabs
                    tabPaneAnalysis.getTabs().add(yaTabMasculine);
                    tabPaneAnalysis.getTabs().add(yaTabFeminine);
                    tabPaneAnalysis.getTabs().add(yaTabNeuter);

                    yaTabMasculine.setText("Maskulin");
                    yaTabFeminine.setText("Feminin");
                    yaTabNeuter.setText("Neutrum");


                    fillTableViewYa();

                } else if (Objects.equals(partOfSpeech, "Pronomen") && Objects.equals(selectedText, "so")
                        || Objects.equals(selectedText, "etaṃ")) {

                    analysisTextArea.setText(displayNounPronounAnalysisTadEtad);

                    // Add and set tabs
                    tabPaneAnalysis.getTabs().add(adjectiveTabFeminine);

                    adjectiveTabFeminine.setText("Deklination");

                    fillTableViewSa();


                } else if (Objects.equals(partOfSpeech, "Pronomen") && (compound == 1)) {

                    analysisTextArea.setText(displayNounPronounAnalysisCompound);

                } else if (Objects.equals(partOfSpeech, "Pronomen")) {


                    analysisTextArea.setText(displayNounPronounAnalysis);

                    // Add and set tabs
                    tabPaneAnalysis.getTabs().add(nounPronounTab);

                    nounPronounTab.setText("Deklination");

                    ObservableList<DataRow> data = FXCollections.observableArrayList(
                            new DataRow("", "Plural", ""),
                            new DataRow("Nominativ", paliWordNominativePl, ""),
                            new DataRow("Akkusativ", paliWordAccusativePl, ""),
                            new DataRow("Instrumental", paliWordInstrumentalPl, ""),
                            new DataRow("Dativ", paliWordDativePl, ""),
                            new DataRow("Ablativ", paliWordAblativePl, ""),
                            new DataRow("Genitiv", paliWordGenitivePl, ""),
                            new DataRow("Lokativ", paliWordLocativePl, "")
                    );

                    tableViewParadigmNounPronoun.setItems(data);


                } else if (Objects.equals(partOfSpeech, "Partizip")) {

                    analysisTextArea.setText(displayNounPronounAnalysis);

                } else if (Objects.equals(partOfSpeech, "Absolutiv") && Objects.equals(root, "pa-i")) {

                    analysisTextArea.setText(displayVerbAnalysisAbsolutive);


                } else if (Objects.equals(partOfSpeech, "Verb") || Objects.equals(partOfSpeech, "Absolutiv")) {


                    // Add and set tabs
                    tabPaneAnalysis.getTabs().add(presentTab);
                    tabPaneAnalysis.getTabs().add(aoristTab);
                    tabPaneAnalysis.getTabs().add(futureTab);


                    presentTab.setText("Präsens");
                    aoristTab.setText("Aorist");
                    futureTab.setText("Futur");


                    if (root.equals("tas")) {

                        fillTableViewPresent();
                        fillTableViewAorist();
                        fillTableViewFuture();

                    } else if (root.equals("vihiṃs")) {
                        fillTableViewPresent();
                        fillTableViewAoristRoot2NoPrefix();
                        fillTableViewFuture();

                    } else if (root.equals("bhī")) {

                        fillTableViewPresentRoot2();
                        fillTableViewAoristRoot2NoPrefix();
                        fillTableViewFutureRoot2();

                    } else if (root.equals("kṛ")) {

                        fillTableViewPresentRoot2();
                        fillTableViewAoristKr();
                        fillTableViewFutureRoot2();


                    } else if (root.equals("han")) {
                        fillTableViewPresent();
                        fillTableViewAoristRoot2NoPrefix();
                        fillTableViewFutureRoot3();

                    } else if (root2.equals("ghāt")) {
                        fillTableViewPresentRoot2();
                        fillTableViewAoristRoot2PrefixA();
                        fillTableViewFutureRoot2();
                    } else if (root.equals("labh")) {
                        fillTableViewPresent();
                        tabPaneAnalysis.getTabs().remove(aoristTab);
                        tabPaneAnalysis.getTabs().remove(futureTab);
                    }

                } else if (Objects.equals(partOfSpeech, "Adverb")) {


                    analysisTextArea.setText(displayNounPronounAnalysis);

                    // Add and set tabs
                    tabPaneAnalysis.getTabs().add(nounPronounTab);

                    nounPronounTab.setText("Deklination");


                    ObservableList<DataRow> data = FXCollections.observableArrayList(
                            new DataRow("", "Singular", "Plural"),
                            new DataRow("Nominativ", paliWordNominativeSg, paliWordNominativePl),
                            new DataRow("Akkusativ", paliWordAccusativeSg, paliWordAccusativePl),
                            new DataRow("Instrumental", paliWordInstrumentalSg, paliWordInstrumentalPl),
                            new DataRow("Dativ", paliWordDativeSg, paliWordDativePl),
                            new DataRow("Ablativ", paliWordAblativeSg, paliWordAblativePl),
                            new DataRow("Genitiv", paliWordGenitiveSg, paliWordGenitivePl),
                            new DataRow("Lokativ", paliWordLocativeSg, paliWordLocativePl),
                            new DataRow("Vokativ", paliWordVocativeSg, paliWordVocativePl)

                    );

                    tableViewParadigmNounPronoun.setItems(data);

                } else if (Objects.equals(partOfSpeech, "Partikel")) {

                    analysisTextArea.setText(translation + "\nWortart: " + partOfSpeech);


                } else if (Objects.equals(partOfSpeech, "Affix")) {

                    analysisTextArea.setText(translation + "\nWortart: " + partOfSpeech);


                }


            }


            // Clear TextAreaAnalysis, tableViewParadigm and columnName of column2 when no words are marked
            if (paliVerseTextArea.getSelectedText().isEmpty()) {

                clearAnalysisTextArea();

            }


        });

        Scene scene = new Scene(vbox, 1400, 750);


        // Link to CSS file
        scene.getStylesheets().add("styles.css");


        // set scene background
        vbox.setStyle("-fx-background-color: #eef2f7;");


        // show main window
        stage.setTitle("Dhammapada Reader");
        stage.setScene(scene);
        stage.show();

    }

    private void fillComboBox() throws SQLException {

        // SELECT query to get verse numbers from table
        String query = "SELECT VerseNr FROM PaliVerse";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                // Go through results
                while (resultSet.next()) {
                    int number = resultSet.getInt("VerseNr");

                    // Add number to ComboBox
                    comboBoxVerseNr.getItems().add(number);
                }
            }
        }


    }


    private void loadTextFromPaliText() throws SQLException {


        // Check if number was chosen
        Integer selectedNumber = comboBoxVerseNr.getValue();

        if (selectedNumber == null) {
            return;
        }


        // Pass chosen verse number of combobox to String queryPalivers
        String queryPalivers = DatabaseMethods.checkVerseNumberPaliVerse(comboBoxVerseNr.getValue());


        // Get values of PaliVerse
        try (PreparedStatement statement = connection.prepareStatement(queryPalivers)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {


                    // Get text values of table
                    String textValue = resultSet.getString("Verse");

                    // add text in text area
                    paliVerseTextArea.setText(textValue);
                } else {


                    // No entry found, clear text area
                    paliVerseTextArea.clear();
                }
            }
        }

        // Activate/deactivate arrow buttons if first / last verse is reached
        if (selectedNumber == minMaxVerseArray[0]) {

            upButton.setDisable(true);

        } else if (selectedNumber == minMaxVerseArray[1]) {

            upButton.setDisable(false);
            downButton.setDisable(true);

        } else {
            upButton.setDisable(false);
            downButton.setDisable(false);

        }

        outputSaveLabel.setText("");

    }

    private void loadTextFromPaliVerseGER() throws SQLException {

        // Check, if number was chosen
        Integer selectedNumber = comboBoxVerseNr.getValue();

        if (selectedNumber == null) {
            return;
        }

        // connect to DB


        String queryPaliVerseGER = DatabaseMethods.checkVerseNumberPaliVerseGER(comboBoxVerseNr.getValue());


        try (PreparedStatement statement = connection.prepareStatement(queryPaliVerseGER)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    String textValue = resultSet.getString("Verse");

                    paliVerseGERTextArea.setText(textValue);
                } else {

                    paliVerseGERTextArea.clear();
                }
            }
        }

    }

    private void loadTextFromPaliTextDecomposed() throws SQLException {


        Integer selectedNumber = comboBoxVerseNr.getValue();

        if (selectedNumber == null) {
            return;
        }


        String queryPalivers = DatabaseMethods.checkVerseNumberPaliVerseDecomposed(comboBoxVerseNr.getValue());


        try (PreparedStatement statement = connection.prepareStatement(queryPalivers)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {


                    String textValue = resultSet.getString("Verse");

                    paliVerseTextArea.setText(textValue);
                } else {


                    paliVerseTextArea.clear();
                }
            }
        }
    }

    private void loadTextFromPaliVerseEN() throws SQLException {

        Integer selectedNumber = comboBoxVerseNr.getValue();

        if (selectedNumber == null) {

            showInformationDialogPaliVerseEN(new Stage());


            return;
        }


        String queryPaliVerseGER = DatabaseMethods.checkVerseNumberPaliVerseEN(comboBoxVerseNr.getValue());


        try (PreparedStatement statement = connection.prepareStatement(queryPaliVerseGER)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    String textValue = resultSet.getString("Verse");

                    paliVerseGERTextArea.setText(textValue);
                } else {

                    paliVerseGERTextArea.clear();
                }
            }
        }

    }


    private void loadNotesFromPaliVerse() throws SQLException {


        // Check if a number has been selected
        Integer selectedNumber = comboBoxVerseNr.getValue();

        if (selectedNumber == null) {
            return;
        }


        // Retrieve verse number based on comboBoxVerseNr

        String queryNote = DatabaseMethods.notesToPaliVerse(comboBoxVerseNr.getValue());


        try (PreparedStatement statement = connection.prepareStatement(queryNote)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    // Retrieve text value from the table
                    String textValue = resultSet.getString("Notes");

                    // Insert text into TextArea
                    notesTextArea.setText(textValue);


                } else {

                    // No corresponding entry found, clear the TextArea
                    notesTextArea.clear();


                }
            }
        }
    }


    private void exportDatabaseComplete(Stage primaryStage) {

        // choose file format
        ChoiceDialog<String> formatDialog = new ChoiceDialog<>("CSV", "CSV", "TXT");
        // Set font to 14pt
        formatDialog.getDialogPane().setStyle("-fx-font-size: 14pt;");
        formatDialog.setTitle("Exportformat wählen");
        formatDialog.setHeaderText(null);
        formatDialog.setContentText("Wählen Sie das Exportformat:");
        String selectedFormat = formatDialog.showAndWait().orElse(null);


        if (selectedFormat != null) {
            // choose folder of destination
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Zielordner auswählen");
            directoryChooser.setInitialDirectory(Paths.get(System.getProperty("user.home")).toFile());
            File targetFolder = directoryChooser.showDialog(primaryStage);

            if (targetFolder != null) {
                String targetPath = targetFolder.getAbsolutePath();

                try {
                    if (selectedFormat.equalsIgnoreCase("CSV")) {
                        exportDatabaseCompleteAsCsv(targetPath);
                    } else if (selectedFormat.equalsIgnoreCase("TXT")) {
                        exportDatabaseCompleteAsText(targetPath);
                    }

                    showAlert(exportAlertSuccessful1(), exportAlertSuccessful2());
                } catch (IOException e) {
                    showAlert(exportAlertFailed1(), exportAlertFailed2() + e.getMessage());
                } catch (SQLException e) {
                    showAlert(exportAlertFailed1(), exportAlertFailed3() + e.getMessage());
                }
            }
        }
    }

    private void exportDatabaseCompleteAsCsv(String targetPath) throws IOException, SQLException {
        DatabaseManager databaseManager = new DatabaseManager();

        Connection connection1;
        if (os.contains("win")) {
            connection1 = DriverManager.getConnection("jdbc:sqlite:" + databaseManager.getDB_URLCreateDatabaseBackupSourceWindows());
        } else {
            connection1 = DriverManager.getConnection("jdbc:sqlite:" + databaseManager.getDB_URLCreateDatabaseBackupSourceLinux());
        }

        try (
                Statement statement = connection1.createStatement();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetPath + "/exportDatabaseComplete.csv"), StandardCharsets.UTF_8))) {

            // Set table names
            String[] tableNames = {
                    "Case1",
                    "Compound",
                    "ConjugationParadigm",
                    "Gender",
                    "Mood",
                    "NounPronounAdjParadigm",
                    "Number",
                    "PaliVerse",
                    "PaliVerseGER",
                    "PaliWord",
                    "PartOfSpeech",
                    "Person",
                    "Tense",
                    "TranslationPaliWordGER"
            };

            for (String tableName : tableNames) {
                // SQL query
                String query = "SELECT * FROM " + tableName;

                ResultSet resultSet = statement.executeQuery(query);

                // Write column names
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    writer.write(metaData.getColumnName(i));
                    if (i < columnCount) {
                        writer.write(",");
                    }
                }
                writer.newLine();


                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        String cellValue = resultSet.getString(i);
                        if ((tableName.equals("PaliVerse")) && i == getColumnIndexByName(metaData, "Verse")) {
                            cellValue = escapeCellValueDatabaseSlash(cellValue);
                        }
                        if (tableName.equals("PaliVerseGER") && metaData.getColumnName(i).equals("Verse")) {
                            cellValue = escapeCellValueComma(cellValue);
                        }
                        if (tableName.equals("TranslationPaliWordGER") && metaData.getColumnName(i).equals("TranslationPaliWordGER")) {
                            cellValue = escapeCellValueComma(cellValue);
                        }
                        writer.write(cellValue);
                        if (i < columnCount) {
                            writer.write(",");
                        }
                    }
                    writer.newLine();
                }

                writer.newLine(); // blank line between tables
            }
        }
    }


    private void exportDatabaseCompleteAsText(String targetPath) throws IOException, SQLException {
        DatabaseManager databaseManager = new DatabaseManager();

        Connection connection1;
        if (os.contains("win")) {
            connection1 = DriverManager.getConnection("jdbc:sqlite:" + databaseManager.getDB_URLCreateDatabaseBackupSourceWindows());
        } else {
            connection1 = DriverManager.getConnection("jdbc:sqlite:" + databaseManager.getDB_URLCreateDatabaseBackupSourceLinux());
        }

        try (
                Statement statement = connection1.createStatement();
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetPath + "/exportDatabaseComplete.txt"), StandardCharsets.UTF_8))) {

            String[] tableNames = {
                    "Case1",
                    "Compound",
                    "ConjugationParadigm",
                    "Gender",
                    "Mood",
                    "NounPronounAdjParadigm",
                    "Number",
                    "PaliVerse",
                    "PaliVerseGER",
                    "PaliWord",
                    "PartOfSpeech",
                    "Person",
                    "Tense",
                    "TranslationPaliWordGER"
            };

            for (String tableName : tableNames) {
                String query = "SELECT * FROM " + tableName;

                ResultSet resultSet = statement.executeQuery(query);

                writer.write("Tabelle: " + tableName);
                writer.newLine();
                writer.write("--------------------");
                writer.newLine();

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    writer.write(metaData.getColumnName(i));
                    if (i < columnCount) {
                        writer.write("\t");
                    }
                }
                writer.newLine();

                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        String cellValue = resultSet.getString(i);
                        if ((tableName.equals("PaliVerse")) && i == getColumnIndexByName(metaData, "Verse")) {
                            cellValue = escapeCellValueDatabaseSlash(cellValue);
                        }
                        if (tableName.equals("PaliVerseGER") && metaData.getColumnName(i).equals("Verse")) {
                            cellValue = escapeCellValueComma(cellValue);
                        }
                        writer.write(cellValue);
                        if (i < columnCount) {
                            writer.write("\t");
                        }
                    }
                    writer.newLine();
                }

                writer.newLine();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void exportPaliVerse(Stage primaryStage) {

        ChoiceDialog<String> formatDialog = new ChoiceDialog<>("CSV", "CSV", "TXT");
        formatDialog.getDialogPane().setStyle("-fx-font-size: 14pt;");
        formatDialog.setTitle("Exportformat wählen");
        formatDialog.setHeaderText(null);
        formatDialog.setContentText("Wählen Sie das Exportformat:");
        String selectedFormat = formatDialog.showAndWait().orElse(null);


        if (selectedFormat != null) {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Zielordner auswählen");
            directoryChooser.setInitialDirectory(Paths.get(System.getProperty("user.home")).toFile());
            File targetFolder = directoryChooser.showDialog(primaryStage);

            if (targetFolder != null) {
                String targetPath = targetFolder.getAbsolutePath();

                try {
                    if (selectedFormat.equalsIgnoreCase("CSV")) {
                        exportPaliVerseCSV(targetPath);
                    } else if (selectedFormat.equalsIgnoreCase("TXT")) {
                        exportPaliVerseTXT(targetPath);
                    }

                    showAlert(exportAlertSuccessful1(), exportAlertSuccessful2());
                } catch (IOException e) {
                    showAlert(exportAlertFailed1(), exportAlertFailed2() + e.getMessage());
                } catch (SQLException e) {
                    showAlert(exportAlertFailed1(), exportAlertFailed3() + e.getMessage());
                }
            }
        }
    }

    private void exportPaliVerseGER(Stage primaryStage) {

        ChoiceDialog<String> formatDialog = new ChoiceDialog<>("CSV", "CSV", "TXT");
        formatDialog.getDialogPane().setStyle("-fx-font-size: 14pt;");
        formatDialog.setTitle("Exportformat wählen");
        formatDialog.setHeaderText(null);
        formatDialog.setContentText("Wählen Sie das Exportformat:");
        String selectedFormat = formatDialog.showAndWait().orElse(null);


        if (selectedFormat != null) {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Zielordner auswählen");
            directoryChooser.setInitialDirectory(Paths.get(System.getProperty("user.home")).toFile());
            File targetFolder = directoryChooser.showDialog(primaryStage);

            if (targetFolder != null) {
                String targetPath = targetFolder.getAbsolutePath();

                try {
                    if (selectedFormat.equalsIgnoreCase("CSV")) {
                        exportPaliVerseGERCSV(targetPath);
                    } else if (selectedFormat.equalsIgnoreCase("TXT")) {
                        exportPaliVerseGERTXT(targetPath);
                    }

                    showAlert(exportAlertSuccessful1(), exportAlertSuccessful2());
                } catch (IOException e) {
                    showAlert(exportAlertFailed1(), exportAlertFailed2() + e.getMessage());
                } catch (SQLException e) {
                    showAlert(exportAlertFailed1(), exportAlertFailed3() + e.getMessage());
                }
            }
        }
    }


    private void exportPaliVerseCSV(String targetPath) throws IOException, SQLException {
        DatabaseManager databaseManager = new DatabaseManager();

        Connection connection1;
        if (os.contains("win")) {
            connection1 = DriverManager.getConnection("jdbc:sqlite:" + databaseManager.getDB_URLCreateDatabaseBackupSourceWindows());
        } else {
            connection1 = DriverManager.getConnection("jdbc:sqlite:" + databaseManager.getDB_URLCreateDatabaseBackupSourceLinux());
        }

        try (
                Statement statement = connection1.createStatement();
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetPath + "/exportPaliVerse.csv"), StandardCharsets.UTF_8))) {

            String tableName = "PaliVerse";

            String query = "SELECT * FROM " + tableName;

            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                writer.write(metaData.getColumnName(i));
                if (i < columnCount) {
                    writer.write(",");
                }
            }
            writer.newLine();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String cellValue = resultSet.getString(i);
                    if (i == getColumnIndexByName(metaData, "Verse")) {
                        cellValue = escapeCellValue(cellValue);
                    }
                    writer.write(cellValue);
                    if (i < columnCount) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            showAlert(exportAlertFailed1(), exportAlertFailed2() + e.getMessage());
        } catch (SQLException e) {
            showAlert(exportAlertFailed1(), exportAlertFailed3() + e.getMessage());
        }
    }


    private void exportPaliVerseGERCSV(String targetPath) throws IOException, SQLException {
        DatabaseManager databaseManager = new DatabaseManager();

        Connection connection1;
        if (os.contains("win")) {
            connection1 = DriverManager.getConnection("jdbc:sqlite:" + databaseManager.getDB_URLCreateDatabaseBackupSourceWindows());
        } else {
            connection1 = DriverManager.getConnection("jdbc:sqlite:" + databaseManager.getDB_URLCreateDatabaseBackupSourceLinux());
        }

        try (
             Statement statement = connection1.createStatement();
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetPath + "/exportPaliVerseGER.csv"), StandardCharsets.UTF_8))) {

            String tableName = "PaliVerseGER";

            String query = "SELECT * FROM " + tableName;

            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                writer.write(metaData.getColumnName(i));
                if (i < columnCount) {
                    writer.write(",");
                }
            }
            writer.newLine();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String cellValue = resultSet.getString(i);
                    if (i == getColumnIndexByName(metaData, "Verse")) {
                        cellValue = escapeCellValueComma(cellValue);
                    }
                    writer.write(cellValue);
                    if (i < columnCount) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }
        }
    }

    private void exportPaliVerseTXT(String targetPath) throws IOException, SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection1;
        if (os.contains("win")) {
            connection1 = DriverManager.getConnection("jdbc:sqlite:" + databaseManager.getDB_URLCreateDatabaseBackupSourceWindows());
        } else {
            connection1 = DriverManager.getConnection("jdbc:sqlite:" + databaseManager.getDB_URLCreateDatabaseBackupSourceLinux());
        }

        try (
                Statement statement = connection1.createStatement();

             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetPath + "/exportPaliVerse.txt"), StandardCharsets.UTF_8))) {

            String tableName = "PaliVerse";

            String query = "SELECT * FROM " + tableName;

            ResultSet resultSet = statement.executeQuery(query);

            writer.write("Tabelle: " + tableName);
            writer.newLine();
            writer.write("--------------------");
            writer.newLine();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                writer.write(metaData.getColumnName(i));
                if (i < columnCount) {
                    writer.write("\t");
                }
            }
            writer.newLine();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String cellValue = resultSet.getString(i);
                    if (columnName.equalsIgnoreCase("Verse") && cellValue != null) {
                        cellValue = escapeCellValueBlankInsteadLineBreakAfterSlash(cellValue);
                    }
                    writer.write(cellValue);
                    if (i < columnCount) {
                        writer.write("\t");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            showAlert(exportAlertFailed1(), exportAlertFailed2() + e.getMessage());
        } catch (SQLException e) {
            showAlert(exportAlertFailed1(), exportAlertFailed3() + e.getMessage());
        }
    }


    private void exportPaliVerseGERTXT(String targetPath) throws IOException, SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection1;
        if (os.contains("win")) {
            connection1 = DriverManager.getConnection("jdbc:sqlite:" + databaseManager.getDB_URLCreateDatabaseBackupSourceWindows());
        } else {
            connection1 = DriverManager.getConnection("jdbc:sqlite:" + databaseManager.getDB_URLCreateDatabaseBackupSourceLinux());
        }

        try (
                Statement statement = connection1.createStatement();
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetPath + "/exportPaliVerseGER.txt"), StandardCharsets.UTF_8))) {

            String tableName = "PaliVerseGER";

            String query = "SELECT * FROM " + tableName;

            ResultSet resultSet = statement.executeQuery(query);

            writer.write("Tabelle: " + tableName);
            writer.newLine();
            writer.write("--------------------");
            writer.newLine();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                writer.write(metaData.getColumnName(i));
                if (i < columnCount) {
                    writer.write("\t");
                }
            }
            writer.newLine();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String cellValue = resultSet.getString(i);
                    if (columnName.equalsIgnoreCase("Verse") && cellValue != null) {
                        cellValue = escapeCellValueComma(cellValue);
                    }
                    writer.write(cellValue);
                    if (i < columnCount) {
                        writer.write("\t");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            showAlert(exportAlertFailed1(), exportAlertFailed2() + e.getMessage());
        } catch (SQLException e) {
            showAlert(exportAlertFailed1(), exportAlertFailed3() + e.getMessage());
        }
    }


    private int getColumnIndexByName(ResultSetMetaData metaData, String columnName) throws SQLException {
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            if (metaData.getColumnName(i).equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }

    private String escapeCellValueComma(String cellValue) {
        if (cellValue != null && cellValue.contains(",")) {
            cellValue = "\"" + cellValue + "\"";
        }
        return cellValue;
    }

    private String escapeCellValueDatabaseSlash(String cellValue) {
        if (cellValue != null && cellValue.contains("/")) {
            cellValue = "\"" + cellValue + "\"";

        }
        return cellValue;
    }


    private String escapeCellValue(String cellValue) {
        if (cellValue != null && cellValue.contains("/")) {
            cellValue = "\"" + cellValue + "\"";
            cellValue = cellValue.replace(System.lineSeparator(), " ");
        }
        return cellValue;
    }

    private String escapeCellValueBlankInsteadLineBreakAfterSlash(String cellValue) {
        if (cellValue != null && cellValue.contains("/")) {
            cellValue = "\"" + cellValue.replace("/", "/ ") + "\"";

        }
        return cellValue;
    }

    private WritableImage convertToJavaFXImage(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        WritableImage writableImage = new WritableImage(width, height);

        javafx.scene.image.PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = bufferedImage.getRGB(x, y);
                pixelWriter.setArgb(x, y, argb);
            }
        }

        return writableImage;
    }


    public String exportAlertSuccessful1() {
        return "Export erfolgreich";
    }

    public String exportAlertSuccessful2() {

        return "Die Datenbank wurde erfolgreich exportiert.";

    }

    public String exportAlertFailed1() {
        return "Export fehlgeschlagen";
    }

    public String exportAlertFailed2() {
        return "Fehler beim Exportieren der Datenbank: ";
    }

    public String exportAlertFailed3() {
        return "Fehler bei der Datenbankabfrage: ";
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setStyle("-fx-font-size: 14pt;");
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showConfirmationDialog(Stage stage) {
        // create confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bestätigung");
        alert.setHeaderText("Programm beenden");
        alert.setContentText("Möchten Sie das Programm wirklich beenden? Nicht gespeicherte Notizen gehen verloren.");

        // add buttons
        ButtonType yesButton = new ButtonType("Ja");
        ButtonType noButton = new ButtonType("Nein");
        alert.getButtonTypes().setAll(yesButton, noButton);

        alert.getDialogPane().setStyle("-fx-font-size: 14pt;");

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == yesButton) {
                stage.close();
                searchResultsStage.close();
                searchStage.close();

            }
        });
    }

    private void showInformationDialogPaliVerseEN(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Bitte zuerst Vers auswählen.");
        alert.getDialogPane().setStyle("-fx-font-size: 14pt;");
        // Show dialog and wait until it is closed
        alert.showAndWait();


    }


    private void showImportDialog(String section) {

        try {
            if (connection.isClosed())
                connection = DatabaseManager.connectToDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        ChoiceDialog<String> dialog = new ChoiceDialog<>("CSV", List.of("CSV", "TXT"));


        dialog.setTitle("Dateiformat wählen");
        dialog.setHeaderText(null);
        dialog.getDialogPane().setStyle("-fx-font-size: 14pt;");
        dialog.setContentText("Wählen Sie das Dateiformat:");


        Optional<String> result = dialog.showAndWait();
        result.ifPresent(format -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(Paths.get(System.getProperty("user.home")).toFile());

            fileChooser.setTitle("Datei auswählen");
            File selectedFile = fileChooser.showOpenDialog(null);

            if (Objects.equals(section, "Paliverse")) {

                if (selectedFile != null) {
                    importDataPaliVerse(selectedFile);

                }
            } else if (Objects.equals(section, "PaliverseGER")) {

                if (selectedFile != null) {
                    importDataPaliVerseGER(selectedFile);

                }

            } else if (Objects.equals(section, "TranslationPaliWordGER")) {

                if (selectedFile != null) {

                    importDataTranslationPaliWordGER(selectedFile);
                }
            }
        });
    }


    private void importDataPaliVerse(File file) {

        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS PaliVerse (PaliSatzID INTEGER PRIMARY KEY NOT NULL," +
                    " VerseNr INTEGER NOT NULL, Verse TEXT NOT NULL," +
                    " Notes TEXT DEFAULT '', PaliVerseGERID INTEGER REFERENCES" +
                    " PaliVerseGER (PaliVerseGERID))";
            Statement statement = connection.createStatement();
            statement.execute(createTableSQL);

            String line;
            BufferedReader reader = new BufferedReader(new FileReader(file));

            int maxPaliSatzID = getMaxPaliSatzID();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                int paliSatzID;
                if (data[0].isEmpty()) {
                    // If no PaliSatzID available, increase maxPaliSatzID
                    paliSatzID = ++maxPaliSatzID;
                } else {
                    paliSatzID = Integer.parseInt(data[0]);
                }

                int verseNr = Integer.parseInt(data[1]);
                String verse = data[2];
                String notes = data[3];
                int paliVerseGERID = Integer.parseInt(data[4]);

                String insertSQL = "INSERT INTO PaliVerse (PaliSatzID, VerseNr, Verse, Notes, PaliVerseGERID) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setInt(1, paliSatzID);
                preparedStatement.setInt(2, verseNr);
                preparedStatement.setString(3, verse);
                preparedStatement.setString(4, notes);
                preparedStatement.setInt(5, paliVerseGERID);
                preparedStatement.executeUpdate();
            }

            // Close connection and resources
            reader.close();
            statement.close();
            connection.close();
            System.out.println("Verbindung zur Datenbank getrennt.");
            showAlert(importSuccessful1(), importSuccessful2());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            showAlert(importFailed1(), importFailedNumberFormat());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(importFailed1(), importFailedFailure());
        }
    }

    private int getMaxPaliSatzID() throws SQLException {
        String selectSQL = "SELECT MAX(PaliSatzID) FROM PaliVerse";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectSQL);
        int maxPaliSatzID = 0;
        if (resultSet.next()) {
            maxPaliSatzID = resultSet.getInt(1);
        }
        resultSet.close();
        statement.close();
        return maxPaliSatzID;
    }


    private void importDataPaliVerseGER(File file) {
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS PaliVerseGER (PaliVerseGERID INTEGER PRIMARY KEY AUTOINCREMENT, VerseNr INTEGER, Verse TEXT NOT NULL)";
            Statement statement = connection.createStatement();
            statement.execute(createTableSQL);

            String line;
            BufferedReader reader = new BufferedReader(new FileReader(file));

            int maxPaliVerseGERID = getMaxPaliVerseGERID();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                int paliVerseGERID;
                if (data[0].isEmpty()) {
                    paliVerseGERID = ++maxPaliVerseGERID;
                } else {
                    paliVerseGERID = Integer.parseInt(data[0]);
                }

                int verseNr = Integer.parseInt(data[1]);
                String verse = data[2];

                String insertSQL = "INSERT INTO PaliVerseGER (PaliVerseGERID, VerseNr, Verse) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setInt(1, paliVerseGERID);
                preparedStatement.setInt(2, verseNr);
                preparedStatement.setString(3, verse);
                preparedStatement.executeUpdate();
            }

            reader.close();
            statement.close();
            connection.close();
            System.out.println("Verbindung zur Datenbank erfolgreich getrennt.");
            showAlert(importSuccessful1(), importSuccessful2());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            showAlert(importFailed1(), importFailedNumberFormat());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(importFailed1(), importFailedFailure());
        }
    }

    private int getMaxPaliVerseGERID() {
        int maxPaliVerseGERID = 0;
        try {
            String query = "SELECT MAX(PaliVerseGERID) FROM PaliVerseGER";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                maxPaliVerseGERID = resultSet.getInt(1);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxPaliVerseGERID;
    }

    private void importDataTranslationPaliWordGER(File file) {
        try {
            // Create table if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS TranslationPaliWordGER (TranslationPaliWordGERID INTEGER PRIMARY KEY AUTOINCREMENT, TranslationPaliWordGER TEXT NOT NULL)";
            Statement statement = connection.createStatement();
            statement.execute(createTableSQL);

            // Read the file
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(file));

            // Get the maximum TranslationPaliWordGERID
            int maxTranslationPaliWordGERID = getMaxTranslationPaliWordGERID();

            // Process each line in the file
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                int translationPaliWordGERID;
                if (data.length >= 2) {
                    // Check if the ID field is empty or non-empty
                    if (data[0].isEmpty()) {
                        // Generate a new ID if empty
                        translationPaliWordGERID = ++maxTranslationPaliWordGERID;
                    } else {
                        try {
                            // Parse the ID as an integer if non-empty
                            translationPaliWordGERID = Integer.parseInt(data[0]);
                        } catch (NumberFormatException e) {
                            // Handle the case where the ID cannot be parsed as an integer
                            continue; // Skip the current line and proceed to the next one
                        }
                    }

                    String translationPaliWordGER = data[1];

                    // Insert the data into the database
                    String insertSQL = "INSERT INTO TranslationPaliWordGER (TranslationPaliWordGERID, TranslationPaliWordGER) VALUES (?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                    preparedStatement.setInt(1, translationPaliWordGERID);
                    preparedStatement.setString(2, translationPaliWordGER);
                    preparedStatement.executeUpdate();
                } else {
                    // Handle the case where the line does not have the expected format
                    continue; // Skip the current line and proceed to the next one
                }
            }

            // Close the file reader and database connection
            reader.close();
            statement.close();
            connection.close();
            System.out.println("Successfully disconnected from the database.");
            showAlert(importSuccessful1(), importSuccessful2());
        } catch (NumberFormatException e) {
            // Handle the case where the ID cannot be parsed as an integer
            e.printStackTrace();
            showAlert(importFailed1(), importFailedNumberFormat());
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            showAlert(importFailed1(), importFailedFailure());
        }
    }


    private int getMaxTranslationPaliWordGERID() {
        int maxTranslationPaliWordGERID = 0;
        try {
            String query = "SELECT MAX(TranslationPaliWordGERID) FROM TranslationPaliWordGER";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                maxTranslationPaliWordGERID = resultSet.getInt(1);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxTranslationPaliWordGERID;
    }


    public String importSuccessful1() {
        return "Import erfolgreich";
    }

    public String importSuccessful2() {
        return "Die Daten wurden erfolgreich importiert.";
    }

    public String importFailed1() {
        return "Import fehlgeschlagen";
    }

    public String importFailedNumberFormat() {
        return "Ungültiges Zahlenformat in der Datei.";
    }

    public String importFailedFailure() {
        return "Beim Importieren der Daten ist ein Fehler aufgetreten.";
    }


    private void searchVerse(String executedBy) {
        searchStage.setTitle("Textsuche");
        GridPane searchPane = new GridPane();
        searchPane.setPadding(new Insets(10));
        searchPane.setHgap(10);
        searchPane.setVgap(10);
        searchPane.setStyle("-fx-font-size: 14pt;");

        Label searchLabel = new Label("Text eingeben:");
        searchLabel.setMinWidth(150);
        GridPane.setConstraints(searchLabel, 0, 0);

        TextArea searchTextArea = new TextArea();
        searchTextArea.setPromptText("Geben Sie den zu suchenden Text ein...");
        GridPane.setConstraints(searchTextArea, 1, 0);

        Button searchButton = new Button("Suchen");
        searchButton.getStyleClass().add("plastic-buttonSave");
        GridPane.setConstraints(searchButton, 1, 1);

        Button cancelButton = new Button("Abbrechen");
        cancelButton.getStyleClass().add("plastic-buttonSave");
        GridPane.setConstraints(cancelButton, 2, 1);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(searchButton, cancelButton);
        GridPane.setConstraints(buttonBox, 1, 1);

        searchPane.getChildren().addAll(searchLabel, searchTextArea,
                buttonBox);

        // Tab key to jump from textfield to search button
        searchTextArea.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB) {
                event.consume();
                searchButton.requestFocus();
            }

        });

        // Replaces tab in textarea with "" (because if tab key is pressed in text area, tab is added within it)
        searchTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains("\t")) {
                searchTextArea.setText(newValue.replace("\t", ""));
            }
        });

        if (Objects.equals(executedBy, "Pali")) {


            searchButton.setOnAction(event -> {
                String searchText = searchTextArea.getText();
                generateSearchResults(searchText);
                searchStage.close();
            });

            // Search with Enter
            searchButton.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    String searchText = searchTextArea.getText();
                    generateSearchResults(searchText);
                    searchStage.close();
                }
            });
        } else if (Objects.equals(executedBy, "Deutsch")) {


            searchButton.setOnAction(event -> {
                String searchText = searchTextArea.getText();
                generateSearchResultsGER(searchText);
                searchStage.close();
            });

            // Search with Enter
            searchButton.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    String searchText = searchTextArea.getText();
                    generateSearchResultsGER(searchText);
                    searchStage.close();
                }
            });

        }

        cancelButton.setOnAction(event -> {
            searchStage.close();
        });

        // Cancel when enter key pressed on button
        cancelButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchStage.close();
            }
        });


        Scene searchScene = new Scene(searchPane, 700, 150);
        searchScene.getStylesheets().add("styles.css");
        searchStage.setScene(searchScene);
        searchStage.show();
    }


    private void generateSearchResults(String searchText) {

        try {
            if (connection.isClosed())
                connection = DatabaseManager.connectToDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        if (searchText.isEmpty()) {
            return;
        }

        boolean isExclusion = false;
        if (searchText.startsWith("!")) {
            isExclusion = true;
            searchText = searchText.substring(1);
        }

        String likePattern = convertWildcardToLikePattern(searchText);

        String query;
        if (isExclusion) {
            query = "SELECT * FROM PaliVerse WHERE Verse NOT LIKE ?";
        } else {
            query = "SELECT * FROM PaliVerse WHERE Verse LIKE ?";
        }

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, likePattern);

            ResultSet resultSet = statement.executeQuery();

            TableView<VerseResult> tableView = new TableView<>();
            TableColumn<VerseResult, Integer> verseNrColumn = new TableColumn<>("Vers Nr.");
            TableColumn<VerseResult, String> verseColumn = new TableColumn<>("Palivers");
            tableView.setStyle("-fx-font-size: 14pt;");
            verseNrColumn.setStyle("-fx-font-size: 14pt;");
            verseColumn.setStyle("-fx-font-size: 14pt;");

            verseNrColumn.setCellValueFactory(new PropertyValueFactory<>("verseNr"));
            verseColumn.setCellValueFactory(new PropertyValueFactory<>("verseText"));


            tableView.getColumns().addAll(verseNrColumn, verseColumn);
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tableView.setRowFactory(tv -> {
                TableRow<VerseResult> row = new TableRow<>();
                row.setMinHeight(70);
                return row;
            });

            // Create user defined cell for verseColumn
            verseColumn.setCellFactory(column -> {
                return new TableCell<VerseResult, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setAlignment(Pos.CENTER); // Center cell vertically
                        } else {
                            setText(item);
                            setAlignment(Pos.CENTER); // Center cell horizontally
                        }
                    }
                };
            });


            verseNrColumn.setMaxWidth(700);

            ObservableList<VerseResult> verseResults = FXCollections.observableArrayList();

            while (resultSet.next()) {
                int verseNr = resultSet.getInt("VerseNr");
                String verseText = resultSet.getString("Verse");
                boolean wordFound = verseText.contains(searchText);

                verseResults.add(new VerseResult(verseNr, verseText));

                String germanVerse = getGermanVerse(verseNr);
                if (!germanVerse.isEmpty()) {
                    verseResults.add(new VerseResult(verseNr, germanVerse));
                }
            }

            // Overwrite -1 with ""
            for (VerseResult verseResult : verseResults) {
                if (verseResult.getVerseNr() == -1) {
                    verseResult.verseNr.set(Integer.parseInt(""));
                }
            }

            tableView.setItems(verseResults);

            HBox buttonBox = new HBox();
            Button backButton = new Button("Zurück");
            Button closeButton = new Button("Schließen");
            Button exportButton = new Button("Suchergebnisse exportieren");

            backButton.getStyleClass().add("plastic-buttonSave");
            closeButton.getStyleClass().add("plastic-buttonSave");
            exportButton.getStyleClass().add("plastic-buttonSave");
            buttonBox.setSpacing(10);
            buttonBox.setPadding(new Insets(10, 10, 10, 0));
            buttonBox.setStyle("-fx-font-size: 14pt;");
            buttonBox.setAlignment(Pos.CENTER);


            backButton.setOnAction(event -> {
                searchVerse("Pali");
                searchResultsStage.close();
            });

            closeButton.setOnAction(event -> {
                // Close window
                searchResultsStage.close();
            });

            buttonBox.getChildren().addAll(backButton, closeButton, exportButton);

            String finalSearchText = searchText;
            exportButton.setOnAction(event -> {
                ChoiceDialog<String> exportDialog = new ChoiceDialog<>("CSV", "TXT", "HTML");
                exportDialog.setTitle("Exportformat auswählen");
                exportDialog.setHeaderText("Wählen Sie das Exportformat:");
                exportDialog.setContentText("Exportformat:");
                exportDialog.getDialogPane().setStyle("-fx-font-size: 14pt;");

                Optional<String> result = exportDialog.showAndWait();
                result.ifPresent(format -> {
                    if (format.equals("CSV")) {
                        exportToCSVTableSearchResult(finalSearchText, tableView.getItems());
                    } else if (format.equals("TXT")) {
                        exportToTXTTableSearchResult(finalSearchText, tableView.getItems());
                    } else if (format.equals("HTML")) {
                        exportToHTMLTableSearchResult(finalSearchText, tableView.getItems());
                    }
                });
            });


            searchResultsStage.setTitle("Suchergebnisse");
            VBox root = new VBox(tableView, buttonBox);
            Scene searchResultsScene = new Scene(root, 800, 400);
            searchResultsScene.getStylesheets().add("styles.css");


            searchResultsStage.setScene(searchResultsScene);
            searchResultsStage.show();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void generateSearchResultsGER(String searchText) {
        if (searchText.isEmpty()) {
            return;
        }

        boolean isExclusion = false;
        if (searchText.startsWith("!")) {
            isExclusion = true;
            searchText = searchText.substring(1);
        }

        String likePattern = convertWildcardToLikePattern(searchText);

        String query;
        if (isExclusion) {
            query = "SELECT * FROM PaliVerseGER WHERE Verse NOT LIKE ?";
        } else {
            query = "SELECT * FROM PaliVerseGER WHERE Verse LIKE ?";
        }

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, likePattern);

            ResultSet resultSet = statement.executeQuery();

            TableView<VerseResult> tableView = new TableView<>();
            TableColumn<VerseResult, Integer> verseNrColumn = new TableColumn<>("Vers Nr.");
            TableColumn<VerseResult, String> verseColumn = new TableColumn<>("Palivers");
            tableView.setStyle("-fx-font-size: 14pt;");
            verseNrColumn.setStyle("-fx-font-size: 14pt;");
            verseColumn.setStyle("-fx-font-size: 14pt;");

            verseNrColumn.setCellValueFactory(new PropertyValueFactory<>("verseNr"));
            verseColumn.setCellValueFactory(new PropertyValueFactory<>("verseText"));


            tableView.getColumns().addAll(verseNrColumn, verseColumn);
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tableView.setRowFactory(tv -> {
                TableRow<VerseResult> row = new TableRow<>();
                row.setMinHeight(70);
                return row;
            });

            verseColumn.setCellFactory(column -> {
                return new TableCell<VerseResult, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setAlignment(Pos.CENTER);
                        } else {
                            setText(item);
                            setAlignment(Pos.CENTER);
                        }
                    }
                };
            });


            verseNrColumn.setMaxWidth(700);

            ObservableList<VerseResult> verseResults = FXCollections.observableArrayList();

            while (resultSet.next()) {
                int verseNr = resultSet.getInt("VerseNr");
                String verseText = resultSet.getString("Verse");
                boolean wordFound = verseText.contains(searchText);

                verseResults.add(new VerseResult(verseNr, verseText));

                String paliVerse = getPaliVerse(verseNr);
                if (!paliVerse.isEmpty()) {
                    verseResults.add(new VerseResult(verseNr, paliVerse));
                }
            }

            for (VerseResult verseResult : verseResults) {
                if (verseResult.getVerseNr() == -1) {
                    verseResult.verseNr.set(Integer.parseInt(""));
                }
            }

            tableView.setItems(verseResults);

            HBox buttonBox = new HBox();
            Button backButton = new Button("Zurück");
            Button closeButton = new Button("Schließen");
            Button exportButton = new Button("Suchergebnisse exportieren");

            backButton.getStyleClass().add("plastic-buttonSave");
            closeButton.getStyleClass().add("plastic-buttonSave");
            exportButton.getStyleClass().add("plastic-buttonSave");
            buttonBox.setSpacing(10);
            buttonBox.setPadding(new Insets(10, 10, 10, 0));
            buttonBox.setStyle("-fx-font-size: 14pt;");
            buttonBox.setAlignment(Pos.CENTER);


            backButton.setOnAction(event -> {
                searchVerse("Deutsch");
                searchResultsStage.close();
            });

            closeButton.setOnAction(event -> {
                searchResultsStage.close();
            });

            buttonBox.getChildren().addAll(backButton, closeButton, exportButton);

            String finalSearchText = searchText;
            exportButton.setOnAction(event -> {
                ChoiceDialog<String> exportDialog = new ChoiceDialog<>("CSV", "TXT", "HTML");
                exportDialog.setTitle("Exportformat auswählen");
                exportDialog.setHeaderText("Wählen Sie das Exportformat:");
                exportDialog.setContentText("Exportformat:");
                exportDialog.getDialogPane().setStyle("-fx-font-size: 14pt;");

                Optional<String> result = exportDialog.showAndWait();
                result.ifPresent(format -> {
                    if (format.equals("CSV")) {
                        exportToCSVTableSearchResult(finalSearchText, tableView.getItems());
                    } else if (format.equals("TXT")) {
                        exportToTXTTableSearchResult(finalSearchText, tableView.getItems());
                    } else if (format.equals("HTML")) {
                        exportToHTMLTableSearchResult(finalSearchText, tableView.getItems());
                    }
                });
            });


            searchResultsStage.setTitle("Suchergebnisse");
            VBox root = new VBox(tableView, buttonBox);
            Scene searchResultsScene = new Scene(root, 800, 400);
            searchResultsScene.getStylesheets().add("styles.css");


            searchResultsStage.setScene(searchResultsScene);
            searchResultsStage.show();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void exportToCSVTableSearchResult(String searchText, ObservableList<VerseResult> verseResults) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Export als CSV");
        directoryChooser.setInitialDirectory(Paths.get(System.getProperty("user.home")).toFile()); // standard target folder is home folder

        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            File file = new File(selectedDirectory, "exportSearchResult.csv");
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
                writer.write("Dhammapada Reader – Suchergebnisse in Paliversen\n");
                writer.write("\n");
                writer.write("Suchparameter:," + searchText + "\n");
                writer.write("\n"); // Blank line

                // write CSV header
                writer.write("Vers Nr.,Palivers\n");

                for (VerseResult verseResult : verseResults) {
                    int verseNr = verseResult.getVerseNr();
                    String verseText = verseResult.getVerseText();
                    writer.write(verseNr + ",\"" + verseText + "\"\n");
                }
                showAlert(exportAlertSuccessful1(), exportAlertSuccessful2());
            } catch (IOException e) {
                showAlert(exportAlertFailed1(), exportAlertFailed2() + e.getMessage());
            }
        }
    }


    private void exportToTXTTableSearchResult(String searchText, ObservableList<VerseResult> verseResults) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Export als TXT");
        directoryChooser.setInitialDirectory(Paths.get(System.getProperty("user.home")).toFile());

        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            File file = new File(selectedDirectory, "exportSearchResult.txt");
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
                writer.write("Dhammapada Reader – Suchergebnisse in Paliversen\n");
                writer.write("\n");
                writer.write("=========================================\n");
                writer.write("Suchparameter: " + searchText + "\n");
                writer.write("=========================================\n");
                writer.write("\n");

                writer.write(String.format("%-10s %-50s\n", "Vers Nr.", "Palivers"));

                writer.write("----------+--------------------------------------------------\n");

                for (VerseResult verseResult : verseResults) {
                    int verseNr = verseResult.getVerseNr();
                    String verseText = verseResult.getVerseText();
                    writer.write(String.format("%-10d %-50s\n", verseNr, verseText));
                }

                writer.write("=========================================\n");

                showAlert(exportAlertSuccessful1(), exportAlertSuccessful2());
            } catch (IOException e) {
                showAlert(exportAlertFailed1(), exportAlertFailed2() + e.getMessage());
            }
        }
    }

    private void exportToHTMLTableSearchResult(String searchText, ObservableList<VerseResult> verseResults) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Export als HTML");
        directoryChooser.setInitialDirectory(Paths.get(System.getProperty("user.home")).toFile());

        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            File file = new File(selectedDirectory, "exportSearchResult.html");
            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
                writer.write("<html>\n");
                writer.write("<head>\n");
                writer.write("<meta charset=\"UTF-8\">\n");
                writer.write("<style>\n");
                writer.write(".container {\n");
                writer.write("    text-align: left;\n");
                writer.write("}\n");
                writer.write("table {\n");
                writer.write("    width: 500px;\n");
                writer.write("    margin-left: 0;\n");
                writer.write("}\n");
                writer.write("th, td {\n");
                writer.write("    border: 1px solid black;\n");
                writer.write("    padding: 8px;\n");
                writer.write("    text-align: left;\n");
                writer.write("}\n");
                writer.write("th:first-child, td:first-child {\n");
                writer.write("    color: #0077a3;\n");
                writer.write("}\n");
                writer.write("</style>\n");
                writer.write("</head>\n");
                writer.write("<body>\n");

                writer.write("<div class=\"container\">\n");
                writer.write("<h2 style=\"color: #0077a3;\">Dhammapada Reader – <br>Suchergebnisse in Paliversen</h2>\n");
                writer.write("<p><b><span style=\"color: #0077a3;\">Suchparameter:</span> " + searchText + "</p></b>\n");
                writer.write("<table>\n");
                writer.write("<tr>\n");
                writer.write("<th style=\"color: #0077a3;\">Vers Nr.</th>\n");
                writer.write("<th style=\"color: #0077a3;\">Palivers</th>\n");
                writer.write("</tr>\n");

                for (VerseResult verseResult : verseResults) {
                    int verseNr = verseResult.getVerseNr();
                    String verseText = verseResult.getVerseText();
                    writer.write("<tr>");
                    writer.write("<td>" + verseNr + "</td>");
                    writer.write("<td>" + verseText + "</td>");
                    writer.write("</tr>\n");
                }

                writer.write("<tr>\n");
                writer.write("<td colspan=\"2\" style=\"border: none;\"></td>\n");
                writer.write("</tr>\n");

                writer.write("<tr>\n");
                writer.write("<td colspan=\"2\" style=\"text-align: center; border: none;\">\n");
                writer.write("<img src=\"Buddha_verkleinert.png\" style=\"max-width: 50%;\">\n");
                writer.write("</td>\n");
                writer.write("</tr>\n");

                writer.write("</table>\n");
                writer.write("<br>\n");

                writer.write("</div>\n");

                BufferedImage image = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("Buddha_verkleinert.png"));
                ImageIO.write(image, "png", new File(selectedDirectory, "Buddha_verkleinert.png"));

                writer.write("</body>\n");
                writer.write("</html>\n");

                showAlert(exportAlertSuccessful1(), exportAlertSuccessful2());
            } catch (IOException e) {
                showAlert(exportAlertFailed1(), exportAlertFailed2() + e.getMessage());
            }
        }
    }


    private String getGermanVerse(int verseNr) {
        String germanVerse = "";
        try {
            String query = "SELECT Verse FROM PaliVerseGER WHERE VerseNr = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, verseNr);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                germanVerse = resultSet.getString("Verse");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return germanVerse;
    }

    private String getPaliVerse(int verseNr) {
        String germanVerse = "";
        try {
            String query = "SELECT Verse FROM PaliVerse WHERE VerseNr = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, verseNr);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                germanVerse = resultSet.getString("Verse");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return germanVerse;
    }


    private String convertWildcardToLikePattern(String searchText) {
        searchText = searchText.replace("\\", "\\\\");

        searchText = searchText.replace("*", "%");
        searchText = searchText.replace("?", "_");


        String[] searchTerms = searchText.split("\\|");
        searchText = String.join("|", searchTerms);

        searchText = "%" + searchText + "%";


        return searchText;
    }


    // Inner class to show search results in table
    public static class VerseResult {
        private final SimpleIntegerProperty verseNr;
        private final SimpleStringProperty verseText;

        public VerseResult(int verseNr, String verseText) {
            this.verseNr = new SimpleIntegerProperty(verseNr);
            this.verseText = new SimpleStringProperty(verseText);
        }

        // Do not delete - are used despite warning from IntelliJ
        public int getVerseNr() {
            return verseNr.get();
        }

        // Do not delete - are used despite warning from IntelliJ
        public String getVerseText() {
            return verseText.get();
        }
    }

    private void styleFirstRow(TableView<DataRow> tableView) {
        tableView.setRowFactory(tv -> {
            return new TableRow<DataRow>() {
                @Override
                protected void updateItem(DataRow item, boolean empty) {
                    super.updateItem(item, empty);
                    if (getIndex() == 0) {
                        setStyle("-fx-font-weight: bold;");
                    } else {
                        setStyle(""); // Revert style of other cells
                    }
                }
            };
        });
    }


    public void fillTableViewAdj() {

        ObservableList<DataRow> data = FXCollections.observableArrayList(
                new DataRow("", "Singular", "Plural"),
                new DataRow("Nominativ", paliWordNominativeSg, paliWordNominativePl),
                new DataRow("Akkusativ", paliWordAccusativeSg, paliWordAccusativePl),
                new DataRow("Instrumental", paliWordInstrumentalSg, paliWordInstrumentalPl),
                new DataRow("Dativ", paliWordDativeSg, paliWordDativePl),
                new DataRow("Ablativ", paliWordAblativeSg, paliWordAblativePl),
                new DataRow("Genitiv", paliWordGenitiveSg, paliWordGenitivePl),
                new DataRow("Lokativ", paliWordLocativeSg, paliWordLocativePl),
                new DataRow("Vokativ", paliWordVocativeSg, paliWordVocativePl)
        );


        tableViewParadigmAdjectiveMasculine.setItems(data);

        ObservableList<DataRow> dataFem = FXCollections.observableArrayList(
                new DataRow("", "Singular", "Plural"),
                new DataRow("Nominativ", paliWordNominativeSgAdjFem, paliWordNominativePlAdjFem),
                new DataRow("Akkusativ", paliWordAccusativeSgAdjFem, paliWordAccusativePlAdjFem),
                new DataRow("Instrumental", paliWordInstrumentalSgAdjFem, paliWordInstrumentalPlAdjFem),
                new DataRow("Dativ", paliWordDativeSgAdjFem, paliWordDativePlAdjFem),
                new DataRow("Ablativ", paliWordAblativeSgAdjFem, paliWordAblativePlAdjFem),
                new DataRow("Genitiv", paliWordGenitiveSgAdjFem, paliWordGenitivePlAdjFem),
                new DataRow("Lokativ", paliWordLocativeSgAdjFem, paliWordLocativePlAdjFem),
                new DataRow("Vokativ", paliWordVocativeSgAdjFem, paliWordVocativePlAdjFem)
        );

        tableViewParadigmAdjectiveFeminine.setItems(dataFem);

        ObservableList<DataRow> dataNeut = FXCollections.observableArrayList(
                new DataRow("", "Singular", "Plural"),
                new DataRow("Nominativ", paliWordNominativeSgAdjNeut, paliWordNominativePlAdjNeut),
                new DataRow("Akkusativ", paliWordAccusativeSgAdjNeut, paliWordAccusativePlAdjNeut),
                new DataRow("Instrumental", paliWordInstrumentalSgAdjNeut, paliWordInstrumentalPlAdjNeut),
                new DataRow("Dativ", paliWordDativeSgAdjNeut, paliWordDativePlAdjNeut),
                new DataRow("Ablativ", paliWordAblativeSgAdjNeut, paliWordAblativePlAdjNeut),
                new DataRow("Genitiv", paliWordGenitiveSgAdjNeut, paliWordGenitivePlAdjNeut),
                new DataRow("Lokativ", paliWordLocativeSgAdjNeut, paliWordLocativePlAdjNeut),
                new DataRow("Vokativ", paliWordVocativeSgAdjNeut, paliWordVocativePlAdjNeut)
        );

        tableViewParadigmAdjectiveNeuter.setItems(dataNeut);


    }

    public void fillTableViewYa() {


        ObservableList<DataRow> dataMasc = FXCollections.observableArrayList(
                new DataRow("", "Singular", "Plural"),
                new DataRow("Nominativ", paliWordNominativeSg, paliWordNominativePl),
                new DataRow("Akkusativ", paliWordAccusativeSg, paliWordAccusativePl),
                new DataRow("Instrumental", paliWordInstrumentalSg, paliWordInstrumentalPl),
                new DataRow("Dativ", paliWordDativeSg, paliWordDativePl),
                new DataRow("Ablativ", paliWordAblativeSg, paliWordAblativePl),
                new DataRow("Genitiv", paliWordGenitiveSg, paliWordGenitivePl),
                new DataRow("Lokativ", paliWordLocativeSg, paliWordLocativePl)
        );


        tableViewParadigmYaMasculine.setItems(dataMasc);

        ObservableList<DataRow> dataFem = FXCollections.observableArrayList(
                new DataRow("", "Singular", "Plural"),
                new DataRow("Nominativ", paliWordNominativeSgYaFem, paliWordNominativePlYaFem),
                new DataRow("Akkusativ", paliWordAccusativeSgYaFem, paliWordAccusativePlYaFem),
                new DataRow("Instrumental", paliWordInstrumentalSgYaFem, paliWordInstrumentalPlYaFem),
                new DataRow("Dativ", paliWordDativeSgYaFem, paliWordDativePlYaFem),
                new DataRow("Ablativ", paliWordAblativeSgYaFem, paliWordAblativePlYaFem),
                new DataRow("Genitiv", paliWordGenitiveSgYaFem, paliWordGenitivePlYaFem),
                new DataRow("Lokativ", paliWordLocativeSgYaFem, paliWordLocativePlYaFem)
        );

        tableViewParadigmYaFeminine.setItems(dataFem);

        ObservableList<DataRow> dataNeut = FXCollections.observableArrayList(
                new DataRow("", "Singular", "Plural"),
                new DataRow("Nominativ", paliWordNominativeSgYaNeut, paliWordNominativePlYaNeut),
                new DataRow("Akkusativ", paliWordAccusativeSgYaNeut, paliWordAccusativePlYaNeut),
                new DataRow("Instrumental", paliWordInstrumentalSgYaNeut, paliWordInstrumentalPlYaNeut),
                new DataRow("Dativ", paliWordDativeSgYaNeut, paliWordDativePlYaNeut),
                new DataRow("Ablativ", paliWordAblativeSgYaNeut, paliWordAblativePlYaNeut),
                new DataRow("Genitiv", paliWordGenitiveSgYaNeut, paliWordGenitivePlYaNeut),
                new DataRow("Lokativ", paliWordLocativeSgYaNeut, paliWordLocativePlYaNeut)
        );

        tableViewParadigmYaNeuter.setItems(dataNeut);


    }

    public void fillTableViewSa() {

        ObservableList<DataRow> dataFem = FXCollections.observableArrayList(
                new DataRow("", "Singular", "Plural"),
                new DataRow("Nominativ", root, paliWordNominativePlRoot2),
                new DataRow("Akkusativ", paliWordAccusativeSgRoot2, paliWordAccusativePlRoot2),
                new DataRow("Instrumental", paliWordInstrumentalSgRoot2, paliWordInstrumentalPlRoot2),
                new DataRow("Dativ", paliWordDativeSgRoot2, paliWordDativePlRoot2),
                new DataRow("Ablativ", paliWordAblativeSgRoot2, paliWordAblativePlRoot2),
                new DataRow("Genitiv", paliWordGenitiveSgRoot2, paliWordGenitivePlRoot2),
                new DataRow("Lokativ", paliWordLocativeSgRoot2, paliWordLocativePlRoot2)
        );

        tableViewParadigmAdjectiveFeminine.setItems(dataFem);
    }


    public void fillTableViewPresent() {


        setAnalysisText(partOfSpeech);

        // Present
        ObservableList<DataRow> data = createDataList(paliWord1PSgPresent, paliWord1PPlPresent,
                paliWord2PSgPresent, paliWord2PPlPresent, paliWord3PSgPresent, paliWord3PPlPresent);
        tableViewPresent.setItems(data);

    }

    public void fillTableViewPresentRoot2() {


        setAnalysisText(partOfSpeech);

        // Present
        ObservableList<DataRow> data = createDataList(paliWord1PSgRoot2Present, paliWord1PPlRoot2Present,
                paliWord2PSgRoot2Present, paliWord2PPlRoot2Present,
                paliWord3PSgRoot2Present, paliWord3PPlRoot2Present);
        tableViewPresent.setItems(data);

    }

    public void fillTableViewAorist() {
        setAnalysisText(partOfSpeech);

        // Aorist
        ObservableList<DataRow> dataAorist = createDataList(paliWord1PSgAorist, paliWord1PPlAorist,
                paliWord2PSgAorist, paliWord2PPlAorist, paliWord3PSgAorist, paliWord3PPlAorist);
        tableViewAorist.setItems(dataAorist);
    }

    public void fillTableViewAoristRoot2PrefixA() {
        setAnalysisText(partOfSpeech);

        // Aorist
        ObservableList<DataRow> dataAorist = createDataList(paliWord1PSgAoristRoot2PrefixA, paliWord1PPlAoristRoot2PrefixA,
                paliWord2PSgAoristRoot2PrefixA, paliWord2PPlAoristRoot2PrefixA, paliWord3PSgAoristRoot2PrefixA, paliWord3PPlAoristRoot2PrefixA);
        tableViewAorist.setItems(dataAorist);
    }

    public void fillTableViewAoristRoot2NoPrefix() {
        setAnalysisText(partOfSpeech);

        // Aorist
        ObservableList<DataRow> dataAorist = createDataList(paliWord1PSgAoristRoot2NoPrefix, paliWord1PPlAoristRoot2NoPrefix,
                paliWord2PSgAoristRoot2NoPrefix, paliWord2PPlAoristRoot2NoPrefix, paliWord3PSgAoristRoot2NoPrefix, paliWord3PPlAoristRoot2NoPrefix);
        tableViewAorist.setItems(dataAorist);
    }

    public void fillTableViewAoristKr() {
        setAnalysisText(partOfSpeech);

        // Aorist
        ObservableList<DataRow> dataAorist = createDataList(paliWord1PSgAoristKr, paliWord2PSgAoristKr,
                paliWord3PSgAoristKr, paliWord1PPlAoristKr, paliWord2PPlAoristKr, paliWord3PPlAoristKr);
        tableViewAorist.setItems(dataAorist);
    }

    public void fillTableViewFuture() {
        setAnalysisText(partOfSpeech);
        // Future
        ObservableList<DataRow> dataFuture = createDataList(paliWord1PSgFuture, paliWord1PPlFuture,
                paliWord2PSgFuture, paliWord2PPlFuture, paliWord3PSgFuture, paliWord3PPlFuture);
        tableViewFuture.setItems(dataFuture);


    }

    public void fillTableViewFutureRoot2() {

        setAnalysisText(partOfSpeech);
        // Future
        ObservableList<DataRow> dataFuture = createDataList(paliWord1PSgFutureRoot2, paliWord1PPlFutureRoot2, paliWord2PSgFutureRoot2,
                paliWord2PPlFutureRoot2, paliWord3PSgFutureRoot2, paliWord3PPlFutureRoot2);
        tableViewFuture.setItems(dataFuture);

    }

    public void fillTableViewFutureRoot3() {
        setAnalysisText(partOfSpeech);
        // Future
        ObservableList<DataRow> dataFuture = createDataList(paliWord1PSgFutureRoot3, paliWord1PPlFutureRoot3, paliWord2PSgFutureRoot3,
                paliWord2PPlFutureRoot3, paliWord3PSgFutureRoot3, paliWord3PPlFutureRoot3);
        tableViewFuture.setItems(dataFuture);


    }

    public void setAnalysisText(String partOfSpeech) {
        if (partOfSpeech.equals("Absolutiv")) {
            analysisTextArea.setText(displayVerbAnalysisAbsolutive);
        } else {
            analysisTextArea.setText(displayVerbAnalysis);
        }
    }

    private ObservableList<DataRow> createDataList(String word1PSg, String word1PPl, String word2PSg, String
            word2PPl, String word3PSg, String word3PPl) {
        return FXCollections.observableArrayList(
                new DataRow("", "Singular", "Plural"),
                new DataRow("1. Person", word1PSg, word1PPl),
                new DataRow("2. Person", word2PSg, word2PPl),
                new DataRow("3. Person", word3PSg, word3PPl)
        );
    }

    public void clearAnalysisTextArea() {

        analysisTextArea.clear();
        tableViewParadigmNounPronoun.getItems().clear();

        tableViewPresent.getItems().clear();
        tableViewAorist.getItems().clear();
        tableViewFuture.getItems().clear();
        tabPaneAnalysis.getTabs().removeAll(nounPronounTab, presentTab, aoristTab, futureTab,
                adjectiveTabMasculine, adjectiveTabFeminine, adjectiveTabNeuter, yaTabMasculine,
                yaTabFeminine, yaTabNeuter);

    }

    private void saveTextToDatabase() {
        String text = notesTextArea.getText();
        if (!text.isEmpty()) {
            try {
                String updateSql = "UPDATE " + "PaliVerse" + " SET Notes = ? WHERE VerseNr = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
                    preparedStatement.setString(1, text);
                    preparedStatement.setInt(2, comboBoxVerseNr.getValue());
                    preparedStatement.executeUpdate();

                }
                outputSaveLabel.setTextFill(Paint.valueOf("green"));
                // Add padding to top side of object
                outputSaveLabel.setStyle("-fx-font-weight: normal; -fx-padding: 8 0 0 0;");
                outputSaveLabel.setText("Speichern erfolgreich.");

            } catch (SQLException e) {
                e.printStackTrace();

            }
        } else {
            outputSaveLabel.setText("Speichern fehlgeschlagen.");
        }
    }


    private void aboutDhammapadaReader() {
        Stage helpStage = new Stage();
        helpStage.setTitle("Über Dhammapada Reader");
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(10));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.setStyle("-fx-font-size: 14pt;");
        gridpane.setAlignment(Pos.CENTER);


        Label aboutLabelDhammapadareader = new Label("Dhammapada Reader");
        aboutLabelDhammapadareader.setStyle("-fx-font-size: 14pt; -fx-font-weight: bold; -fx-text-fill: #0077a3;");

        Label aboutLabelContent = new Label("zeigt Dhammapada-Verse in Pali\n" +
                "mit Übersetzung an\nund liefert dazugehörige\n" +
                "grammatische Informationen.");
        aboutLabelContent.setStyle("-fx-font-size: 12pt;");

//        Label aboutLabelCopyright = new Label("Copyright © 2023");
//        aboutLabelCopyright.setStyle("-fx-font-size: 10pt;");

        Button closeButton = new Button("Schließen");
        closeButton.getStyleClass().add("plastic-buttonSave");


        gridpane.add(aboutLabelDhammapadareader, 0, 0);
        GridPane.setHalignment(aboutLabelDhammapadareader, HPos.CENTER);
        gridpane.add(aboutLabelContent, 0, 1);
//        gridpane.add(aboutLabelCopyright, 0, 2);
        gridpane.add(closeButton, 0, 3);
        GridPane.setHalignment(closeButton, HPos.CENTER);

        closeButton.setOnAction(event -> {
            helpStage.close();
        });

        Scene helpScene = new Scene(gridpane, 320, 220);
        helpScene.getStylesheets().add("styles.css");
        helpStage.setScene(helpScene);

        aboutLabelDhammapadareader.requestFocus();

        helpStage.show();
    }


    // stop method called automatically by JavaFX if application is closed
    @Override
    public void stop() throws Exception {
        // Close connection to DB
        super.stop();
        DatabaseManager.closeConnection();
    }
}

