package com.company;

import java.math.BigInteger;
import java.util.Random;

public class PrimeGenerator {
    public static BigInteger getRandomPrime(){
        Random r = new Random();
        int randomInt = r.nextInt(127) + 1;

        BigInteger random = BigInteger.probablePrime(128, new Random());
        return random;

    }
}
