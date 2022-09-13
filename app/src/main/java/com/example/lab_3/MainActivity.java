package com.example.lab_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String searchTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);

        List<String> list = new ArrayList<>();
        list.add("Message");
        list.add("Map");
        list.add("Image");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    Intent smsIntent = getPackageManager().getLaunchIntentForPackage(Telephony.Sms.getDefaultSmsPackage(MainActivity.this));
                    if (smsIntent != null) {
                        startActivity(smsIntent);
                    }
                }
                else if (position == 1) {
                    AlertDialog.Builder mapDialog = new AlertDialog.Builder(MainActivity.this);
                    mapDialog.setTitle("Enter Your Location");

                    final EditText searchEditTxt = new EditText(MainActivity.this);
                    searchEditTxt.setInputType(InputType.TYPE_CLASS_TEXT);
                    mapDialog.setView(searchEditTxt);

                    mapDialog.setPositiveButton("Search", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            searchTxt = searchEditTxt.getText().toString();
                            String location = "https://www.google.com/maps/search/?api=1&query="+searchTxt;
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(location));
                            startActivity(mapIntent);
                        }
                    });
                    mapDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    mapDialog.show();
                }

                else{
                    Intent photoIntent = new Intent(MainActivity.this,Photo_Activity.class);
                    startActivity(photoIntent);
                }
            }
        });
    }
}