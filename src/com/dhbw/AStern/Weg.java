package com.dhbw.AStern;

import java.util.ArrayList;
import java.util.Arrays;

public class Weg {
	private ArrayList<Feld> felder;
	private double kosten;
	private boolean istWegZumZiel;
	
	public Weg() {
		felder = new ArrayList<Feld>();
		istWegZumZiel=false;
	}
	
	public Weg(Weg aWeg) {
		felder = new ArrayList<Feld>();
		for (Feld feld : aWeg.getFelder()) {
			getFelder().add(feld);
		}
		istWegZumZiel= false;
	}
	
	public void addFeld(Feld aFeld) {
		felder.add(aFeld);
	}

	/**
	 * @return the felder
	 */
	public ArrayList<Feld> getFelder() {
		return felder;
	}

	

	/**
	 * @return the kosten
	 */
	public double getKosten() {
		return kosten;
	}

	/**
	 * @param kosten the kosten to set
	 */
	public void setKosten(double kosten) {
		this.kosten = kosten;
	}

	/**
	 * @return the istWegZumZiel
	 */
	public boolean isIstWegZumZiel() {
		return istWegZumZiel;
	}

	/**
	 * @param istWegZumZiel the istWegZumZiel to set
	 */
	public void setIstWegZumZiel(boolean istWegZumZiel) {
		this.istWegZumZiel = istWegZumZiel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder theStringBuilder = new StringBuilder();
		
		theStringBuilder.append("Weg:\n");
		for(int i = 0; i < getFelder().size()-1; i++) {
			theStringBuilder.append("Kosten " + getFelder().get(i).getKosten() + " von " + getFelder().get(i) + " nach " + getFelder().get(i+1) + "\n");
		}
		theStringBuilder.append("Schritte: " + getWeglaenge() + "\n");
		theStringBuilder.append("Gesamtkosten: " + getGesamtkosten());
		
		return theStringBuilder.toString();
	}
	
	public int getGesamtkosten() {
		int gesamtkosten = 0;
		for (Feld feld : getFelder()) {
			gesamtkosten += feld.getKosten();
		}
		
		if (istWegZumZiel) {
			gesamtkosten -= getFelder().get(getFelder().size()-1).getKosten();
		}
		return gesamtkosten;
	}
	
	public int getWeglaenge() {
		if (istWegZumZiel) {
			return getFelder().size()-1;
		}
		return getFelder().size();
	}
	
	

}
