package com.dmens.pokeno.Effect;

/*
 * An ApplyStatus effect.
 *
 * @author James
 */
public class ApplyStatus implements Effect {

	private String mTarget;
	private String mStatus;
	
	/*
	 * Constructor
	 * 
	 * @param		tar		Target.
	 * @param		stat	Status.
	 */
	public ApplyStatus(String tar,String stat)
	{
		this.mTarget = tar;
		this.mStatus = stat;
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
     * Get the status that the effect will apply.
     * 
     * @return		The status as a string.
     */
	public String getStatus()
	{
		return this.mStatus;
	}
	

	@Override
	public void execute()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString()
	{
		return String.format("%s:\t\tTAR: %s\t\tSTA: %s", ApplyStatus.class, this.mTarget, this.mStatus);
	}
}
