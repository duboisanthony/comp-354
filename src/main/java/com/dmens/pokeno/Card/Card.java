package com.dmens.pokeno.Card;

public abstract class Card {

	private String mName;

	public Card(){}
	protected Card(String name)
	{
		this.mName = name;
	}
	
	public String getName()
	{
		return this.mName;
	}
	
	public abstract boolean isType(CardTypes c);
	
	public abstract CardTypes getType();
	
	@Override
	public abstract String toString();
}
