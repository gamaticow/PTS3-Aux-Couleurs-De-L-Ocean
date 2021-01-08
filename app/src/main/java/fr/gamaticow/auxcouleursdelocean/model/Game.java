package fr.gamaticow.auxcouleursdelocean.model;

/**
 * Created by Corentin COUTEAUX on 09/12/2020 at 20:15.
 */

public abstract class Game<T extends GameLevel> implements Runnable {

    private final T gameLevel;
    private boolean running;

    public Game(T gameLevel){
        this.gameLevel = gameLevel;
        running = false;
    }

    private void thread(){
        while (running){
            run();
        }
    }

    public void start(){
        if(running) return;
        new Thread(this::thread).start();
        running = true;
    }

    public void stop(){
        if(!running) return;
        running = false;
    }

    public T getGameLevel(){
        return gameLevel;
    }

}
