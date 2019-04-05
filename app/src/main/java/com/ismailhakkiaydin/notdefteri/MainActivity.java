package com.ismailhakkiaydin.notdefteri;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.ismailhakkiaydin.notdefteri.data.DatabaseHelper;
import com.ismailhakkiaydin.notdefteri.data.NotDefterimContract.KategoriEntry;
import com.ismailhakkiaydin.notdefteri.data.NotDefterimContract.NotlarEntry;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ListView notlarListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        notOlustur();

        spinner = findViewById(R.id.spinner);
        notlarListesi = findViewById(R.id.listViewNotlar);

        String[] notlar = getResources().getStringArray(R.array.notlar);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(MainActivity.this, R.layout.notlar_tek_satir, R.id.tvNot, notlar);
        notlarListesi.setAdapter(aa);
        notlarListesi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, NotActivity.class);
                String tiklanilanNot = (String) notlarListesi.getItemAtPosition(i);
                intent.putExtra("notIcerik", tiklanilanNot);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NotActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.kategoriler) {
            Intent intent = new Intent(this,KategoriActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void notOlustur() {

        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String insertSorgusu = "INSERT INTO notlar ("
                + NotlarEntry.COLUMN_NOT_ICERIK + ","
                + NotlarEntry.COLUMN_KATEGORI_ID + ","
                + NotlarEntry.COLUMN_OLUSTURULMA_TARIHI + ","
                + NotlarEntry.COLUMN_BITIS_TARIHI + " ,"
                + NotlarEntry.COLUMN_YAPILDI + ")"
                + " VALUES (\"SPORA GIT\", 1, \"07-05-2017\", \"\", 0)";

        db.execSQL(insertSorgusu);

        ContentValues yeniKayit = new ContentValues();
        yeniKayit.put(NotlarEntry.COLUMN_NOT_ICERIK, "Okula Uğra");
        yeniKayit.put(NotlarEntry.COLUMN_KATEGORI_ID, 1);
        yeniKayit.put(NotlarEntry.COLUMN_OLUSTURULMA_TARIHI, "06-05-2017");
        yeniKayit.put(NotlarEntry.COLUMN_YAPILDI, 0);

        long id = db.insert(NotlarEntry.TABLE_NAME, null, yeniKayit);
        db.close();

    }

}
