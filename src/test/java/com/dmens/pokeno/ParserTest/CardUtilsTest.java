package com.dmens.pokeno.ParserTest;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dmens.pokeno.Card.EnergyCard;
import com.dmens.pokeno.Card.EnergyTypes;
import com.dmens.pokeno.Card.Pokemon;
import com.dmens.pokeno.Card.TrainerCard;
import com.dmens.pokeno.Utils.CardUtil;
import com.dmens.pokeno.database.AbilitiesDatabase;

public class CardUtilsTest {
	private static String pokemonString = "Shellder:pokemon:cat:basic:cat:water:60:retreat:cat:colorless:1:attacks:cat:colorless:1,cat:water:1:9";
	private static String energyString = "Water:energy:cat:water";
	private static String trainerString = "Clemont:trainer:cat:supporter:35";
	
	@BeforeClass
	public static void initAbilities(){
		AbilitiesDatabase.getInstance().initialize("data/abilities.txt");
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
