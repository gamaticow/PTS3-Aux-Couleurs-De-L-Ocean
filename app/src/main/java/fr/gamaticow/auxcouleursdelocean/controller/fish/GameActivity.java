package fr.gamaticow.auxcouleursdelocean.controller.fish;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.gamaticow.auxcouleursdelocean.R;
import fr.gamaticow.auxcouleursdelocean.controller.EndGameActivity;
import fr.gamaticow.auxcouleursdelocean.controller.LevelActivity;
import fr.gamaticow.auxcouleursdelocean.model.Settings;
import fr.gamaticow.auxcouleursdelocean.model.fish.BucketsContainer;
import fr.gamaticow.auxcouleursdelocean.model.fish.Fish;
import fr.gamaticow.auxcouleursdelocean.model.fish.FishGameLevel;
import fr.gamaticow.auxcouleursdelocean.model.LevelDifficulties;
import fr.gamaticow.auxcouleursdelocean.model.fish.GameFish;

public class GameActivity extends AppCompatActivity {

    public ConstraintLayout fishLayout;
    public BucketsContainer container;
    public FishGameLevel level;
    private GameFish gameFish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_game);

        this.fishLayout = findViewById(R.id.fish_layout);

        LevelDifficulties difficulties = (LevelDifficulties) getIntent().getSerializableExtra(LevelActivity.EXTRA_DIFFICULTY);
        this.level = new FishGameLevel(this, difficulties, findViewById(R.id.hearts_layout));
        container = level.initialise(findViewById(R.id.buckets_layout));
        level.setOnEnd(() -> {
            Intent intent = new Intent(this, EndGameActivity.class);
            intent.putExtra(EndGameActivity.INTENT_STAR_NUMBER, level.getLives());
            startActivity(intent);
            finish();
        });

        if(Settings.getInstance().isBackgroundEnabled())
            fishLayout.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.aquarium, null));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(gameFish != null)
            gameFish.start();
    }

    @Override
    protected void onPause() {
        gameFish.stop();
        super.onPause();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if(hasFocus){
            if (gameFish == null){
                gameFish = new GameFish(this, level);
                gameFish.start();
            }
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