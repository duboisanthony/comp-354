package com.dmens.pokeno.Card;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dmens.pokeno.Effect.Damage;

public class EnergyCard extends Card {

    private static final Logger LOG = LogManager.getLogger(EnergyCard.class);
	
	private String mCategory;

	public EnergyCard(String name, String category)
	{
		super(name);
		this.mCategory = category;
	}

	public String getCategory() {
		return this.mCategory;
	}
	
	public String toString()
	{
		return String.format("%s:\t\tNAME: %s\t\tCAT:%s\n", EnergyCard.class, this.getName(), this.mCategory);
	}
}
