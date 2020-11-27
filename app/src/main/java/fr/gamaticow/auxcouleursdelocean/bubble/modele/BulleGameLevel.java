package fr.gamaticow.auxcouleursdelocean.bubble.modele;

import android.widget.ImageView;
import android.widget.LinearLayout;

import fr.gamaticow.auxcouleursdelocean.R;
import fr.gamaticow.auxcouleursdelocean.bubble.controleur.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class BulleGameLevel {

    private MainActivity context;
    private LevelDifficulties difficulties;
    private int lives;
    private int nbBulle;
    private ImageView[] hearts;
    private List<Couleur> listDeJeu;
    private Couleur couleurPlanche;

    public BulleGameLevel(MainActivity context, LevelDifficulties difficulties, LinearLayout livesLayout){
        this.context = context;
        this.difficulties = difficulties;
        this.lives = 3;
        this.hearts = new ImageView[lives];
        listDeJeu = new ArrayList<>();
        nbBulle = 0;
        createHearths(livesLayout);
    }

    public Couleur initialisePlanche(){
        switch (difficulties){
            case FACILE:
                couleurPlanche = Couleur.getRandomEasyColor();
                break;
            case NORMAL:
                if(Math.random() < 0.5) {
                    couleurPlanche = Couleur.getRandomHardColor();
                }else{
                    couleurPlanche = Couleur.getRandomEasyColor();
                }
                break;
            case DIFFICLE:
                couleurPlanche = Couleur.getRandomHardColor();
                break;
        }
        return couleurPlanche;
    }
    public void initialise(){
        Couleur color;
        for (int i= 0; i < 30 ; i++){
            if (Math.random()<0.3){
                color = couleurPlanche;
                nbBulle++;
                listDeJeu.add(color);
            }else{
                switch (difficulties){
                    case FACILE:
                        do{
                            color = Couleur.getRandomEasyColor();
                        }while (color == couleurPlanche);
                        listDeJeu.add(color);
                        break;
                    case NORMAL:
                        if(Math.random() < 0.5) {
                            do{
                                color = Couleur.getRandomHardColor();
                            }while (color == couleurPlanche);
                            listDeJeu.add(color);
                        }else{
                            do{
                                color = Couleur.getRandomEasyColor();
                            }while (color == couleurPlanche);
                            listDeJeu.add(color);
                        }
                        break;
                    case DIFFICLE:
                        do{
                            color = Couleur.getRandomHardColor();
                        }while (color == couleurPlanche);
                        listDeJeu.add(color);
                        break;
                }
            }
        }
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
    public List<Couleur> getListDeJeu() {
        return listDeJeu;
    }
    public void removeLife(){
        lives--;
        hearts[lives].setImageResource(R.drawable.heart_empty);
    }
    public int getNbBulle() { return nbBulle; }

    public void setNbBulle(int nbBulle) {
        this.nbBulle = nbBulle;
    }

}