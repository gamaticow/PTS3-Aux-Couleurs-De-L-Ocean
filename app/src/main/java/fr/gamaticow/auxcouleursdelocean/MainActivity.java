package fr.gamaticow.auxcouleursdelocean;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import fr.gamaticow.auxcouleursdelocean.bubble.controleur.MenuActivity;
import fr.gamaticow.auxcouleursdelocean.crab.MainActivity2;
import fr.gamaticow.auxcouleursdelocean.fish.controller.FishLevelActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.fish_game).setOnClickListener(event -> startActivity(new Intent(this, FishLevelActivity.class)));
        findViewById(R.id.crab_game).setOnClickListener(event -> startActivity(new Intent(this, MainActivity2.class)));
        findViewById(R.id.bubble_game).setOnClickListener(event -> startActivity(new Intent(this, MenuActivity.class)));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if(hasFocus){
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }
}