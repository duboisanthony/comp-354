package com.dmens.pokeno.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.dmens.pokeno.Ability.Ability;
import com.dmens.pokeno.Effect.ApplyStatus;
import com.dmens.pokeno.Effect.Damage;
import com.dmens.pokeno.Effect.Effect;
import com.dmens.pokeno.Effect.EffectTypes;
import com.dmens.pokeno.Effect.Heal;

public class AbilityUtil {
	
	public static Ability getAbilityFromString(String abilityInformation){
    	int indexName = abilityInformation.indexOf(":");
    	Ability ability = new Ability(abilityInformation.substring(0,indexName));
    	String restStr = abilityInformation.substring(indexName + 1, abilityInformation.length());
    	List<String> effects = Arrays.asList(restStr.split(","));
    	
    	effects.forEach(effect ->{
    		System.out.println("--- " + effect);
    		ability.addEffect(ParseEfect(effect));
    	});
    	
    	return ability;
	}
	

	private static Effect ParseEfect(String effect)
	{
		Stack<String> effectStack = new Stack<String>();
		String[] parsedString = effect.split(":");
		for (int i = parsedString.length -1; i >= 0; i--) 
			effectStack.add(parsedString[i]);
		switch(EffectTypes.valueOf(effectStack.pop().toUpperCase())){
		case DAM:
			return getDamageEffect(effectStack);
		case HEAL:
			return getHealEffect(effectStack);
		case APPLYSTAT:
			return getApplyStatusEffect(effectStack);
		default:
			return null;
		}
	}
	
	private static String getTarget(Stack<String> effectStack){
		effectStack.pop();	// target
		String target = effectStack.pop();
		if(target.contains("choice"))
			return null;
		return target;
	}
	
	private static Effect getDamageEffect(Stack<String> effectStack){
		String target = getTarget(effectStack);
		if(target == null)
			return null;
		// TODo Count multiplier
		if(effectStack.peek().contains("count"))
			return null;
		int damage = Integer.parseInt(effectStack.pop());
		return new Damage(target, damage); 
	}
	
	private static Effect getHealEffect(Stack<String> effectStack){
		String target = getTarget(effectStack);
		if(target == null)
			return null;
		int healValue = Integer.parseInt(effectStack.pop());
		return new Heal(target, healValue); 
	}
	
	private static Effect getApplyStatusEffect(Stack<String> effectStack){
		String status = getStatus(effectStack);
		String target = effectStack.pop();
		return new ApplyStatus(target, status); 
	}
	
	private static String getStatus(Stack<String> effectStack){
		effectStack.pop();	// status
		return effectStack.pop();
	}
	
	

}
