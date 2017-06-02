package com.dmens.pokeno.CardTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import com.dmens.pokeno.Ability.Ability;
import com.dmens.pokeno.Effect.*;
import com.dmens.pokeno.Card.*;

public class CardTest {
	
	static String mEnergyCardName = "Water";
	static String mEnergyCardCategory = "water";
	static String mTrainerCardPotionName = "Potion";
	static String mTrainerCardPotionCategory = "item";
	static String mAbilityPotionName = "Potion";
	static String mAbilityPotionTarget = "your";
	static int mAbilityPotionValue = 30;
	

	@Test
    public void cardTest(){

		// Test EnergyCard
        EnergyCard energyCard = new EnergyCard(mEnergyCardName, mEnergyCardCategory);
        Assert.assertEquals(energyCard.getName(), mEnergyCardName);
        Assert.assertEquals(energyCard.getCategory(), mEnergyCardCategory);
        
        // Test TrainerCard
        ArrayList<Ability> potionAbilities = new ArrayList<Ability>();
        Ability ability = new Ability(mAbilityPotionName);
        ability.addEffect(new Heal(mAbilityPotionTarget, mAbilityPotionValue));
        potionAbilities.add(ability);
        		
        TrainerCard trainerCard = new TrainerCard(mTrainerCardPotionName, mTrainerCardPotionCategory, potionAbilities);
        Assert.assertEquals(trainerCard.getName(), mTrainerCardPotionName);
        Assert.assertEquals(trainerCard.getCategory(), mTrainerCardPotionCategory);
        Assert.assertEquals(trainerCard.getAbilities(), potionAbilities);
        
        Assert.assertEquals(trainerCard.getAbilities().get(0).getName(), mAbilityPotionName);
        Assert.assertEquals(trainerCard.getAbilities().get(0).getHealEffect().getTarget(), mAbilityPotionTarget);
        Assert.assertEquals(trainerCard.getAbilities().get(0).getHealEffect().getValue(), mAbilityPotionValue);
        
        // Test Pokemon
    }

}
