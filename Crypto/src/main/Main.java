package main;

import model.Cryptographer;
import ui.ConsoleUI;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    private static Path sourceFile;
    private static Path destCesarEncryptFile;

    public static void main(String[] args) {
        System.out.println("Hello, guest");
        ConsoleUI.startUI();
//        Cryptographer coder = new Cryptographer("Я рюшил отдельным сообщением поставить ссылку на отправку PR.  Будет закреплено в канале. Всем, кто не понимает, что происходит, нужно посмотреть сегодняшнюю лекцию. Если кратко: это форма для отправки результатов работы над вашим проектом.", 5);
//        coder.encrypt();
//
//        Cryptographer uncoder = new Cryptographer(coder.getEncryptText(), -5);
//        uncoder.encrypt();


    }

    public static Path getSourceFile() {
        return sourceFile;
    }

    public static Path getDestCesarEncryptFile() {
        return destCesarEncryptFile;
    }

    public static void setSourceFile(Path sourcePath) {
        sourceFile = sourcePath;
    }

    public static void setDestCesarEncryptFile(Path sourcePath) {
        destCesarEncryptFile = sourcePath;
    }

}
