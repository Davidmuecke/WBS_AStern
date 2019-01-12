package com.dhbw.AStern;

import java.io.File;

import javafx.util.Pair;

public class Main {

    public static void main(String[] args) {
    	// TODO Eingabe als Argumente der main Methode
    	// TODO Prüfung, ob Punkte auf der Karte liegen
    	File theInputFile = new File("C:\\Daten\\Jonathan\\repos\\WBS_AStern\\WBS_AStern\\src\\com\\dhbw\\AStern\\S_012_Daten.csv");
    	Feld startFeld = new Feld(0,0,"0");
    	Feld zielFeld = new Feld(14,14,"0");
    	
    	
    	Karte theKarte = new Karte(KartenLeser.readCSV(theInputFile), startFeld, zielFeld);
    	

    	System.out.println("Startpukt: " + startFeld);
    	System.out.println("Zielpunkt: " + zielFeld);
    	System.out.println(theKarte);
    	
    	AStern aStern = new AStern(theKarte);
    	
    	System.out.println(aStern.suche());
    }
}
