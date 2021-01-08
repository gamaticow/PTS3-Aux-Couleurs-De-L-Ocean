package fr.gamaticow.auxcouleursdelocean.model;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import fr.gamaticow.auxcouleursdelocean.R;

/**
 * Created by Corentin COUTEAUX on 09/12/2020 at 14:12.
 */

public class GameLevel {

    protected final Activity context;
    protected LevelDifficulties difficulty;
    private int lives;
    private final ImageView[] hearts;
    private Runnable onEnd;

    public GameLevel(Activity context, LevelDifficulties difficulty, LinearLayout livesLayout){
        this.context = context;
        this.difficulty = difficulty;
        this.lives = 3;
        this.hearts = new ImageView[lives];

        createHearths(livesLayout);
    }

    public LevelDifficulties getDifficulty(){
        return difficulty;
    }

    public void setOnEnd(Runnable onEnd){
        this.onEnd = onEnd;
    }

    public void end(){
        onEnd.run();
    }

    private void createHearths(LinearLayout layout){
        float dp = context.getResources().getDisplayMetrics().density;
        final int SIZE = 50;

        for(int i = 0; i < hearts.length; i++){
            ImageView heart = new ImageView(context);
            heart.setImageResource(R.drawable.heart);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (SIZE*dp), (int) (SIZE*dp));
            heart.setLayoutParams(params);
            layout.addView(heart);

            hearts[i] = heart;
        }
    }

    public int getLives() {
        return lives;
    }

    public void removeLife(){
        lives--;
        SoundManager.playSound(Sound.LOSE_HEART, SoundManager.SoundType.FORCE);
        if(lives <= 0){
            end();
            return;
        }

        context.runOnUiThread(() -> hearts[lives].setImageResource(R.drawable.heart_empty));
    }

}
