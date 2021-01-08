package fr.gamaticow.auxcouleursdelocean.model.crab;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.gamaticow.auxcouleursdelocean.controller.crab.GameActivity;
import fr.gamaticow.auxcouleursdelocean.model.Game;
import fr.gamaticow.auxcouleursdelocean.model.LevelDifficulties;
import fr.gamaticow.auxcouleursdelocean.model.SoundManager;

/**
 * Created by Corentin COUTEAUX on 09/12/2020 at 23:45.
 */

public class GameCrab extends Game<CrabGameLevel> {

    public static final int NB_BALLON = 10;

    private final GameActivity context;

    public List<Ballon> listBallon;
    public List<Oiseau> listOiseau;
    private Crab crabe;

    public GameCrab(GameActivity context, CrabGameLevel gameLevel) {
        super(gameLevel);
        this.context = context;

        listBallon = new ArrayList<>();
        listOiseau = new ArrayList<>();

        init();
    }


    private int step = 0;
    private int nextStep;
    private List<Oiseau> toRemove = new ArrayList<>();
    private List<Oiseau> toAdd = new ArrayList<>();

    @Override
    public void run() {
        if(getGameLevel().getDifficulty() == LevelDifficulties.HARD){
            if (step >= nextStep) {
                nextStep = new Random().nextInt(50)+50;
                step = 0;
            }

            if (step == 0)
                genrationOiseau();

            step++;

            for (Oiseau oi : listOiseau)
                oi.move();

            for (Oiseau oi : toRemove)
                listOiseau.remove(oi);

            listOiseau.addAll(toAdd);

            toRemove.clear();
            toAdd.clear();
        }

        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void genrationOiseau() {
        if (listOiseau.size() < 6) {
            Oiseau oiseau = new Oiseau(context);
            oiseau.setOnDestroy(() -> {
                toRemove.add(oiseau);
            });
            toAdd.add(oiseau);
        }
    }

    private void init(){
        generationBallon();
        crabe = new Crab(context, listBallon.get(0).getCouleur());
    }

    private void generationBallon() {
        Ballon ballon;
        for (int i = 0; i < NB_BALLON; i++) {
            ballon = new Ballon(context, getGameLevel().getDifficulty());
            listBallon.add(ballon);
        }

    }

    private void newColor() {
        int count = 0;
        for (int i = 0; i < listBallon.size(); i++) {
            if (crabe.getCrabColor() == listBallon.get(i).getCouleur())
                count += 1;
        }
        if (count == 0 && !listBallon.isEmpty()) {
            crabe.setNewCouleur(listBallon.get(0).getCouleur());
        } else if(listBallon.isEmpty()) {
            getGameLevel().end();
        }

    }

    @Override
    public void stop() {
        crabe.stop();
        super.stop();
    }

    @Override
    public void start() {
        super.start();
        crabe.start();
    }

    public void removeBallon(Ballon qballon) {
        SoundManager.playSound(qballon.ballonCouleur.getSound(), SoundManager.SoundType.WIN_LOSE);
        context.screen.removeView(qballon.imageBallonView);
        context.gameCrab.listBallon.remove(qballon);
        if (qballon.getCouleur() != crabe.getCrabColor()) {
            getGameLevel().removeLife();
        }
        newColor();
    }
}
