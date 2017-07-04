package com.dmens.pokeno.PlayerTest;

import org.junit.Assert;
import org.junit.Test;

import com.dmens.pokeno.database.AbilitiesDatabase;
import com.dmens.pokeno.database.CardsDatabase;
import com.dmens.pokeno.deck.Deck;
import com.dmens.pokeno.player.Player;
import com.dmens.pokeno.utils.DeckCreator;

/**
 * Basic test class for the Player class. 
 * 
 * @author tarikabou-saddik
 */

public class PlayerTest{
	
	private static final String LOCATION_FIRST_DECK = "decks/deck1.txt";
	private static final String LOCATION_SECOND_DECK = "decks/deck2.txt";
	private static final String LOCATION_CARDS = "cards.txt";
	private static final String LOCATION_ABILITIES = "abilities.txt";
	private static Deck testDeck1 = new Deck();
	private static Deck testDeck2 = new Deck();
	
	
	@Test
	public void testSetupRewards()
	{
		
		AbilitiesDatabase.getInstance().initialize(LOCATION_ABILITIES);
		CardsDatabase.getInstance().initialize(LOCATION_CARDS);
		
		//! Initialize test decks. 
		testDeck1 = DeckCreator.Instance().DeckCreation(LOCATION_FIRST_DECK);
		testDeck2 = DeckCreator.Instance().DeckCreation(LOCATION_SECOND_DECK);
		
		
		//! Set up players with their respective decks. 
		Player player1 = new PlayerStubbedDriver(testDeck1);
		Player player2 = new PlayerStubbedDriver(testDeck2);
		
		
		
		//! Set up rewards cards for players
		player1.setUpRewards();
		player2.setUpRewards();
		
		//! Run assertions to test number of rewards cards attributed to each player. 
		Assert.assertTrue(player1.getRewards().size() <= 6 && player1.getRewards().size() > 0);
		Assert.assertTrue(player2.getRewards().size() <= 6 && player2.getRewards().size() > 0);
	}
	
	private class PlayerStubbedDriver extends Player{
		
		public PlayerStubbedDriver(Deck deck){
			super(deck);
		}
		
		@Override
		public void updateBoard() {
			return;
		}
	}
	
}
