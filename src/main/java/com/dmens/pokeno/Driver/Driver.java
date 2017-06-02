package com.dmens.pokeno.Driver;

import com.dmens.pokeno.Ability.Ability;
import com.dmens.pokeno.Board.GameBoard;
import com.dmens.pokeno.Card.*;
import com.dmens.pokeno.Utils.*;
import com.dmens.pokeno.Player.*;

import java.util.*;
import javax.swing.JOptionPane;

public class Driver {

	private static final String LOCATION_FIRST_DECK = "data/deck1.txt";
	private static final String LOCATION_SECOND_DECK = "data/deck2.txt";
	private static final String LOCATION_CARDS = "data/cards.txt";
	private static final String LOCATION_ABILITIES = "data/abilities.txt";

	private static ArrayList<Card> mFirstDeck = null;
	private static ArrayList<Card> mSecondDeck = null;
	
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
		if(!IsDeckValid(mFirstDeck)) {
			System.out.println("First Deck is invalid");
			// TODO: how do we handle invalid deck?
		}
		mSecondDeck = Parser.Instance().DeckCreation(LOCATION_SECOND_DECK);
		if(!IsDeckValid(mSecondDeck)) {
			System.out.println("Second Deck is invalid");
			// TODO: how do we handle invalid deck?
		}
		
		// Create Players and assign decks
		// TODO: we need to allow player to choose his deck or randomly assign decks
		System.out.println("Creating players and assigning decks...");
		mPlayers = new ArrayList<Player>();
		mPlayers.add(new Player(mFirstDeck));
		mPlayers.add(new AIPlayer(mSecondDeck));
		
                boolean playersReady = false;
                ArrayList<Player> playersDeclaringMulligan = new ArrayList<Player>();
                
                board = new GameBoard();
                board.setVisible(true);
                
                // Set up player's hand and rewards
                while(!playersReady)
                {
                    for(int i = 0; i < mPlayers.size(); ++i) {
                            Player currentPlayer = mPlayers.get(i);
                            if(!currentPlayer.getIsReadyToStart())
                            {
                                currentPlayer.drawCardsFromDeck(6);
                                if(!currentPlayer.hasBasicPokemon())
                                {
                                    JOptionPane.showMessageDialog(null, "Player " + i + " has declared a Mulligan");
                                    playersDeclaringMulligan.add(currentPlayer);
                                }
                            }
                    }
                    if(playersDeclaringMulligan.size() > 0)
                    {
                        boolean offerDrawCard = playersDeclaringMulligan.size() != mPlayers.size();
                        
                        for(int i = 0; i < mPlayers.size(); i++)
                        {
                            Player currentPlayer = mPlayers.get(i);
                            if(playersDeclaringMulligan.contains(currentPlayer))
                            {
                                currentPlayer.putHandBackToDeck();
                                currentPlayer.shuffleDeck();
                            }
                            else if(offerDrawCard)
                            {
                                // CG - should we actually ask, or just assume they will always take...?
                                // CG - we would have to add a prompt in the UI then...
                                JOptionPane.showMessageDialog(null, "Player " + i + " receives an extra card.");
                                currentPlayer.drawCardsFromDeck(1);
                            }
                        }
                        // reset to check again
                        playersDeclaringMulligan.clear();
                    }
                    else
                    {
                        playersReady = true;

			//TODO: do we draw cards first or set up the rewards deck first?
			for(Player currentPlayer : mPlayers)
				currentPlayer.setUpRewards();
                    }
		}
		
                mPlayers.get(0).setOpponent(mPlayers.get(1));
                mPlayers.get(1).setOpponent(mPlayers.get(0));
                AIPlayer opp = (AIPlayer)mPlayers.get(1);
                opp.startPhase();
                
                //mPlayers.get(0);
                
                //Uncomment to see example board
                //GameBoard board = new GameBoard();
                //board.setVisible(true);
                
//                EnergyCard electric = new EnergyCard("ElecEnergy", "Electric");
//                
//                ArrayList<String> categories = new ArrayList<String>();
//                categories.add("Normal");
//                categories.add("Electric");
//                
//                ArrayList<Ability> abilities = new ArrayList<Ability>();
//                Ability tackle = new Ability("Tackle\nDeals 10 damage");
//                abilities.add(tackle);
//                
//                Pokemon pikachu = new Pokemon("Pikachu", categories, 50, 2, abilities);
//                
//                board.updateBoard(pikachu, pikachu, 6, 4, 5, "Player play cards");
//                
//                board.addCardToHand(electric, true);
//                board.addCardToHand(pikachu, true);
//                
//                board.addCardToBench(pikachu, false);
//                board.update();
                
                
                
		// Main game loop
		/*
 		while(!mGameOver) {
			// TODO: each player plays his cards
			// TODO: decide whether there is a winner
		}
		*/
		
		System.out.println("\nTest Run: PokemonNoGo");
		System.out.println("Print out the Hand of the First Player...");
		for(int i = 0; i < mPlayers.get(0).getHand().size(); ++i){
			System.out.println(mPlayers.get(0).getHand().get(i));
		}
			
		
		
		//Clean up	
	}
	
	// Deck validation
	public static boolean IsDeckValid(ArrayList<Card> deck) {
			
		if(deck == null)
			return false;
		// deck should have exactly 60 cards
		if(deck.size() != 60)
			return false;
		// deck should have at least one pokemon card
		if(!deckHasPokemonCard(deck)) 
			return false;
		if(deckHasMoreThanFourNoneEnergyCard(deck))
			return false;
		if(!allCardsArePlayableInDeck(deck))
			return false;
		return true;
	}
	
	private static boolean deckHasPokemonCard(ArrayList<Card> deck) {
		assert deck != null;
		if(deck.size() == 0) 
			return false;
		for(int i = 0; i < deck.size(); ++i) {
			Card card = deck.get(i);
			if(card instanceof Pokemon) {
				return true;
			}
		}
			
		return false;
	}
	
	private static boolean deckHasMoreThanFourNoneEnergyCard(ArrayList<Card> deck) {
     
		// TODO
		return false;
	}
	
	private static boolean allCardsArePlayableInDeck(ArrayList<Card> deck) {
		
		// TODO
		return true;
	}
}
