package com.dmens.pokeno.ParserTest;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dmens.pokeno.card.EnergyCard;
import com.dmens.pokeno.card.EnergyTypes;
import com.dmens.pokeno.card.Pokemon;
import com.dmens.pokeno.card.TrainerCard;
import com.dmens.pokeno.database.AbilitiesDatabase;
import com.dmens.pokeno.utils.CardUtil;

public class CardUtilsTest {
	private static String pokemonString = "Shellder:pokemon:cat:basic:cat:water:60:retreat:cat:colorless:1:attacks:cat:colorless:1,cat:water:1:9";
	private static String energyString = "Water:energy:cat:water";
	private static String trainerString = "Clemont:trainer:cat:supporter:35";
	
	@BeforeClass
	public static void initAbilities(){
		AbilitiesDatabase.getInstance().initialize("abilities.txt");
	}

	@Test
	public void testCreatePokemonCard() {
		Pokemon c = (Pokemon) CardUtil.getCardFromString(pokemonString);
		Assert.assertNotNull(c);
		assertEquals(c.getName(), "Shellder");
		assertEquals(c.getCategory(), "basic");
		assertEquals(c.getPokemonType(), "water");
	}
	
	public void testCreateEnergyCard(){
		EnergyCard energy = (EnergyCard) CardUtil.getCardFromString(energyString);
		assertEquals(energy.getName(), "Water");
		assertEquals(energy.getCategory(), EnergyTypes.WATER);
	}
	
	public void testCreateTrainerCard(){
		TrainerCard trainerCard = (TrainerCard) CardUtil.getCardFromString(trainerString);
		assertEquals(trainerCard.getName(), "Clemont");	
	}

}
