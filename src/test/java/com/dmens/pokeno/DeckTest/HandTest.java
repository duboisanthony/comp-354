package com.dmens.pokeno.DeckTest;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dmens.pokeno.Deck.Deck;
import com.dmens.pokeno.Deck.Hand;
import com.dmens.pokeno.Utils.Parser;

import org.junit.Assert;

public class HandTest {
	private Hand hand;
	private Deck deck;
	
	@BeforeClass
	public static void loadParser(){
		Parser.Instance().LoadCards("data/cards.txt");
		Parser.Instance().LoadAbilities("data/abilities.txt");
	}
	
	@Before
	public void setupCardContainer(){
		deck = Parser.Instance().DeckCreation("data/deck1.txt");
		hand = new Hand();
	}
	
	@Test
	public void testDrawCardsToHand(){
		hand.addCards(deck.draw(7));
		Assert.assertEquals(7, hand.size());
		Assert.assertEquals(53, deck.size());
	}

}
