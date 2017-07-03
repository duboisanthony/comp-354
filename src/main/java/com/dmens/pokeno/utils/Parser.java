package com.dmens.pokeno.utils;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dmens.pokeno.Ability.Ability;
import com.dmens.pokeno.Ability.AbilityCost;
import com.dmens.pokeno.Card.Card;
import com.dmens.pokeno.Card.EnergyCard;
import com.dmens.pokeno.Card.EnergyTypes;
import com.dmens.pokeno.Card.Pokemon;
import com.dmens.pokeno.Card.TrainerCard;
import com.dmens.pokeno.Deck.Deck;
import com.dmens.pokeno.Effect.ApplyStatus;
import com.dmens.pokeno.Effect.Damage;
import com.dmens.pokeno.Effect.Effect;
import com.dmens.pokeno.Effect.Heal;

/*
 * Parser object
 *
 * @author Jing
 * @author James
 */
public class Parser {

    private static final Logger LOG = LogManager.getLogger(Parser.class);
    
    private static boolean mSupportedPokemonOnly = true;
    private static String[] mSupportedPokemon = {"Electrike", "Froakie", "Electabuzz", "Machop", "Zubat"};
    
    private static boolean mSupportedTrainersOnly = true;
    private static String[] mSupportedTrainers = {""};

	private static Parser instance = null;
	private static final String ENCODING = "UTF-8";
	
	private List<String> mCardList = null;
	private List<String> mAbilitiesList = null;
	
	private Parser() { }
	
	/*
	 * Get the instance (lazy instantiation).
	 * 
	 * @param		The instance of this object.
	 */
	public static Parser Instance( ) {
		if(instance == null)
		{
			instance = new Parser();
		}
		
		return instance;
	}
	
	public List<String> GetCardList() {
		return mCardList;
	}
	
	public List<String> GetAbilitiesList() {
		return mAbilitiesList;
	}
	
	/*
	 * Point to the location of the file and load it.
	 */
	public boolean LoadCards(String cardLocation)
	{
		this.mCardList = FileUtils.getFileContentsAsList(cardLocation);
		
		return true;
	}
	
	/*
	 * Point to the location of the file and load it.
	 */
	public boolean LoadAbilities(String abilitiesLocation)
	{
		this.mAbilitiesList = FileUtils.getFileContentsAsList(abilitiesLocation);
		return true;
	}
	
	/*
	 * Takes a line in abilities file and creates the ability.
	 */
	private Ability CreateAbility(String abilityInformation)
	{			
    	int i = abilityInformation.indexOf(":");	
    	String name = abilityInformation.substring(0,i);
    	
    	// 1) Create the ability, you got its name
    	Ability ability = new Ability(name);
    	
    	// 2) Look at the rest of the string and figure it out!
    	String restStr = abilityInformation.substring(i + 1, abilityInformation.length());
    	
    	int c = restStr.indexOf(",");
    	
    	if(c > -1)
    	{
    		// Split on Comma
    		String[] bigResults = restStr.split(":");
    		
    		for(int k = 0; k < bigResults.length; ++k)
    		{
    			// it should either be an effect or condition
    			
    			ability.addEffect(ParseEfect(restStr));
			}
    		
    	}
    	else
    	{
    		ability.addEffect(ParseEfect(restStr));
    	}
    	
    	return ability;
	}
	
	/*
	 * Parse and Effect - > returns what was created.
	 */
	private Effect ParseEfect(String restStr)
	{
		String[] results = restStr.split(":");
		int c = restStr.indexOf(",");
				
		if(results[0].equals("dam"))
    	{  			
			//System.out.println("DAMAGE effect added");
			return new Damage(results[2], Integer.parseInt(results[3]));
    	}
		else if(results[0].equals("heal") && c == -1)
		{
			//System.out.println("HEAL effect added");
			return new Heal(results[2], Integer.parseInt(results[3]));
		}
		else if(results[0].equals("applystat") && c == -1)
		{
			//System.out.println("APPLYSTATUS effect added");
			return new ApplyStatus(results[2], results[3]);
		}
		
		// fix because it wasnt implemented
		return null;
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
			//System.out.println("Created PokemonCard");
			// split the line with 2 delimiters : and ,
			String[] results = cardContents.split(":|\\,");
			String pokemonName = null;
			String basePokemonName = null;
			int hpIndex = -1;
			int hp = 0;
			ArrayList<String> categories = new ArrayList<String>();
			int retreatIndex = -1;
			int retreatCost = 0;
			int abilitiesIndex = -1;
			ArrayList<Ability> abilities = new ArrayList<Ability>();
			
			// parse pokemon name
			pokemonName = results[0];
			
			if(mSupportedPokemonOnly)
			{
				boolean supported = false;
				
				//check if its supported
				for(int i = 0; i < mSupportedPokemon.length; ++i)
				{
					if(mSupportedPokemon[i].equals(pokemonName))
						supported = true;
				}
				
				if(!supported)
				{
					//System.out.println("Pokemon was not supported... creating a random energy card.");
					c = (Card) this.ReplaceCardWithRandomEnergy();
					//System.out.print(c.toString());
					return c;
				}
					
			}
			
			// parse initial hp, we are assuming the first integer will be the HP value
			for(int i = 1; i < results.length - 1; ++i) {
				try {
					hp = Integer.parseInt(results[i]);
					hpIndex = i;
					break;
				} catch (NumberFormatException e) {}
			}
			
			// parse pokemon categories
			for(int i = 1; i <  hpIndex; ++i) {
				if(results[i].equals("cat")) {
					categories.add(results[i+1]);
					if(results[i+1].equals("stage-one")) {
						basePokemonName = results[i+2];
						++i;
					}
					++i;
				}
			}
			
			for(int i = 0; i < results.length; ++i) {
				if(results[i].equals("retreat")) {
					retreatIndex = i;
					break;
				}
			}
			
			// in case we have a label called "retreat"
			if(retreatIndex != -1) {
				int retreatCostIndex = hpIndex + 4;
				// parse pokemon retreat cost
				try {
					retreatCost = Integer.parseInt(results[retreatCostIndex]);	
				} catch (NumberFormatException e) {}
			} else {
				// in case there is no retreat information due to typo, we will set the cost to 1
				retreatCost = 1;
			}
			
			for(int i = 0; i < results.length; ++i) {
				if(results[i].equals("attacks")) {
					abilitiesIndex = i;
					break;
				}
			}
			
			if(abilitiesIndex != -1) {
				// parse pokemon abilities
																		
				for(int i = abilitiesIndex + 4; i < results.length; i += 4){
					try {						
						int abilityIndex = Integer.parseInt(results[i]);				
						
						abilities.add(CreateAbility(this.mAbilitiesList.get(abilityIndex - 1)));
						
					} catch (NumberFormatException e) {System.out.print("EXXXXXXXXCEPTION");}
				}
			}
			
			Ability ability = abilities.get(0);

            //TODO: Remove hard coded energy cost
			AbilityCost abilityCost = new AbilityCost(ability);
			abilityCost.addCost(EnergyTypes.COLORLESS, 1);

			c = new Pokemon(pokemonName, categories, hp, retreatCost);
			
			((Pokemon)c).AddAbilityAndCost(abilityCost);
			
			
			if(basePokemonName != null) 
				((Pokemon)c).setBasePokemonName(basePokemonName);
			else
				((Pokemon)c).setBasePokemonName(pokemonName);
			
			System.out.println("=====================");
			//System.out.println(c.getName());
			System.out.println(c.toString());
			System.out.println("=====================");
		}
		else if(cardContents.matches("(.*)trainer(.*)"))
		{
			String[] results = cardContents.split(":");
			if(mSupportedTrainersOnly)
			{
				boolean supported = false;
				
				//check if its supported
				for(int i = 0; i < mSupportedTrainers.length; ++i)
				{
					if(mSupportedTrainers[i].equals(results[0]))
						supported = true;
				}
				
				if(!supported)
				{
					//System.out.println("Trainer was not supported... creating a random energy card.");
					c = (Card) this.ReplaceCardWithRandomEnergy();
					//System.out.print(c.toString());
					return c;
					
				}	
			}

			//System.out.println("Created TrainerCard");
			ArrayList<Ability> abilities = new ArrayList<>();
			abilities.add(CreateAbility(this.mAbilitiesList.get(Integer.parseInt(results[4]) - 1)));
			c = new TrainerCard(results[0], results[3], abilities);
			
		}
		else if(cardContents.matches("(.*)energy(.*)"))
		{
			String[] results = cardContents.split(":");
			//System.out.println("Created EnergyCard");
			c = new EnergyCard(results[0], results[3]);	
		}

		//System.out.print(c.toString());
		return c;
	}
	
	/*
	 * For the cards that are not supported, this method will be called to create a
	 * replacement card.
	 * 
	 * @return		A random energy card.
	 */
	private EnergyCard ReplaceCardWithRandomEnergy()
	{
		Random rand = new Random();
		int MIN = 1;
		int MAX = 5;
		
		int num = MIN + rand.nextInt((MAX - MIN) + 1);
		
		switch(num)
		{
			case(1):
				return new EnergyCard("Water", "water");
			case(2):
				return new EnergyCard("Lightning", "lightning");
			case(3):
				return new EnergyCard("Fight", "fight");
			case(4):
				return new EnergyCard("Psychic", "psychic");
			default:
				return new EnergyCard("Colorless", "colorless");
		}
	}
	
	public Deck DeckCreation(String deckLocation)
	{
		assert mCardList != null;
		
		Deck deck = new Deck();
		FileUtils.getFileContentsAsList(deckLocation).forEach(line->{
			Card e = CreateCard(this.mCardList.get(Integer.parseInt(line) - 1));
		    deck.addCard(e);
		});
		
		return deck;
	}
}
