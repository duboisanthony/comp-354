package com.dmens.pokeno.Deck;

import java.util.ArrayList;

import com.dmens.pokeno.Card.Card;

public class CardContainer {
	protected static ArrayList<Card> cards;
	
	public CardContainer(){
		cards = new ArrayList<Card>();
	}
	
	/**
	 * pick first card
	 * @return
	 */
	public Card pickCard(){
		Card card = cards.get(cards.size()-1);
		cards.remove(cards.size()-1);
		return card;
	}
	
	/**
	 * pick a card from position
	 * @param index
	 * @return
	 */
	public Card pickCardFromPosition(int index){
		Card card = cards.get(index);
		cards.remove(index);
		return card;
	}
	
	/**
	 * Add a card to the top of the container
	 * @param card
	 */
	public void addCard(Card c){
		cards.add(c);
	}
	
	/**
	 * Add multiple cards to container
	 * @param cardsToAdd
	 */
	public void addCards(Card[] cardsToAdd){
		cards.addAll(cards);
	}
	
	public Card[] dumpCards(){
		Card[] dump = new Card[size()];
		cards.toArray(dump);
		cards = new ArrayList<Card>();
		return dump;
	}
	
	public int size(){
		return cards.size();
	}
}
