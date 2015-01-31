package org.ngo.think.dm.common.util;

import java.util.Random;

import org.ngo.think.dm.common.enums.RandomNumberType;

public class RandomNumberGenerator
{
	public static final String DONOR_PREFIX = "D";
	public static final String REQUEST_PREFIX = "R";
	public static final String DEFAULT_PREFIX = "";
	
	public static String generateRandomNumber(RandomNumberType randomNumberType)
	{
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		int number = random.nextInt(9000) + 1000;
		if(randomNumberType.equals(RandomNumberType.DONOR_UUID))
		{
			return DONOR_PREFIX + number;
		}
		else if(randomNumberType.equals(RandomNumberType.REQUEST_NUMBER))
		{
			return REQUEST_PREFIX + number;
		}
		else {
			return DEFAULT_PREFIX + number;
		}
	}
}
