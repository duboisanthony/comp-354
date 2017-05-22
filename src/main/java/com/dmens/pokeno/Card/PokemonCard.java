package com.dmens.pokeno.Card;

import java.util.ArrayList;

public class PokemonCard extends Card {

	private ArrayList<String> mCategories;
	private ArrayList<String> mAbilities;
	
	public PokemonCard(String name)
	{
		super(name);
	}
	
	public void AddCategory(String category)
	{
		this.mCategories.add(category);
	}
	
	public void AddAbility(String ability)
	{
		this.mAbilities.add(ability);
	}
	
	public String toString()
	{
		return String.format("|POKEMON CARD|\n|%s|\n", this.getName());
	}
}
