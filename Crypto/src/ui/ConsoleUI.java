package ui;

import exeptions.CryptographyException;
import main.Main;
import model.Cryptographer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private static final String EXIT_CHAR = "q";

    public static void startUI() {
        Scanner consoleScanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            System.out.println("Follow next step's or press " + EXIT_CHAR + " end ENTER to Exit!");
            if (Main.getSourceFile() == null) {
                System.out.println("1. Please, select source file:");

                Path src = Paths.get(consoleScanner.nextLine());
                if (Files.exists(src)) {
                    Main.setSourceFile(src);
                } else {
                    try {
                        Files.createFile(src);
                        Main.setSourceFile(src);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Source file selected:" + Main.getSourceFile());
            }
            if (Main.getDestCesarEncryptFile() == null) {
                System.out.println("1. Please, select destination file:");

                Path dst = Paths.get(consoleScanner.nextLine());
                if (Files.exists(dst)) {
                    Main.setDestCesarEncryptFile(dst);

                } else {
                    try {
                        Files.createFile(dst);
                        Main.setDestCesarEncryptFile(dst);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Destination file selected:" + Main.getDestCesarEncryptFile());
            }
            showMenu();

            String choice = consoleScanner.nextLine().toLowerCase();

            switch (choice) {
                case "1" : {
                    choiceOneEncrypt();
                    break;
                }
                case "2" : {
                    choiceSecondDecode();
                    break;
                }
                case "3" : {
                    System.out.println("third");
                    break;
                }
                case "4" : {
                    System.out.println("Four");
                    break;
                }
                case "5" : {
                    System.out.println("Fives");
                    break;
                }
                case EXIT_CHAR : {
                    isExit = true;
                    break;
                }
            }
        }
    }

    private static void choiceOneEncrypt() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter key for encrypt file");
        int key = scanner.nextInt();
        Cryptographer coder = new Cryptographer();
        try {
            coder.setPrivateKey(key);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Main.getSourceFile().toFile()));
            List<String> list = new ArrayList<>();
            String curLine;
            while ((curLine = bufferedReader.readLine()) != null) {
                coder.setOriginalText(curLine.toLowerCase());
                coder.encrypt();
                list.add(coder.getEncryptText());
            }

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Main.getDestCesarEncryptFile().toFile()));
            for(String line: list) {
                bufferedWriter.write(line + "\n");
            }
            bufferedReader.close();
            bufferedWriter.close();

        } catch (CryptographyException e) {
            e.showErrorMessage();
            choiceOneEncrypt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showWellDone();
    }

    private static void choiceSecondDecode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter key for decode file");
        int key = scanner.nextInt();
        Cryptographer coder = new Cryptographer();
        try {
            coder.setPrivateKey(key);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Main.getDestCesarEncryptFile().toFile()));
            List<String> list = new ArrayList<>();
            String curLine;
            while ((curLine = bufferedReader.readLine()) != null) {
                coder.setOriginalText(curLine.toLowerCase());
                coder.decode();
                list.add(coder.getEncryptText());
            }

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Main.getDestCesarEncryptFile().toFile()));
            for(String line: list) {
                bufferedWriter.write(line + "\n");
            }
            bufferedReader.close();
            bufferedWriter.close();

        } catch (CryptographyException e) {
            e.showErrorMessage();
            choiceOneEncrypt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showWellDone();
    }

    private static void showWellDone() {
        System.out.println("*******************************");
        System.out.println("**                           **");
        System.out.println("**      Successfully!!!      **");
        System.out.println("**                           **");
        System.out.println("*******************************");
    }

    private static void showMenu() {
        System.out.println("*******************************");
        System.out.println("Source file selected:" + Main.getSourceFile());
        System.out.println("Destination file selected:" + Main.getDestCesarEncryptFile());
        System.out.println("===============================");
        System.out.println("Select 1 for encrypt with Cesar key");
        System.out.println("Select 2 for decode with Cesar key");
        System.out.println("Select 3 for decode with Brute Force");
        System.out.println("Select 4 for decode with Brute Force (statistics mode)");
        System.out.println("===============================");
        System.out.println("Select 5 for show decode file");
    }


}
