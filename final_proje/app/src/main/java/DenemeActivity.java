package com.example.final_proje;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DenemeActivity extends AppCompatActivity {

    private TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deneme);

        tvResults = findViewById(R.id.tvResults);

        SharedPreferences sharedPreferences = getSharedPreferences("TYT", MODE_PRIVATE);
        String isim = sharedPreferences.getString("isim", "N/A");
        String soyisim = sharedPreferences.getString("soyisim", "N/A");
        float turkcenet = sharedPreferences.getFloat("turkcenet", 0);
        float matematiknet = sharedPreferences.getFloat("matematiknet", 0);
        float sosyalnet = sharedPreferences.getFloat("sosyalnet", 0);
        float fennet = sharedPreferences.getFloat("fennet", 0);
        float toplampuan = sharedPreferences.getFloat("toplampuan", 0);

        String results = String.format("Ad: %s %s\nTürkçe Net: %.2f\nMatematik Net: %.2f\nSosyal Net: %.2f\nFen Net: %.2f\nToplam Puan: %.2f",
                isim, soyisim,turkcenet, matematiknet, sosyalnet, fennet, toplampuan);
        tvResults.setText(results);
    }
}
