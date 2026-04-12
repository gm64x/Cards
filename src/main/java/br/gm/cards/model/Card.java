/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.gm.cards.model;

/**
 *
 * @author Admin
 */
public class Card {
    private String pergunta;
    private String resposta;

    public Card(){}

    public Card(String pergunta, String resposta) {
        this.pergunta = pergunta;
        this.resposta = resposta;
    }


    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "pergunta='" + pergunta + '\'' +
                ", resposta='" + resposta + '\'' +
                '}';
    }

    public String toString(int index) {
        return "Carta{" +
                "index=" + index +
                ", pergunta='" + pergunta + '\'' +
                ", resposta='" + resposta + '\'' +
                '}';
    }
}

