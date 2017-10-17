package com.company;

import java.math.BigInteger;
import java.util.Random;

public class PrimeGenerator {
    public static BigInteger getRandomPrime(){
        BigInteger random = BigInteger.probablePrime(1024, new Random());
        return random;

    }
}
