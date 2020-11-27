package fr.gamaticow.auxcouleursdelocean.bubble.modele;

import java.util.ArrayList;
import java.util.List;

import fr.gamaticow.auxcouleursdelocean.R;

public enum Couleur {


    BLANC(true, R.drawable.bubble_white, "BLANC",R.drawable.board_white),
    NOIR(true,R.drawable.bubble_black, "NOIR",R.drawable.board_black),
    MARRON(true,R.drawable.bubble_brown, "MARRON",R.drawable.board_brown),
    ROUGE(false,R.drawable.bubble_red, "ROUGE",R.drawable.board_red),
    JAUNE(false,R.drawable.bubble_yellow, "JAUNE",R.drawable.board_yellow),
    VERT(false,R.drawable.bubble_green, "VERT",R.drawable.board_green),
    BLEU(false,R.drawable.bubble_blue, "BLEU",R.drawable.board_blue),
    ORANGE(false,R.drawable.bubble_orange, "ORANGE",R.drawable.board_orange),
    ROSE(false,R.drawable.bubble_pink, "ROSE",R.drawable.board_pink),
    VIOLET(false,R.drawable.bubble_purple, "VIOLET",R.drawable.board_purple),
    GRIS(true,R.drawable.bubble_grey, "GRIS",R.drawable.board_grey);

    private static List<Couleur> couleurFacile = new ArrayList<>();
    private static List<Couleur> couleurDur = new ArrayList<>();

    static {
        for(Couleur color : Couleur.values()){
            if(color.couleurDiffile){
                couleurDur.add(color);
            }
            else{
                couleurFacile.add(color);
            }
        }
    }

    private boolean couleurDiffile;
    private int bulleImage;
    private int plancheImage;
    private String name;

    Couleur(Boolean couleurDiffile, int bulleImage, String name,int plancheImage){
        this.couleurDiffile = couleurDiffile;
        this.bulleImage = bulleImage;
        this.name = name;
        this.plancheImage = plancheImage;
    }

    public int getImage() {
        return bulleImage;
    }

    public int getPlancheImage() {
        return plancheImage;
    }

    public static Couleur getRandomEasyColor(){
        int random = (int) (Math.random()* couleurFacile.size());
        return couleurFacile.get(random);
    }
    public static Couleur getRandomHardColor(){
        int random = (int) (Math.random()* couleurDur.size());
        return couleurDur.get(random);
    }

    public String getName() {
        return name;
    }
}
