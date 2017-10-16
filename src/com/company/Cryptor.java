package com.company;

import java.math.BigInteger;

public abstract class Cryptor {

    protected boolean showOutput;

    public void setShowOutput(boolean showOutput) {
        this.showOutput = showOutput;
    }

    public BigInteger squareAndMultiply(BigInteger base, BigInteger exponent, BigInteger modulo) {
        if(showOutput)System.out.println("Square and multiply (Schnelle Exponentation): ");
        BigInteger k = base;
        BigInteger h = new BigInteger("1");
        int i = exponent.bitLength() - 1;
        String binary = exponent.toString(2);
        char[] b = binary.toCharArray();

        while (i >= 0) {

            if(showOutput) {
                System.out.print(i + " ");
                System.out.print(h + " ");
                System.out.println(k);
            }


            if (("" + b[i]).equals("1")) {
                h = (h.multiply(k)).mod(modulo);
            }

            k = (k.multiply(k)).mod(modulo);
            i--;
        }

        if(showOutput) System.out.println("h: " + h);

        return h;
    }

}
