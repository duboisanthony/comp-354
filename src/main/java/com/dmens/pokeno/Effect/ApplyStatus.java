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

	@Override
	public void Execute()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GetInformation()
	{
		// TODO Auto-generated method stub
		
	}

}
