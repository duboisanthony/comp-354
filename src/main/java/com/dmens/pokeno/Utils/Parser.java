package com.dmens.pokeno.Utils;
import com.dmens.pokeno.Ability.Ability;
import com.dmens.pokeno.Card.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parser {

    private static final Logger LOG = LogManager.getLogger(Parser.class);

	private static Parser instance = null;
	private static final String ENCODING = "UTF-8";
	
	private ArrayList<String> mCardList = null;
	private ArrayList<String> mAbilitiesList = null;
	
	private Parser()
	{ }
	
	/*
	 * lazy instantiation
	 */
	public static Parser Instance( )
	{
		if(instance == null)
		{
			instance = new Parser();
		}
		
		return instance;
	}
	
	/*
	 * Get the contents as an ArrayList which will facilitate specific read lines to be accessed.
	 */
	private static ArrayList<String> GetFileContentsAsArrayList(String location) throws FileNotFoundException
	{
		File file = new File(location);
		Scanner scanner = new Scanner(file, ENCODING);
		
		ArrayList<String> contents = new ArrayList<String>();

		// Processing the file line by line
		while(scanner.hasNext())
		{
		    String nextLine = scanner.nextLine();
		    contents.add(nextLine);
		}

		scanner.close();
		return contents;
	}
	
	/*
	 * Get the file contents as a Set, thus ensuring that there are no duplicate cards.
	 */
	private static Set<String> GetFileContentsAsSet(String location) throws FileNotFoundException
	{
		File file = new File(location);
		Scanner scanner = new Scanner(file, ENCODING);
		
		Set<String> contents = new HashSet<String>();

		// Processing the file line by line
		while(scanner.hasNext())
		{    
		    String nextLine = scanner.nextLine();
		    contents.add(nextLine);
		}

		scanner.close();
		return contents;
	}
	
	/*
	 * Point to the location of the file and load it.
	 */
	public void LoadCards(String cardLocation)
	{
		try
		{
			this.mCardList = Parser.GetFileContentsAsArrayList(cardLocation);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * Point to the location of the file and load it.
	 */
	public void LoadAbilities(String abilitiesLocation)
	{
		try
		{
			this.mAbilitiesList = Parser.GetFileContentsAsArrayList(abilitiesLocation);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * Parse the cardContents string which represents a line in cards.txt.
	 * Creates the appropriate card that will then be added to the deck.
	 */
	private Card CreateCard(String cardContents)
	{
		Card c = null;
		
		if(cardContents.matches("(.*):pokemon:(.*)"))
		{
			System.out.println("Created PokemonCard");
			c = new Pokemon("card");

		}
		else if(cardContents.matches("(.*)trainer(.*)"))
		{
			String[] results = cardContents.split(":");
			System.out.println("Created TrainerCard");
			ArrayList<Ability> abilities = new ArrayList<>();
			abilities.add(new Ability(this.mAbilitiesList.get(Integer.parseInt(results[4]) - 1)));
			c = new TrainerCard(results[0], results[3], abilities);

		}
		else if(cardContents.matches("(.*)energy(.*)"))
		{
			String[] results = cardContents.split(":");
			System.out.println("Created EnergyCard");
			c = new EnergyCard(results[0], results[3]);
			
		}

		System.out.print(c.toString());
		return c;
	}
	
	public ArrayList<Card> DeckCreation(String deckLocation)
	{
		// should assert that mCardList is not null
		
		ArrayList<Card> deck = new ArrayList<Card>();
		
		try
		{
			Set<String> deckNumbers = Parser.GetFileContentsAsSet(deckLocation);
			
			for (String line : deckNumbers)
			{
			    Card e = CreateCard(this.mCardList.get(Integer.parseInt(line) - 1));
			    deck.add(e);
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
				
		System.out.println("--------\nDsize: " + deck.size() + "\n--------");
		
		return deck;
				
	}
}
