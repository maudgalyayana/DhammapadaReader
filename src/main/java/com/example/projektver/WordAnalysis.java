package com.example.projektver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WordAnalysis {

    Connection connection;

    // Constructor for Connection
    public WordAnalysis(Connection connection) {
        this.connection = connection;
    }



    public String genderAnalysis(String word) {
        String matchingValue = "-";

        try {

            String query = "SELECT Gender1 FROM PaliWord JOIN Gender ON PaliWord.GenderID = " +
                    "Gender.GenderID WHERE PaliWord.PaliWord = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, word);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                matchingValue = resultSet.getString("Gender1");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingValue;
    }

    public String translationGERAnalysis(String word) {
        String matchingValue = "-";

        try {

            String query = "SELECT TranslationPaliWordGER FROM PaliWord JOIN TranslationPaliWordGER ON PaliWord.TranslationPaliWordGERID = " +
                    "TranslationPaliWordGER.TranslationPaliWordGERID WHERE PaliWord.PaliWord = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, word);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                matchingValue = resultSet.getString("TranslationPaliWordGER");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingValue;
    }

    public String rootAnalysis(String word) {
        String matchingValue = "-";

        try {

            String query = "SELECT Root FROM PaliWord WHERE PaliWord = ?";
            PreparedStatement statement = connection.prepareStatement(query);


            statement.setString(1, word);


            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                matchingValue = resultSet.getString("Root");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingValue;
    }

    public String root2Analysis(String word) {
        String matchingValue = "-";

        try {

            String query = "SELECT Root2 FROM PaliWord WHERE PaliWord = ?";
            PreparedStatement statement = connection.prepareStatement(query);


            statement.setString(1, word);


            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                matchingValue = resultSet.getString("Root2");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingValue;
    }

    public String root3Analysis(String word) {
        String matchingValue = "-";

        try {

            String query = "SELECT Root3 FROM PaliWord WHERE PaliWord = ?";
            PreparedStatement statement = connection.prepareStatement(query);


            statement.setString(1, word);


            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                matchingValue = resultSet.getString("Root3");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingValue;
    }

    public String root4Analysis(String word) {
        String matchingValue = "-";

        try {

            String query = "SELECT Root4 FROM PaliWord WHERE PaliWord = ?";
            PreparedStatement statement = connection.prepareStatement(query);


            statement.setString(1, word);


            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                matchingValue = resultSet.getString("Root4");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingValue;
    }




    public String queryNumber(String word) {
        String matchingValue = "-";

        try {

            String query = "SELECT NumberRow FROM PaliWord JOIN Number ON PaliWord.NumberID = " +
                    "Number.NumberID WHERE PaliWord.PaliWord = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, word);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                matchingValue = resultSet.getString("NumberRow");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingValue;
    }

    public String queryPerson(String word) {
        String matchingValue = "-";

        try {

            String query = "SELECT PersonRow FROM PaliWord JOIN Person ON PaliWord.PersonID = " +
                    "Person.PersonID WHERE PaliWord.PaliWord = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, word);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                matchingValue = resultSet.getString("PersonRow");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingValue;
    }

    public String queryCase(String word) {
        String matchingValue = "-";

        try {

            String query = "SELECT CaseRow FROM PaliWord JOIN Case1 ON PaliWord.CaseID = " +
                    "Case1.CaseID WHERE PaliWord.PaliWord = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, word);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                matchingValue = resultSet.getString("CaseRow");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingValue;
    }

}
