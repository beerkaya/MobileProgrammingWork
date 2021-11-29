package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toolbar;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    private ArrayList<Person> persons;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private PersonRecyclerAdapter personRecyclerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        persons = new ArrayList<Person>();

        // Contacts Data Import
        final Uri content_uri = ContactsContract.Contacts.CONTENT_URI;
        final String ID = ContactsContract.Contacts._ID;
        final String Name = ContactsContract.Contacts.DISPLAY_NAME;
        final String HasPhoneNumber = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        final String PhotoId = ContactsContract.Contacts.PHOTO_ID;

        final Uri phone_uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        final String phone_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        final String phone_number = ContactsContract.CommonDataKinds.Phone.NUMBER;

        ContentResolver contentResolver = getContentResolver();
        Bundle bundle = new Bundle();
        bundle.putInt(ContentResolver.QUERY_ARG_LIMIT, 100);
        Cursor cursor = contentResolver.query(content_uri, null, bundle, null);

        while (cursor.moveToNext()){
            @SuppressLint("Range") String person_id = cursor.getString(cursor.getColumnIndex(ID));
            @SuppressLint("Range") String person_name = cursor.getString(cursor.getColumnIndex(Name));

            Bitmap photo = null;
            try{
                InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(), ContentUris.withAppendedId(content_uri, Long.valueOf(person_id)));
                if (inputStream != null){
                    photo = BitmapFactory.decodeStream(inputStream);

                    assert inputStream != null;
                    inputStream.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }

            Cursor phoneCursor = contentResolver.query(phone_uri, null, phone_ID+"=?", new String[] {person_id}, null);

            while (phoneCursor.moveToNext()){
                @SuppressLint("Range") final String t = phoneCursor.getString(phoneCursor.getColumnIndex(phone_number));

                persons.add(new Person(photo, person_name, t, false));
                break;
            }
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
        personRecyclerAdapter = new PersonRecyclerAdapter(persons);
        recyclerView.setAdapter(personRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}