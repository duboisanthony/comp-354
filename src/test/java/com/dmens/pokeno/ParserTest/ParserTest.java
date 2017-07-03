package com.dmens.pokeno.ParserTest;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.*;

import com.dmens.pokeno.Deck.Deck;
import com.dmens.pokeno.database.AbilitiesDatabase;
import com.dmens.pokeno.database.CardsDatabase;
import com.dmens.pokeno.utils.Parser;

public class ParserTest {

    private static final Logger LOG = LogManager.getLogger(ParserTest.class);
    
	@BeforeClass
	public static void setUp() {
		AbilitiesDatabase.getInstance().initialize("abilities.txt");
		CardsDatabase.getInstance().initialize("cards.txt");
	}
	
	@Test
	public void testCreateDeck() {
		Deck deck = Parser.Instance().DeckCreation("decks/deck1.txt");
        assertNotNull(deck);
        assertEquals(deck.size(), 60);
	}
}
