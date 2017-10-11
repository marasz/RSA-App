package com.company;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;

public class RSA {
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phyOfN;
    private BigInteger e;
    private BigInteger d;

    public RSA() {
        calculateN();
        calculatePhyOfN();
        calculateE();
        calculateD();
    }

    public String getPrivateKey() {
        return (n + "," + d);
    }

    public String getPublicKey() {
        return (n + "," + e);
    }

    public void exportPrivateKey(String path) {
        export(getPrivateKey(), path);
    }

    public void exportPublicKey(String path) {
        export(getPublicKey(), path);
    }

    public void export(String text, String path){
        File file = new File(path);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void calculateN() {
        p = PrimeGenerator.getRandomPrime();
        q = PrimeGenerator.getRandomPrime();
        if (p.equals(q)) {
            calculateN();
            return;
        }

        n = p.multiply(q);
    }

    private void calculatePhyOfN() {
        BigInteger one = new BigInteger("1");
        phyOfN = p.subtract(one).multiply(q.subtract(one));
    }

    private void calculateE() {
        e = n.subtract(phyOfN);
    }

    private void calculateD() {
        BigInteger a = this.e;
        BigInteger b = this.phyOfN;
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

            System.out.print(a + " | ");
            System.out.print(b + " | ");
            System.out.print(x0 + " | ");
            System.out.print(y0 + " | ");
            System.out.print(x1 + " | ");
            System.out.print(y1 + " | ");
            System.out.print(q + " | ");
            System.out.println(r + " | ");

            a = b;
            b = r;

            x0temp = x1;
            y0temp = y1;

            x1 = x0.subtract(q.multiply(x1));
            y1 = y0.subtract(q.multiply(y1));
            x0 = x0temp;
            y0 = y0temp;

        }

        if (x0.compareTo(BigInteger.ZERO) < 0) {
            x0 = x0.mod(this.n);
        }

        this.d = x0;
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

    public void encryptFileToFile(String from, String to){
        export(encryptFile(from), to);
    }

    public String encryptFile(String path) {
        String text = importText(path);
        return encrypt(text);

    }

    private String importText(String path) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        String text = "";
        try {
            String line;

            try {
                while ((line = br.readLine()) != null) {
                    text += line;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                br.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return text;
    }

    private String encrypt(String text) {
        char[] ascii = text.toCharArray();
        ArrayList<BigInteger> encryptedCharacters = new ArrayList<>();

        for (char character : ascii) {
            encryptedCharacters.add(encryptCharacter(character));
        }

        String encrypted = "";
        for (BigInteger value : encryptedCharacters){
            encrypted += value.toString() + ",";
        }

        return encrypted;
    }

    private BigInteger encryptCharacter(char character) {
        BigInteger k = new BigInteger("" + (int) character);
        BigInteger h = new BigInteger("1");

        int i = this.e.bitLength() - 1;
        String binary = e.toString(2);
        char[] b = binary.toCharArray();

        while (i >= 0) {

            System.out.print(i + " ");
            System.out.print(h + " ");
            System.out.println(k);

            if (("" + b[i]).equals("1")) {
                h = (h.multiply(k)).mod(this.d);
            }

            k = (k.multiply(k)).mod(this.d);
            i--;
        }
        return h;
    }

}
