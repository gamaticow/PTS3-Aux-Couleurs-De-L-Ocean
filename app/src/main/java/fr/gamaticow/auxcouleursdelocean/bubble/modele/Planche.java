package fr.gamaticow.auxcouleursdelocean.bubble.modele;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import fr.gamaticow.auxcouleursdelocean.bubble.controleur.MainActivity;

public class Planche {

    public Couleur couleur;
    public ImageView planche;
    private RelativeLayout relativeLayout;

    public Planche(MainActivity context, Couleur couleur,RelativeLayout relativeLayout){
        final int heightPlanche = 30;
        final int widthPlanche = 125;
        float dp = context.getResources().getDisplayMetrics().density;

        this.couleur = couleur;
        this.relativeLayout = relativeLayout;
        planche = new ImageView(context);
        planche.setBackgroundResource(couleur.getPlancheImage());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int)(widthPlanche*dp),(int)(heightPlanche*dp));
        planche.setLayoutParams(params);
        planche.setX((relativeLayout.getWidth()-(widthPlanche*dp))/2);
        planche.setY(relativeLayout.getHeight()-(heightPlanche*dp)*2);
        relativeLayout.addView(planche);
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public ImageView getPlanche() {
        return planche;
    }

}
