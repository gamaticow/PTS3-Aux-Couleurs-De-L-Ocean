package fr.gamaticow.auxcouleursdelocean.model.fish;

import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import fr.gamaticow.auxcouleursdelocean.controller.fish.GameActivity;
import fr.gamaticow.auxcouleursdelocean.model.GameLevel;
import fr.gamaticow.auxcouleursdelocean.model.LevelDifficulties;

/**
 * Created by Corentin COUTEAUX on 01/10/2020 at 13:52.
 */

public class FishGameLevel extends GameLevel {

    private Bucket[] buckets;

    public FishGameLevel(GameActivity context, LevelDifficulties difficulties, LinearLayout livesLayout){
        super(context, difficulties, livesLayout);
    }

    public BucketsContainer initialise(LinearLayout bucketsLayout){
        BucketsContainer container = new BucketsContainer((GameActivity) context, this, bucketsLayout);

        FishGameDrawable color1 = FishGameDrawable.getRandomEasyColor();
        FishGameDrawable color2 = FishGameDrawable.getRandomEasyColor();
        while (color2 == color1) {
            color2 = FishGameDrawable.getRandomEasyColor();
        }

        if(difficulty == LevelDifficulties.EASY){
            buckets = new Bucket[]{ new Bucket(container, color1, false), new Bucket(container, color2, false) };
        }else if(difficulty == LevelDifficulties.NORMAL){
            if(Math.random() < 0.5)
                color2 = FishGameDrawable.getRandomHardColor();

            buckets = new Bucket[]{ new Bucket(container, color1, true), new Bucket(container, color2, true) };
        }else if(difficulty == LevelDifficulties.HARD){
            color2 = FishGameDrawable.getRandomHardColor();
            buckets = new Bucket[]{ new Bucket(container, color1, true), new Bucket(container, color2, true) };
        }

        return container;
    }

    private int lastCorresponding = 0;
    private int nextCorresponding = 3;
    public synchronized FishGameDrawable getFishColor(){
        lastCorresponding++;
        if(lastCorresponding >= nextCorresponding) {
            nextCorresponding = new Random().nextInt(4)+2;
            lastCorresponding = 0;
            return getRandomColorFromBuckets();
        }
        return getRandomColorExceptBuckets();
    }

    private FishGameDrawable getRandomColorFromBuckets(){
        List<FishGameDrawable> colors = new ArrayList<>();
        for(Bucket bucket : buckets){
            if(!bucket.isFilled())
                colors.add(bucket.getColor());
        }
        Collections.shuffle(colors);
        return !colors.isEmpty() ? colors.get(new Random().nextInt(colors.size())) : FishGameDrawable.getRandomColor();
    }

    private FishGameDrawable getRandomColorExceptBuckets(){
        List<FishGameDrawable> colors = new ArrayList<>(Arrays.asList(FishGameDrawable.values()));
        for(Bucket bucket : buckets)
            colors.remove(bucket.getColor());
        Collections.shuffle(colors);
        return colors.get(new Random().nextInt(colors.size()));
    }



}
