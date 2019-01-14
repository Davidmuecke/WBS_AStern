package com.dhbw.AStern;

import java.io.File;

/**
 * Hauptklasse zum Starten des Programms.
 * @author Jonathan Weyl
 *
 */
public class Main {

	/**
	 * Main-Methode zum Ausführen des Programms
	 * @param args Zu übergebende Argumente
	 * [0]	-	Pfad zur Datei, die die Karte enthält (im csv-Format)
	 * [1]	-	X-Koordinate des Startpunktes
	 * [2]	-	Y-Koordinate des Startpunktes
	 * [3]	-	X-Koordinate des Zielpunktes
	 * [4]	-	Y-Koordinate des Zielpunktes
	 */
    public static void main(String[] args) {
    	if(args.length != 5) {
    		StringBuilder stringBuilder = new StringBuilder();
    		stringBuilder.append("Bitte Programm mit folgenden Parametern Aurufen:\n");
    		stringBuilder.append("[0]	-	Pfad zur Datei, die die Karte enthält (im csv-Format)\n");
    		stringBuilder.append("[1]	-	X-Koordinate des Startpunktes\n");
    		stringBuilder.append("[2]	-	Y-Koordinate des Startpunktes\n");
    		stringBuilder.append("[3]	-	X-Koordinate des Zielpunktes\n");
    		stringBuilder.append("[4]	-	Y-Koordinate des Zielpunktes");
    		
    		System.out.println(stringBuilder.toString());
    		return;
    	}
    	
    	// Datei, die die Landkarte enthält.
    	File inputFile = new File(args[0]);
    	// Feld auf dem die Wanderung begonnen wird. Da intern bei 0 angefangen wird,
    	// aber nach außen hin bei 1, wird hier eins abgezogen.
    	Feld startFeld = new Feld(new Integer(args[1])-1,new Integer(args[2])-1);
    	// Ziel der Wanderung. Da intern bei 0 angefangen wird,
    	// aber nach außen hin bei 1, wird hier eins abgezogen.
    	Feld zielFeld = new Feld(new Integer(args[3])-1,new Integer(args[4])-1);
    	
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
