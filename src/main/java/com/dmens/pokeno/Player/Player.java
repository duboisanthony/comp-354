package com.dmens.pokeno.Player;

import com.dmens.pokeno.Card.Card;
import com.dmens.pokeno.Card.EnergyCard;
import com.dmens.pokeno.Card.Pokemon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Devin on 2017-05-26.
 */
public class Player {

    private static final Logger LOG = LogManager.getLogger(Player.class);

    private Pokemon activePokemon;
    private ArrayList<Pokemon> benchedPokemon;
    private ArrayList<Card> hand;
    private ArrayList<Card> deck;
    private ArrayList<Card> rewards;

    public Pokemon getActivePokemon() {
        return activePokemon;
    }

    public ArrayList<Pokemon> getBenchedPokemon() {
        return benchedPokemon;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card> getRewards() {
        return rewards;
    }



    public Player(String deckList){}

    public void shuffleDeck(){
        Collections.shuffle(this.deck);
    }

    public void Mulligan(){}

    public void setActivePokemon(Pokemon activePokemon){}

    public void benchPokemon(Pokemon benchPokemon){}

    public void pickCard(){}

    public void pickCard(int pickedCardPosition){}

    public void useCard(Card card){}

    public void swapPokemon(Pokemon benchedPokemon){}

    public void swapPokemon(Pokemon benchedPokemon, int retreatCost){}

    public void evolvePokemon(Pokemon basePokemon, Pokemon evolvedPokemon){}

    public void attachEnergy(EnergyCard energy, Pokemon pokemon){}

    public void collectPrize(int prizeCardPosition){}

    public void lookatDeck(){}

}
