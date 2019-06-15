package com.ddogan.gururecipe;

import android.content.Context;
import android.content.res.Resources;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DeleteRecipe extends AppCompatActivity {
    ListView tarifler;
    List<Tarif> tariflerJava = new ArrayList<>();
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_recipe);
        tarifler = (ListView) findViewById(R.id.listView2);

        //WebServisiIleListeyiDoldur();
        XmlVerisiOku();
        tarifler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "İşleminiz alındı.", Toast.LENGTH_LONG).show();
                //position'i alinan veri silinecek
            }
        });
    }
    private void XmlVerisiOku() {
        Document document = null;
        try {
            Resources r = getResources();
            InputStream xmlFile = r.openRawResource(R.raw.tarifler);
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
            String icerik = nodeListIcerik.item(0).getFirstChild().getNodeValue();
            String etiket = nodeListEtiket.item(0).getFirstChild().getNodeValue();
            String ad = nodeListAd.item(0).getFirstChild().getNodeValue();
            tariflerJava.add(new Tarif(ad ,icerik, etiket));
        }

        CustomAdapter adapter = new CustomAdapter(context,tariflerJava);
        tarifler.setAdapter(adapter);
    }
    private void XmlVerisiSil(int position) {
        // File xmlFile = new File("/Users/User/Desktop/today.xml");
        Document document = null;
        Resources r = getResources();
        InputStream xmlFile= r.openRawResource(R.raw.tarifler);
        try {
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
        // NodeList tarifNodeList = document.getElementsByTagName("Tarif");
        NodeList tarifNodeList = document.getElementsByTagName("Currency");
        Node x = document.getElementsByTagName("Currency").item(position);
        document.getDocumentElement().removeChild(x);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(String.valueOf(r.openRawResource(R.raw.tarifler)));
            transformer.transform(source,result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        // DOMSource source = new DOMSource(document);
        // StreamResult result = new StreamResult(new DOMSource());

        for (int i=0; i<tarifNodeList.getLength(); i++){

            Element element = (Element) tarifNodeList.item(i);
            if(i == position) {
              /*  Node parent = element.getParentNode();
                parent.removeChild(element);
                parent.normalize();
               */
            }
          /*  //NodeList nodeListIcerik = element.getElementsByTagName("Icerik");
            NodeList nodeListIcerik = element.getElementsByTagName("Unit");
            //NodeList nodeListEtiket = element.getElementsByTagName("Etiket");
            NodeList nodeListEtiket = element.getElementsByTagName("Isim");
            String icerik = nodeListIcerik.item(0).getFirstChild().getNodeValue();
            String etiket = nodeListEtiket.item(0).getFirstChild().getNodeValue();
            tariflerJava.add(new Tarif("tarif adi",icerik, etiket));*/
        }
        /*
        CustomAdapter adapter = new CustomAdapter(context,tariflerJava);
        tarifler.setAdapter(adapter);
        */
    }

    /*
        Ornek web servisten veri cekme methodu
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
