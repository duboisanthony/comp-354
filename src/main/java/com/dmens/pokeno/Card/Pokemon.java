package com.dmens.pokeno.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dmens.pokeno.Ability.Ability;
import com.dmens.pokeno.Ability.AbilityCost;
import com.dmens.pokeno.Driver.GameController;
import com.dmens.pokeno.Effect.*;

public class Pokemon extends Card {

    private static final Logger LOG = LogManager.getLogger(Pokemon.class);

    private int mHP;
    private int mDamage;
    private ArrayList<EnergyCard> mAttachedEnergy;
	private ArrayList<String> mCategories;
	private ArrayList<Ability> mAbilities;
    private ArrayList<AbilityCost> mAbilitiesAndCost;
	private int mRetreatCost;
	private String mBasePokemonName;
    private boolean mPoisoned;
    private boolean mConfused;
    private boolean mParalyzed;
    private boolean mSleep;
    
    public Pokemon(String name, ArrayList<String> categories, int initialHP, Integer retreatCost){
        super(name);
        mCategories = categories;
        mHP = initialHP;
        mRetreatCost = retreatCost;
        mAbilities = new ArrayList<Ability>();
        mAttachedEnergy = new ArrayList<EnergyCard>();
        mAbilitiesAndCost = new ArrayList<AbilityCost>();
    }
	
	public void AddCategory(String category)
	{
		this.mCategories.add(category);
	}
	
	public void AddAbilityAndCost(AbilityCost abilityCost)
	{
		this.mAbilitiesAndCost.add(abilityCost);
	}

	public String toString()
	{
		StringBuilder abilitiesAsList = new StringBuilder();
//		for (AbilityCost tuple: mAbilitiesAndCost)
//		{
//			abilitiesAsList.append(String.format("--T:%s C:%d\t",  tuple.y, tuple.z) + tuple.x.toString() + "\n");
//		}
		
		return String.format("%s:\t\tNAME: %s\n%s", Pokemon.class, this.getName(), abilitiesAsList.toString());
	}
	
	public boolean addDamage(int damage)
        {
            mDamage += damage;
            /*if (mDamage >= mHP)
            {
                return true;
            }*/
            //if damage > hp -> "faint"
            return false;
        }

	public void removeDamage(int damageToRemove) {
		if(damageToRemove < 0) {
			return;
		}
		
		if(damageToRemove > mDamage) {
			mDamage = 0;
			return;
		}
		mDamage -= damageToRemove;
	}

	public void addEnergy(EnergyCard energy){
		mAttachedEnergy.add(energy);
	}

	public void removeEnergy(ArrayList<EnergyCard> energy){
		
	}

	public void setBasePokemonName(String basePokemonName) {
		assert basePokemonName != null;
		mBasePokemonName = basePokemonName;
	}
	
    public boolean useAbility(int ability, Pokemon target)
    {
        if (mAbilitiesAndCost.size() <= ability)
            return false;
        Ability a = mAbilitiesAndCost.get(ability).getAbility();//mAbilities.get(ability);
        HashMap <EnergyTypes, Integer> cost = mAbilitiesAndCost.get(ability).getCosts();
        ArrayList<Integer> energyCounts = GameController.getAttachedEnergyList(getMapOfAttachedEnergies());
        int remainingEnergyCount = 0;
        for (int count : energyCounts)
        {
            remainingEnergyCount += count;
        }
        
        boolean hasEnoughEnergy = true;
        if(cost.containsKey(EnergyTypes.FIGHT))
        {
            if (cost.get(EnergyTypes.FIGHT) <= energyCounts.get(0))
                remainingEnergyCount -= cost.get(EnergyTypes.FIGHT);
            else
                hasEnoughEnergy = false;
        }
        if(cost.containsKey(EnergyTypes.LIGHTNING))
        {
            if (cost.get(EnergyTypes.LIGHTNING) <= energyCounts.get(1))
                remainingEnergyCount -= cost.get(EnergyTypes.LIGHTNING);
            else
                hasEnoughEnergy = false;
        }

        if(cost.containsKey(EnergyTypes.PSYCHIC))
        {
            if (cost.get(EnergyTypes.PSYCHIC) <= energyCounts.get(2))
                remainingEnergyCount -= cost.get(EnergyTypes.PSYCHIC);
            else
                hasEnoughEnergy = false;
        }
        if(cost.containsKey(EnergyTypes.WATER))
        {
            if (cost.get(EnergyTypes.WATER) <= energyCounts.get(3))
                remainingEnergyCount -= cost.get(EnergyTypes.WATER);
            else
                hasEnoughEnergy = false;
        }
        if (cost.containsKey(EnergyTypes.COLORLESS) && cost.get(EnergyTypes.COLORLESS) > remainingEnergyCount)
            hasEnoughEnergy = false;
        
        if (hasEnoughEnergy)
        {
        	//is it a simple ApplyStatus effect or a simple Damage effect
        	if(a.getEffects().get(0) instanceof ApplyStatus)
        	{
        		String status = a.getApplyStatusEffect().getStatus();
        		
        		//TODO check target
        		if(status.compareTo("asleep") == 0)
        			{
        				target.setSleep(true);
        				GameController.board.addStatus(1, false);
        			}
        		
        		return true;
        	}
        	else if (a.getEffects().get(0) instanceof Damage)
        	{
        		target.addDamage(a.getDamageEffect().getValue());
                return true;
        	}
        }
        return false;
    }
        
    public void setPoisoned(boolean poisoned) {
        this.mPoisoned = poisoned;
    }

    public void setConfused(boolean confused) {
        this.mConfused = confused;
    }

    public void setParalyzed(boolean paralyzed) {
        this.mParalyzed = paralyzed;
    }

    public void setSleep(boolean sleep) {
        this.mSleep = sleep;
    }

    public int getHP() {
        return mHP;
    }

    public int getDamage() {
        return mDamage;
    }

    public ArrayList<EnergyCard> getAttachedEnergy() {
        return mAttachedEnergy;
    }

    public ArrayList<String> getCategories() {
        return mCategories;
    }

    public ArrayList<Ability> getAbilities() {
        return mAbilities;
    }

    public int getRetreatCost() {
        return mRetreatCost;
    }

    public String getBasePokemonName() {
        return mBasePokemonName;
    }

    public boolean isPoisoned() {
        return mPoisoned;
    }

    public boolean isConfused() {
        return mConfused;
    }

    public boolean isParalyzed() {
        return mParalyzed;
    }

    public boolean isSleep() {
        return mSleep;
    }

    public void evolvePokemon(Pokemon basePokemon){
	    this.mDamage = basePokemon.getDamage();
    }
    
    public ArrayList<AbilityCost> getAbilitiesAndCost(){
        return mAbilitiesAndCost;
    }
    
    public Map<EnergyTypes, Integer> getTotalEnergyNeeds(){
    	Map<EnergyTypes, Integer> totalCosts = new HashMap<EnergyTypes, Integer>();
    	this.mAbilitiesAndCost.forEach(abilityCost ->{
    		abilityCost.getCosts().forEach((energy, cost) ->{
    			if(totalCosts.containsKey(energy))
    				totalCosts.put(energy, cost + totalCosts.get(energy));
    			else
    				totalCosts.put(energy, cost);
    		});
    	});
    	this.mAttachedEnergy.forEach(energyAttached ->{
    		if(totalCosts.containsKey(energyAttached.getCategory())){
    			totalCosts.put(energyAttached.getCategory(), totalCosts.get(energyAttached.getCategory()) - 1);
    		}else if(totalCosts.containsKey(EnergyTypes.COLORLESS)){
    			totalCosts.put(EnergyTypes.COLORLESS, totalCosts.get(EnergyTypes.COLORLESS) - 1);
    		}
    	});
    	return totalCosts;
    }
    
    public Map<EnergyTypes, Integer> getMapOfAttachedEnergies(){
        Map<EnergyTypes, Integer> energies = new HashMap<EnergyTypes, Integer>();
        this.mAttachedEnergy.forEach(energyAttached ->{
    		if(energies.containsKey(energyAttached.getCategory())){
    			energies.put(energyAttached.getCategory(), energies.get(energyAttached.getCategory()) + 1);
    		}else{
    			energies.put(energyAttached.getCategory(), 1);
    		}
    	});
        return energies;
    }
    
    @Override
	public boolean isType(CardTypes c) {
		return (c == CardTypes.POKEMON) ? true : false;
	}

	@Override
	public CardTypes getType() {
		return CardTypes.POKEMON;
	}
}
