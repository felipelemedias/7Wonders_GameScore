package com.example.trabfinalpdm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Novojogo extends AppCompatActivity {
    private LinearLayout layoutEditTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novojogo);

        TextView textJogadores = findViewById(R.id.textJogadores);
        Spinner spinnerJogadores = findViewById(R.id.spinnerJogadores);
        layoutEditTexts = findViewById(R.id.layoutEditTexts);

        // Configurar o Spinner com os números de jogadores
        List<String> numJogadoresList = new ArrayList<>();
        for (int i = 3; i <= 7; i++) {
            numJogadoresList.add(String.valueOf(i));
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numJogadoresList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJogadores.setAdapter(spinnerAdapter);

        // Definir o ouvinte de seleção do Spinner
        spinnerJogadores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int numJogadores = Integer.parseInt(parent.getItemAtPosition(position).toString());
                atualizarEditTexts(numJogadores);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nada a fazer aqui
            }
        });

        Button buttonVoltar = findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarTelaInicial();
            }
        });

        Button buttonIniciar = findViewById(R.id.buttonIniciar);
        buttonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarJogo();
            }
        });
    }

    private void atualizarEditTexts(int numJogadores) {
        layoutEditTexts.removeAllViews();

        for (int i = 1; i <= numJogadores; i++) {
            EditText editText = new EditText(this);
            editText.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            editText.setHint("Jogador " + i);
            layoutEditTexts.addView(editText);
        }
    }

    private void voltarTelaInicial() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void iniciarJogo() {
        int numJogadores = layoutEditTexts.getChildCount();
        String[] nomesJogadores = new String[numJogadores];

        for (int i = 0; i < numJogadores; i++) {
            EditText editText = (EditText) layoutEditTexts.getChildAt(i);
            nomesJogadores[i] = editText.getText().toString();
        }

        Intent intent = new Intent(this, TelaJogo.class);
        intent.putExtra("nomeJogador", nomesJogadores);
        startActivity(intent);
    }
}