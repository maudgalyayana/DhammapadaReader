package com.example.projektver;

import java.sql.*;

import static com.example.projektver.DatabaseManager.getConnection;



public class Paradigm {



    Connection connection;


    private static final String queryCaseEnding = " FROM PaliWord JOIN NounPronounAdjParadigm" +
            " ON PaliWord.NounPronounAdjParadigmID = " +
            "NounPronounAdjParadigm.NounPronounAdjParadigmID" +
            " WHERE PaliWord.Paliword = ?";

    private static final String getQueryCaseEndingAllCases = "SELECT NominativeSg, AccusativeSg, " +
            "InstrumentalSg, DativeSg, " +
            "AblativeSg, GenitiveSg, LocativeSg, VocativeSg, " +
            "NominativePl, AccusativePl, InstrumentalPl, DativePl, " +
            "AblativePl, GenitivePl, LocativePl, VocativePl ";

    private static final String queryVerbEnding = " FROM PaliWord JOIN ConjugationParadigm" +
            " ON PaliWord.ConjugationParadigmID = " +
            "ConjugationParadigm.ConjugationParadigmID" +
            " WHERE PaliWord.Paliword = ?";


    public Paradigm(Connection connection) {
        this.connection = connection;

    }

    public String queryPartOfSpeech(String word) {
        String matchingValue = "-";

        try {

            String query = "SELECT PartOfSpeechRow FROM PaliWord JOIN PartOfSpeech ON PaliWord.PartOfSpeechID = " +
                    "PartOfSpeech.PartOfSpeechID WHERE PaliWord.PaliWord = ?";
            PreparedStatement statement = getConnection().prepareStatement(query);

            statement.setString(1, word);


            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                matchingValue = resultSet.getString("PartOfSpeechRow");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingValue;
    }

    public String queryParadigmType(String word) {
        String matchingValue = "-";

        try {

            String query = "SELECT ParadigmType" + queryCaseEnding;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, word);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                matchingValue = resultSet.getString("ParadigmType");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingValue;
    }

    public int[] getMinMaxVerse(Connection conn) throws SQLException {
        int[] verseArray = new int[2];



            String query = "SELECT MIN(VerseNr), MAX(VerseNr) FROM PaliVerse";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                if (rs.next()) {
                    verseArray[0] = rs.getInt(1);  // lowest value
                    verseArray[1] = rs.getInt(2);  // highest value
                }
            }

           return verseArray;
        }

    public String queryMood(String word) {
        String matchingValue = "-";

        try {

            String query = "SELECT MoodRow FROM PaliWord JOIN Mood" +
                    " ON PaliWord.MoodID = " +
                    "Mood.MoodID" +
                    " WHERE PaliWord.Paliword = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, word);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                matchingValue = resultSet.getString("MoodRow");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingValue;
    }

    public String queryTense(String word) {
        String matchingValue = "-";

        try {

            String query = "SELECT TenseRow FROM PaliWord JOIN Tense" +
                    " ON PaliWord.TenseID = " +
                    "Tense.TenseID" +
                    " WHERE PaliWord.Paliword = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, word);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                matchingValue = resultSet.getString("TenseRow");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingValue;
    }

    public String[] queryCaseEndings(String word) {
        String[] endings = new String[16];
        for (int i = 0; i < 16; i++) {
            endings[i] = "-";
        }

        try {
            String query = getQueryCaseEndingAllCases + queryCaseEnding;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, word);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                endings[0] = resultSet.getString("NominativeSg");
                endings[1] = resultSet.getString("AccusativeSg");
                endings[2] = resultSet.getString("InstrumentalSg");
                endings[3] = resultSet.getString("DativeSg");
                endings[4] = resultSet.getString("AblativeSg");
                endings[5] = resultSet.getString("GenitiveSg");
                endings[6] = resultSet.getString("LocativeSg");
                endings[7] = resultSet.getString("VocativeSg");
                endings[8] = resultSet.getString("NominativePl");
                endings[9] = resultSet.getString("AccusativePl");
                endings[10] = resultSet.getString("InstrumentalPl");
                endings[11] = resultSet.getString("DativePl");
                endings[12] = resultSet.getString("AblativePl");
                endings[13] = resultSet.getString("GenitivePl");
                endings[14] = resultSet.getString("LocativePl");
                endings[15] = resultSet.getString("VocativePl");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return endings;
    }

    public int queryCompound(String word) {
        int matchingValue = 0;

        try {

            String query = "SELECT CompoundRow FROM PaliWord JOIN Compound" +
                    " ON PaliWord.CompoundID = " +
                    "Compound.CompoundID" +
                    " WHERE PaliWord.Paliword = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, word);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                matchingValue = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingValue;
    }


    public String[] queryPersonPresentEndings(String word) {
        String[] endings = new String[6];
        for (int i = 0; i < 6; i++) {
            endings[i] = "-";
        }

        try {
            String query = "SELECT FirstPSg, SecondPSg, ThirdPSg," +
                    "FirstPPl, SecondPPl, ThirdPPl" + queryVerbEnding;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, word);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                endings[0] = resultSet.getString("FirstPSg");
                endings[1] = resultSet.getString("SecondPSg");
                endings[2] = resultSet.getString("ThirdPSg");
                endings[3] = resultSet.getString("FirstPPl");
                endings[4] = resultSet.getString("SecondPPl");
                endings[5] = resultSet.getString("ThirdPPl");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return endings;
    }

    public String[] queryPersonAoristEndings(String word) {
        String[] endings = new String[6];
        for (int i = 0; i < 6; i++) {
            endings[i] = "-";
        }

        try {
            String query = "SELECT AoristFirstPSg, AoristSecondPSg, AoristThirdPSg," +
                    "AoristFirstPPl, AoristSecondPPl, AoristThirdPPl" + queryVerbEnding;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, word);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                endings[0] = resultSet.getString("AoristFirstPSg");
                endings[1] = resultSet.getString("AoristSecondPSg");
                endings[2] = resultSet.getString("AoristThirdPSg");
                endings[3] = resultSet.getString("AoristFirstPPl");
                endings[4] = resultSet.getString("AoristSecondPPl");
                endings[5] = resultSet.getString("AoristThirdPPl");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return endings;
    }

    public String[] queryPersonFutureEndings(String word) {
        String[] endings = new String[6];
        for (int i = 0; i < 6; i++) {
            endings[i] = "-";
        }

        try {
            String query = "SELECT FutureFirstPSg, FutureSecondPSg, FutureThirdPSg," +
                    "FutureFirstPPl, FutureSecondPPl, FutureThirdPPl" + queryVerbEnding;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, word);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                endings[0] = resultSet.getString("FutureFirstPSg");
                endings[1] = resultSet.getString("FutureSecondPSg");
                endings[2] = resultSet.getString("FutureThirdPSg");
                endings[3] = resultSet.getString("FutureFirstPPl");
                endings[4] = resultSet.getString("FutureSecondPPl");
                endings[5] = resultSet.getString("FutureThirdPPl");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return endings;
    }


}
