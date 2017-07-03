package com.dmens.pokeno.card;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dmens.pokeno.effect.Damage;

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
		mCategory = EnergyTypes.valueOf(cat.toUpperCase());
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

	@Override
	public Card copy() {
		return new EnergyCard(this.getName(), this.getCategory().toString());
	}
	
	
}
