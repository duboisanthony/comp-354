package com.dmens.pokeno.Effect;

public class Damage implements Effect {

	private int mValue;
	private String mTarget;
	
	public Damage(String tar, int val)
	{
		this.mTarget = tar;
		this.mValue = val;		
	}
	
	public String getTarget()
	{
		return this.mTarget;
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
		return String.format("%s:\t\tTAR: %s\t\tVAL:%d", Damage.class, this.mTarget, this.mValue);
	}	
}