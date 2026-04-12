package br.gm.cards.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


public class Deck {
    private ArrayList<Card> cartas = new ArrayList<Card>();
    private ArrayList<Card> cartasEasy = new ArrayList<Card>();
    private ArrayList<Card> cartasShuffle = new ArrayList<Card>();
   private boolean shuffled = false;
   
   private int dificuldade = 0;

    public boolean isShuffled() {
        return shuffled;
    }


    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
        if (dificuldade == 0 || dificuldade == 1) {
            cartasEasy.clear();
            cartasEasy.addAll(cartas);
            cartas.clear();
            cartas.addAll(cartasEasy);
        } else {
            cartasEasy.clear();
            cartasEasy.addAll(cartas);
            this.shuffle();
            cartas.clear();
            cartas.addAll(cartasShuffle);
        }
    }

    public void add(String pergunta, String resposta){
        Card carta = new Card(pergunta, resposta);
        cartas.add(carta);
    }

    public void add(Card carta) {
        cartas.add(carta);
    }

    public void remove(Card carta) {
        cartas.remove(carta);
    }

    public void remove(String questionTextField, String answerTextField) {
        Iterator<Card> iterator = cartas.iterator();

        while (iterator.hasNext()) {
            Card card = iterator.next();

            if (card.getPergunta().equals(questionTextField) && card.getResposta().equals(answerTextField)) {
                iterator.remove();
                break;
            }
        }
    }

    public void removeFromShuffled(String questionTextField, String answerTextField) {
        Iterator<Card> iterator = cartasShuffle.iterator();

        while (iterator.hasNext()) {
            Card card = iterator.next();

            if (card.getPergunta().equals(questionTextField) && card.getResposta().equals(answerTextField)) {
                iterator.remove();
                break;
            }
        }
    }

    public void remove(int index){
        cartas.remove(index);
    }
    
    public void removeAll(){
        cartas.clear();
    }
    
    
    public int getIndex(Card carta) {
        return cartas.indexOf(carta);
    }

    public int size(){
        return cartas.size();
    }

    
    public void shuffle(){
        cartasShuffle = (ArrayList<Card>) cartas.clone();
       Collections.shuffle(cartasShuffle);
       shuffled = true;
       
    }
    
    // public void shuffleClear(){
    // if(cartasShuffle.size() > cartas.size()){
   
    // for(int i = cartasShuffle.size(); i > cartas.size(); i++){
    // cartasShuffle.remove(i);
    // }
    // }
        
    // shuffled = false;
    // }
    
    public void clearShuffled(){
        cartasShuffle.clear();
        shuffled = false;
    }
    
    public String getPergunta(int index){
        
        if(shuffled){
            return cartasShuffle.get(index).getPergunta();
        } else {
            return cartas.get(index).getPergunta();
        }
      
    }
    
    public String getResposta(int index){
        if (shuffled) {
            return cartasShuffle.get(index).getResposta();
        } else {
            return cartas.get(index).getResposta();
        }
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Original Deck{");
        for (int i = 0; i < cartas.size(); i++) {
            sb.append(cartas.get(i).toString(i));
            if (i < cartas.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(", dificuldade=").append(dificuldade).append('}');
        sb.append("Shuffled Deck{");
        for (int i = 0; i < cartasShuffle.size(); i++) {
            sb.append(cartasShuffle.get(i).toString(i));
            if (i < cartasShuffle.size() - 1) {
                sb.append(", ");
            }
        }
          sb.append(", dificuldade=").append(dificuldade).append('}');
        return sb.toString();
    }

    
    
    
}
