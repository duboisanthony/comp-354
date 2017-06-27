package com.dmens.pokeno.Card;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dmens.pokeno.Effect.Damage;

public class EnergyCard extends Card {

    private static final Logger LOG = LogManager.getLogger(EnergyCard.class);
	
	private EnergyTypes mCategory;

	public EnergyCard(String name, String category)
	{
		super(name);
		setCategory(category);
	}

	public EnergyTypes getCategory() {
		return this.mCategory;
	}
	
	private void setCategory(String cat){
		if(cat.equalsIgnoreCase("COLORLESS"))
			mCategory = EnergyTypes.COLORLESS;
		else if(cat.equalsIgnoreCase("WATER"))
			mCategory = EnergyTypes.WATER;
		else if(cat.equalsIgnoreCase("FIGHT"))
			mCategory = EnergyTypes.FIGHT;
		else if(cat.equalsIgnoreCase("FIRE"))
			mCategory = EnergyTypes.FIRE;
		else if(cat.equalsIgnoreCase("PSYCHIC"))
			mCategory = EnergyTypes.PSYCHIC;
		else if(cat.equalsIgnoreCase("GRASS"))
			mCategory = EnergyTypes.GRASS;
		else if(cat.equalsIgnoreCase("LIGHTNING"))
			mCategory = EnergyTypes.LIGHTNING;
	}
	
	public String toString()
	{
		return String.format("%s:\t\tNAME: %s\t\tCAT:%s\n", EnergyCard.class, this.getName(), this.mCategory);
	}

	@Override
	public boolean isType(CardTypes c) {
		return (c == CardTypes.ENERGY) ? true : false;
	}

	@Override
	public CardTypes getType() {
		return CardTypes.ENERGY;
	}
	
	public boolean isCategory(EnergyTypes energy){
		return (mCategory == energy || energy == EnergyTypes.COLORLESS ? true : false);
	}
}
