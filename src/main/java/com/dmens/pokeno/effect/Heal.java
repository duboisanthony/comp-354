package com.dmens.pokeno.effect;

import com.dmens.pokeno.controller.GameController;
/*
 * A Heal effect.
 *
 * @author James
 */
public class Heal implements Effect {

	private int mValue;
	private String mTarget;
	
	// we have three possible targets to heal
	private final String YOUR_ACTIVE = "your-active";
	private final String CHOICE = "choice";
	private final String SELF = "self";

	/*
	 * Constructor
	 * 
	 * @param		tar		Target.
	 * @param		val		Integer value (amount).
	 */
	public Heal(String tar, int val)
	{
		this.mTarget = tar;
		this.mValue = val;		
	}
	
	/*
	 * Copy Constructor
	 * 
	 * @param		h		Heal Effect.
	 */
	public Heal(Heal h)
	{
		this.mTarget = h.mTarget;
		this.mValue = h.mValue;		
	}
	
	/*
     * Get the target of this Effect.
     * 
     * @return		The target as a string.
     */
	public String getTarget()
	{
		return this.mTarget;
	}
	
	/*
     * Get the value of this Effect.
     * 
     * @return		The value as an integer.
     */
	public int getValue()
	{
		return this.mValue;
	}

	@Override
	public void execute()
	{
		if(mTarget.equals(YOUR_ACTIVE)) {
			// heal the player's active pokement
			if(GameController.getIsHomePlayerPlaying()) {
				GameController.getHomePlayer().getActivePokemon().removeDamage(this.mValue);
			} else {
				GameController.getAIPlayer().getActivePokemon().removeDamage(this.mValue);
			}
		} else if (mTarget.equals(CHOICE)) {
			// TODO: heal a pokemon of choice, need to get input from UI
		} else if(mTarget.equals(SELF)) {
			// TODO: heal the pokemon this card is attached to, specific to Floral Crown
		}

	}

	@Override
	public String toString()
	{
		return String.format("%s:\t\tTAR: %s\t\tVAL:%d", Heal.class, this.mTarget, this.mValue);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		Heal h = (Heal) obj;
		if(h.mTarget.equals(this.mTarget) && h.mValue == this.mValue)
			return true;
		
		return false;
	}
}
