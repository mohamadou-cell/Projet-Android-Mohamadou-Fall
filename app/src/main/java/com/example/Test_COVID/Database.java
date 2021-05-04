package com.example.Test_COVID;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import Model.Personne;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "COVID";

    private static final String TABLE_NAME = "Infos";

    private static final String COLUMN_ID ="Id";
    private static final String COLUMN_NOM ="Nom";
    private static final String COLUMN_PRENOM = "Prenom";
    private static final String COLUMN_ADDRESS = "Email";
    private static final String COLUMN_PROFIL = "Profil";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NOM + " TEXT,"
                + COLUMN_PRENOM + " TEXT" + COLUMN_ADDRESS + "TEXT" + COLUMN_PROFIL + "TEXT" + ")";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void ajoutInfos(Personne p1) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOM, p1.getNom());
        values.put(COLUMN_PRENOM, p1.getPrenom());
        values.put(COLUMN_ADDRESS, p1.getEmail());
        values.put(COLUMN_PROFIL, p1.getProfil());

        // Insertion d'une ligne
        db.insert(TABLE_NAME, null, values);

        // Fermeture de la connexion à la base de données
        db.close();
    }

}
