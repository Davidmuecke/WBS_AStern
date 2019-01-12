package com.dhbw.AStern;

import java.util.ArrayList;

/**
 * Stellt eine Folge von Feldern dar.
 * @author Jonathan Weyl
 *
 */
public class Weg {
	private ArrayList<Feld> felder;

	/**
	 * Wenn der Weg als "wegZumZiel" markiert ist, enth�lt er auch das Schlussfeld,
	 * um den L�sungsweg sch�n darstellen zu k�nnen.
	 */
	private boolean istWegZumZiel;
	
	/**
	 * Ersch�pfungsz�hler.
	 */
	private double erschoepfung;
	
	/**
	 * Pausen
	 */
	private int pausen;

	/**
	 * Konstruktor zum erzeugen eines leeren Wegs
	 */
	public Weg() {
		setFelder(new ArrayList<Feld>());
		setIstWegZumZiel(false);
		setErschoepfung(0.0);
	}

	/**
	 * Konstruktor zum erzeugen eines Weges, der die Felder eines anderen �bernimmt.
	 * 
	 * @param aWeg Weg, dessen Felder �bernommen werden sollen.
	 */
	public Weg(Weg aWeg) {
		setFelder(new ArrayList<Feld>());
		for (Feld feld : aWeg.getFelder()) {
			getFelder().add(feld);
		}
		setIstWegZumZiel(false);
		setErschoepfung(aWeg.getErschoepfung());
		setPausen(aWeg.getPausen());
	}

	/**
	 * @param felder the felder to set
	 */
	private void setFelder(ArrayList<Feld> felder) {
		this.felder = felder;
	}

	/**
	 * @return the felder
	 */
	public ArrayList<Feld> getFelder() {
		return felder;
	}

	/**
	 * F�gt dem Weg ein Feld hinzu.
	 * 
	 * @param aFeld Feld, das hinzugef�gt wird.
	 */
	public void addFeld(Feld aFeld) {
		felder.add(aFeld);

		switch(aFeld.getGelaende()) {
			case "0": setErschoepfung(getErschoepfung() + 0);
			break;
			case "1": setErschoepfung(getErschoepfung() + 4);
			break;
			case "2": setErschoepfung(getErschoepfung() + 0);
			break;
			case "3": setErschoepfung(getErschoepfung() / 2);
			break;
			case "4": setErschoepfung(getErschoepfung() + 0);
			break;
			case "5": setErschoepfung(getErschoepfung() + 3);
			break;
			default:  setErschoepfung(getErschoepfung() + 0);
		}
		if(getErschoepfung() >= 10) {
			setErschoepfung(getErschoepfung() -10 );
			setPausen(getPausen()+ 1);
		}
	}

	/**
	 * @return the istWegZumZiel
	 */
	public boolean isIstWegZumZiel() {
		return istWegZumZiel;
	}

	/**
	 * @param istWegZumZiel
	 *            the istWegZumZiel to set
	 */
	public void setIstWegZumZiel(boolean istWegZumZiel) {
		this.istWegZumZiel = istWegZumZiel;
	}
	
	/**
	 * @return the erschoepfung
	 */
	public double getErschoepfung() {
		return erschoepfung;
	}

	/**
	 * @param erschoepfung the erschoepfung to set
	 */
	public void setErschoepfung(double erschoepfung) {
		this.erschoepfung = erschoepfung;
	}

	/**
	 * @return the pausen
	 */
	public int getPausen() {
		return pausen;
	}

	/**
	 * @param pausen the pausen to set
	 */
	public void setPausen(int pausen) {
		this.pausen = pausen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("Weg:\n");
		for (int i = 0; i < getFelder().size() - 1; i++) {
			stringBuilder.append("Kosten " + getFelder().get(i).getKosten() + " von " + getFelder().get(i) + " nach "
					+ getFelder().get(i + 1) + "\n");
		}
		stringBuilder.append("Schritte: " + getWeglaenge() + "\n");
		stringBuilder.append("Gesamtkosten: " + getGesamtkosten() + "\n");
		stringBuilder.append("Pausen: " + getPausen());

		return stringBuilder.toString();
	}

	/**
	 * Ermittelt die Gesamtkosten des Weges. Die Kosten eiens Feldes werden beim
	 * Verlassen des Feldes berechnet.
	 * 
	 * @return Gesamtkosten
	 */
	public int getGesamtkosten() {
		int gesamtkosten = 0;
		for (Feld feld : getFelder()) {
			gesamtkosten += feld.getKosten();
		}

		// Wenn es sich um einen "WegZumZiel" handelt, enth�lt dieser auch das letzte
		// Feld.
		// F�r dieses m�ssen die Kosten nicht mit einberechnet werden, da es nicht
		// verlassen wird.

		if (istWegZumZiel) {
			gesamtkosten -= getFelder().get(getFelder().size() - 1).getKosten();
		}
		
		gesamtkosten += getPausen()*5;
		return gesamtkosten;
	}
	

	/**
	 * Ermittelt die Gesamtl�nge eines Weges in Feldern.
	 * 
	 * @return L�nge des Weges in Feldern
	 */
	public int getWeglaenge() {
		// Wenn es sich um einen "WegZumZiel" handelt, enth�lt dieser auch das letzte
		// Feld.
		// Das Zielfeld wird nicht mit gez�hlt, denn es stellt keinen Schritt dar.
		if (istWegZumZiel) {
			return getFelder().size() - 1;
		}
		return getFelder().size();
	}
	
}
