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
    
    public void AddEffect(Effect e)
	{
		this.mEffects.add(e);
	} 
    
    public void AddCondition(Condition c)
	{
		this.mConditions.add(c);
	}
    
    public String GetName()
    {
    	return this.mName;
    }
    
    public ArrayList<Effect> GetEffects()
    {
    	return this.mEffects;
    }
    
    // Assumption is that there is only one damage right now
    public Damage GetDamage()
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
}