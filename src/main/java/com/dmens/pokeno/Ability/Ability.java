package com.dmens.pokeno.Ability;

import com.dmens.pokeno.Condition.Condition;
import com.dmens.pokeno.Effect.Damage;
import com.dmens.pokeno.Effect.Effect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Devin on 2017-05-26.
 */
public class Ability {
	
    private static final Logger LOG = LogManager.getLogger(Ability.class);
    private String mName = "";
    
    private ArrayList<Effect> mEffects;
    private ArrayList<Condition> mConditions;
    
    public Ability(String name)
    {
    	this.mName = name;
    	this.mEffects = new ArrayList<Effect>();
    	this.mConditions = new ArrayList<Condition>();
    }
    
    public void addEffect(Effect e)
	{
		this.mEffects.add(e);
	} 
    
    public void addCondition(Condition c)
	{
		this.mConditions.add(c);
	}
    
    public String getName()
    {
    	return this.mName;
    }
    
    public ArrayList<Effect> getEffects()
    {
    	return this.mEffects;
    }
    
    // Assumption is that there is only one damage right now
    public Damage getDamage()
    {
    	for (Effect effect: mEffects)
    	{
    	   if(effect.getClass() == Damage.class)
    	   {
    		   return (Damage) effect;
    	   }
    	}
    	 return null;
    }
    
    public String toString()
	{
    	StringBuilder effectsAsList = new StringBuilder();
    	
    	for (Effect effect: mEffects)
    	{
    		effectsAsList.append("--" + effect.toString() + "\n");
    	}
    	
		return String.format("%s:\n%s", Ability.class, effectsAsList.toString());
	}
}