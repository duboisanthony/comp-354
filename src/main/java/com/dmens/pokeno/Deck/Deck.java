package com.dmens.pokeno.Deck;

import java.util.ArrayList;
import java.util.Collections;

import com.dmens.pokeno.Card.Card;
import com.dmens.pokeno.Card.Pokemon;

public class Deck extends CardContainer {
	
	public Deck(){
		super();
	}
	
	/**
	 * Draw a number n of cards from deck
	 * @param n
	 * @return array of cards
	 */
	public ArrayList<Card> draw(int n){
		ArrayList<Card> drawnCards = new ArrayList<Card>(n);
		for(int i = 0; i < n; i++){
			drawnCards.add(super.pickCard());
		}
		return drawnCards;
	}
	/**
	 * Draw exactly 1 card
	 * @return card
	 */
	public Card draw(){
		return super.pickCard();
	}
	
	public boolean checkValidity(){
		// deck should have exactly 60 cards
		if(size() != 60)
			return false;
		// deck should have at least one pokemon card
		if(!deckHasPokemonCard()) 
			return false;
		if(deckHasMoreThanFourNoneEnergyCard())
			return false;
		if(!allCardsArePlayableInDeck())
			return false;
		return true;
	}
	
	private boolean deckHasPokemonCard() {
		for(int i = 0; i < size(); ++i) {
			Card card = cards.get(i);
			if(card instanceof Pokemon)
				return true;
		}
		return false;
	}
	
	private boolean deckHasMoreThanFourNoneEnergyCard() {
	     
		// TODO
		return false;
	}
	
	private boolean allCardsArePlayableInDeck() {
		
		// TODO
		return true;
	}
	
	public void shuffle(){
		Collections.shuffle(cards);
	}
}
