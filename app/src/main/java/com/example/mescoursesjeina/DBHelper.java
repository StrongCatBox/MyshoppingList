package com.example.mescoursesjeina;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "MyDataBase3";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Créer la table pour stocker les comptes utilisateurs
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Méthode pour ajouter un nouveau compte utilisateur dans la base de données
    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        return result != -1; // Retourne true si l'insertion a réussi, false sinon
    }


    public boolean authenticateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        boolean isAuthenticated = false;

        try {
            // Sélectionner l'utilisateur avec le nom d'utilisateur donné et le mot de passe donné
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                    COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
            cursor = db.rawQuery(query, new String[]{username, password});

            // Vérifier si le curseur a retourné des lignes
            if (cursor != null && cursor.getCount() > 0) {
                isAuthenticated = true; // L'utilisateur est authentifié
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return isAuthenticated;
    }

}
