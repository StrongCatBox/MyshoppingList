package com.example.mescoursesjeina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private TextView textViewUsername;
    private Button buttonChangePassword;
    private Switch switchDarkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialisation des vues
        textViewUsername = findViewById(R.id.textViewUsername);
        buttonChangePassword = findViewById(R.id.buttonChangePassword);
        switchDarkMode = findViewById(R.id.switchDarkMode);

        // Récupérer le nom d'utilisateur de l'intent
        String username = getIntent().getStringExtra("username");
        textViewUsername.setText("Bienvenue, " + username + "!");

        // Gestionnaire de clics pour le bouton "Modifier mot de passe"
        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton est cliqué (par exemple, ouvrir une nouvelle activité pour modifier le mot de passe)
            }
        });

        // Gestionnaire de changement d'état pour le switch "Mode sombre"
        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Action à effectuer lorsque l'état du switch change (par exemple, changer le thème de l'application en mode sombre ou clair)
                if (isChecked) {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }
}
