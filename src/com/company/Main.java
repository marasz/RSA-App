package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static boolean showOutput;

    public static void main(String[] args) {
        menu();
    }

    private static void menu(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println();
        System.out.println("######################################");
        System.out.println();
        System.out.println("Menü: ");
        System.out.println("1 - RSA Schlüssel generieren");
        System.out.println("2 - Verschlüsseln");
        System.out.println("3 - Entschlüsseln");
        System.out.println("4 - Output anzeigen aktivieren/deaktivieren");
        System.out.println();
        System.out.println("######################################");
        System.out.print("Ihre Eingabe: ");
        String eingabe = "";
        System.out.println();

        try {
            eingabe = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (eingabe){
            case "1":
                rsa(showOutput);
                break;
            case "2":
                encrypt(showOutput);
                break;
            case "3":
                decrypt(showOutput);
                break;
            case "4":
                showOutput = !showOutput;
                break;

            default:
                menu();
                break;
        }

        menu();
    }

    private static void rsa(Boolean showOuput){
        //RSA Schlüssel erstellen
        RSA rsa = new RSA(showOuput);

        //Private Key in Datei exportieren
        rsa.exportPrivateKey("sk.txt");
        System.out.println("Private Key in sk.txt gespeichert.");

        //Public Key in Datei exportieren
        rsa.exportPublicKey("pk.txt");
        System.out.println("Public Key in pk.txt gespeichert.");

    }

    private static void encrypt(Boolean showoOutput){
        //Text verschlüsseln
        Encryptor encryptor = new Encryptor();
        encryptor.setShowOutput(showoOutput);
        String encrypted = encryptor.encryptMessage("text.txt", "pk.txt");

        FileHandler.export(encrypted, "chiffre.txt");
        System.out.println("Text aus text.txt in chiffre.txt mit pk.txt verschlüsselt");
    }

    private static void decrypt(Boolean showOutput){
        //Text entschlüsseln
        Decryptor decryptor = new Decryptor();
        decryptor.setShowOutput(showOutput);
        String decrypted = decryptor.decryptMessage("chiffre.txt", "sk.txt");

        System.out.println("Text aus chiffre.txt mit sk.txt entschlüsselt");

        //Entschlüsselten Text ausgeben
        System.out.println();
        System.out.println("Entschlüsselter Text: ");
        System.out.println(decrypted);
    }
}
