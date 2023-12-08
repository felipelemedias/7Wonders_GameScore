package com.example.trabfinalpdm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Jogosalvo extends AppCompatActivity {

    private ListView listViewJogos;

    private SQLiteDatabase database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogosalvo);

        listViewJogos = findViewById(R.id.listViewJogos);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        exibirjogos();
    }

    private void exibirjogos() {
        ArrayList<PontuacaoFinal> pontuacoesList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_PONTUACAO,
                null, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PONTOS_ID);
                int jogadorIdIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PONTOS_JOGADOR_ID);
                int jogoIdIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PONTOS_JOGO_ID);
                int pontosIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_JOGO_NOME);

                do {
                    if (idIndex >= 0 && jogadorIdIndex >= 0 && jogoIdIndex >= 0 && pontosIndex >= 0) {
                        int id = cursor.getInt(idIndex);
                        int jogadorId = cursor.getInt(jogadorIdIndex);
                        int jogoId = cursor.getInt(jogoIdIndex);
                        int pontos = cursor.getInt(pontosIndex);

                        PontuacaoFinal pontuacao = new PontuacaoFinal(id, jogadorId, jogoId, pontos);
                        pontuacoesList.add(pontuacao);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        ArrayAdapter<PontuacaoFinal> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, pontuacoesList);
        listViewJogos.setAdapter(adapter);
    }
}