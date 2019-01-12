package com.dhbw.AStern;

public class Feld implements Comparable<Feld>{
    private int x,y;
    private double gvonx,hvonx,fvonx;
    private String gelaende;
    
    
    public Feld(int x, int y, String gelaende){
        this.x = x;
        this.y =y;
        this.gelaende = gelaende;
    }

    public int getX() {
        return x;
    }

    public Double getGvonx() {
        return gvonx;
    }

    public void setGvonx(double gvonx) {
        this.gvonx = gvonx;
    }

    public Double getHvonx() {
        return hvonx;
    }

    public void setHvonx(double hvonx) {
        this.hvonx = hvonx;
    }

    public Double getFvonx() {
        return fvonx;
    }

    public void setFvonx(double fvonx) {
        this.fvonx = fvonx;
    }

    public int getY() {
        return y;
    }

    public String getGelaende() {
        return gelaende;
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
		return "Feld [x=" + x + ", y=" + y + "]";
	}

}
