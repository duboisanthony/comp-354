package com.dmens.pokeno.DeckTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dmens.pokeno.Card.Card;
import com.dmens.pokeno.Card.CardTypes;
import com.dmens.pokeno.Deck.Deck;
import com.dmens.pokeno.Utils.Parser;

public class DeckTest {
	private Deck validDeck;
	private Deck invalidDeck;
	
	@BeforeClass
	public static void loadParser(){
		Parser.Instance().LoadCards("data/cards.txt");
		Parser.Instance().LoadAbilities("data/abilities.txt");
	}
	
	@Before
	public void setup(){
		validDeck = Parser.Instance().DeckCreation("data/deck1.txt");
		invalidDeck = Parser.Instance().DeckCreation("data/deck1.txt");
		invalidDeck.draw(10);
	}

	@Test
	public void testDeckIsValid() {
		Assert.assertTrue(validDeck.checkValidity());
		Assert.assertFalse(invalidDeck.checkValidity());
	}
	
	@Test
	public void testDrawCard(){
		Card c = validDeck.draw();
		Assert.assertNotNull("Card should not be null",c);
		Assert.assertEquals(59, validDeck.size());
		//Fix this test when Shauna is implemented
		//Assert.assertTrue("Card Drawn should be Shauna. Got "+c.getName(), c.getName().equals("Shauna"));
		Assert.assertTrue("First Card implemented should an energy",c.isType(CardTypes.ENERGY));
	}
	
	@Test
	public void testShuffle(){
		Card c  = validDeck.viewCardInPosition(6);
		System.out.println(validDeck);
		Assert.assertEquals("Card should be Machop",  "Machop", c.getName());
		validDeck.shuffle();
		c = validDeck.viewCardInPosition(6);
		System.out.println(validDeck);
		Assert.assertNotEquals("Card should not be Machop anymore", "Machop", c.getName());
	}

}
