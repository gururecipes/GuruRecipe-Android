package com.ddogan.gururecipe;


import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 13.06.2019.
 */

public class Tarif {
    String tarifAdi;
    String tarifIcerik;
    String tarifEtiket;
    ArrayList tarifResimUrl;


    public ArrayList getTarifResimUrl() {
        return tarifResimUrl;
    }

    public void setTarifResimUrl(ArrayList tarifResimUrl) {
        this.tarifResimUrl = tarifResimUrl;
    }

    public Tarif(String tarifAdi, String tarifIcerik, String tarifEtiket, ArrayList resimUrl) {
        this.tarifAdi = tarifAdi;
        this.tarifIcerik = tarifIcerik;
        this.tarifEtiket = tarifEtiket;
        this.tarifResimUrl = resimUrl;
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
