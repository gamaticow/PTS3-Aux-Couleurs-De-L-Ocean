package fr.gamaticow.auxcouleursdelocean.controller;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import fr.gamaticow.auxcouleursdelocean.R;
import fr.gamaticow.auxcouleursdelocean.model.LevelDifficulties;

public class LevelActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_GAME = "EXTRA_GAME";
    public static final String EXTRA_DIFFICULTY = "EXTRA_DIFFICULTY";

    private Class<?> clazz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clazz = (Class<?>) getIntent().getSerializableExtra(EXTRA_GAME);
        if(clazz == null)
            finish();

        setContentView(R.layout.activity_level);

        ImageView easy = findViewById(R.id.easy);
        ImageView normal = findViewById(R.id.norm);
        ImageView hard = findViewById(R.id.hard);

        easy.setTag(LevelDifficulties.EASY);
        normal.setTag(LevelDifficulties.NORMAL);
        hard.setTag(LevelDifficulties.HARD);

        easy.setOnClickListener(this);
        normal.setOnClickListener(this);
        hard.setOnClickListener(this);

        findViewById(R.id.menu_btn).setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onClick(View v) {
        if(v.getTag() instanceof LevelDifficulties){
            startGame((LevelDifficulties) v.getTag());
        }
    }

    private void startGame(LevelDifficulties difficulties){
        Intent intent = new Intent(this, clazz);
        intent.putExtra(EXTRA_DIFFICULTY, difficulties);
        startActivity(intent);
        finish();
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