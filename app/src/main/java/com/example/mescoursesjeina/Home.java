package com.example.mescoursesjeina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Home extends AppCompatActivity {

    private TextView textViewUsername;
    private Button buttonChangePassword;
    private Switch switchDarkMode;

    private FloatingActionButton fabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialisation des vues
        textViewUsername = findViewById(R.id.textViewUsername);
        buttonChangePassword = findViewById(R.id.buttonChangePassword);
        switchDarkMode = findViewById(R.id.switchDarkMode);
        fabMenu = findViewById(R.id.fabMenu);

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

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });
    }

    private void showMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.menu_fab, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_activity1) {
                    recreate();
                    return true;
                } else if (item.getItemId() == R.id.action_activity2) {
                    Log.d("HomeActivity", "Launching makeshoplist activity");

                    startActivity(new Intent(Home.this, makeShopList.class));
                    return true;
                } else if (item.getItemId() == R.id.action_activity3) {
                    startActivity(new Intent(Home.this, seeShopList.class));
                    return true;
                } else if (item.getItemId() == R.id.action_activity4) {
                    startActivity(new Intent(Home.this, seeArchives.class));
                    return true;
                } else {

                    return false;
                }
            }
        });

        popup.show();
    }
}
