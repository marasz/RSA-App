package com.company;

import java.math.BigInteger;
import java.util.ArrayList;

public class Encryptor extends Cryptor {


    public String encryptMessage(String pathToDecryptedMessage, String pathToPublicKey){
        String text = FileHandler.importText(pathToDecryptedMessage);
        if(showOutput) System.out.println("imported Text: " + text);

        String keys = FileHandler.importText(pathToPublicKey);
        String[] key = keys.split(",");
        BigInteger n = new BigInteger(key[0]);
        BigInteger e = new BigInteger(key[1]);

        if(showOutput) System.out.println("Imported public key: " + keys);

        return  encrypt(text, e, n);
    }

    private String encrypt(String text, BigInteger e, BigInteger n) {
        char[] ascii = text.toCharArray();
        ArrayList<BigInteger> encryptedCharacters = new ArrayList<>();

        for (char character : ascii) {
            BigInteger asciiBig = BigInteger.valueOf((int)character);
            encryptedCharacters.add(squareAndMultiply(asciiBig, e, n));
        }

        String encrypted = "";
        for (BigInteger value : encryptedCharacters){
            encrypted += value.toString() + ",";
        }
        if(showOutput) System.out.println("Encrypted Text: " + encrypted);
        return encrypted;
    }

}
