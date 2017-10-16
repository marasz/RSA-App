package com.company;

public class Main {

    public static void main(String[] args) {
        RSA rsa = new RSA(false);

        //Mit Ausgabe
        //RSA rsa = new RSA(false);

        rsa.exportPrivateKey("sk.txt");
        rsa.exportPublicKey("pk.txt");

        Encryptor encryptor = new Encryptor();
        //encryptor.setShowOutput(true);
        String encrypted = encryptor.encryptMessage("text.txt", "pk.txt");
        FileHandler.export(encrypted, "chiffre.txt");

        Decryptor decryptor = new Decryptor();
        //decryptor.setShowOutput(true);
        String decrypted = decryptor.decryptMessage("chiffre.txt", "sk.txt");

        System.out.println(decrypted);

    }
}
