package fr.gamaticow.auxcouleursdelocean.model;

import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

import fr.gamaticow.auxcouleursdelocean.MainActivity;

/**
 * Created by Corentin COUTEAUX on 13/12/2020 at 14:03.
 */

public final class SoundManager implements SoundPool.OnLoadCompleteListener {

    private static final SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);
    private static final HashMap<Sound, Integer> soundMap = new HashMap<>(Sound.values().length);
    private static boolean soundPoolLoaded = false;

    public SoundManager(MainActivity context){
        for(Sound sound : Sound.values()){
            soundMap.put(sound, soundPool.load(context, sound.getRaw(), 1));
        }

        soundPool.setOnLoadCompleteListener(this);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        soundPoolLoaded = true;
    }

    public static void playSound(Sound sound, SoundType type){
        if(soundPoolLoaded){
            if(type == SoundType.WIN_LOSE && !Settings.getInstance().isElementEnabled())
                return;
            if(type == SoundType.ELEMENT_CLICK && !Settings.getInstance().isConsigneEnabled())
                return;
            float volume = 1;
            soundPool.play(soundMap.get(sound), volume, volume, 1, 0, 1f);
        }
    }


    public enum SoundType {

        FORCE,
        WIN_LOSE,
        ELEMENT_CLICK;

    }

}
