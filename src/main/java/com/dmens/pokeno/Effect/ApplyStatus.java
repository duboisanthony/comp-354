package com.dmens.pokeno.Effect;

public class ApplyStatus implements Effect {

	private int mValue;
	private String mTarget;
	private String mStatus;
	
	public ApplyStatus(String tar,String stat, int val)
	{
		this.mTarget = tar;
		this.mStatus = stat;
		this.mValue = val;	
	}
	
	public String getTarget()
	{
		return this.mTarget;
	}
	
	public String getStatus()
	{
		return this.mStatus;
	}
	
	public int getValue()
	{
		return this.mValue;
	}

	@Override
	public void execute()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString()
	{
		return String.format("%s:\t\tTAR: %s\t\tSTA: %s\t\tVAL:%d", ApplyStatus.class, this.mTarget, this.mStatus, this.mValue);
	}
}
