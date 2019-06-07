package com.ddogan.gururecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tv = (TextView) findViewById(R.id.islemtv);
    }
    public void tiklandi(View view){
        if(view.getId()==R.id.ekleButon){
            //Tarif ekleme ekranina gider.
            Intent intent = new Intent(getApplicationContext(),CreateRecipe.class);
            startActivity(intent);
        }
        else if(view.getId()==R.id.silButon){
            //Tarif silem ekranina gider.
            Intent intent = new Intent(getApplicationContext(),DeleteRecipe.class);
            startActivity(intent);
        }
        else if(view.getId()==R.id.gorButon){
            //Tarifleri goruntuleme ekranina gider.
            Intent intent = new Intent(getApplicationContext(),ViewRecipe.class);
            startActivity(intent);
        }

    }
}
