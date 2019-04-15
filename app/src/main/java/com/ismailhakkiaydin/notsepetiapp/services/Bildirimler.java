package com.ismailhakkiaydin.notsepetiapp.services;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;

import android.database.Cursor;
import android.net.Uri;
import java.util.ArrayList;

import com.ismailhakkiaydin.notsepetiapp.MainActivity;
import com.ismailhakkiaydin.notsepetiapp.R;
import com.ismailhakkiaydin.notsepetiapp.data.Notlar;
import com.ismailhakkiaydin.notsepetiapp.data.NotlarProvider;

import br.com.goncalves.pugnotification.notification.PugNotification;

public class Bildirimler extends IntentService {

    public static final Uri CONTENT_URI = NotlarProvider.CONTENT_URI;
    ArrayList<Notlar> tamamlanmayanNotlar = new ArrayList<>();

    public Bildirimler() {
        super("Bildirimler");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        tamamlanmayanNotlar = tamamlanmayanNotlariGetir();

        for (Notlar geciciNot : tamamlanmayanNotlar){

            if (bildirimGerekli(geciciNot.getNotEklenmeTarihi(), geciciNot.getNotTarih())){

                bildirimYolla();


            }

        }

    }

    private void bildirimYolla() {

        PugNotification.with(this)
                .load()
                .title("ZAMAN DARALIYOR")
                .message("Az bir süre kaldı. Bir an önce işini halletmelisin.")
                .bigTextStyle("HEMEN GÖREVİNİ TAMAMLA")
                .smallIcon(R.drawable.ic_action_balloon)
                .largeIcon(R.drawable.ic_action_balloon)
                .flags(Notification.DEFAULT_ALL)
                .click(MainActivity.class)
                .autoCancel(true)
                .simple()
                .build();

    }

    private boolean bildirimGerekli(long notEklenmeTarihi, long notTarih) {

        long nowTime = System.currentTimeMillis();

        if (nowTime > notTarih){
            return false;
        }else {
            long yuzde95 = (long)0.9 * (notTarih - notEklenmeTarihi);

            return (nowTime > (notEklenmeTarihi + yuzde95)) ? true : false;
        }
    }

    private ArrayList<Notlar> tamamlanmayanNotlariGetir(){

        ArrayList<Notlar> tamamlanmayanlar = new ArrayList<>();

        Cursor cursor = getContentResolver().query(CONTENT_URI, new String[]{"id", "notIcerik", "notEklenmeTarihi", "notTarih", "tamamlandi"}, "tamamlandi=?", new String[]{"0"}, null);

        if (cursor != null){
            while (cursor.moveToNext()){
                Notlar geciciNot = new Notlar();
                geciciNot.setId(cursor.getInt(cursor.getColumnIndex("id")));
                geciciNot.setNotIcerik(cursor.getString(cursor.getColumnIndex("notIcerik")));
                geciciNot.setNotTarih(cursor.getLong(cursor.getColumnIndex("notTarih")));
                geciciNot.setTamamlandi(cursor.getInt(cursor.getColumnIndex("tamamlandi")));
                tamamlanmayanlar.add(geciciNot);
            }
        }
        return tamamlanmayanlar;
    }

}
