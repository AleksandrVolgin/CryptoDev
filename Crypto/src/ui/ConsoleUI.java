package ui;


import model.FileUtile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ConsoleUI {
    public static final String EXIT_CHAR = "q";

    public static Path initialFilePath(ConsoleReader scanner, String message) {
        System.out.println(message);
        Path curPath = Paths.get(scanner.readFromConsole());
        if (!Files.exists(curPath)) {
            FileUtile.createNewFile(curPath);
            System.out.println("File not found, " + curPath + "was created!");
        }

        return curPath;
    }

    public static String receiveFromScanner(ConsoleReader scanner, String message) {
        System.out.println(message);
        return scanner.readFromConsole().toLowerCase();
    }


    public static void showWellDone() {
        System.out.println("*******************************");
        System.out.println("**                           **");
        System.out.println("**      Successfully!!!      **");
        System.out.println("**                           **");
        System.out.println("*******************************");
    }

    public static void showMenu(Path srcPath, Path dstPath) {
        System.out.println("*******************************");
        System.out.println("Source file selected:" + srcPath);
        System.out.println("Destination file selected:" + dstPath);
        System.out.println("===============================");
        System.out.println("Select 1 for encrypt with Cesar key");
        System.out.println("Select 2 for decode with Cesar key");
        System.out.println("Select 3 for decode with Brute Force");
        System.out.println("===============================");
        System.out.println("Select 4 for show decode file");
    }

    public static void showMenuForBruteForce() {
        System.out.println("*******************************");
        System.out.println("**                           **");
        System.out.println("**    Brute Force decode!    **");
        System.out.println("**                           **");
        System.out.println("*******************************");
        System.out.println("Select 1 for adding representative text/words in library");
        System.out.println("Select 2 decode with brute force!");
        System.out.println("Select 3 for exit to main menu");
    }
}


