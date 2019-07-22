package com.ddogan.gururecipe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class TarifDetaylari extends AppCompatActivity {

    TextView tv, tv2, tv3;
    TextView tvAd, tvIcerik, tvEtiket;

    ListView resimView;
    List<Resim> resimlerListe = new ArrayList<>();
    Context context = this;
    ResimAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarif_detaylari);

        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);

        resimView =  (ListView) findViewById(R.id.listView2);

        tvAd = (TextView) findViewById(R.id.textAd);
        tvIcerik = (TextView) findViewById(R.id.textIcerik);
        tvEtiket = (TextView) findViewById(R.id.textEtiket);
        Intent intent = getIntent();
        String tarifAdi= intent.getStringExtra("tarifAdi");
        String tarifEtiket = intent.getStringExtra("tarifEtiket");
        String tarifIcerik = intent.getStringExtra("tarifIcerik");
        ArrayList tarifResimUrls = intent.getParcelableArrayListExtra("tarifResimUrl");
        resimOku(tarifResimUrls);
        // iv.setImageBitmap(tarifResim);
        //Picasso.with(this).load(tarifResimUrls.get(0).toString()).into(iv);
        tvAd.setText(tarifAdi);
        tvIcerik.setText(tarifIcerik);
        tvEtiket.setText(tarifEtiket);
    }

    private void resimOku(ArrayList tarifResimUrls) {
        for (int i =0; i<tarifResimUrls.size();i++){
            resimlerListe.add(new Resim(tarifResimUrls.get(i).toString()));
        }
        //resimlerListe.add(new Resim(tarifResimUrl));
        adapter = new ResimAdapter(context,resimlerListe);
        resimView.setAdapter(adapter);
    }



}
