package fr.gamaticow.auxcouleursdelocean.crab;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import fr.gamaticow.auxcouleursdelocean.R;
import fr.gamaticow.auxcouleursdelocean.fish.controller.EndGameActivity;

public class GameHardView  extends View implements SensorEventListener {

    private Bitmap background;
    public GameHard context;
    public SensorManager sensorManager;
    public Sensor accelerometer;
    private int imageWidth;
    private int imageHeight;
    private int currentX;
    private int currentY;
    public Bitmap bird;
    private Paint paint = new Paint();

    private Bitmap shark;
    int sharkX= (int) (Math.random() * ( 1300 - 1 ));
    int sharkY=(int) (Math.random() * ( 475 - 1 ));
    private int sharkSpeed = 10;

    private Bitmap shark2;
    int shark2X= (int) (Math.random() * ( 1300 - 1 ));
    int shark2Y=(int) (Math.random() * ( 475 - 1 ));
    private int shark2Speed = 10;

    private Bitmap shark3;
    int shark3X= (int) (Math.random() * ( 1300 - 1 ));
    int shark3Y=(int) (Math.random() * ( 475 - 1 ));
    private int shark3Speed = 10;

    private Paint sharkpaint;

    private Paint couleurAsk = new Paint();

    // Life
    private Bitmap vie[] = new Bitmap[2];
    private int vie_nombre;


    private Bitmap life[] = new Bitmap[2];
    private int life_count;

    private Bitmap brouge;
    int rougex= (int) (Math.random() * ( 1300 - 1 ));


    private Bitmap bvert;
    int vertx=(int) (Math.random() * ( 1300 - 1 ));


    private Bitmap bviolet;
    int violetx=(int) (Math.random() * ( 1300 - 1 ));


    private Bitmap bjaune;
    int jaunex=(int) (Math.random() * ( 1300 - 1 ));


    private Bitmap bmarron;
    int marronx=(int) (Math.random() * ( 1300 - 1 ));


    private Bitmap bnoir;
    int noirx=(int) (Math.random() * ( 1300 - 1 ));

    private Bitmap bgris;
    int grisx=(int) (Math.random() * ( 1300 - 1 ));


    private Bitmap bblanc;
    int blancx=(int) (Math.random() * ( 1300 - 1 ));


    private Bitmap bbleu;
    int bleux=(int) (Math.random() * ( 1300 - 1 ));


    private Bitmap borange;
    int orangex=(int) (Math.random() * ( 1300 - 1 ));


    private Bitmap brose;
    int rosex=(int) (Math.random() * ( 1300 - 1 ));


    private Paint scorePaint = new Paint();
    private int score;

    private Bitmap star1;


    private final static int rouge = 1;
    private final static int noir = 2;
    private final static int vert = 3;
    private final static int bleu = 4;
    private final static int jaune = 5;
    private final static int orange = 6;
    private final static int gris = 7;
    private final static int violet = 8;
    private final static int rose = 9;
    private final static int blanc = 10;
    private final static int marron = 11;

    private boolean valide=true;

    int crab_stat;
    int ball_stat;
    Random r;

    public GameHardView(GameHard pcontext) {
        super( pcontext);
        this.context = pcontext;
        init(pcontext);

    }

    public int marrondelete() {
        List<Integer> givenList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        return randomElement;
    }

    public int rosedelete() {
        List<Integer> givenList = Arrays.asList(1,2,3,4,5,6,7,8,10,11);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        return randomElement;
    }

    public int violetdelete() {
        List<Integer> givenList = Arrays.asList(1,2,3,4,5,6,7,9,10,11);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        return randomElement;
    }

    public int grisdelete() {
        List<Integer> givenList = Arrays.asList(1,2,3,4,5,6,8,9,10,11);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        return randomElement;
    }

    public int orangedelete() {
        List<Integer> givenList = Arrays.asList(1,2,3,4,5,7,8,9,10,11);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        return randomElement;
    }

    public int jaunedelete() {
        List<Integer> givenList = Arrays.asList(1,2,3,4,6,7,8,9,10,11);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        return randomElement;
    }

    public int bleudelete() {
        List<Integer> givenList = Arrays.asList(1,2,3,5,6,7,8,9,10,11);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        return randomElement;
    }
    public int vertdelete() {
        List<Integer> givenList = Arrays.asList(1,2,4,5,6,7,8,9,10,11);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        return randomElement;
    }
    public int noirdelete() {
        List<Integer> givenList = Arrays.asList(1, 3,4,5,6,7,8,9,10,11);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        return randomElement;
    }

    public int blancdelete() {
        List<Integer> givenList = Arrays.asList(1, 2, 3,4,5,6,7,8,9,11);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        return randomElement;
    }
    public int rougedelete() {
        List<Integer> givenList = Arrays.asList( 2, 3,4,5,6,7,8,9,10,11);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        return randomElement;
    }

    private void init( Context context ) {

        r=new Random();
        crab_stat=r.nextInt(11)+1;
        setCrabState(crab_stat);

        background=BitmapFactory.decodeResource(getResources(), R.drawable.fond_crabe);
        star1= BitmapFactory.decodeResource(getResources(),R.drawable.victory1);
        shark=BitmapFactory.decodeResource(getResources(),R.drawable.oiseau);
        shark2=BitmapFactory.decodeResource(getResources(),R.drawable.oiseau);
        shark3=BitmapFactory.decodeResource(getResources(),R.drawable.oiseau);

        this.imageWidth = bird.getWidth();
        this.imageHeight = bird.getHeight();
        brouge = BitmapFactory.decodeResource(getResources(), R.drawable.ballon_red);
        bnoir = BitmapFactory.decodeResource(getResources(), R.drawable.ballon_black );
        bvert= BitmapFactory.decodeResource(getResources(), R.drawable.ballon_green );
        bbleu = BitmapFactory.decodeResource(getResources(), R.drawable.ballon_blue );
        bjaune = BitmapFactory.decodeResource(getResources(), R.drawable.ballon_yellow );
        borange = BitmapFactory.decodeResource(getResources(), R.drawable.ballon_orrange );
        bgris = BitmapFactory.decodeResource(getResources(), R.drawable.ballon_grey );
        bviolet = BitmapFactory.decodeResource(getResources(), R.drawable.ballon_purple );
        brose = BitmapFactory.decodeResource(getResources(), R.drawable.ballon_pink );
        bblanc= BitmapFactory.decodeResource(getResources(), R.drawable.ballon_white);
        bmarron = BitmapFactory.decodeResource(getResources(), R.drawable.ballon_brown );

        couleurAsk.setColor(Color.BLACK);
        couleurAsk.setTextSize(32);
        couleurAsk.setTypeface(Typeface.DEFAULT_BOLD);
        couleurAsk.setAntiAlias(true);

        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(32);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.progressbar);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.empty);

        vie[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        vie[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_empty);


        life_count=0;
        vie_nombre=3;


    }

    public int getLife_count() {
        return life_count;
    }

    private void setCrabState(int state) {
        switch (state){
            case rouge:
                this.bird = BitmapFactory.decodeResource(getResources(), R.drawable.crabe_red);
                crab_stat=rouge;
                break;
            case noir:
                this.bird = BitmapFactory.decodeResource(getResources(), R.drawable.crabe_black );
                crab_stat=noir;
                break;
            case vert:
                this.bird = BitmapFactory.decodeResource(getResources(), R.drawable.crabe_green );
                crab_stat=vert;
                break;
            case bleu:
                this.bird = BitmapFactory.decodeResource(getResources(), R.drawable.crabe_blue );
                crab_stat=bleu;
                break;
            case jaune:
                this.bird = BitmapFactory.decodeResource(getResources(), R.drawable.crabe_yellow );
                crab_stat=jaune;
                break;
            case orange:
                this.bird = BitmapFactory.decodeResource(getResources(), R.drawable.crabe_orange );
                crab_stat=orange;
                break;
            case gris:
                this.bird = BitmapFactory.decodeResource(getResources(), R.drawable.crabe_grey );
                crab_stat=gris;
                break;
            case violet:
                this.bird = BitmapFactory.decodeResource(getResources(), R.drawable.crabe_purple );
                crab_stat=violet;
                break;
            case rose:
                this.bird = BitmapFactory.decodeResource(getResources(), R.drawable.crabe_pink );
                crab_stat=rose;
                break;
            case blanc:
                bblanc= BitmapFactory.decodeResource(getResources(), R.drawable.crabe_white);
                crab_stat=blanc;
                break;
            case marron:
                this.bird = BitmapFactory.decodeResource(getResources(), R.drawable.crabe_brown );
                crab_stat=marron;
                break;

        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        //float z = event.values[2];

        this.moveImage( y*5, x*5 );
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int minBirdY = bird.getHeight();
        int maxBirdY = canvas.getHeight() - bird.getHeight() * 3;

        int calcul_taille=(canvas.getHeight()/11)-10;
        int rougey=calcul_taille;
        int verty=calcul_taille*2;
        int blancy=calcul_taille*3;
        int bleuy=calcul_taille*3;
        int orangey=calcul_taille*4;
        int jauney=calcul_taille*5;
        int marrony=calcul_taille*6;
        int rosey=calcul_taille*7;
        int violety=calcul_taille*8;
        int noiry=calcul_taille*9;
        int grisy=calcul_taille*10;

        Rect dest = new Rect(0, 0, getWidth(), getHeight());
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        canvas.drawBitmap(background, null, dest, paint);

        canvas.drawBitmap(bblanc, blancx, blancy, this.paint);
        canvas.drawBitmap(bbleu, bleux, bleuy, this.paint);
        canvas.drawBitmap(borange, orangex, orangey, this.paint);
        canvas.drawBitmap(bjaune, jaunex, jauney, this.paint);
        canvas.drawBitmap(bmarron, marronx, marrony, this.paint);
        canvas.drawBitmap(brouge, rougex, rougey, this.paint);
        canvas.drawBitmap(bvert, vertx, verty, this.paint);
        canvas.drawBitmap(brose, rosex, rosey, this.paint);
        canvas.drawBitmap(bviolet, violetx, violety, this.paint);
        canvas.drawBitmap(bnoir, noirx, noiry, this.paint);
        canvas.drawBitmap(bgris, grisx, grisy, this.paint);




        sharkX -= sharkSpeed;
        if (sharkX < 0) {
            sharkX = canvas.getWidth() + 20;
            sharkY = (int) Math.floor(Math.random() * (maxBirdY - minBirdY)) + minBirdY;
        }
        canvas.drawBitmap(shark,sharkX,sharkY,sharkpaint);


        shark2X -= shark2Speed;
        if (shark2X < 0) {
            shark2X = canvas.getWidth() + 20;
            shark2Y = (int) Math.floor(Math.random() * (maxBirdY - minBirdY)) + minBirdY;
        }
        canvas.drawBitmap(shark2,shark2X,shark2Y,sharkpaint);


        shark3X -= shark3Speed;
        if (shark3X < 0) {
            shark3X = canvas.getWidth() + 20;
            shark3Y = (int) Math.floor(Math.random() * (maxBirdY - minBirdY)) + minBirdY;
        }
        canvas.drawBitmap(shark3,shark3X,shark3Y,sharkpaint);


        canvas.drawBitmap(this.bird, this.currentX, this.currentY, this.paint);
       // canvas.drawText("Erreur : " + score, 1, canvas.getHeight(), scorePaint);
        if (crab_stat == 1) {
            canvas.drawText("ROUGE ", canvas.getWidth() / 2, 750, couleurAsk);
        } else if (crab_stat == 2) {
            canvas.drawText("NOIR ", canvas.getWidth() / 2, 750, couleurAsk);
        } else if (crab_stat == 3) {
            canvas.drawText("VERT ", canvas.getWidth() / 2, 750, couleurAsk);
        } else if (crab_stat == 4) {
            canvas.drawText("BLEU ", canvas.getWidth() / 2, 750, couleurAsk);
        } else if (crab_stat == 5) {
            canvas.drawText("JAUNE ", canvas.getWidth() / 2, 750, couleurAsk);
        } else if (crab_stat == 6) {
            canvas.drawText("ORANGE ", canvas.getWidth() / 2, 750, couleurAsk);
        } else if (crab_stat == 7) {
            canvas.drawText("GRIS ", canvas.getWidth() / 2, 750, couleurAsk);
        } else if (crab_stat == 8) {
            canvas.drawText("VIOLET ", canvas.getWidth() / 2, 750, couleurAsk);
        } else if (crab_stat == 9) {
            canvas.drawText("ROSE ", canvas.getWidth() / 2, 750, couleurAsk);
        } else if (crab_stat == 10) {
            canvas.drawText("BLANC ", canvas.getWidth() / 2, 750, couleurAsk);
        } else if (crab_stat == 11) {
            canvas.drawText("MARRON ", canvas.getWidth() / 2, 750, couleurAsk);
        }


        if (hitCheck(blancx, blancy)) {
            ball_stat = blanc;
            if (ball_stat == crab_stat) {
                blancx = -500;

                crab_stat = blancdelete();
                setCrabState(crab_stat);
                blancx = (int) (Math.random() * (1300 - 1));
                life_count+=1;
            }
            else {
                blancx = -500;

                crab_stat = blancdelete();
                setCrabState(crab_stat);
                blancx = (int) (Math.random() * (1300 - 1));
                score+=1;
            }
            return;

        }

        if (hitCheck(bleux, bleuy)) {
            ball_stat = bleu;
            if (ball_stat == crab_stat) {
                bleux = -500;
                crab_stat = bleudelete();
                bleux = (int) (Math.random() * (1300 - 1));
                setCrabState(crab_stat);
                life_count+=1;
            }
            else {
                bleux = -500;
                score += 1;
                crab_stat = bleudelete();
                bleux = (int) (Math.random() * (1300 - 1));
                setCrabState(crab_stat);
            }
            return;
        }

        if (hitCheck(orangex, orangey)) {
            ball_stat = orange;
            if (ball_stat == crab_stat) {
                orangex = -500;
                crab_stat = orangedelete();
                setCrabState(crab_stat);
                orangex = (int) (Math.random() * (1300 - 1));
                life_count+=1;
            }
            else {
                orangex = -500;
                score += 1;
                crab_stat = orangedelete();
                setCrabState(crab_stat);
                orangex = (int) (Math.random() * (1300 - 1));
            }
            return;
        }

        if (hitCheck(jaunex, jauney)) {
            ball_stat = jaune;
            if (ball_stat == crab_stat) {
                jaunex = -500;
                crab_stat = jaunedelete();
                jaunex = (int) (Math.random() * (1300 - 1));
                setCrabState(crab_stat);
                life_count+=1;
            }
            else {
                jaunex = -500;
                score += 1;
                crab_stat = jaunedelete();
                jaunex = (int) (Math.random() * (1300 - 1));
                setCrabState(crab_stat);
            }
            return;
        }

        if (hitCheck(marronx, marrony)) {
            ball_stat = marron;
            if (ball_stat == crab_stat) {
                marronx = -500;
                crab_stat = marrondelete();
                setCrabState(crab_stat);
                marronx = (int) (Math.random() * (1300 - 1));
                life_count+=1;
            }
            else {
                marronx = -500;
                score += 1;
                crab_stat = marrondelete();
                setCrabState(crab_stat);
                marronx = (int) (Math.random() * (1300 - 1));
            }
            return;
        }

        if (hitCheck(rougex, rougey)) {
            ball_stat = rouge;
            if (ball_stat == crab_stat) {
                rougex = -500;
                score += 1;
                crab_stat = rougedelete();
                setCrabState(crab_stat);
                rougex = (int) (Math.random() * (1300 - 1));
                life_count+=1;
            }
            else {
                rougex = -500;
                score += 1;
                crab_stat = rougedelete();
                setCrabState(crab_stat);
                rougex = (int) (Math.random() * (1300 - 1));

            }
            return;
        }

        if (hitCheck(vertx, verty)) {
            ball_stat = vert;
            if (ball_stat == crab_stat) {
                vertx = -500;
                crab_stat = vertdelete();
                vertx = (int) (Math.random() * (1300 - 1));
                setCrabState(crab_stat);
                life_count+=1;
            }
            else {
                vertx = -500;
                score += 1;
                crab_stat = vertdelete();
                vertx = (int) (Math.random() * (1300 - 1));
                setCrabState(crab_stat);
            }
            return;
        }

        if (hitCheck(rosex, rosey)) {
            ball_stat = rose;
            if (ball_stat == crab_stat) {
                rosex = -500;
                crab_stat = rosedelete();
                setCrabState(crab_stat);
                rosex = (int) (Math.random() * (1300 - 1));
                life_count+=1;
            }
            else {
                rosex = -500;
                score += 1;
                crab_stat = rosedelete();
                setCrabState(crab_stat);
                rosex = (int) (Math.random() * (1300 - 1));
            }
            return;
        }

        if (hitCheck(violetx, violety)) {
            ball_stat = violet;
            if (ball_stat == crab_stat) {
                violetx = -500;
                crab_stat = violetdelete();
                setCrabState(crab_stat);
                life_count+=1;
                violetx = (int) (Math.random() * (1300 - 1));
            }
            else {
                violetx = -500;
                score += 1;
                crab_stat = violetdelete();
                setCrabState(crab_stat);
                violetx = (int) (Math.random() * (1300 - 1));
            }
            return;
        }

        if (hitCheck(noirx, noiry)) {
            ball_stat = noir;
            if (ball_stat == crab_stat) {
                noirx = -500;
                crab_stat = noirdelete();
                setCrabState(crab_stat);
                life_count+=1;
                noirx = (int) (Math.random() * (1300 - 1));
            }
            else {
                noirx = -500;
                score += 1;
                crab_stat = noirdelete();
                setCrabState(crab_stat);
                noirx = (int) (Math.random() * (1300 - 1));
            }
            return;
        }

        if (hitCheck(grisx, grisy)) {
            ball_stat = gris;
            if (ball_stat == crab_stat) {
                grisx = -500;
                crab_stat = grisdelete();
                setCrabState(crab_stat);
                grisx = (int) (Math.random() * (1300 - 1));
            }
            else {
                score += 1;
                grisx = -500;
                crab_stat = grisdelete();
                setCrabState(crab_stat);
                grisx = (int) (Math.random() * (1300 - 1));

            }
            return;
        }

        if(hitCheck(sharkX,sharkY)){
            vie_nombre-=1;
            sharkX = (int) (Math.random() * (1300 - 1));
        }
        if(hitCheck(shark2X,shark2Y)){
            vie_nombre-=1;
            shark2X = (int) (Math.random() * (1300 - 1));
        }

        if(hitCheck(shark3X,shark3Y)){
            vie_nombre-=1;
            shark3X = (int) (Math.random() * (1300 - 1));
        }



        for (int i = 0; i < 3; i++) {
            int x = (int) (1100 + life[0].getWidth() * 0.8 * i);
            int y = 700;

            if (i < vie_nombre) {
                canvas.drawBitmap(vie[0], x, y, null);
            } else {
                canvas.drawBitmap(vie[1], x, y, null);
            }
        }


        if(life_count ==10 && vie_nombre==3){
            Intent intent = new Intent(context, EndGameActivity.class);
            intent.putExtra(EndGameActivity.INTENT_STAR_NUMBER, 3);
            context.startActivity(intent);
        }

        if(life_count ==10 && vie_nombre==2){
            Intent intent = new Intent(context, EndGameActivity.class);
            intent.putExtra(EndGameActivity.INTENT_STAR_NUMBER, 2);
            context.startActivity(intent);
        }

        if(life_count ==10 && vie_nombre==1){
            Intent intent = new Intent(context, EndGameActivity.class);
            intent.putExtra(EndGameActivity.INTENT_STAR_NUMBER, 1);
            context.startActivity(intent);
        }

        if(life_count ==10 && vie_nombre<1){
            Intent intent = new Intent(context, EndGameActivity.class);
            intent.putExtra(EndGameActivity.INTENT_STAR_NUMBER, 0);
            context.startActivity(intent);
        }





    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.currentX = (getWidth() - this.imageWidth) / 2;
        this.currentY = (getHeight() - this.imageHeight) / 2;
    }


    private void moveImage(float x, float y) {
        this.currentX += (int) x;
        this.currentY += (int) y;
        if ( this.currentX < 0 ) {
            this.currentX = 0;
        } else if ( this.currentX + this.imageWidth > this.getWidth() ){
            this.currentX = this.getWidth() - this.imageWidth;
        }

        if ( this.currentY < 0 ) {
            this.currentY = 0;
        } else if ( this.currentY + this.imageHeight > this.getHeight() ){
            this.currentY = this.getHeight() - this.imageHeight;
        }

        this.invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public boolean hitCheck(int x, int y) {
        if (currentX< x && x < (currentX + bird.getWidth()) &&
                currentY < y && y < (currentY + bird.getHeight())) {
            return true;
        }
        return false;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

}
