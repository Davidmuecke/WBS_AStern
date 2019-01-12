/**
 * 
 */
package com.dhbw.AStern;

/**
 * @author Jonathan Weyl
 *
 */
public class ListElement {
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

	private Weg weg ;
	private Feld feld;
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
	
	public ListElement(Feld aFeld, Weg aWeg) {
		setWeg(aWeg);
		setFeld(aFeld);
	}
	
	public void addFeldToWeg(Feld aFeld) {
		getWeg().addFeld(aFeld);
	}
	
	
	public int getGesamtkosten() {
		return getWeg().getGesamtkosten();
	}
}
