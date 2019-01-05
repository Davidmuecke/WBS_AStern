package com.dhbw.AStern;

/**
 *
 */
public class Karte {
    private Feld[][] felder;

    public Karte(Feld[][] f) {
        this.felder = f;
    }

    public Feld getFeld(int x, int y) throws IndexOutOfBoundsException{
        return felder[y][x];
    }
    public Feld[][] getFelder() {
        return felder;
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
}
