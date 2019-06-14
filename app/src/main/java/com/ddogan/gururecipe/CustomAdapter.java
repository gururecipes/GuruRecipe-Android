package com.ddogan.gururecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 13.06.2019.
 */

public class CustomAdapter extends BaseAdapter {
    List<Tarif> tariflerJava = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public CustomAdapter(Context _context, List<Tarif> _tariflerJava) {
        this.context = _context;
        this.tariflerJava = _tariflerJava;
    }

    @Override
    public int getCount() {
        // listview de goruntulenecek satir sayisi
        return tariflerJava.size();
    }

    @Override
    public Object getItem(int position) {
        // posistion ile sirasi gelen eleman
        return position;
    }

    @Override
    public long getItemId(int position) {
        // varsa id bilgisi
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //position ile sirasi gelen satir icin view dondurur
        layoutInflater = LayoutInflater.from(context);
        View kartvizit = layoutInflater.inflate(R.layout.kartvizit,null);
        ImageView resim = (ImageView) kartvizit.findViewById(R.id.tarifResmi);
        TextView ad = (TextView) kartvizit.findViewById(R.id.tarifAdi);
        TextView icerik = (TextView) kartvizit.findViewById(R.id.tarifIcerik);
        TextView etiket = (TextView) kartvizit.findViewById(R.id.tarifEtiket);
        Tarif tarif = tariflerJava.get(position);
        ad.setText(tarif.getTarifAdi());
        icerik.setText(tarif.getTarifIcerik());
        etiket.setText(tarif.getTarifEtiket());
        resim.setImageResource(R.mipmap.recipe);
        return kartvizit;
    }
}
