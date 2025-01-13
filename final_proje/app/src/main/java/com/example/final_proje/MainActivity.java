package com.example.final_proje;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText idisim, idsoyisim, idturkced, idturkcey,
            idmatematikd, idmatematiky, idsosyald, idsosyaly, idfend , idfeny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idisim = findViewById(R.id.isim);
        idsoyisim = findViewById(R.id.soyisim);
        idturkced = findViewById(R.id.turkceD);
        idturkcey = findViewById(R.id.turkceY);
        idmatematikd = findViewById(R.id.matematikD);
        idmatematiky = findViewById(R.id.matematikY);
        idsosyald = findViewById(R.id.sosyalD);
        idsosyaly = findViewById(R.id.sosyalY);
        idfend = findViewById(R.id.fenD);
        idfeny = findViewById(R.id.fenY);
        Button hesapla = findViewById(R.id.hesapla);

        hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String isim = idisim.getText().toString().trim();
                String soyisim = idsoyisim.getText().toString().trim();

                if (isim.isEmpty() || soyisim.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Ad ve soyad alanlarını doldurunuz!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int turkced = getIntFromEditText(idturkced);
                int turkcey = getIntFromEditText(idturkcey);
                int matematikd  = getIntFromEditText(idmatematikd);
                int matematiky = getIntFromEditText(idmatematiky);
                int sosyald = getIntFromEditText(idsosyald);
                int sosyaly = getIntFromEditText(idsosyaly);
                int fend = getIntFromEditText(idfend);
                int feny = getIntFromEditText(idfeny);

                float turkcenet = enet(turkced, turkcey);
                float matematiknet = enet(matematikd, matematiky);
                float sosyalnet = enet(sosyald, sosyaly);
                float fennet = enet(fend, feny);

                float toplamnet = turkcenet + matematiknet + sosyalnet + fennet;
                float toplampuan = epuan(toplamnet);

                SharedPreferences sharedPreferences = getSharedPreferences("TYT", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("isim", isim);
                editor.putString("soyisim", soyisim);
                editor.putFloat("turkcenet", turkcenet);
                editor.putFloat("matematiknet", matematiknet);
                editor.putFloat("sosyalnet", sosyalnet);
                editor.putFloat("fennet", fennet);
                editor.putFloat("toplampuan", toplampuan);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, com.example.final_proje.DenemeActivity.class);
                startActivity(intent);
            }
        });
    }

    private int getIntFromEditText(EditText editText) {
        String text = editText.getText().toString().trim();
        if (text.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(text);
        }
    }

    private float enet(int dogru, int yanlis) {
        return dogru - (yanlis / 4.0f);
    }

    private float epuan(float toplamnet) {
        return 100 + (toplamnet * 3); // 100 temel puan + net başına 3 puan
    }
}
