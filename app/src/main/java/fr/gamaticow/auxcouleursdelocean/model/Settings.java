package fr.gamaticow.auxcouleursdelocean.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public final class Settings {

    private static final String CONSIGNE = "CONSIGNE";
    private static final String ELEMENT = "ELEMENT";
    private static final String BACKGROUND = "BACKGROUND";
    private static final String SHOW_COLOR = "SHOW_COLOR";

    private static Settings instance;

    private final SharedPreferences preferences;

    private boolean consigne;
    private boolean element;
    private boolean background;
    private boolean showColor;

    private Settings(Activity context){
        preferences = context.getPreferences(Context.MODE_PRIVATE);
        consigne = preferences.getBoolean(CONSIGNE, true);
        element = preferences.getBoolean(ELEMENT, true);
        background = preferences.getBoolean(BACKGROUND, true);
        showColor = preferences.getBoolean(SHOW_COLOR, true);
    }

    public boolean isConsigneEnabled(){
        return consigne;
    }

    public boolean isElementEnabled(){
        return element;
    }

    public boolean isBackgroundEnabled(){
        return background;
    }

    public boolean isShowColor() {
        return showColor;
    }

    public void setConsigne(boolean value){
        this.consigne = value;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(CONSIGNE, value);
        editor.apply();
    }

    public void setElement(boolean value){
        this.element = value;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(ELEMENT, value);
        editor.apply();
    }

    public void setBackground(boolean value){
        this.background = value;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(BACKGROUND, value);
        editor.apply();
    }

    public void setShowColor(boolean showColor) {
        this.showColor = showColor;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(SHOW_COLOR, showColor);
        editor.apply();
    }

    public static void loadSettings(Activity context){
        if(instance == null)
            instance = new Settings(context);
    }

    public static Settings getInstance() {
        return instance;
    }
}
