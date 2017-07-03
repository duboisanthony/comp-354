package com.dmens.pokeno.DeckTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dmens.pokeno.card.Card;
import com.dmens.pokeno.database.AbilitiesDatabase;
import com.dmens.pokeno.database.CardsDatabase;
import com.dmens.pokeno.deck.CardContainer;
import com.dmens.pokeno.utils.DeckCreator;

public class CardContainerTest {
	private static CardContainer ct;
	
	@BeforeClass
	public static void loadParser(){
		AbilitiesDatabase.getInstance().initialize("abilities.txt");
		CardsDatabase.getInstance().initialize("cards.txt");
	}
	
	@Before
	public void setupCardContainer(){
		ct = DeckCreator.Instance().DeckCreation("decks/deck1.txt");
	}
	
	@Test
	public void testPickCard(){
		Card c = ct.pickCard();
		Assert.assertNotNull("Card should not be null", c);
		Assert.assertEquals("Size of container should be 59, got "+ ct.size(), 59, ct.size());
	}
	
	@Test
	public void testPickCardFromPosition(){
		Card c = ct.pickCardFromPosition(10);
		Assert.assertNotNull("Card should not be null", c);
		Assert.assertEquals("Size of container should be 59, got "+ ct.size(), 59, ct.size());
	}
	
	@Test
	public void testDumpCards(){
		ct.dumpCards();
		Assert.assertEquals("Container should be empty", 0, ct.size());
	}
	
	@Test
	public void testAddCard(){
		Card c = ct.pickCardFromPosition(0);
		ct.addCard(c);
		Assert.assertEquals(c.getName(), ct.viewCardInPosition(ct.size()-1).getName());
	}
	
	@Test
	public void testViewCardInPosition(){
		Card c = ct.viewCardInPosition(0);
		Assert.assertNotNull(c);
		Assert.assertEquals(60, ct.size());
	}

}
