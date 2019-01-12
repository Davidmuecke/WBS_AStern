package com.dhbw.AStern;

import java.io.*;
import java.util.ArrayList;

/**
 * Statische Klasse zum einlesen der Karte.
 *
 */
public class KartenLeser {

	/**
	 * Liest die Landkarte aus der übergebenen Datei ein.
	 * @param aFile Datei aus der die Landkarte gelesen wird.
	 * @return mehrdimensionales Array an Feldern
	 */
    public static Feld[][] readCSV(File aFile){
        ArrayList<ArrayList<Feld>> felder = new ArrayList<>();

        try {
            BufferedReader bf = new BufferedReader(new FileReader(aFile));
            int y = 0;
            String line = bf.readLine();
            while(line != null && !line.contains(";;")){
                String[] values = line.split(";");
                felder.add(new ArrayList<>());
                for (int x = 0; x < values.length; x++) {
                    felder.get(y).add(new Feld(x,y,values[x]));
                }
                y++;
                line=bf.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        Feld[][] felderArray = new Feld[felder.size()][felder.get(0).size()];
        for (int i = 0; i < felder.size(); i++) {
            felderArray[i] = felder.get(i).toArray(new Feld[1]);
        }
        return felderArray;
    }
}
