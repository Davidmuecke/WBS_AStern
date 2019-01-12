/**
 * 
 */
package com.dhbw.AStern;

/**
 * Stellt ein Element einer Open-Liste oder Close-Liste im AStern-Algorithmus dar
 * @author Jonathan Weyl
 *
 */
public class ListElement {
	/**
	 * Weg bis zum Feld.
	 */
	private Weg weg ;
	
	/**
	 * Feld, zu dem der Weg führt.
	 */
	private Feld feld;
	
	
	
	/**
	 * Konstruktor zur Erzeugung eines Listen-Elements.
	 * @param aFeld Feld, zu dem der Weg führt.
	 * @param aWeg Weg bis zum Feld.
	 */
	public ListElement(Feld aFeld, Weg aWeg) {
		setWeg(aWeg);
		setFeld(aFeld);
	}
	
	/**
	 * @return the weg
	 */
	public Weg getWeg() {
		return weg;
	}
	/**
	 * @param weg the weg to set
	 */
	public void setWeg(Weg weg) {
		this.weg = weg;
	}

	/**
	 * @return the feld
	 */
	public Feld getFeld() {
		return feld;
	}
	/**
	 * @param feld the feld to set
	 */
	public void setFeld(Feld feld) {
		this.feld = feld;
	}
	
	

	/**
	 * Hängt ein weiters Feld an den Weg an und berechnet den neuen Erschoepfungswert.
	 * @param aFeld neues Feld.
	 */
	public void addFeldToWeg(Feld aFeld) {
		getWeg().addFeld(aFeld);
	}
	
	/**
	 * Berechnet die Gesamtkosten des Weges.
	 * @return Gesamtkosten des Weges.
	 */
	public int getGesamtkosten() {
		return getWeg().getGesamtkosten();
	}
}
