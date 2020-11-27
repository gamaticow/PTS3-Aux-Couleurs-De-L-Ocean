package fr.gamaticow.auxcouleursdelocean.bubble.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import fr.gamaticow.auxcouleursdelocean.R;
import fr.gamaticow.auxcouleursdelocean.bubble.modele.BulleGameLevel;
import fr.gamaticow.auxcouleursdelocean.bubble.modele.GameBulle;
import fr.gamaticow.auxcouleursdelocean.bubble.modele.LevelDifficulties;
import fr.gamaticow.auxcouleursdelocean.fish.controller.EndGameActivity;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mainFrame;
    private LinearLayout heartLayout;
    private GameBulle gameBulle;
    private BulleGameLevel bulleGameLevel;
    private Handler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_main);

        LevelDifficulties difficulties = (LevelDifficulties) getIntent().getSerializableExtra(MenuActivity.EXTRA_DIFFICULTY);

        mainFrame = findViewById(R.id.mainFrame);
        heartLayout = findViewById(R.id.hearts_layout);
        bulleGameLevel = new BulleGameLevel(this,difficulties,heartLayout);
    }

    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            gameBulle.run();
            if(!gameBulle.isEndGame()){
                myHandler.postDelayed(this,10);
            }else{
                Intent back = new Intent(MainActivity.this, EndGameActivity.class);
                back.putExtra(EndGameActivity.INTENT_STAR_NUMBER, bulleGameLevel.getLives());
                startActivity(back);
            }
        }
    };
    public void onPause() {
        super.onPause();
        if(myHandler != null)
            myHandler.removeCallbacks(myRunnable); // On arrete le callback
    }
    @Override
    public void onResume(){
        super.onResume();
        if(gameBulle != null)
            myRunnable.run();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if(hasFocus){
            if(gameBulle == null) {
                gameBulle = new GameBulle(this, bulleGameLevel, mainFrame);
                myHandler = new Handler();
                myRunnable.run();
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
