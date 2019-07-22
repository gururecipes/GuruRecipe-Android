package com.ddogan.gururecipe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 13.06.2019.
 */

public class CustomAdapter extends BaseAdapter {
    List<Tarif> tariflerJava = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;
    ImageView resim;

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
        View kartvizit = layoutInflater.inflate(R.layout.kartvizit, null);
        resim = (ImageView) kartvizit.findViewById(R.id.tarifResmi);
        TextView ad = (TextView) kartvizit.findViewById(R.id.tarifAdi);
       // TextView icerik = (TextView) kartvizit.findViewById(R.id.tarifIcerik);
        TextView etiket = (TextView) kartvizit.findViewById(R.id.tarifEtiket);
        Tarif tarif = tariflerJava.get(position);
        ad.setText(tarif.getTarifAdi());
        //icerik.setText(tarif.getTarifIcerik());
        etiket.setText(tarif.getTarifEtiket());
        resim.setImageResource(R.mipmap.recipe);
        Picasso.with(context).load(tarif.getTarifResimUrl().get(0).toString()).into(resim);
          /*  Bitmap b = ((BitmapDrawable)resim.getDrawable()).getBitmap();
            tarif.setTarifResim(b);*/
        /*
        try {
            URL url = new URL(tarif.getTarifResim());
            baglanti = (HttpURLConnection) url.openConnection();
            int baglanti_durumu = baglanti.getResponseCode();
            if (baglanti_durumu == HttpURLConnection.HTTP_OK) {
                BufferedInputStream stream = new BufferedInputStream(baglanti.getInputStream());
                //InputStream input = baglanti.getInputStream();
                myBitmap = BitmapFactory.decodeStream(stream);
                resim.setImageBitmap(myBitmap);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        //resim.setImageResource(R.mipmap.recipe); // resim.setImageURI(tarif.getResimler().get(0));
        return kartvizit;
    }
}