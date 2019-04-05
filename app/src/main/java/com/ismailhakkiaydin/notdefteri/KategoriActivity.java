package com.ismailhakkiaydin.notdefteri;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class KategoriActivity extends AppCompatActivity {

    ListView kategoriListesi;
    EditText kategoriAdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        kategoriListesi = findViewById(R.id.lvKategoriListesi);
        kategoriAdi = findViewById(R.id.etKategoriAdi);

        kategoriListesi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String tiklanilanKategori = (String) kategoriListesi.getItemAtPosition(position);
                kategoriAdi.setText(tiklanilanKategori);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
