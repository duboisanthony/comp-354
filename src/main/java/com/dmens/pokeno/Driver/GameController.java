package com.dmens.pokeno.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JOptionPane;

import com.dmens.pokeno.Board.GameBoard;
import com.dmens.pokeno.Card.Card;
import com.dmens.pokeno.Card.EnergyTypes;
import com.dmens.pokeno.Card.Pokemon;
import com.dmens.pokeno.Deck.Deck;
import com.dmens.pokeno.Deck.Hand;
import com.dmens.pokeno.Player.AIPlayer;
import com.dmens.pokeno.Player.Player;
import com.dmens.pokeno.Utils.Parser;
import java.util.Collections;

public class GameController {

	private static final String LOCATION_FIRST_DECK = "data/deck1.txt";
	private static final String LOCATION_SECOND_DECK = "data/deck2.txt";
	private static final String LOCATION_CARDS = "data/cards.txt";
	private static final String LOCATION_ABILITIES = "data/abilities.txt";
	
	private static ArrayList<Player> mPlayers = null;
	
	private static boolean mGameOver = false;
	private static boolean mIsHomePlayerPlaying = false;
	
    public static GameBoard board;
        
	public static void main(String[] args) {
		Deck mFirstDeck = null;
		Deck mSecondDeck = null;
		// Parse Cards
		System.out.println("Parsing cards and abilities...");
		boolean result = Parser.Instance().LoadCards(LOCATION_CARDS);
		assert result;
		result = Parser.Instance().LoadAbilities(LOCATION_ABILITIES);
		assert result;
		
		// Deck creation and validation
		System.out.println("Creating decks and validating them...");
		mFirstDeck = Parser.Instance().DeckCreation(LOCATION_FIRST_DECK);
		if(!mFirstDeck.checkValidity()) {
			System.out.println("First Deck is invalid");
			// TODO: how do we handle invalid deck?
		}
		mSecondDeck = Parser.Instance().DeckCreation(LOCATION_SECOND_DECK);
		if(!mSecondDeck.checkValidity()) {
			System.out.println("Second Deck is invalid");
			// TODO: how do we handle invalid deck?
		}
		// Create Players and assign decks
		// TODO: we need to allow player to choose his deck or randomly assign decks
		System.out.println("Creating players and assigning decks...");
		Player homePlayer = new Player(mFirstDeck);
		Player adversaryPlayer = new AIPlayer(mSecondDeck);
		homePlayer.setOpponent(adversaryPlayer);
		adversaryPlayer.setOpponent(homePlayer);
		
		mPlayers = new ArrayList<Player>();
		mPlayers.add(homePlayer);
		mPlayers.add(adversaryPlayer);
        
        board = new GameBoard();
        board.setVisible(true);
        AtomicReference<Boolean> readyToStart = new AtomicReference<>(true);
        do
        {
        	// Draw Cards and check for mulligans
        	System.out.println(mPlayers.get(0).getDeck().size());
        	mPlayers.stream()
        	.filter(player->!player.getIsReadyToStart())
        	.forEach(player-> {
        		board.updateHand(player.drawCardsFromDeck(6), player.isHumanPlayer());
        		player.checkIfPlayerReady();
        	});
            // Execute mulligans
            mPlayers.stream()
            .filter(player->player.isInMulliganState())
            .forEach(player->{
            	player.mulligan();
            	readyToStart.set(player.getIsReadyToStart());;
            });
            
        // Repeat until no more mulligans
		}while(!readyToStart.get());
        
        mPlayers.forEach(currentPlayer->{ currentPlayer.setUpRewards(); });
        
        AIPlayer opp = (AIPlayer)mPlayers.get(1);
        opp.selectStarterPokemon();
		
		System.out.println("\nTest Run: PokemonNoGo");
		System.out.println("Print out the Hand of the First Player...");
		for(int i = 0; i < mPlayers.get(0).getHand().size(); ++i){
			System.out.println(mPlayers.get(0).getHand().getCards().get(i));
		}
			
		
		
		//Clean up	
	}
	
	public static void useCardForPlayer(Card card, int player){
		mPlayers.get(player).useCard(card);
	}
	
	public static boolean useActivePokemonForPlayer(int player, int ability){
		return mPlayers.get(player).useActivePokemon(ability);
	}
	
	public static void startAITurn(){
		mPlayers.get(1).startTurn();
	}
	
	public static void displayMessage(String msg){
		JOptionPane.showMessageDialog(null, msg);
	}
	
	public static Player getHomePlayer() {
		return mPlayers.get(0);
	}
	
	public static Player getAIPlayer() {
		return mPlayers.get(1);
	}
	
	public static boolean getIsHomePlayerPlaying() {
		return mIsHomePlayerPlaying;
	}
	
	public static void setIsHomePlayerPlaying(boolean isPlaying) {
		mIsHomePlayerPlaying = isPlaying;
	}
	
	public static void checkGameStatus(){
		
	}
	
	public static void updateHand(Hand hand, boolean player){
		board.updateHand(hand, player);
	}
        
    public static void updateDeck(int deckSize, boolean player){
        board.updateDeckSize(deckSize, player);
    }
    
    public static void updateGraveyard(int graveyard, boolean player){
    	board.updateGraveyard(graveyard, player);
    }
    
    public static void updateRewards(int rewardsSize, boolean player){
        board.setRewardCount(rewardsSize, player);
    }
    
    public static void cleanActivePokemon(boolean player){
    	board.clearActivePokemon(player);
    }
    
    public static void updateEnergyCountersForCard(Card card, int player){
        mPlayers.get(player).updateEnergyCounters((Pokemon) card, true);
    }
    
    public static void updateEnergyCounters(Map<EnergyTypes, Integer> energies, boolean player){
        board.setEnergy(getAttachedEnergyList(energies), player);
    }
    
    public static void updateEnergyCountersPreview(Map<EnergyTypes, Integer> energies, boolean player){
        board.setEnergyPreview(getAttachedEnergyList(energies));
    }
    
    public static ArrayList<Integer> getAttachedEnergyList(Map<EnergyTypes, Integer> energies){
        ArrayList<Integer> energyList = new ArrayList<Integer>(5);
        energyList.add(0);energyList.add(0);energyList.add(0);energyList.add(0);energyList.add(0);
    	energies.forEach((energyType, amount) -> {
    		switch(energyType){
    		case FIGHT:
                    energyList.set(0, amount);
                    break;
    		case LIGHTNING:
                    energyList.set(1, amount);
                    break;
    		case PSYCHIC:
                    energyList.set(2, amount);
                    break;
    		case WATER:
                    energyList.set(3, amount);
                    break;
    		case COLORLESS:
                    energyList.set(4, amount);
                    break;
    		case FIRE:
                    break;
    		case GRASS:
                    break;
    		}
    	});
        return energyList;
    }
        
}
