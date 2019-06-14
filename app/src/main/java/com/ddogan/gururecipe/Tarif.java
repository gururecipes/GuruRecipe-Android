package com.ddogan.gururecipe;

/**
 * Created by User on 13.06.2019.
 */

public class Tarif {
    String tarifAdi;
    String tarifIcerik;
    String tarifEtiket;

    public Tarif(String tarifAdi, String tarifIcerik, String tarifEtiket) {
        this.tarifAdi = tarifAdi;
        this.tarifIcerik = tarifIcerik;
        this.tarifEtiket = tarifEtiket;
    }

    public String getTarifAdi() {
        return tarifAdi;
    }

    public void setTarifAdi(String tarifAdi) {
        this.tarifAdi = tarifAdi;
    }

    public String getTarifIcerik() {
        return tarifIcerik;
    }

    public void setTarifIcerik(String tarifIcerik) {
        this.tarifIcerik = tarifIcerik;
    }

    public String getTarifEtiket() {
        return tarifEtiket;
    }

    public void setTarifEtiket(String tarifEtiket) {
        this.tarifEtiket = tarifEtiket;
    }

}
