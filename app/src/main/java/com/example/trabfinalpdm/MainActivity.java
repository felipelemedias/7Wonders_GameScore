package com.example.trabfinalpdm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase bancoDados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNovoJogo = findViewById(R.id.btnNovoJogo);
        Button btnJogosSalvos = findViewById(R.id.btnJogosSalvos);

        btnNovoJogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Novojogo.class);
                startActivity(intent);
            }
        });

        btnJogosSalvos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aqui você pode criar a intenção para abrir a página de jogos salvos
                Intent intent = new Intent(MainActivity.this, Jogosalvo.class);
                startActivity(intent);
            }
        });
    }

}