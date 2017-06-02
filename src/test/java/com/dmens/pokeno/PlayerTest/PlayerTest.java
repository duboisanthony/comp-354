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

public class PlayerTest {
	
	private static final String LOCATION_FIRST_DECK = "data/deck1.txt";
	testDeck = Parser.Instance().DeckCreation(LOCATION_FIRST_DECK);
	
	@Test
	public void testCardShuffle() {
		
		Player testPlayer = new Player();
		
	}
	

}
