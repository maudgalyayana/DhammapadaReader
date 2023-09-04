package com.example.projektver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class DatabaseManager {

    private static Connection connection;

    private static String homeDir = System.getProperty("user.home");
    private static final String DB_URL_Linux = "jdbc:sqlite:" + homeDir +
            "/DhammapadaReader/Datenbanken/PaliDeutsch.db";
    private static final String DB_URL_Windows = "jdbc:sqlite:" + homeDir +
            "/DhammapadaReader/Datenbanken/PaliDeutsch.db";

    private final String DB_URLCreateDatabaseBackupSourceLinux = homeDir +
            "/DhammapadaReader/Datenbanken/PaliDeutsch.db";

    private final String DB_URLCreateDatabaseBackupSourceWindows = homeDir + "/DhammapadaReader/Datenbanken/PaliDeutsch.db";

    private final String DB_URL_BackupCreateDatabaseBackupTargetLinux = homeDir +
            "/DhammapadaReader/Datenbanken/Backup/";
    private final String DB_URL_BackupCreateDatabaseBackupTargetWindows =
            homeDir + "/DhammapadaReader/Datenbanken/Backup/";

    public String getDB_URLCreateDatabaseBackupSourceLinux() {
        return DB_URLCreateDatabaseBackupSourceLinux;
    }

    public String getDB_URL_BackupCreateDatabaseBackupTargetLinux() {
        return DB_URL_BackupCreateDatabaseBackupTargetLinux;
    }

    public String getDB_URLCreateDatabaseBackupSourceWindows() {
        return DB_URLCreateDatabaseBackupSourceWindows;
    }

    public String getDB_URL_BackupCreateDatabaseBackupTargetWindows() {
        return DB_URL_BackupCreateDatabaseBackupTargetWindows;
    }

    public static Connection getConnection() {

        return connection;
    }

    //  Connect to DB
    public static Connection connectToDB() {
        try {

            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {

                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(DB_URL_Windows);
                    System.out.println("Verbindung zur Datenbank erfolgreich hergestellt.");

                }
            } else {
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(DB_URL_Linux);
                    System.out.println("Verbindung zur Datenbank erfolgreich hergestellt.");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Create Backup of DB

    public static void createDatabaseBackup(String databasePath, String backupPath) {

        File backupFolder = new File(backupPath);
        if (!backupFolder.exists()) {
            backupFolder.mkdir();
        }

        // If more than 30 backup files, delete oldest one
        File[] existingBackups = backupFolder.listFiles();
        if (existingBackups != null && existingBackups.length >= 30) {
            // Sort existing backups by last modified date
            Arrays.sort(existingBackups, Comparator.comparingLong(File::lastModified));

            // Delete the oldest backup file
            existingBackups[0].delete();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String timeStamp = dateFormat.format(new Date());
        String backupFileName = "BackupSQLiteDatei_" + timeStamp + ".db";

        File sourceFile = new File(databasePath);
        File destinationFile = new File(backupFolder, backupFileName);

        // Copy DB file

        try {
            Files.copy(sourceFile.toPath(), destinationFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Sicherungskopie erstellt unter: "
                    + destinationFile.getAbsolutePath());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Close Connection to DB
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        System.out.println("Verbindung zur Datenbank erfolgreich getrennt.");
    }

}
