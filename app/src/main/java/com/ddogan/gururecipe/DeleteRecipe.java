package com.ddogan.gururecipe;

import android.content.Context;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DeleteRecipe extends AppCompatActivity {
    ListView tarifler;
    List<Tarif> tariflerJava = new ArrayList<>();
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_recipe);
        tarifler = (ListView) findViewById(R.id.listView2);

        WebServisiIleListeyiDoldur();

        tarifler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "secilen elemanin indisi: "+ position, Toast.LENGTH_LONG).show();
            }
        });
    }
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

                // NodeList tarifNodeList = document.getElementsByTagName("Tarif");
                NodeList tarifNodeList = document.getElementsByTagName("Currency");

                for (int i=0; i<tarifNodeList.getLength(); i++){

                    Element element = (Element) tarifNodeList.item(i);
                    //NodeList nodeListIcerik = element.getElementsByTagName("Icerik");
                    NodeList nodeListIcerik = element.getElementsByTagName("Unit");
                    //NodeList nodeListEtiket = element.getElementsByTagName("Etiket");
                    NodeList nodeListEtiket = element.getElementsByTagName("Isim");
                    String icerik = nodeListIcerik.item(0).getFirstChild().getNodeValue();
                    String etiket = nodeListEtiket.item(0).getFirstChild().getNodeValue();

                    tarif_list.add("icerik: "+icerik+ "etiket: "+ etiket+ " ");
                    tariflerJava.add(new Tarif("tarif adi",icerik, etiket));
                }
            }

        } catch (Exception e) {
            Log.e("Xml parse hatasi",e.getMessage().toString());
        }
        finally {
            if(baglanti !=null)
                baglanti.disconnect();
        }
        CustomAdapter adapter = new CustomAdapter(context,tariflerJava);
        tarifler.setAdapter(adapter);
    }
}
