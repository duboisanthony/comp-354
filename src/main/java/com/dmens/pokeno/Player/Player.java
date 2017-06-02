package com.dmens.pokeno.Player;

import com.dmens.pokeno.Card.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Devin on 2017-05-26.
 */
public class Player {

    private static final Logger LOG = LogManager.getLogger(Player.class);
	private static final int NUM_OF_REWARD_CARDS = 6;

    private Pokemon mActivePokemon = null;
    private ArrayList<Pokemon> mBenchedPokemon = null;
    private ArrayList<Card> mHand = null;
    private ArrayList<Card> mDeck = null;
    private ArrayList<Card> mRewards = null;
    private ArrayList<Card> mDiscards = null;

    public Player() {
    }
    
    public Player(ArrayList<Card> deckList) {
    	mDeck = deckList;
    	mBenchedPokemon = new ArrayList<Pokemon>();
    	mHand = new ArrayList<Card>();
    	mRewards = new ArrayList<Card>();
    	mDiscards = new ArrayList<Card>();
    }

    public Pokemon getActivePokemon() {
        return mActivePokemon;
    }

    public ArrayList<Pokemon> getBenchedPokemon() {
        return mBenchedPokemon;
    }

    public ArrayList<Card> getHand() {
        return mHand;
    }

    public ArrayList<Card> getDeck() {
        return mDeck;
    }

    public ArrayList<Card> getRewards() {
        return mRewards;
    }

    public ArrayList<Card> getDiscards() {
        return mDiscards;
    }
    
    public void shuffleDeck(){
        Collections.shuffle(this.mDeck);
    }
    
    //NOTE: Size of mHand should be at most 7. 
    public void drawCardsFromDeck(int numOfCards) {
    	assert numOfCards >= 0;
    	assert mDeck.size() >= numOfCards;
    	
    	// note that we are drawing from the end of the deck list for better performance
    	for(int i = 0; i < numOfCards; ++i) {
    		Card card = mDeck.get(mDeck.size() - 1);
        	mDeck.remove(card);
        	mHand.add(card);
    	}
    }
    
    // allows player to pick a specific card from hand and put it back to the deck
    public void putHandBackToDeck(int index) {
    	assert (mHand != null && mHand.size() > 0);
    	
    	Card card = mHand.get(index);
    	mHand.remove(card);
    	mDeck.add(card);
    }
    // puts all cards from hand back to the deck
    public void putHandBackToDeck() {
    	assert (mHand != null && mHand.size() > 0);
    	for(int i = mHand.size() - 1; i >= 0; --i) {
        	Card card = mHand.get(i);
        	mHand.remove(card);
        	mDeck.add(card);
    	}
    }
    
    public void setUpRewards() {
    	mRewards = new ArrayList<Card>();
    	for(int i = 0; i < NUM_OF_REWARD_CARDS; ++i) {
    		Card card = mDeck.get(mDeck.size() - 1);
        	mDeck.remove(card);
        	mRewards.add(card);
    	}
    }
    
    public void Mulligan(){}
   
    /**
     * Sets a selected Pokemon to be the
     * player's active Pokemon. 
     * 
     * @param activePokemon
     */
    public void setActivePokemon(Pokemon activePokemon){
    	mActivePokemon = activePokemon; 
    }
    
    /**
     * Sends a Pokemon to player's bench. Condition
     * verifies if player's bench has already reached
     * max capacity or not. 
     * 
     * @param benchPokemon
     */
    public void benchPokemon(Pokemon benchPokemon){
    	assert(mBenchedPokemon.size() < 5);
    	mBenchedPokemon.add(benchPokemon);
    }
;
    //TODO
    public void pickCard(){}
    
    /**
     * Allows player to pick a card from his/her 
     * hand. (Will allow us to display the card,
     * thus allowing the player to decide whether 
     * to do anything with it). 
     * 
     * @param pickedCardPosition
     */
    public void pickCard(int pickedCardPosition){
    	assert(mHand !=null && mHand.size() > 0); 
    }

    public void useCard(Card card){}

    public void swapPokemon(Pokemon benchedPokemon){}

    public void swapPokemon(Pokemon benchedPokemon, int retreatCost){}

    public void evolvePokemon(Pokemon basePokemon, Pokemon evolvedPokemon){}

    public void attachEnergy(EnergyCard energy, Pokemon pokemon){}
    
    /**
     * Allows player to select a prize card from deck. 
     * TODO: Function should perhaps signal end of match
     * if size of mRewards is 0 (i.e. player wins match). 
     * 
     * @param prizeCardPosition
     */
    public void collectPrize(int prizeCardPosition){
    	assert(mRewards !=null);
    	Card prizeCard = mRewards.get(prizeCardPosition);
    	mHand.add(prizeCard);
    	mRewards.remove(prizeCard);
    }

    public void lookatDeck(){}

}
