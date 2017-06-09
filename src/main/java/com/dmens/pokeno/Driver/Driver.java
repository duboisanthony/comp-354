package com.dmens.pokeno.Driver;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.dmens.pokeno.Board.GameBoard;
import com.dmens.pokeno.Card.Card;
import com.dmens.pokeno.Card.Pokemon;
import com.dmens.pokeno.Deck.Deck;
import com.dmens.pokeno.Player.AIPlayer;
import com.dmens.pokeno.Player.Player;
import com.dmens.pokeno.Utils.Parser;

public class Driver {

	private static final String LOCATION_FIRST_DECK = "data/deck1.txt";
	private static final String LOCATION_SECOND_DECK = "data/deck2.txt";
	private static final String LOCATION_CARDS = "data/cards.txt";
	private static final String LOCATION_ABILITIES = "data/abilities.txt";

	private static Deck mFirstDeck = null;
	private static Deck mSecondDeck = null;
	
	public static ArrayList<Player> mPlayers = null;
	
	private static boolean mGameOver = false;
        
    public static GameBoard board;
        
	public static void main(String[] args) {
		
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
		Player adversaryPlayer = new Player(mSecondDeck);
		homePlayer.setOpponent(adversaryPlayer);
		adversaryPlayer.setOpponent(homePlayer);
		
		mPlayers = new ArrayList<Player>();
		mPlayers.add(homePlayer);
		mPlayers.add(adversaryPlayer);
        
        board = new GameBoard();
        board.setVisible(true);
        
        boolean readyToStart = true;
        do
        {
        	// Draw Cards and check for mulligans
        	mPlayers.stream()
        	.filter(player->player.getIsReadyToStart())
        	.forEach(player-> {
        		board.displayHand(player.drawCardsFromDeck(6));
        		player.checkIfPlayerReady();
        	});
            // Execute mulligans
            mPlayers.stream()
            .filter(player->player.isInMulliganState())
            .forEach(player->{
            	player.mulligan();
            	readyToStart = false;
            });
            
        // Repeat until no more mulligans
		}while(!readyToStart);
        
        mPlayers.forEach(currentPlayer->{ currentPlayer.setUpRewards(); });
        
        AIPlayer opp = (AIPlayer)mPlayers.get(1);
        opp.startPhase();
		
		System.out.println("\nTest Run: PokemonNoGo");
		System.out.println("Print out the Hand of the First Player...");
		for(int i = 0; i < mPlayers.get(0).getHand().size(); ++i){
			System.out.println(mPlayers.get(0).getHand().get(i));
		}
			
		
		
		//Clean up	
	}
	
	public static void displayMessage(String msg){
		JOptionPane.showMessageDialog(null, msg);
	}
}
