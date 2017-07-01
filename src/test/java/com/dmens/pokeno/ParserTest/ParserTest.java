package com.dmens.pokeno.ParserTest;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.*;

import com.dmens.pokeno.Deck.Deck;
import com.dmens.pokeno.Utils.Parser;
import com.dmens.pokeno.database.AbilitiesDatabase;
import com.dmens.pokeno.database.CardsDatabase;

public class ParserTest {

    private static final Logger LOG = LogManager.getLogger(ParserTest.class);
    
	@BeforeClass
	public static void setUp() {
		AbilitiesDatabase.getInstance().initialize("data/abilities.txt");
		CardsDatabase.getInstance().initialize("data/cards.txt");
	}
	
	@Test
	public void testCreateDeck() {
		Deck deck = Parser.Instance().DeckCreation("data/validDeck.txt");
        assertNotNull(deck);
        assertEquals(deck.size(), 3);
	}
}
