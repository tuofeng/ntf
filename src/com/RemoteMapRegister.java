package com;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteMapRegister {

    private static Map<String, List<String>> REGISTER = new HashMap<>();

    public static void main(String[] args) {
        REGISTER.put("com.RemoteMapRegister", new ArrayList<String>());
        saveFile();
    }


    private static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\work\\workspace\\wx\\pay\\hftest\\temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/temp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
