package com.company;

import java.math.BigInteger;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        RSA rsa = new RSA();
        System.out.println(rsa.toString());
        rsa.exportPrivateKey("sk.txt");
        rsa.exportPublicKey("pk.txt");

        rsa.encryptFileToFile("text.txt", "chiffre.txt");
        System.out.println(rsa.encryptFile("text.txt"));

    }
}
