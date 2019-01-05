package com.dhbw.AStern;

public class Feld {
    private Integer x,y;
    private Double gvonx,hvonx,yvonx;
    private String gelaende;

    public Feld(int x, int y, String gelaende){
        this.x = x;
        this.y =y;
        this.gelaende = gelaende;
    }

    public Integer getX() {
        return x;
    }

    public Double getGvonx() {
        return gvonx;
    }

    public void setGvonx(Double gvonx) {
        this.gvonx = gvonx;
    }

    public Double getHvonx() {
        return hvonx;
    }

    public void setHvonx(Double hvonx) {
        this.hvonx = hvonx;
    }

    public Double getYvonx() {
        return yvonx;
    }

    public void setYvonx(Double yvonx) {
        this.yvonx = yvonx;
    }

    public Integer getY() {
        return y;
    }

    public String getGelaende() {
        return gelaende;
    }

}
