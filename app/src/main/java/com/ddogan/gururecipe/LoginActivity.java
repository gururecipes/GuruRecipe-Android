package com.ddogan.gururecipe;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    EditText kullaniciAdi;
    EditText sifre;
    Button giris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sifre = (EditText) findViewById(R.id.sifre);
        kullaniciAdi = (EditText) findViewById(R.id.kullaniciAdi);
        giris = (Button) findViewById(R.id.giris);

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "giriş"+ kullaniciAdi.getText() + sifre.getText(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Welcome.class);
                intent.putExtra("kullaniciAdi", kullaniciAdi.getText() );
                startActivity(intent);
            }
        });
    }

}

