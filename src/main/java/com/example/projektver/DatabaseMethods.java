package com.example.projektver;

public class DatabaseMethods {

    public static String checkVerseNumberPaliVerse(int VersNr) {

        return "SELECT Verse FROM PaliVerse WHERE VerseNr =" + VersNr;

    }

    public static String checkVerseNumberPaliVerseDecomposed(int VersNr) {

        return "SELECT Verse FROM PaliVerseDecomposed WHERE VerseNr =" + VersNr;

    }

    public static String checkVerseNumberPaliVerseGER(int VersNr) {

        return "SELECT Verse FROM PaliVerseGER WHERE VerseNr =" + VersNr;

    }

    public static String checkVerseNumberPaliVerseEN(int VersNr) {

        return "SELECT Verse FROM PaliVerseEN WHERE VerseNr =" + VersNr;

    }

    public static String notesToPaliVerse(int VersNr) {

        return "SELECT Notes FROM PaliVerse WHERE VerseNr =" + VersNr;

    }

}
