package com.dmens.pokeno.Card;

public class TrainerCard extends Card {

	private String mCategory;
	private String mAbility;

	public TrainerCard(String name, String category, String ability)
	{
		super(name);
		this.mCategory = category;
		this.mAbility = ability;
	}

	public String toString()
	{
		return String.format("|TRAINER CARD|\n|%s|\n||%s||\n|%s|\n", this.getName(), this.mCategory, this.mAbility);
	}
}
