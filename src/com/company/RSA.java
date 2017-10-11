package com.company;

import java.math.BigInteger;

public class RSA {
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phyOfN;
    private BigInteger e;
    private BigInteger d;

    public RSA(){
        calculateN();
        calculatePhyOfN();
        calculateE();
        calculateD();
    }

    private void calculateN(){
        p = PrimeGenerator.getRandomPrime();
        q = PrimeGenerator.getRandomPrime();
        if(p.equals(q)){
            calculateN();
            return;
        }

        n = p.multiply(q);
    }

    private void calculatePhyOfN(){
        BigInteger one = new BigInteger("1");
        phyOfN = p.subtract(one).multiply(q.subtract(one));
    }

    private void calculateE(){
        e = n.subtract(phyOfN);
    }

    private void calculateD(){
        BigInteger a = this.phyOfN;
        BigInteger b = this.e;
        BigInteger x0 = new BigInteger("1");
        BigInteger y0 = new BigInteger("0");
        BigInteger x1 = new BigInteger("0");
        BigInteger y1 = new BigInteger("1");
        BigInteger q;
        BigInteger r;
        BigInteger x0temp;
        BigInteger y0temp;

        while(!b.equals(new BigInteger("0"))){
            q = a.divide(b);
            r = a.mod(b);

/*            System.out.print(a + " | ");
            System.out.print(b + " | ");
            System.out.print(x0 + " | ");
            System.out.print(y0 + " | ");
            System.out.print(x1 + " | ");
            System.out.print(y1 + " | ");
            System.out.print(q + " | ");
            System.out.println(r + " | ");*/

            a = b;
            b = r;

            x0temp = x1;
            y0temp = y1;

            x1 = x0.subtract(q.multiply(x1));
            y1 = y0.subtract(q.multiply(y1));
            x0 = x0temp;
            y0 = y0temp;

        }

        this.d = x0;
    }

    public String toString(){
        String toString = "p: " + this.p;
        toString += "\nq: " + this.q;
        toString += "\nn:" + this.n;
        toString += "\nPhy of n:" + this.phyOfN;
        toString += "\ne: " + this.e;
        toString += "\nd: " + this.d;
        return toString;
    }

}
