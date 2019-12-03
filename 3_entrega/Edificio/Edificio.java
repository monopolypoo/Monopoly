package Edificio;

import Casilla.*;

public abstract class Edificio {
    private double precioConstruir;
    private String id;
    private Solar solar;

    public Edificio() {
        this.precioConstruir = 0;
        this.id = null;
        this.solar = new Solar();
    }

    public Edificio(double precioConstruir, String id, Solar solar) {
        if (precioConstruir >= 0) {
            this.precioConstruir = precioConstruir;
        } else {
            this.precioConstruir = 0;
        }
        if (id != null) {
            this.id = id;
        } else {
            this.id = null;
        }
        if (solar != null){
            this.solar = solar;
        } else {
            this.solar = new Solar();
        }
    }

    public double getPrecioConstruir() {
        return precioConstruir;
    }

    public void setPrecioConstruir(double precioConstruir) {
        if (precioConstruir >= 0) {
            this.precioConstruir = precioConstruir;
        }
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        if (id != null) {
            this.id = id;
        }
    }

    public Solar getSolar() {
        return solar;
    }

    public void setSolar(Solar solar) {
        if (solar != null) {
            this.solar = solar;
        }
    }
}
