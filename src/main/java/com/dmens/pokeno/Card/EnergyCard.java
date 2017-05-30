package com.dmens.pokeno.Card;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnergyCard extends Card {

    private static final Logger LOG = LogManager.getLogger(EnergyCard.class);
	
	private String mCategory;

	public EnergyCard(String name, String category)
	{
		super(name);
		this.mCategory = category;
	}

	public EnergyCard(String description){}

	public String getCategory() {
		return this.mCategory;
	}
	
	public String toString()
	{
		return String.format("|ENERGY CARD|\n|%s|\n||%s||\n", this.getName(), this.mCategory);
	}
}
