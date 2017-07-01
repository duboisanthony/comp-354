package com.dmens.pokeno.ParserTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dmens.pokeno.Ability.Ability;
import com.dmens.pokeno.Utils.AbilityUtil;

public class AbilityUtilsTest {
	private static String damageAbilityString = "Bite:dam:target:opponent-active:40";

	@Test
	public void testCreateDamageAbility() {
		Ability a = AbilityUtil.getAbilityFromString(damageAbilityString);
		assertEquals(a.getName(), "Bite");
		assertEquals(a.getDamageEffect().getTarget(), "opponent-active");
		assertEquals(a.getDamageEffect().getValue(), 40);
	}

}
