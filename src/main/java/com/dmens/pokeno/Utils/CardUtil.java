package com.dmens.pokeno.Utils;

import java.util.Stack;

import com.dmens.pokeno.Ability.Ability;
import com.dmens.pokeno.Ability.AbilityCost;
import com.dmens.pokeno.Card.Card;
import com.dmens.pokeno.Card.CardTypes;
import com.dmens.pokeno.Card.EnergyCard;
import com.dmens.pokeno.Card.EnergyTypes;
import com.dmens.pokeno.Card.Pokemon;
import com.dmens.pokeno.Card.TrainerCard;
import com.dmens.pokeno.database.AbilitiesDatabase;

public class CardUtil {
	
	public static Card getCardFromString(String cardString){
		System.out.println(cardString);
		if(cardString.contains("#"))
			return null;
		Stack<String> cardStack = new Stack<String>();
		String[] parsedString = cardString.replaceAll(":",  ",").split(",");
		for (int i = parsedString.length -1; i >= 0; i--) {
			cardStack.add(parsedString[i]);
		}
		String cardName = cardStack.pop();
		String cardType = cardStack.pop();
		switch(CardTypes.valueOf(cardType.toUpperCase())){
		case POKEMON:
			return getPokemonCard(cardStack,cardName);
		case ENERGY:
			return getEnergyCard(cardStack,cardName);
		case TRAINER:
			return getTrainerCard(cardStack,cardName);
		default:
			return null;
		}
		
	}
	
	private static Card getPokemonCard(Stack<String> cardStack, String name){
		Pokemon p = new Pokemon(name);
		setPokemonCategory(cardStack, p);
		setPokemonType(cardStack, p);
		p.setHP(Integer.parseInt(cardStack.pop()));
		setRetreatCost(cardStack, p);
		setPokemonAttack(cardStack, p);
		return p;
	}
	
	private static void setPokemonCategory(Stack<String> cardStack, Pokemon p){
		cardStack.pop();	// cat
		String category = cardStack.pop();
		p.setCategory(category);
		if(p.isEvolvedCategory())
			p.setBasePokemonName(cardStack.pop());
	}
	
	private static void setPokemonType(Stack<String> cardStack, Pokemon p){
		cardStack.pop();	// cat
		String type = cardStack.pop();
		p.setPokemonType(type);
	}
	
	private static void setRetreatCost(Stack<String> cardStack, Pokemon p){
		cardStack.pop();	// retreat
		cardStack.pop();	// cat
		cardStack.pop();	// colorless
		int cost = Integer.parseInt(cardStack.pop());
		p.setmRetreatCost(cost);
	}
	
	protected static void setPokemonAttack(Stack<String> cardStack, Pokemon p){
		cardStack.pop();	// attacks
		while(!cardStack.isEmpty()){
			AbilityCost abilityCost = new AbilityCost();
			do{
				cardStack.pop();	// cat
				EnergyTypes energyType = EnergyTypes.valueOf(cardStack.pop().toUpperCase());	// colorless
				int energyCost = Integer.parseInt(cardStack.pop());
				abilityCost.addCost(energyType, energyCost);
			}while(!cardStack.peek().matches("\\d+"));
			int abilityIndex = Integer.parseInt(cardStack.pop());
			Ability ability = AbilitiesDatabase.getInstance().query(abilityIndex);
			abilityCost.setAbility(ability);
			p.AddAbilityAndCost(abilityCost);
		}
	}
	
	private static Card getEnergyCard(Stack<String> cardStack, String name){
		cardStack.pop();	// cat
		return new EnergyCard(name, cardStack.pop());	
	}

	private static Card getTrainerCard(Stack<String> cardStack, String name){
		cardStack.pop(); // cat
		String category = cardStack.pop();
		int abilityIndex = Integer.parseInt(cardStack.pop());
		Ability ability = AbilitiesDatabase.getInstance().query(abilityIndex);
		return new TrainerCard(name, category, ability);
	}
}
