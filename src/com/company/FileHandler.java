package com.company;

import java.io.*;

public class FileHandler {

    public static String importText(String path) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        } catch (FileNotFoundException e1) {
            System.out.println("Datei " + path + " nicht gefunden.");
            //e1.printStackTrace();
        }
        String text = "";
        String line;

        try {
            while ((line = br.readLine()) != null) {
                text += line;
            }
        } catch (IOException e1) {
            //e1.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("Datei konnte nicht geschlossen werden");
            }
        }
        return text;
    }

    public static void export(String text, String path) {
        File file = new File(path);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);
        } catch (IOException e1) {
            //e1.printStackTrace();
            System.out.println("Datei" + path +  "konnte nicht erstellt werden.");
        } finally {
            try {
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
