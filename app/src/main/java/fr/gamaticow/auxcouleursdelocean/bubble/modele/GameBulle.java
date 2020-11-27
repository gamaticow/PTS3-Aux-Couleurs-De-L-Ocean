package fr.gamaticow.auxcouleursdelocean.bubble.modele;

import android.widget.RelativeLayout;

import fr.gamaticow.auxcouleursdelocean.bubble.controleur.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class GameBulle {

    private MainActivity context;
    private BulleGameLevel level;
    private RelativeLayout mainFrame;
    private boolean init,endGame;
    private int nbBulle;
    private Planche planche;
    public static List<Bulle> listBulleEclate;
    public static List<Bulle> listBulle;



    public GameBulle(MainActivity context, BulleGameLevel level, RelativeLayout mainFrame){
        this.context = context;
        this.level = level;
        this.mainFrame = mainFrame;
        listBulle = new ArrayList<>();
        listBulleEclate = new ArrayList<>();
        init =false;
        endGame =false;
    }

    public void run(){
        if (!init){
            planche = new Planche(context,level.initialisePlanche(),mainFrame);
            level.initialise();
            generationBulle();
            init =true;
            nbBulle = level.getNbBulle();
        }
        if (level.getLives() == 0  || nbBulle ==0){
            endGame();
        }


        updateGame();
        move();
    }

    private void updateGame(){
        for (Bulle bulle : listBulleEclate) {
            if (bulle.getCouleur() != planche.getCouleur()) {
                level.removeLife();
            }else{
                nbBulle = nbBulle - 1;
            }
        }
        listBulleEclate.clear();
    }
    private boolean verifSpawn(Bulle bulle) {
        for (int i= 0; i < listBulle.size(); i++) {
            if(bulle != listBulle.get(i)){
                if (bulle.colision(listBulle.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void generationBulle(){
        Bulle bulle;
        for (int i = 0; i < level.getListDeJeu().size(); i++) {
            Couleur couleur = level.getListDeJeu().get(i);
            bulle = new Bulle(context,couleur,200,mainFrame);
            while (!verifSpawn(bulle)){
                mainFrame.removeView(bulle.getBulle());
                bulle = new Bulle(context,couleur,200,mainFrame);
            }
            listBulle.add(bulle);
        }
    }
    private void move(){
        for (Bulle bulle : listBulle){
            bulle.update(mainFrame.getWidth(),mainFrame.getHeight());
            for(int i = 0;i < listBulle.size();i++){
                if(bulle != listBulle.get(i)){
                    if(bulle.colision(listBulle.get(i))){
                        bulle.makeCollision(listBulle.get(i));
                    }
                }
            }
        }
    }
    public void endGame(){
        context.onPause();
        endGame = true;
    }

    public boolean isEndGame() {
        return endGame;
    }
}
