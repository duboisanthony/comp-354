package com.dmens.pokeno.RandomizerTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.dmens.pokeno.Utils.Randomizer;

/**
*
* @author James
*/
public class RandomizerTest {
	
	static int mDesiredResults = 50;
	static double mThreshold = 0.10;
	static int mFlips = mDesiredResults * 2;

	@Test
	public void RandomizerTest() {
		
		Randomizer rando = null;
		rando = Randomizer.Instance();
		Assert.assertNotEquals(rando, null);
		
		int headsCount = 0;
		int tailsCount = 0;
		
		//TODO? loop this again and take an average?!
		
		for(int i = 0; i < mFlips; i++)
		{
			if(rando.getFiftyPercentChance())
				headsCount++;
			else
				tailsCount++;
		}
		
		// Check that there's nothing fishy...
		Assert.assertEquals(headsCount + tailsCount , mFlips);
		
		// acceptable range is within 10 of desired results
		System.out.println("RandomizerTest-Heads: " + headsCount);
		System.out.println("RandomizerTest-Tails: " + tailsCount);
		Assert.assertTrue((mDesiredResults - (int) (mFlips * mThreshold )) <= tailsCount && tailsCount <= (mDesiredResults + (int) (mFlips * mThreshold )));
		Assert.assertTrue((mDesiredResults - (int) (mFlips * mThreshold )) <= headsCount && headsCount <= (mDesiredResults + (int) (mFlips * mThreshold )));
	}

}
