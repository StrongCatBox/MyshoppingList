package com.example.mescoursesjeina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText editTextLogin, editTextPassword;
    private Button buttonLogin, buttonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les données saisies par l'utilisateur
                String username = editTextLogin.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Vérifier les identifiants dans la base de données
                DBHelper dbHelper = new DBHelper(Login.this);
                boolean isAuthenticated = dbHelper.authenticateUser(username, password);

                if (isAuthenticated) {
                    // Rediriger vers la page d'accueil si l'authentification réussit
                    Intent intent = new Intent(Login.this, Home.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish(); // Optionnel : pour fermer l'activité actuelle et éviter de revenir en arrière avec le bouton de retour
                } else {
                    // Afficher un message d'erreur si l'authentification échoue
                    Toast.makeText(Login.this, "Nom d'utilisateur ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers la page d'inscription
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}
