package model;

import main.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtile {

    public static String readFileFromPath(Path path) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()));
        StringBuilder stringBuilder = new StringBuilder();
        String curLine;

        while ((curLine = bufferedReader.readLine()) != null) {

            stringBuilder.append(curLine.toLowerCase() + "\n");

        }

        bufferedReader.close();
        return stringBuilder.toString();
    }

    public static boolean writeFile(Path path, String text) {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path.toFile()));
            bufferedWriter.write(text);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Could't save the file:" + path.toString());
            return false;
        }
        return true;
    }



    public static void createNewFile(Path path) {

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Could't create new file:" + path.toString());
            }
        }

    }



}
