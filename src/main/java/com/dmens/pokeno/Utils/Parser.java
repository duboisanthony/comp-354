package com.dmens.pokeno.Utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
		
		try (BufferedReader br = new BufferedReader(new FileReader(Thread.currentThread().getContextClassLoader().getResource(deckLocation).getPath()))) {
			String line;
			while ((line = br.readLine()) != null)
				deck.addCard(CardsDatabase.getInstance().query(Integer.parseInt(line)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return deck;
	}
}
