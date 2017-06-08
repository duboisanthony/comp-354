package com.dmens.pokeno.Card;

import com.dmens.pokeno.Ability.Ability;
import com.dmens.pokeno.Effect.Effect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class TrainerCard extends Card {

    private static final Logger LOG = LogManager.getLogger(TrainerCard.class);

	private String mCategory;
	private ArrayList<Ability> mAbilities;

	public TrainerCard(String name, String category, ArrayList<Ability> abilities)
	{
		super(name);
		this.mCategory = category;
		this.mAbilities = abilities;
	}
	
	public String getCategory() {
		return this.mCategory;
	}
	
	public ArrayList<Ability> getAbilities() {
		return this.mAbilities;
	}
	
	public String toString()
	{
		StringBuilder abilitiesAsList = new StringBuilder();
		for (Ability ability: mAbilities)
		{
			abilitiesAsList.append("--" + ability.toString() + "\n");
		}
		
		return String.format("%s:\t\tNAME: %s\t\tCAT:%s\n%s", TrainerCard.class, this.getName(), this.mCategory, abilitiesAsList.toString());
	}
	
	@Override
	public boolean isType(CardTypes c) {
		return (c == CardTypes.TRAINER) ? true : false;
	}
}
