package com.example.trabfinalpdm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "meu_banco_de_dados";
    public static final int DATABASE_VERSION = 1;

    // Tabela jogador
    public static final String TABLE_JOGADOR = "jogador";
    public static final String COLUMN_JOGADOR_ID = "id";
    public static final String COLUMN_JOGADOR_NOME = "nome";

    // Tabela jogo
    public static final String TABLE_JOGO = "jogo";
    public static final String COLUMN_JOGO_ID = "id";
    public static final String COLUMN_JOGO_NOME = "nome";

    // Tabela pontos
    public static final String TABLE_PONTUACAO = "pontos";
    public static final String COLUMN_PONTOS_ID = "id";
    public static final String COLUMN_PONTOS_JOGADOR_ID = "jogador_id";
    public static final String COLUMN_PONTOS_JOGO_ID = "jogo_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createJogadorTable = "CREATE TABLE " + TABLE_JOGADOR + "("
                + COLUMN_JOGADOR_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_JOGADOR_NOME + " TEXT"
                + ")";
        db.execSQL(createJogadorTable);

        String createJogoTable = "CREATE TABLE " + TABLE_JOGO + "("
                + COLUMN_JOGO_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_JOGO_NOME + " TEXT"
                + ")";
        db.execSQL(createJogoTable);

        String createPontosTable = "CREATE TABLE " + TABLE_PONTUACAO + "("
                + COLUMN_PONTOS_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_PONTOS_JOGADOR_ID + " INTEGER,"
                + COLUMN_PONTOS_JOGO_ID + " INTEGER,"
                + "FOREIGN KEY(" + COLUMN_PONTOS_JOGADOR_ID + ") REFERENCES " + TABLE_JOGADOR + "(" + COLUMN_JOGADOR_ID + "),"
                + "FOREIGN KEY(" + COLUMN_PONTOS_JOGO_ID + ") REFERENCES " + TABLE_JOGO + "(" + COLUMN_JOGO_ID + ")"
                + ")";
        db.execSQL(createPontosTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PONTUACAO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOGADOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOGO);

        onCreate(db);
    }
}
