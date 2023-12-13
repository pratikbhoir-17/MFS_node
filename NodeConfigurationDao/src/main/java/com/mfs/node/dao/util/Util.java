package com.mfs.node.dao.util;

import java.util.Random;

public class Util {
	public static char[] generateOTP() {
		String numbers = "1234567890";
		Random random = new Random();
		char[] otp = new char[6];

		for (int i = 0; i < 6; i++) {
			otp[i] = numbers.charAt(random.nextInt(numbers.length()));
		}
		return otp;
	}
}
