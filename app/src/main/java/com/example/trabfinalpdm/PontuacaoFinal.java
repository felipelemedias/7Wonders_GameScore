package com.example.trabfinalpdm;

public class PontuacaoFinal {
    private int id;
    private int jogador_id;
    private int jogo_id;
    private int pontos;

    public PontuacaoFinal(int id, int jogador_id, int jogo_id, int pontos) {
        this.id = id;
        this.jogador_id = jogador_id;
        this.jogo_id = jogo_id;
        this.pontos = pontos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJogadorId() {
        return jogador_id;
    }

    public void setJogadorId(int jogador_id) {
        this.jogador_id = jogador_id;
    }

    public int getJogoId() {
        return jogo_id;
    }

    public void setJogoId(int jogo_id) {
        this.jogo_id = jogo_id;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    @Override
    public String toString() {
        return "Código: " + id + "\nId Jogador: " + jogador_id + "\nID Jogo: " + jogo_id + "\nPontos: " + pontos;
    }
}