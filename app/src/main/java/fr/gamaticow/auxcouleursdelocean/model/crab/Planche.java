package fr.gamaticow.auxcouleursdelocean.model.crab;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import fr.gamaticow.auxcouleursdelocean.R;
import fr.gamaticow.auxcouleursdelocean.controller.crab.GameActivity;
import fr.gamaticow.auxcouleursdelocean.model.LevelDifficulties;
import fr.gamaticow.auxcouleursdelocean.model.Settings;
import fr.gamaticow.auxcouleursdelocean.model.SoundManager;

public class Planche implements View.OnClickListener {

    private GameActivity context;
    private Couleur couleur;
    private ImageView planche;
    private TextView textView;
    private RelativeLayout relativeLayout;
    private float dp;

    public Planche(GameActivity context, Couleur couleur, RelativeLayout relativeLayout){
        this.context = context;
        final int heightPlanche = 30;
        final int widthPlanche = 125;
        dp = context.getResources().getDisplayMetrics().density;

        this.couleur = couleur;
        this.relativeLayout = relativeLayout;
        planche = new ImageView(context);
        planche.setImageResource(context.crabGameLevel.getDifficulty() == LevelDifficulties.EASY ? couleur.getPlanche() : Couleur.GRIS.getPlanche());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int)(widthPlanche*dp),(int)(heightPlanche*dp));
        planche.setLayoutParams(params);
        planche.setX((relativeLayout.getWidth()-(widthPlanche*dp))/2);
        planche.setY(relativeLayout.getHeight()-(heightPlanche*dp)*2);

        planche.setOnClickListener(this);

        textView = new TextView(context);
        textView.setText(couleur.getName().toUpperCase());
        ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams((int)(widthPlanche*dp),(int)(heightPlanche*dp));
        textView.setLayoutParams(params1);
        textView.setX((relativeLayout.getWidth()-(widthPlanche*dp))/2);
        textView.setY(relativeLayout.getHeight()-(heightPlanche*dp)*2);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(10*dp);
        textView.setTypeface(null, Typeface.BOLD);

        context.runOnUiThread(() -> {
            relativeLayout.addView(planche);
            relativeLayout.addView(textView);
        });

        if(context.crabGameLevel.getDifficulty() != LevelDifficulties.HARD)
            addSoundSymbol(widthPlanche, heightPlanche);
    }

    public void setColor(Couleur couleur){
        this.couleur = couleur;
        if(context.crabGameLevel.getDifficulty()== LevelDifficulties.EASY)
            planche.setImageResource(couleur.getPlanche());
        textView.setText(couleur.getName());
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public ImageView getPlanche() {
        return planche;
    }

    @Override
    public void onClick(View v) {
        if(context.crabGameLevel.getDifficulty() != LevelDifficulties.HARD){
            SoundManager.playSound(couleur.getSound(), SoundManager.SoundType.ELEMENT_CLICK);
            if(context.crabGameLevel.getDifficulty() == LevelDifficulties.NORMAL && Settings.getInstance().isShowColor()){
                context.runOnUiThread(() -> planche.setImageResource(couleur.getPlanche()));
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        context.runOnUiThread(() -> planche.setImageResource(Couleur.GRIS.getPlanche()));
                    }
                }, 500);
            }
        }
    }

    private void addSoundSymbol(int wp, int hp){
        int size = 20;
        ImageView sound = new ImageView(context);
        sound.setImageResource(R.drawable.speaker);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) (size*dp), (int) (size*dp));
        sound.setLayoutParams(params);
        sound.setX((relativeLayout.getWidth()-(wp*dp))/2);
        sound.setY(relativeLayout.getHeight()-(hp*dp)*2);
        relativeLayout.addView(sound);
    }
}
