package com.dmens.pokeno.PlayerTest;

import com.dmens.pokeno.Player.*;
import com.dmens.pokeno.Utils.Parser;
import com.dmens.pokeno.Card.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

/**
 * Basic test class for the Player class. 
 * 
 * @author tarikabou-saddik
 */

public class PlayerTest{
	
	private static final String LOCATION_FIRST_DECK = "data/deck1.txt";
	private static final String LOCATION_SECOND_DECK = "data/deck2.txt";
	private static final String LOCATION_CARDS = "data/cards.txt";
	private static final String LOCATION_ABILITIES = "data/abilities.txt";
	private static ArrayList<Card> testDeck1 = new ArrayList<Card>();
	private static ArrayList<Card> testDeck2 = new ArrayList<Card>();	
	
	
	@Test
	public void testSetupRewards()
	{
		
		//! Load cards from text files. 
		boolean result = Parser.Instance().LoadCards(LOCATION_CARDS);
		assert result;
		result = Parser.Instance().LoadAbilities(LOCATION_ABILITIES);
		assert result;
		
		//! Initialize test decks. 
		testDeck1 = Parser.Instance().DeckCreation(LOCATION_FIRST_DECK);
		testDeck2 = Parser.Instance().DeckCreation(LOCATION_SECOND_DECK);
		
		
		//! Set up players with their respective decks. 
		Player player1 = new Player(testDeck1);
		Player player2 = new Player(testDeck2);
		
		Assert.assertFalse(player1.getDeck().equals(player2.getDeck()));
	}
	
}
