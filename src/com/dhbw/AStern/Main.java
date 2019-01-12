package com.dhbw.AStern;

import java.io.File;


public class Main {

    public static void main(String[] args) {
    	if(args.length != 5) {
    		System.out.println("Bitte Programm mit folgenden Parametern Aurufen:");
    		return;
    	}
    	
    	// Datei, die die Landkarte enthält.
    	File inputFile = new File(args[0]);
    	// Feld auf dem die Wanderung begonnen wird.
    	Feld startFeld = new Feld(new Integer(args[1]),new Integer(args[2]));
    	// Ziel der Wanderung.
    	Feld zielFeld = new Feld(new Integer(args[3]),new Integer(args[4]));
    	
    	// Landkarte.
    	Karte karte;
    	
    	try {
    		karte = new Karte(KartenLeser.readCSV(inputFile), startFeld, zielFeld);
    	} catch (IndexOutOfBoundsException ex) {
    		System.out.println("Startfeld oder Zielfeld liegen ausserhalb des Kartenbereichs.");
    		return; 
    	}
    	
    	// Gibt die Eingansparameter aus:
    	System.out.println("Startpukt: " + startFeld);
    	System.out.println("Zielpunkt: " + zielFeld);
    	System.out.println(karte);
    	
    	// Initialieren des ASternProzessors
    	ASternProcessor aStern = new ASternProcessor(karte);
    	
    	// Berechnung und Ausgabe des besten Weges vom Start zum Ziel
    	System.out.println(aStern.suche());
    }
}
