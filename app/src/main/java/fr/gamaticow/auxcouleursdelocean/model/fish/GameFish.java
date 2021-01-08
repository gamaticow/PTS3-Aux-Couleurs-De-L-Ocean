package fr.gamaticow.auxcouleursdelocean.model.fish;

import java.util.ArrayList;
import java.util.List;

import fr.gamaticow.auxcouleursdelocean.controller.fish.GameActivity;
import fr.gamaticow.auxcouleursdelocean.model.Game;

/**
 * Created by Corentin COUTEAUX on 09/12/2020 at 20:52.
 */

public class GameFish extends Game<FishGameLevel> {

    public static final int MAX_FISH = 10;

    private final GameActivity context;
    private List<Fish> fishs = new ArrayList<>();
    private List<Fish> toRemove = new ArrayList<>();
    private List<Fish> toAdd = new ArrayList<>();
    private int step = 0;
    private int nextStep;

    public GameFish(GameActivity context, FishGameLevel gameLevel) {
        super(gameLevel);
        this.context = context;
    }

    @Override
    public void run() {
        if(step >= nextStep)
            step = 0;

        if(step == 0)
            generateFish(false);

        step++;

        for(Fish fish : fishs)
            fish.move();

        context.container.animate();

        for(Fish fish : toRemove)
            fishs.remove(fish);

        fishs.addAll(toAdd);

        toRemove.clear();
        toAdd.clear();

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void generateFish(boolean fromDestroy){
        if(fromDestroy || fishs.size() < MAX_FISH) {
            Fish fish = new Fish(context, Math.random() > 0.5 ? Fish.RIGHT : Fish.LEFT, getGameLevel().getFishColor());
            if(fromDestroy)
                toAdd.add(fish);
            else
                fishs.add(fish);
            fish.setOnDestroy(() -> {
                toRemove.add(fish);
                generateFish(true);
            });
        }

        if(!fromDestroy)
            nextStep = (int) (Math.random()*100+100);
    }
}
