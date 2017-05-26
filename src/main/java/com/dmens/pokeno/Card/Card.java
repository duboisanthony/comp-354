package com.dmens.pokeno.Card;

public abstract class Card {

	private String mName;
	
	protected Card(String name)
	{
		this.mName = name;
	}
	
	protected String getName()
	{
		return this.mName;
	}
	
	@Override
	public abstract String toString();
}
