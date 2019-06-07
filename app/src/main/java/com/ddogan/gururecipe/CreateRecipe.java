package com.ddogan.gururecipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CreateRecipe extends AppCompatActivity {

    TextView etiket_giriniz, tarif_giriniz;
    Button btn;
    EditText tarif, etiket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        etiket_giriniz = (TextView) findViewById(R.id.tv);
        tarif_giriniz = (TextView) findViewById(R.id.tv2);
        btn = (Button) findViewById(R.id.button);
        tarif = (EditText) findViewById(R.id.tarifText);
        etiket = (EditText) findViewById(R.id.etiketText);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //etiket.setText("Tarif Ekliyorum!");
                Toast.makeText(getApplicationContext(), "Tarif başarılı bir şekilde eklendi."+ tarif.getText()+ " " +etiket.getText(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
