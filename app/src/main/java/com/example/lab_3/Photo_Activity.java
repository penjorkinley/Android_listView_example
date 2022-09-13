package com.example.lab_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import org.jetbrains.annotations.NonNls;

public class Photo_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        ImageView imageView1 = findViewById(R.id.imageView1);
        ImageView imageView2 = findViewById(R.id.imageView2);

        registerForContextMenu(imageView1);
        registerForContextMenu(imageView2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.image_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.view:
                Intent imgIntent1 = new Intent(Photo_Activity.this,Image1.class);
                startActivity(imgIntent1);

                return true;
            case R.id.view1:
                Intent imgIntent2 = new Intent(Photo_Activity.this, Image2.class);
                startActivity(imgIntent2);
            default:
                return super.onContextItemSelected(item);
        }
    }
}