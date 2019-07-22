package com.ddogan.gururecipe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CreateRecipe extends AppCompatActivity {

    TextView etiket_giriniz, tarif_giriniz, tarifAdi_giriniz;
    Button btn;
    EditText tarif, etiket, tarifAdi;
    Button btn3;//resim secen buton
    public static final int PICK_IMAGE_REQUEST= 71;//resim kodu
    Uri kaydetmeUrisi;
    List<Uri> resimler = new ArrayList<>();


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
        btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resimSec();
            }
        });

    }

    private void resimSec() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Resim seç"),PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            kaydetmeUrisi = data.getData();
            Toast.makeText(getApplicationContext(), "Resim eklendi.", Toast.LENGTH_LONG).show();
            //btn3.setText("Resim Seçildi");
           // iv.setImageURI(kaydetmeUrisi);
            resimler.add(kaydetmeUrisi);
        }
    }
/* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        Bitmap bm = (Bitmap) b.get("data");//burada farklı tiplerlede donulebilir resim
        iv.setImageBitmap(bm);//uri olarak da set edilebilir.
    }*/
}
