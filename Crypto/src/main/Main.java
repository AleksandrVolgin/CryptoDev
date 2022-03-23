package main;

import exeptions.CryptographyException;
import model.Cryptographer;
import model.FileUtile;
import model.Library;
import ui.ConsoleReader;
import ui.ConsoleUI;

import java.io.*;


import java.nio.file.Path;

import java.util.HashSet;



public class Main {

    public static void main(String[] args) {

        ConsoleReader scanner = ConsoleReader.getInstance();
        Library.initiationLibraryWords();
        initialStartMenu(scanner);

    }

    public static void initialStartMenu(ConsoleReader scanner) {

        System.out.println("Follow next step's or press " + ConsoleUI.EXIT_CHAR + " end ENTER to Exit!");

        Path sourceFile = ConsoleUI.initialFilePath(scanner, "1. Please, select source file:");

        Path destinationFile = ConsoleUI.initialFilePath(scanner, "2. Please, select destination file:");

        boolean isExit = false;

        while (!isExit) {

            ConsoleUI.
            showMenu(sourceFile, destinationFile);

            String select = ConsoleUI.receiveFromScanner(scanner, "Enter num of menu or press " + ConsoleUI.EXIT_CHAR );

            switch (select) {
                case "1" : {
                    encrypt(scanner, sourceFile, destinationFile);
                    break;
                }
                case "2" : {
                    decode(scanner, destinationFile);
                    break;
                }
                case "3" : {
                    decodeBruteForceMenu(scanner, destinationFile);
                    break;
                }
                case "4" : {
                    showDecodedFile(destinationFile);
                    break;
                }
                case ConsoleUI.EXIT_CHAR : {
                    isExit = true;
                    break;
                }
            }
        }

    }

    private static void showDecodedFile(Path destinationFile) {

        try {
            String text = FileUtile.readFileFromPath(destinationFile);
            System.out.println(text);
        } catch (IOException e) {
            System.out.println("File reader error!" + destinationFile);
        }

    }

    private static void decodeBruteForceMenu(ConsoleReader scanner, Path destinationFile){

        ConsoleUI.showMenuForBruteForce();
        String choice = ConsoleUI.receiveFromScanner(scanner, "Enter num of menu or press " + ConsoleUI.EXIT_CHAR);

        switch (choice) {
            case "1": {
                Library.showUniqueLibrary();
                break;
            }
            case "2": {
                decodeWithBruteForce(destinationFile);
                break;
            }
            case "3": {
                System.out.println("EXIT to MAIN menu!");
            }
        }



    }

    private static void decodeWithBruteForce(Path destinationFile) {

        try {
            String text = FileUtile.readFileFromPath(destinationFile);
            Cryptographer decoder = new Cryptographer();
            int key  = decoder.findKeyForDecodeWithBruteForce(text);
            decoder.setOriginalText(text);
            decoder.setPrivateKey(key);
            decoder.decode();
            FileUtile.writeFile(destinationFile, decoder.getEncryptText());

        } catch (IOException e) {
            System.out.println("File reader error!" + destinationFile);
        }
    }

    private static void decode(ConsoleReader scanner, Path destinationFile) {
        int key = Integer.parseInt(ConsoleUI.receiveFromScanner(scanner, "Enter key for decode file"));
        Cryptographer decoder = new Cryptographer();
        try {
            decoder.setPrivateKey(key);
            String text = FileUtile.readFileFromPath(destinationFile);

            decoder.setOriginalText(text);
            decoder.decode();

            FileUtile.writeFile(destinationFile, decoder.getEncryptText());

            ConsoleUI.showWellDone();

        } catch (CryptographyException e) {
            e.showErrorMessage();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("File reader error! " + destinationFile);
        }

    }

    public static void encrypt(ConsoleReader scanner, Path sourceFile, Path destinationFile) {

        int key = Integer.parseInt(ConsoleUI.receiveFromScanner(scanner, "Enter key for encrypt file"));
        Cryptographer coder = new Cryptographer();
        try {
            coder.setPrivateKey(key);

            String text = FileUtile.readFileFromPath(sourceFile);
            coder.setOriginalText(text);
            coder.encrypt();
            String encryptText = coder.getEncryptText();
            FileUtile.writeFile(destinationFile, encryptText);

            ConsoleUI.showWellDone();

        } catch (CryptographyException e) {
            e.showErrorMessage();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}


