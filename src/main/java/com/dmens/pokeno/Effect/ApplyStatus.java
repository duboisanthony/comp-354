package com.dmens.pokeno.Effect;

public class ApplyStatus implements Effect {

	private String mTarget;
	private String mStatus;
	
	public ApplyStatus(String tar,String stat)
	{
		this.mTarget = tar;
		this.mStatus = stat;
	}
	
	public String getTarget()
	{
		return this.mTarget;
	}
	
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
