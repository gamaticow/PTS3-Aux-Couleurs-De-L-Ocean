package fr.gamaticow.auxcouleursdelocean.controller;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import fr.gamaticow.auxcouleursdelocean.R;
import fr.gamaticow.auxcouleursdelocean.model.Settings;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SwitchCompat consigne = findViewById(R.id.consigne);
        SwitchCompat element = findViewById(R.id.element);
        SwitchCompat background = findViewById(R.id.background);
        SwitchCompat showColor = findViewById(R.id.show_color);

        consigne.setChecked(Settings.getInstance().isConsigneEnabled());
        element.setChecked(Settings.getInstance().isElementEnabled());
        background.setChecked(Settings.getInstance().isBackgroundEnabled());
        showColor.setChecked(Settings.getInstance().isShowColor());

        consigne.setOnCheckedChangeListener((buttonView, isChecked) -> Settings.getInstance().setConsigne(isChecked));
        element.setOnCheckedChangeListener((buttonView, isChecked) -> Settings.getInstance().setElement(isChecked));
        background.setOnCheckedChangeListener((buttonView, isChecked) -> Settings.getInstance().setBackground(isChecked));
        showColor.setOnCheckedChangeListener((buttonView, isChecked) -> Settings.getInstance().setShowColor(isChecked));

        findViewById(R.id.menu_btn).setOnClickListener(v -> onBackPressed());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if(hasFocus){
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }
}