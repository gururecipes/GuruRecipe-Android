package com.ddogan.gururecipe;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CreateRecipe extends AppCompatActivity {

    TextView etiket_giriniz, tarif_giriniz, tarifAdi_giriniz;
    Button btn;
    EditText tarif, etiket, tarifAdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        etiket_giriniz = (TextView) findViewById(R.id.tv);
        tarif_giriniz = (TextView) findViewById(R.id.tv2);
        tarifAdi_giriniz = (TextView) findViewById(R.id.tv0);
        btn = (Button) findViewById(R.id.button);
        tarif = (EditText) findViewById(R.id.tarifText);
        etiket = (EditText) findViewById(R.id.etiketText);
        tarifAdi = (EditText) findViewById(R.id.tarifAdiText);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Tarif verileriniz başarılı bir şekilde alındı.", Toast.LENGTH_LONG).show();
                //tarif.getText() ve etiket.getText() tarifAdi.getText() ten tarif verileri tutulur.
            }
        });
    }

}
