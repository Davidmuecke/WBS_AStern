package com.dhbw.AStern;


/**
 * Stellt ein Feld der Karte dar
 *
 */
public class Feld implements Comparable<Feld>{
	/**
	 * X-Koordinate des Feldes.
	 */
	private int x;
	
	/**
	 * Y-Koordinate des Feldes.
	 */
    private int y;
    
    /**
     * h-Funktion an der Stelle des Feldes.
     * Geschätzter Abstand bis zum Ziel (optimistisch geschätzt).
     */
    private double hvonx;
    
    /**
     * g-Funktion an der Stelle des Feldes.
     * Bisher bester Weg vom Start zum Feld.
     */
    private double gvonx;
    
    /**
     * f-Funktion an der Stelle des Feldes.
     * Addition aus g-Funktion und h-Funktion an der STelle des Feldes.
     */
    private double fvonx;
    
    /**
     * Geländebezeichnung des Feldes.
     */
    private String gelaende;
    
    
    /**
     * Konstruktor zur Erzeugung eines Feldes.
     * @param x X-Koordinate
     * @param y Y-Koordinate
     * @param gelaende Geländebezeichnung
     */
    public Feld(int x, int y, String gelaende){
        this.x = x;
        this.y =y;
        this.gelaende = gelaende;
    }
    
    /**
     * Konstruktor zur Erzeugung eines Feldes.
     * @param x X-Koordinate
     * @param y Y-Koordinate
     */
    public Feld(int x, int y){
        this(x, y, "0");
    }

    

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}



	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}



	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}



	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}



	/**
	 * @return the ghvonx
	 */
	public double getHvonx() {
		return hvonx;
	}



	/**
	 * @param hvonx the hvonx to set
	 */
	public void setHvonx(double hvonx) {
		this.hvonx = hvonx;
	}



	/**
	 * @return the gvonx
	 */
	public double getGvonx() {
		return gvonx;
	}



	/**
	 * @param gvonx the gvonx to set
	 */
	public void setGvonx(double gvonx) {
		this.gvonx = gvonx;
	}



	/**
	 * @return the fvonx
	 */
	public double getFvonx() {
		return fvonx;
	}



	/**
	 * @param fvonx the fvonx to set
	 */
	public void setFvonx(double fvonx) {
		this.fvonx = fvonx;
	}



	/**
	 * @return the gelaende
	 */
	public String getGelaende() {
		return gelaende;
	}



	/**
	 * @param gelaende the gelaende to set
	 */
	public void setGelaende(String gelaende) {
		this.gelaende = gelaende;
	}



	@Override
	public int compareTo(Feld o) {
		if(getGvonx() > o.getGvonx()) {
			return 1;
		} else if (getGvonx() < o.getGvonx()) {
			return -1;
		}
		return 0;
	}
	
	/**
	 * Mapt das Gelände auf die dazu gehörenden Kosten.
	 * @return kosten, das Feld zu überqueren.
	 */
	public int getKosten() {
		switch(getGelaende()) {
			case "0": return 7;
			case "1": return 10;
			case "2": return 4;
			case "3": return 9;
			case "4": return 5;
			case "5": return 12;
			default: return 0;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Feld [" + x + "," + y + "](" + getGelaendeAsString() +")";
	}
	
	/**
	 * Gibt zurück, ob das andere Feld die selbe Position hat.
	 * @param aFeld Feld, mit dem verglichen wird.
	 * @return
	 */
	public boolean samePosition(Feld aFeld) {
		if(getX() == aFeld.getX() && getY() == aFeld.getY()) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return das Gelände des Feldes als String.
	 */
	private String getGelaendeAsString() {
		switch(getGelaende()) {
		case "0": return "Ebene";
		case "1": return "Fluss";
		case "2": return "Weg";
		case "3": return "Wald";
		case "4": return "Bruecke";
		case "5": return "Felswand";
		default:  return "";
	}
	}

}
