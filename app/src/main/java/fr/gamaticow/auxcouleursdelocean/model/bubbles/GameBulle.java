package fr.gamaticow.auxcouleursdelocean.model.bubbles;

import android.util.Log;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import fr.gamaticow.auxcouleursdelocean.controller.bubbles.GameActivity;
import fr.gamaticow.auxcouleursdelocean.model.Game;

/**
 * Created by Corentin COUTEAUX on 09/12/2020 at 20:22.
 */

public class GameBulle extends Game<BulleGameLevel> {

    private final GameActivity context;
    private final RelativeLayout mainFrame;

    private boolean init;
    private Planche planche;
    private double speed;

    public List<Bulle> listBulle = new ArrayList<>();
    public List<Bulle> listBulleEclate = new ArrayList<>();

    public GameBulle(GameActivity context, BulleGameLevel gameLevel, RelativeLayout mainFrame) {
        super(gameLevel);
        this.context = context;
        this.mainFrame = mainFrame;

        init = false;
    }

    @Override
    public void run() {
        if (!init){
            Couleur couleurPlanche = getGameLevel().initialisePlanche();
            planche = new Planche(context, couleurPlanche, mainFrame);
            switch (getGameLevel().getDifficulty()){
                case EASY:
                    speed = 0;
                    break;
                case NORMAL:
                    speed = 0.54;
                    break;
                case HARD:
                    speed = 1.10;
                    break;
            }
            getGameLevel().initialise();
            generationBulle();
            init = true;
        }

        updateGame();
        move();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void generationBulle(){
        Log.d("Test", "generationBulle: Test");
        Bulle bulle;
        for (int i = 0; i < getGameLevel().getListDeJeu().size(); i++) {
            Couleur couleur = getGameLevel().getListDeJeu().get(i);
            bulle = new Bulle(context,couleur,72,mainFrame,speed);
            listBulle.add(bulle);
        }
    }

    private void updateGame(){
        for (Bulle bulle : listBulleEclate) {
            if (bulle.getCouleur() != planche.getCouleur()) {
                getGameLevel().removeLife();
            }
        }
        listBulle.removeAll(listBulleEclate);
        listBulleEclate.clear();
        boolean resteBulle = false;
        for(Bulle bulle : listBulle){
            if(bulle.getCouleur() == planche.getCouleur()){
                resteBulle = true;
                break;
            }
        }

        if(!resteBulle)
            getGameLevel().end();
    }

    private void move(){
        for (Bulle bulle : listBulle){
            bulle.update(mainFrame.getWidth(),mainFrame.getHeight());
            for(int i = 0;i < listBulle.size();i++){
                if(bulle != listBulle.get(i)){
                    if(bulle.collision(listBulle.get(i))){
                        bulle.makeCollision(listBulle.get(i));
                    }
                }
            }
        }
    }

}
