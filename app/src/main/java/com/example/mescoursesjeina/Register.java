package com.example.mescoursesjeina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText editTextLogin, editTextPassword, editTextConfirmPassword;
    private Button buttonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les données saisies par l'utilisateur
                String username = editTextLogin.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();

                // Vérifier que les champs ne sont pas vides
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(Register.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(Register.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                } else {
                    // Insérer les données dans la base de données SQLite
                    DBHelper dbHelper = new DBHelper(Register.this);
                    boolean isSuccess = dbHelper.addUser(username, password);

                    if (isSuccess) {
                        Toast.makeText(Register.this, "Compte créé avec succès", Toast.LENGTH_SHORT).show();
                        // Effacer les champs après la création du compte
                        editTextLogin.setText("");
                        editTextPassword.setText("");
                        editTextConfirmPassword.setText("");

                        // Retourner à l'activité de connexion (login)
                        retournerPageLogin();
                    } else {
                        Toast.makeText(Register.this, "Erreur lors de la création du compte", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // Méthode pour retourner à l'activité de connexion (login)
    private void retournerPageLogin() {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish(); // Optionnel : pour fermer l'activité actuelle et éviter de revenir en arrière avec le bouton de retour
    }
}
