package com.dmens.pokeno.Driver;

import com.dmens.pokeno.Card.*;
import com.dmens.pokeno.Utils.*;
import java.util.*;

public class Driver {

	public static void main(String[] args) {

		System.out.println("Pokeno Parse Testing...");
		
		String directory = System.getProperty("user.dir"); 
		String location1 = directory + "\\data\\deck1.txt";
		String location2 = directory + "\\data\\deck2.txt";
		String locationCards = directory + "\\data\\cards.txt";
		String locationAbilities = directory + "\\data\\abilities.txt";

		Parser.Instance().LoadCards(locationCards);
		Parser.Instance().LoadAbilities(locationAbilities);
		
		ArrayList<Card> deck1 = Parser.Instance().DeckCreation(location1);
		ArrayList<Card> deck2 = Parser.Instance().DeckCreation(location2);

		
	}

}
