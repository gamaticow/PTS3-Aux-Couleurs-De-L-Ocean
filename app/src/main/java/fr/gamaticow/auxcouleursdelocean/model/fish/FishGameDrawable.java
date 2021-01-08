package fr.gamaticow.auxcouleursdelocean.model.fish;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import fr.gamaticow.auxcouleursdelocean.R;
import fr.gamaticow.auxcouleursdelocean.model.Sound;

/**
 * Created by Corentin COUTEAUX on 30/09/2020 at 16:48.
 */

public enum FishGameDrawable {

    RED     (   R.string.red,       false, Sound.RED,       R.drawable.fish_red,    R.drawable.bucket_red,      R.drawable.bucket_red1,     R.drawable.bucket_red2,     R.drawable.bucket_red3,     R.drawable.bucket_red4,     R.drawable.bucket_red5      ),
    BLUE    (   R.string.blue,      false, Sound.BLUE,      R.drawable.fish_blue,   R.drawable.bucket_blue,     R.drawable.bucket_blue1,    R.drawable.bucket_blue2,    R.drawable.bucket_blue3,    R.drawable.bucket_blue4,    R.drawable.bucket_blue5     ),
    YELLOW  (   R.string.yellow,    false, Sound.YELLOW,    R.drawable.fish_yellow, R.drawable.bucket_yellow,   R.drawable.bucket_yellow1,  R.drawable.bucket_yellow2,  R.drawable.bucket_yellow3,  R.drawable.bucket_yellow4,  R.drawable.bucket_yellow5   ),
    BROWN   (   R.string.brown,     true, Sound.BROWN,      R.drawable.fish_brown,  R.drawable.bucket_brown,    R.drawable.bucket_brown1,   R.drawable.bucket_brown2,   R.drawable.bucket_brown3,   R.drawable.bucket_brown4,   R.drawable.bucket_brown5    ),
    ORANGE  (   R.string.orange,    false, Sound.ORANGE,    R.drawable.fish_orange, R.drawable.bucket_orange,   R.drawable.bucket_orange1,  R.drawable.bucket_orange2,  R.drawable.bucket_orange3,  R.drawable.bucket_orange4,  R.drawable.bucket_orange5   ),
    GREEN   (   R.string.green,     false, Sound.GREEN,     R.drawable.fish_green,  R.drawable.bucket_green,    R.drawable.bucket_green1,   R.drawable.bucket_green2,   R.drawable.bucket_green3,   R.drawable.bucket_green4,   R.drawable.bucket_green5    ),
    WHITE   (   R.string.white,     true, Sound.WHITE,      R.drawable.fish_white,  R.drawable.bucket_white,    R.drawable.bucket_white1,   R.drawable.bucket_white2,   R.drawable.bucket_white3,   R.drawable.bucket_white4,   R.drawable.bucket_white5    ),
    PINK    (   R.string.pink,      false, Sound.PINK,      R.drawable.fish_pink,   R.drawable.bucket_pink,     R.drawable.bucket_pink1,    R.drawable.bucket_pink2,    R.drawable.bucket_pink3,    R.drawable.bucket_pink4,    R.drawable.bucket_pink5     ),
    PURPLE  (   R.string.purple,    false, Sound.PURPLE,    R.drawable.fish_purple, R.drawable.bucket_purple,   R.drawable.bucket_purple1,  R.drawable.bucket_purple2,  R.drawable.bucket_purple3,  R.drawable.bucket_purple4,  R.drawable.bucket_purple5   ),
    GREY    (   R.string.grey,      true, Sound.GREY,       R.drawable.fish_grey,   R.drawable.bucket_grey,     R.drawable.bucket_grey1,    R.drawable.bucket_grey2,    R.drawable.bucket_grey3,    R.drawable.bucket_grey4,    R.drawable.bucket_grey5     ),
    BLACK   (   R.string.black,     true, Sound.BLACK,      R.drawable.fish_black,  R.drawable.bucket_black,    R.drawable.bucket_black1,   R.drawable.bucket_black2,   R.drawable.bucket_black3,   R.drawable.bucket_black4,   R.drawable.bucket_black5    );

    private static List<FishGameDrawable> easyColors = new ArrayList<>();
    private static List<FishGameDrawable> hardColors = new ArrayList<>();
    static {
        for(FishGameDrawable color : FishGameDrawable.values()){
            if(color.hardColor)
                hardColors.add(color);
            else
                easyColors.add(color);
        }
    }

    private int name;
    private boolean hardColor;
    private Sound sound;
    private int fish;
    private int[] buckets;

    FishGameDrawable(int name, boolean hardColor, Sound sound, int fish, int... buckets) {
        this.name = name;
        this.hardColor = hardColor;
        this.sound = sound;
        this.fish = fish;
        this.buckets = buckets;
    }

    public Sound getSound() {
        return sound;
    }

    public int getFish(){
        return fish;
    }

    public int getBucket(int fillLevel){
        int index = Math.max(0, Math.min(fillLevel, buckets.length - 1));
        return buckets[index];
    }

    public static FishGameDrawable getRandomColor(){
        int random = (int) (Math.random()* FishGameDrawable.values().length);
        return FishGameDrawable.values()[random];
    }

    public static FishGameDrawable getRandomEasyColor(){
        int random = (int) (Math.random()* easyColors.size());
        return easyColors.get(random);
    }

    public static FishGameDrawable getRandomHardColor(){
        int random = (int) (Math.random()* hardColors.size());
        return hardColors.get(random);
    }

    public String getName(Activity context) {
        return context.getResources().getString(name);
    }
}
