package com.example.whatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp.Adapters.FragmentsAdapters;
import com.example.whatsapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        auth = FirebaseAuth.getInstance();
        mainBinding.viewpager.setAdapter(new FragmentsAdapters(getSupportFragmentManager()));
        mainBinding.tablayout.setupWithViewPager(mainBinding.viewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings :
                Intent intent3 = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent3);
                break;

            case R.id.logOut :
                auth.signOut();
                Intent intent = new Intent(MainActivity.this,SignInActivity.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(intent);
                break;

            case R.id.groupChat :
                Intent intent2 = new Intent(MainActivity.this,GroupChatActivity.class);
                startActivity(intent2);
        }

        return true;
    }
}