package com.dmens.pokeno.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dmens.pokeno.Deck.Deck;
import com.dmens.pokeno.database.CardsDatabase;

/*
 * Parser object
 *
 * @author Jing
 * @author James
 */
public class Parser {
    private static final Logger LOG = LogManager.getLogger(Parser.class);
	private static Parser instance = null;
	
	private Parser() { }
	
	/*
	 * Get the instance (lazy instantiation).
	 * 
	 * @param		The instance of this object.
	 */
	public static Parser Instance( ) {
		if(instance == null)
			instance = new Parser();
		return instance;
	}
	
	public Deck DeckCreation(String deckLocation){		
		Deck deck = new Deck();
		FileUtils.getFileContentsAsList(deckLocation).forEach(line ->{
			deck.addCard(CardsDatabase.getInstance().query(Integer.parseInt(line)));
		});
		return deck;
	}
}
