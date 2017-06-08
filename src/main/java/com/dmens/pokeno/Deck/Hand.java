package com.dmens.pokeno.Deck;

import com.dmens.pokeno.Card.Card;
import com.dmens.pokeno.Card.CardTypes;
import com.dmens.pokeno.Card.Pokemon;
import com.dmens.pokeno.Driver.Driver;

public class Hand extends CardContainer {
	
	public boolean hasBasicPokemon(){
		for(Card card : cards)
            if(card.isType(CardTypes.POKEMON) && ((Pokemon)card).getBasePokemonName().equals(card.getName()))
                return true;
		return false;
	}
}
