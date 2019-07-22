package com.ddogan.gururecipe;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ViewRecipe extends AppCompatActivity {//implements SearchView.OnQueryTextListener

    ListView tarifler;
    List<Tarif> tariflerJava = new ArrayList<>();
    Context context = this;
    CustomAdapter adapter;
    EditText et;
    ArrayList resimUrls = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        tarifler = (ListView) findViewById(R.id.listView);

        et = (EditText) findViewById(R.id.search);
       resimUrls.add("http://lezzet.blob.core.windows.net/images-category/turkiye-turu.jpg");
        XmlVerisiOku();
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    adapter = new CustomAdapter(context,tariflerJava);
                    tarifler.setAdapter(adapter);
                }else{
                    arama(s.toString().toLowerCase());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tarifler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "İşleminiz alındı.", Toast.LENGTH_LONG).show();
                //position'i alinan veri silinecek
                Tarif tarif = tariflerJava.get(position);
                tarifDetaylariniGoster(tarif);
            }
        });
       // WebServisiIleListeyiDoldur();
    }
    private void arama(String kelime){
        List<Tarif> filter = new ArrayList<>();

        for (Tarif t: tariflerJava){
            if(t.getTarifAdi().toLowerCase().contains(kelime.toLowerCase())||t.getTarifEtiket().toLowerCase().contains(kelime.toLowerCase())||t.getTarifIcerik().toLowerCase().contains(kelime.toLowerCase())){
                filter.add(t);
            }
        }
        adapter = new CustomAdapter(context,filter);
        tarifler.setAdapter(adapter);
    }

    private void tarifDetaylariniGoster(Tarif tarif) {
        Intent intent = new Intent(getApplicationContext(), TarifDetaylari.class);
        intent.putExtra("tarifAdi", tarif.getTarifAdi() );
        intent.putExtra("tarifEtiket", tarif.getTarifEtiket());
        intent.putExtra("tarifIcerik", tarif.getTarifIcerik());
       // intent.putExtra("tarifResimUrl", (CharSequence) tarif.getTarifResimUrl());
        intent.putParcelableArrayListExtra("tarifResimUrl", tarif.getTarifResimUrl());
        startActivity(intent);
    }

    private void XmlVerisiOku() {
        Document document = null;
        try {
            Resources r = getResources();
            InputStream   xmlFile = r.openRawResource(R.raw.tarifler);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(xmlFile);
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        NodeList tarifNodeList = document.getElementsByTagName("Currency");

        for (int i=0; i<tarifNodeList.getLength(); i++){

            Element element = (Element) tarifNodeList.item(i);
            NodeList nodeListAd = element.getElementsByTagName("Unit");
            NodeList nodeListIcerik = element.getElementsByTagName("Isim");
            NodeList nodeListEtiket = element.getElementsByTagName("CurrencyName");
            NodeList nodeListResim = element.getElementsByTagName("Picture");
            String icerik = nodeListIcerik.item(0).getFirstChild().getNodeValue();
            String etiket = nodeListEtiket.item(0).getFirstChild().getNodeValue();
            String ad = nodeListAd.item(0).getFirstChild().getNodeValue();
            NodeList resimUrl = nodeListResim;
            ArrayList n = new ArrayList();
            for(int j=0;j<resimUrl.getLength();j++){
                n.add(resimUrl.item(j).getFirstChild().getNodeValue());
            }
            tariflerJava.add(new Tarif(ad ,icerik, etiket, n));
        }

        adapter = new CustomAdapter(context,tariflerJava);
        tarifler.setAdapter(adapter);
    }

    /*
        Ornek web servisten veri cekme
     */
    private void WebServisiIleListeyiDoldur() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String tarif_url = "http://www.tcmb.gov.tr/kurlar/today.xml";
        List<String> tarif_list = new ArrayList<>();//buraya <~> gelebilir!!!
        HttpURLConnection baglanti = null;

        try{
            URL url = new URL(tarif_url);
            baglanti = (HttpURLConnection) url.openConnection();

            int baglanti_durumu = baglanti.getResponseCode();
            if(baglanti_durumu==HttpURLConnection.HTTP_OK){
                BufferedInputStream stream = new BufferedInputStream(baglanti.getInputStream());
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(stream);
                NodeList tarifNodeList = document.getElementsByTagName("Currency");

                for (int i=0; i<tarifNodeList.getLength(); i++){

                    Element element = (Element) tarifNodeList.item(i);
                    NodeList nodeListIcerik = element.getElementsByTagName("Unit");
                    NodeList nodeListEtiket = element.getElementsByTagName("Isim");
                    String icerik = nodeListIcerik.item(0).getFirstChild().getNodeValue();
                    String etiket = nodeListEtiket.item(0).getFirstChild().getNodeValue();

                    tarif_list.add("icerik: "+icerik+ "etiket: "+ etiket+ " ");
                    //setImageResource(R.mipmap.recipe);
                    String resimUrl=null;//burayı sonra kaldır
                    tariflerJava.add(new Tarif("tarif adi",icerik, etiket, resimUrls));
                }
            }

        } catch (Exception e) {
            Log.e("Xml parse hatasi",e.getMessage().toString());
        }
        finally {
            if(baglanti !=null)
                baglanti.disconnect();
        }
        adapter = new CustomAdapter(context,tariflerJava);
        tarifler.setAdapter(adapter);
    }

}
