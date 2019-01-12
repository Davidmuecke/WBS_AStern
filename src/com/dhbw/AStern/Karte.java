package com.dhbw.AStern;

import javafx.util.Pair;

/**
 *
 */
public class Karte {
    private Feld[][] felder;
    
    private Feld start;
    private Feld ziel;

    public Karte(Feld[][] feld, Feld start, Feld ziel) {
        this.felder = feld;
        this.start = getFeld(start.getX(), start.getY());
        this.ziel = getFeld(ziel.getX(), ziel.getY());
    }

    public Feld getFeld(int x, int y) throws IndexOutOfBoundsException{
        return felder[y][x];
    }
    public Feld[][] getFelder() {
        return felder;
    }
    
    /**
	 * @return the start
	 */
	public Feld getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(int x, int y) {
		ziel = this.getFeld(x, y);
	}

	/**
	 * @return the ziel
	 */
	public Feld getZiel() {
		return ziel;
	}

	/**
	 * @param ziel the ziel to set
	 */
	public void setZiel(int x, int y) {
		
		ziel = this.getFeld(x, y);
	}

	public int getWidth() {
    	return getFelder()[0].length;
    }
    
    public int getHeight() {
    	return getFelder().length;
    }

    @Override
    public String toString(){
        if (felder != null){
            StringBuilder sb = new StringBuilder();
            for (int y = 0; y < felder.length ; y++) {
                for (int x = 0; x < felder[y].length; x++) {
                    sb.append(felder[y][x].getGelaende());
                    sb.append("\t");
                }
                sb.append("\n");
            }
            return sb.toString();
        }
        return "Karte enthält keine Felder!";
    }
    
    /**
     * Schätzt den Abstand jedes Feldes auf dem Plan bis zum Ziel.
     * @param aZielfeld Zielfeld der Karte
     */
    public void calculateHFunction(Feld aZielfeld) {
    	for (int x = 0; x < getWidth(); x++) {
    		for(int y = 0; y < getHeight(); y++) {
    			int xDistanz = Math.abs(x - aZielfeld.getX());
    			int yDistanz = Math.abs(x - aZielfeld.getY());
    			
    			getFeld(x,y).setHvonx(Math.sqrt(Math.pow(xDistanz, 2)+ Math.pow(yDistanz, 2)));
    		}
    	}
    }
}
