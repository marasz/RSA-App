package com.company;

import java.math.BigInteger;

public class Decryptor extends Cryptor{

    public String decryptMessage(String pathToEncryptedMessage, String pathToSk){
        String keys = FileHandler.importText(pathToSk);

        if(showOutput) System.out.println("Imported private key: " + keys);

        String[] key = keys.split(",");
        BigInteger n = new BigInteger(key[0]);
        BigInteger d = new BigInteger(key[1]);
        String message = FileHandler.importText(pathToEncryptedMessage);

        if(showOutput) System.out.println("Imported message: " + message);

        return decryptMessage(message, n, d);

    }

    public String decryptMessage(String message, BigInteger n, BigInteger d){
        String[] encryptedChars = message.split(",");
        String decryptedText = "";
        for (String encryptedChar: encryptedChars) {
            BigInteger encryptedCharBi = new BigInteger(encryptedChar);
            String decryptedCharacter = squareAndMultiply(encryptedCharBi, d, n).toString();
            char character = (char) Integer.parseInt(decryptedCharacter);
            decryptedText += "" + character;
        }

        if(showOutput) System.out.println("Decrypted Text: " + decryptedText);
        return decryptedText;
    }

}
