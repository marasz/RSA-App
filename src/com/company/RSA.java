package com.company;

import java.math.BigInteger;
import java.util.ArrayList;

public class RSA {
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phyOfN;
    private BigInteger e;
    private BigInteger d;
    private boolean showOutput;

    public RSA(boolean showOutput) {

        this.showOutput = showOutput;
        this.p = PrimeGenerator.getRandomPrime();
        this.q = PrimeGenerator.getRandomPrime();
        this.n = calculateN(this.p, this.q);
        this.phyOfN = calculatePhyOfN(this.p, this.q);
        this.e = calculateE(this.n, this.phyOfN);
        this.d = calculateD(this.e, this.phyOfN);

        if(showOutput)System.out.println(toString());
    }

    public String getPrivateKey() {
        return (n + "," + d);
    }

    public String getPublicKey() {
        return (n + "," + e);
    }

    public void exportPrivateKey(String path) {
        FileHandler.export(getPrivateKey(), path);
    }

    public void exportPublicKey(String path) {
        FileHandler.export(getPublicKey(), path);
    }

    private BigInteger calculateN(BigInteger p, BigInteger q) {
        return p.multiply(q);
    }

    private BigInteger calculatePhyOfN(BigInteger p, BigInteger q) {
        BigInteger one = new BigInteger("1");
        return p.subtract(one).multiply(q.subtract(one));
    }

    private BigInteger calculateE(BigInteger n, BigInteger phyOfN) {
        return n.subtract(phyOfN);
    }

    private BigInteger calculateD(BigInteger e, BigInteger phyOfN) {
        if(showOutput)System.out.println("euclidean algorithm: ");
        BigInteger a = e;
        BigInteger b = phyOfN;
        BigInteger x0 = new BigInteger("1");
        BigInteger y0 = new BigInteger("0");
        BigInteger x1 = new BigInteger("0");
        BigInteger y1 = new BigInteger("1");
        BigInteger q;
        BigInteger r;
        BigInteger x0temp;
        BigInteger y0temp;

        while (!b.equals(new BigInteger("0"))) {
            q = a.divide(b);
            r = a.mod(b);

            if(showOutput) {
                System.out.print(a + " | ");
                System.out.print(b + " | ");
                System.out.print(x0 + " | ");
                System.out.print(y0 + " | ");
                System.out.print(x1 + " | ");
                System.out.print(y1 + " | ");
                System.out.print(q + " | ");
                System.out.println(r + " | ");
            }

            a = b;
            b = r;

            x0temp = x1;
            y0temp = y1;

            x1 = x0.subtract(q.multiply(x1));
            y1 = y0.subtract(q.multiply(y1));
            x0 = x0temp;
            y0 = y0temp;

        }

        if(showOutput) {
            System.out.print(a + " | ");
            System.out.print(b + " | ");
            System.out.print(x0 + " | ");
            System.out.println(y0 + " | ");
        }

        if (x0.compareTo(BigInteger.ZERO) < 0) {
            x0 = x0.add(phyOfN);
        }

        return x0;
    }

    public String toString() {
        String toString = "p: " + this.p;
        toString += "\nq: " + this.q;
        toString += "\nn:" + this.n;
        toString += "\nPhy of n:" + this.phyOfN;
        toString += "\ne: " + this.e;
        toString += "\nd: " + this.d;
        return toString;
    }
}
