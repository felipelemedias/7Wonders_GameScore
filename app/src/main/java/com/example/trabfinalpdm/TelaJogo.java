package com.example.trabfinalpdm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class TelaJogo extends AppCompatActivity {

    private TextView textNomeJogador;
    private EditText[] editPontuacoes;
    private Button buttonCalcular;
    private TextView textTotalPontos;
    private Button buttonLimpar;

    private int totalPontos = 0;
    private String[] nomesJogadores;
    private Map<String, Integer> pontuacoesJogadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telajogo);

        textNomeJogador = findViewById(R.id.textNomeJogador);

        // Obter os nomes dos jogadores da intent
        nomesJogadores = getIntent().getStringArrayExtra("nomeJogador");

        if (nomesJogadores != null && nomesJogadores.length > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < nomesJogadores.length; i++) {
                stringBuilder.append(nomesJogadores[i]);
                if (i < nomesJogadores.length - 1) {
                    stringBuilder.append(", ");
                }
            }
            textNomeJogador.setText(stringBuilder.toString());
        }

        editPontuacoes = new EditText[]{
                findViewById(R.id.editPontuacao1),
                findViewById(R.id.editPontuacao2),
                findViewById(R.id.editPontuacao3),
                findViewById(R.id.editPontuacao4),
                findViewById(R.id.editPontuacao5),
                findViewById(R.id.editPontuacao6),
                findViewById(R.id.editPontuacao7)
        };
        buttonCalcular = findViewById(R.id.buttonCalcular);
        textTotalPontos = findViewById(R.id.textTotalPontos);
        buttonLimpar = findViewById(R.id.buttonLimpar);

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularTotalPontos();
            }
        });

        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });

        pontuacoesJogadores = new HashMap<>();
        for (String nomeJogador : nomesJogadores) {
            pontuacoesJogadores.put(nomeJogador, 0);
        }
    }

    public void onSetaEsquerdaClick(View view) {
        // Lógica para avançar para o jogador anterior
        if (nomesJogadores != null && nomesJogadores.length > 0) {
            // Encontre o índice do jogador atual
            int jogadorAtual = -1;
            for (int i = 0; i < nomesJogadores.length; i++) {
                if (textNomeJogador.getText().toString().equals(nomesJogadores[i])) {
                    jogadorAtual = i;
                    break;
                }
            }

            // Avance para o jogador anterior
            if (jogadorAtual > 0) {
                atualizarPontuacaoJogador(textNomeJogador.getText().toString());
                textNomeJogador.setText(nomesJogadores[jogadorAtual - 1]);
                exibirPontuacaoJogador(textNomeJogador.getText().toString());
            }
        }
    }

    public void onSetaDireitaClick(View view) {
        // Lógica para avançar para o próximo jogador
        if (nomesJogadores != null && nomesJogadores.length > 0) {
            // Encontre o índice do jogador atual
            int jogadorAtual = -1;
            for (int i = 0; i < nomesJogadores.length; i++) {
                if (textNomeJogador.getText().toString().equals(nomesJogadores[i])) {
                    jogadorAtual = i;
                    break;
                }
            }

            // Avance para o próximo jogador
            if (jogadorAtual < nomesJogadores.length - 1) {
                atualizarPontuacaoJogador(textNomeJogador.getText().toString());
                textNomeJogador.setText(nomesJogadores[jogadorAtual + 1]);
                exibirPontuacaoJogador(textNomeJogador.getText().toString());
            }
        }
    }

    private void calcularTotalPontos() {
        totalPontos = 0;

        for (EditText editPontuacao : editPontuacoes) {
            String pontuacaoStr = editPontuacao.getText().toString().trim();
            if (!pontuacaoStr.isEmpty()) {
                int pontuacao = Integer.parseInt(pontuacaoStr);
                totalPontos += pontuacao;
            }
        }

        textTotalPontos.setText("Total de Pontos: " + totalPontos);

        // Atualizar a pontuação do jogador atual
        String jogadorAtual = textNomeJogador.getText().toString();
        pontuacoesJogadores.put(jogadorAtual, totalPontos);
    }

    private void limparCampos() {
        for (EditText editPontuacao : editPontuacoes) {
            editPontuacao.setText("");
        }
        totalPontos = 0;
        textTotalPontos.setText("Total de Pontos: ");
    }

    private void exibirPontuacaoJogador(String jogador) {
        if (pontuacoesJogadores.containsKey(jogador)) {
            int pontuacao = pontuacoesJogadores.get(jogador);
            totalPontos = pontuacao;
            textTotalPontos.setText("Total de Pontos: " + pontuacao);
        }
    }

    private void atualizarPontuacaoJogador(String jogador) {
        pontuacoesJogadores.put(jogador, totalPontos);
    }
}