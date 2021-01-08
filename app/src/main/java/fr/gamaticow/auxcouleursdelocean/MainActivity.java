package fr.gamaticow.auxcouleursdelocean;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import fr.gamaticow.auxcouleursdelocean.controller.CreditActivity;
import fr.gamaticow.auxcouleursdelocean.controller.LevelActivity;
import fr.gamaticow.auxcouleursdelocean.controller.SettingsActivity;
import fr.gamaticow.auxcouleursdelocean.model.Settings;
import fr.gamaticow.auxcouleursdelocean.model.SoundManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Settings.loadSettings(this);
        new SoundManager(this);


        final Intent intent = new Intent(this, LevelActivity.class);

        findViewById(R.id.fish_game).setOnClickListener(view -> {
            intent.putExtra(LevelActivity.EXTRA_GAME, fr.gamaticow.auxcouleursdelocean.controller.fish.GameActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.crab_game).setOnClickListener(view -> {
            intent.putExtra(LevelActivity.EXTRA_GAME, fr.gamaticow.auxcouleursdelocean.controller.crab.GameActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.bubble_game).setOnClickListener(view -> {
            intent.putExtra(LevelActivity.EXTRA_GAME, fr.gamaticow.auxcouleursdelocean.controller.bubbles.GameActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.credit).setOnClickListener(view -> {
            Intent credit = new Intent(this, CreditActivity.class);
            startActivity(credit);
        });

        findViewById(R.id.settings).setOnClickListener(view -> {
            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
        });
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