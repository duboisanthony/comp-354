package com.dmens.pokeno.Player;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dmens.pokeno.Card.Card;
import com.dmens.pokeno.Card.EnergyCard;
import com.dmens.pokeno.Card.Pokemon;
import com.dmens.pokeno.Deck.CardContainer;
import com.dmens.pokeno.Deck.Deck;
import com.dmens.pokeno.Deck.Hand;
import com.dmens.pokeno.Driver.Driver;

/**
 * Created by Devin on 2017-05-26.
 */
public class Player {

    private static final Logger LOG = LogManager.getLogger(Player.class);
	private static final int NUM_OF_REWARD_CARDS = 6;

    private Pokemon mActivePokemon = null;
    private ArrayList<Pokemon> mBenchedPokemon = null;
    private Hand mHand = null;
    private Deck mDeck = null;
    private CardContainer mRewards = null;
    private CardContainer mDiscards = null;
    
    private Player opponent;
    private boolean mIsReadyToStart = false;
    private boolean mIsInMulliganState = false;
    
    protected boolean humanPlayer;

    public Player() {
        humanPlayer = true;
    }
    
    public Player(Deck deckList) {
    	mDeck = deckList;
    	System.out.println(mDeck.size());
    	mBenchedPokemon = new ArrayList<Pokemon>();
    	mHand = new Hand();
    	mRewards = new CardContainer();
    	mDiscards = new CardContainer();
        humanPlayer = true;
    }

    public Pokemon getActivePokemon() {
        return mActivePokemon;
    }

    public ArrayList<Pokemon> getBenchedPokemon() {
        return mBenchedPokemon;
    }

    public Hand getHand() {
        return mHand;
    }

    public Deck getDeck() {
        return mDeck;
    }

    public CardContainer getRewards() {
        return mRewards;
    }

    public CardContainer getDiscards() {
        return mDiscards;
    }
    
    public void shuffleDeck(){
       this.mDeck.shuffle();
    }
    
    //NOTE: Size of mHand should be at most 7. 
    public Hand drawCardsFromDeck(int numOfCards) {
    	assert numOfCards >= 0;
    	assert mDeck.size() >= numOfCards;
    	
		mHand.addCards(mDeck.draw(numOfCards));
        return mHand;
    }
    
    public void startTurn()
    {
        drawCardsFromDeck(1);
        if (this instanceof AIPlayer)
        {
            AIPlayer ai = (AIPlayer)this;
            ai.startPhase();
            opponent.startTurn();
        }
    }
    
    // allows player to pick a specific card from hand and put it back to the deck
    public void putHandBackToDeck(int index) {
    	assert (mHand != null && mHand.size() > 0);
    	
    	mDeck.addCard(mHand.pickCardFromPosition(index));
    }
    // puts all cards from hand back to the deck
    public void putHandBackToDeck() {
    	assert (mHand != null && mHand.size() > 0);
    	mDeck.addCards(mHand.dumpCards());
    }
    
    public void setUpRewards() {
    	mRewards.addCards(mDeck.draw(NUM_OF_REWARD_CARDS));
    }

    public void setOpponent(Player enemy)
    {
        opponent = enemy;
    }

   
    /**
     * Sets a selected Pokemon to be the
     * player's active Pokemon. 
     * 
     * @param activePokemon
     */
    public void setActivePokemon(Pokemon activePokemon){
    	mActivePokemon = activePokemon;
        //if (humanPlayer)
        Driver.board.setActivePokemon(activePokemon, humanPlayer);
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
        //if (humanPlayer)
        Driver.board.addCardToBench(benchPokemon, humanPlayer);
    }
    
    /**
     * Allows player to pick a card from his/her 
     * hand. (Will allow us to display the card,
     * thus allowing the player to decide whether 
     * to do anything with it). 
     * 
     * @param pickedCardPosition
     * @return Card that the player has chosen. 
     */
    public Card pickCard(int pickedCardPosition){
    	assert(mHand !=null && mHand.size() > 0);
    	Card pickedCard = mHand.pickCardFromPosition(pickedCardPosition);
    	return pickedCard;
    }
    
    public void useActivePokemon(int ability)
    {
        mActivePokemon.useAbility(ability, opponent.getActivePokemon());
        Driver.board.updateActivePokemon(opponent);
        
        if (opponent.getActivePokemon().getDamage() >= opponent.getActivePokemon().getHP())
        {
            opponent.setActivePokemon(null);
            if (humanPlayer)
            {   
                AIPlayer ai = (AIPlayer)opponent;
                ai.activeFainted();
                Driver.board.OpponentBenchPanel.remove(Driver.board.OpponentBenchPanel.getComponent(0));
            }
            collectPrize(mRewards.size()-1);
        }
    }
    
    private void declareMulligan(){
    	mIsInMulliganState = true;
    	Driver.displayMessage(((humanPlayer) ? "Human " : "AI ") + "Player has declared a Mulligan");
    }
    
    public boolean isInMulliganState(){
    	return mIsInMulliganState;
    }
    
    public void mulligan(){
    	if(mHand.hasBasicPokemon()){
    		mIsReadyToStart = true;
    		mIsInMulliganState = false;
    	} else{
    		mIsReadyToStart = false;
    		this.putHandBackToDeck();
        	this.shuffleDeck();
        	opponent.notifyMulligan();
    	}
    }
    
    private void notifyMulligan(){
    	 int reply = JOptionPane.showConfirmDialog(null, "Would you like to draw a card?", "Mulligan", JOptionPane.YES_NO_OPTION);
         if (reply == JOptionPane.YES_OPTION){
          	this.drawCardsFromDeck(1);
          	Driver.displayMessage("Player received an extra card.");
         }
    }

    // for checking if player should declare a mulligan on their starting hand
    public void checkIfPlayerReady(){
    	if(mHand.hasBasicPokemon()){
    		mIsReadyToStart = true;
    	} else{
    		mIsReadyToStart = false;
    		declareMulligan();
    	}
    }
    
    public boolean hasBasicPokemon(){
    	return this.mHand.hasBasicPokemon();
    }
    
    public boolean getIsReadyToStart(){return mIsReadyToStart;}

    public void pickCard(){}

    public void useCard(Card card)
    {
        if (card instanceof Pokemon)
        {
            if(mActivePokemon == null)
            	setActivePokemon((Pokemon)card);
            else
                benchPokemon((Pokemon)card);
        }
        if (card instanceof EnergyCard)
            mActivePokemon.addEnergy((EnergyCard) card);
        mHand.getCards().remove(card);
    }

    /**
     * Allows player to retreat active Pokemon
     * and to swap it with a benched Pokemon. 
     * @param benchedPokemon
     */
    public void swapPokemon(Pokemon benchedPokemon){
    	int numEnergyCards = mActivePokemon.getAttachedEnergy().size();
    	if(numEnergyCards >= mActivePokemon.getRetreatCost()){
    		
    		// Pokemon cannot retreat if affected by sleep or paralysis. 
    		if(!mActivePokemon.isSleep() || !mActivePokemon.isParalyzed()){
    			mBenchedPokemon.add(mActivePokemon);
    			this.setActivePokemon(benchedPokemon);
    		}
    	}
    	
    	//TODO: Handle event where number of energy cards isn't sufficient. 
    	// e.g. Pop-up on GUI. 
    		
    }
    
    public void evolvePokemon(Pokemon basePokemon, Pokemon evolvedPokemon){
    	
    }
    
    /**
     * Allows player to attach an Energy card onto
     * a Pokemon. (Can only be done once per turn, per 
     * Pokemon). 
     * 
     * @param energy
     * @param pokemon
     */
    public void attachEnergy(EnergyCard energy, Pokemon pokemon){
    	pokemon.addEnergy(energy);
    }
    
    /**.
     * Allows player to select a prize card from deck. 
     * TODO: Function should perhaps signal end of match
     * if size of mRewards is 0 (i.e. player wins match). 
     * 
     * @param prizeCardPosition
     */
    public void collectPrize(int prizeCardPosition){
    	assert(mRewards !=null);
    	Card card = mRewards.pickCardFromPosition(prizeCardPosition);
    	mHand.addCard(card);
        if (humanPlayer && mIsReadyToStart)
            Driver.board.addCardToHand(card, humanPlayer);
        Driver.board.setRewardCount(mRewards.size(), humanPlayer);
        if (mRewards.size() <= 0)
        {
            Driver.board.AnnouncementBox.setText("No more reward cards! The player wins!");
        }
    }

    //TODO
    public void lookatDeck(){}

}
