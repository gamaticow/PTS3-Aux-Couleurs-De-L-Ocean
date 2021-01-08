package fr.gamaticow.auxcouleursdelocean.model.fish;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import fr.gamaticow.auxcouleursdelocean.R;
import fr.gamaticow.auxcouleursdelocean.controller.fish.GameActivity;
import fr.gamaticow.auxcouleursdelocean.model.LevelDifficulties;
import fr.gamaticow.auxcouleursdelocean.model.Settings;
import fr.gamaticow.auxcouleursdelocean.model.SoundManager;

/**
 * Created by Corentin COUTEAUX on 30/09/2020 at 13:52.
 */

public class Bucket implements View.OnClickListener {

    private static final int SIZE = 100;
    private static final FishGameDrawable NO_COLOR_BUCKET = FishGameDrawable.GREY;

    private final GameActivity context;
    private final FishGameDrawable color;
    private FishGameDrawable bucketDisplayColor;
    private int fillLevel;
    private final ImageView imageView;
    private final LinearLayout linearLayout;
    public final float dp;
    private RelativeLayout relativeLayout;

    public Bucket(BucketsContainer container, FishGameDrawable color, boolean noColor){
        this.context = container.getContext();
        this.color = color;
        bucketDisplayColor = color;
        fillLevel = 0;
        if(noColor)
            bucketDisplayColor = NO_COLOR_BUCKET;

        dp = context.getResources().getDisplayMetrics().density;

        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = (int) (10*dp);
        layoutParams.leftMargin = (int) (10*dp);
        layoutParams.rightMargin = (int) (10*dp);
        relativeLayout.setLayoutParams(layoutParams);

        this.imageView = new ImageView(context);
        imageView.setImageResource(bucketDisplayColor.getBucket(fillLevel));

        int width = (int) (SIZE*dp);
        int height = (int) (SIZE*dp);

        imageView.setLayoutParams(new LinearLayout.LayoutParams(width, height));

        TextView textColor = new TextView(context);
        textColor.setText(color.getName(context));
        textColor.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textColor.setTypeface(null, Typeface.BOLD);
        textColor.setTextColor(Color.BLACK);

        relativeLayout.addView(imageView);
        linearLayout.addView(relativeLayout);
        linearLayout.addView(textColor);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 0;
        params.bottomMargin = (int) (10*dp);

        linearLayout.setLayoutParams(params);

        container.addBucket(this);

        linearLayout.setOnClickListener(this);

        if(context.level.getDifficulty() != LevelDifficulties.HARD)
            addSoundSymbol();
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    private static final int ANIMATION_SPEED = 3;
    private static final int ANIMATION_END = 2;
    private int animationStep = 0;
    private int direction = 1;
    public void animate(){
        if(animationStep > ANIMATION_END)
            direction = -1;
        else if(animationStep < ANIMATION_END*-1)
            direction = 1;

        animationStep += direction;
        rotation(animationStep*ANIMATION_SPEED);
    }

    public void resetAnimation(){
        animationStep = 0;
        direction = 1;
        rotation(0);
    }

    private void rotation(float value){
        context.runOnUiThread(() -> imageView.setRotation(value));
    }

    public FishGameDrawable getColor(){
        return color;
    }

    public void scorePoint(){
        SoundManager.playSound(color.getSound(), SoundManager.SoundType.WIN_LOSE);
        fillLevel++;
        context.runOnUiThread(() -> imageView.setImageResource(bucketDisplayColor.getBucket(fillLevel)));
    }

    public boolean isFilled(){
        return bucketDisplayColor.getBucket(fillLevel) == bucketDisplayColor.getBucket(fillLevel+1);
    }

    @Override
    public void onClick(View v) {
        if(context.level.getDifficulty() != LevelDifficulties.HARD){
            SoundManager.playSound(color.getSound(), SoundManager.SoundType.ELEMENT_CLICK);
            if(context.level.getDifficulty() == LevelDifficulties.NORMAL && Settings.getInstance().isShowColor()){
                context.runOnUiThread(() -> imageView.setImageResource(color.getBucket(fillLevel)));
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        context.runOnUiThread(() -> imageView.setImageResource(bucketDisplayColor.getBucket(fillLevel)));
                    }
                }, 500);
            }
        }
    }

    private void addSoundSymbol(){
        int size = 20;
        ImageView sound = new ImageView(context);
        sound.setImageResource(R.drawable.speaker);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) (size*dp), (int) (size*dp));
        params.topMargin = (int) (SIZE*dp-size*dp);
        sound.setLayoutParams(params);
        relativeLayout.addView(sound);
    }
}
