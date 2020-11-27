package fr.gamaticow.auxcouleursdelocean.bubble.modele;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import fr.gamaticow.auxcouleursdelocean.bubble.controleur.MainActivity;
import java.util.Random;

public class Bulle implements View.OnTouchListener{

    private Couleur couleur;
    private int dimension;
    private MainActivity context;
    private RelativeLayout relativeLayout;
    private double speed,direction,true_x,true_y;
    private ImageButton bulle;

    private int x,y,dx,dy,radius;

    public Bulle(MainActivity context, Couleur couleur, int dimension, RelativeLayout relativeLayout) {
        this.context = context;
        this.couleur = couleur;
        this.dimension = dimension;
        this.relativeLayout = relativeLayout;
        float dp = context.getResources().getDisplayMetrics().density;

        bulle = new ImageButton(context);
        bulle.setBackgroundResource(couleur.getImage());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dimension,dimension);
        bulle.setLayoutParams(params);
        radius = 60;
        Random r =  new Random();
        x = r.nextInt(relativeLayout.getWidth() - dimension);
        y = r.nextInt(relativeLayout.getHeight() - dimension);
        bulle.setX(x);
        bulle.setY(y);
        relativeLayout.addView(bulle);

        true_x = x;
        true_y = y;

        dx = Math.random() > 0.5? 1: -1;
        dy = Math.random() > 0.5? 1: -1;
        speed = 2.0;
        direction = (int) (Math.random() * 360);

        bulle.setOnTouchListener(this);


    }

    public void move(int x_limit, int y_limit) {
        true_x = true_x + speed * Math.cos(direction * Math.PI / 180);
        true_y = true_y + speed * Math.sin(direction * Math.PI / 180);
        x = (int) true_x;
        y = (int) true_y;
        changeDirection(x_limit, y_limit);
        bulle.setY(y);
        bulle.setX(x);
    }
    private void changeDirection(int x_limit, int y_limit) {
        if (y < -45) {
            if (dx == 1)
                direction -= direction * 2;
            else
                direction += (180 - direction) * 2;

            dx -= dx;
        }

        if (y > y_limit - dimension +45 ) { // 45 le decalage des axes x y
            if (dx == 1)
                direction += (360 - direction) * 2;
            else
                direction -= (direction - 180) * 2;

            dx -= dx;
        }

        if (x < -45) {
            if (dy == 1)
                direction += (270 - direction) * 2;
            else
                direction -= (direction - 90) * 2;

            dy -= dy;
        }

        if (x > x_limit - dimension +45 ) {
            if (dy == 1)
                direction -= (direction - 270) * 2;
            else
                direction += (90 - direction) * 2;

            dy -= dy;
        }
    }
    public void makeCollision(Bulle bulle) {
        int tmp_dx, tmp_dy;
        double tmp_direction;

        tmp_dx = this.getDx();
        tmp_dy = this.getDy();
        tmp_direction = this.getDirection();

        this.setDx(bulle.getDx());
        this.setDy(bulle.getDy());
        this.setDirection(bulle.getDirection());

        bulle.setDx(tmp_dx);
        bulle.setDy(tmp_dy);
        bulle.setDirection(tmp_direction);
    }
    public boolean colision(Bulle bulle) {

        double ecartX = this.getX() - bulle.getX();
        double ecartY = this.getY() - bulle.getY();
        double distance = Math.sqrt(ecartX * ecartX + ecartY * ecartY);

        if (distance < this.getRadius() + bulle.getRadius()) {
            return true;
        }
        return false;
    }

    public void update(int x_limit, int y_limit){
        move(x_limit, y_limit);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(GameBulle.listBulle.contains(this))
            GameBulle.listBulleEclate.add(this);
        GameBulle.listBulle.remove(this);
        relativeLayout.removeView(v);
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public double getRadius() {
        return radius;
    }
    public ImageButton getBulle() {
        return bulle;
    }

    public Couleur getCouleur() {
        return couleur;
    }



}
