package com.dmens.pokeno.Card;

public class EnergyCard extends Card {
	
	private String mCategory;

	public EnergyCard(String name, String category)
	{
		super(name);
		this.mCategory = category;
	}

	public String toString()
	{
		return String.format("|ENERGY CARD|\n|%s|\n||%s||\n", this.getName(), this.mCategory);
	}
}
