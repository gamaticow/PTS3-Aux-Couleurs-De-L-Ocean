package fr.gamaticow.auxcouleursdelocean.model;

import fr.gamaticow.auxcouleursdelocean.R;

/**
 * Created by Corentin COUTEAUX on 13/12/2020 at 14:03.
 */

public enum Sound {

    BLACK       (R.raw.black),
    BLUE        (R.raw.blue),
    BROWN       (R.raw.brown),
    GREEN       (R.raw.green),
    GREY        (R.raw.grey),
    ORANGE      (R.raw.orange),
    PINK        (R.raw.pink),
    PURPLE      (R.raw.purple),
    RED         (R.raw.red),
    WHITE       (R.raw.white),
    YELLOW      (R.raw.yellow),
    LOSE_HEART  (R.raw.lose_heart);

    private final int raw;

    Sound(int raw){
        this.raw = raw;
    }

    protected int getRaw() {
        return raw;
    }
}
