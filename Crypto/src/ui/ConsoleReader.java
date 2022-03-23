package ui;

import java.util.Scanner;

public final class ConsoleReader {

    private static ConsoleReader INSTANCE;
    private Scanner scanner = new Scanner(System.in);

    private ConsoleReader() {

    }

    public static ConsoleReader getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ConsoleReader();
        }

        return INSTANCE;
    }

    public String readFromConsole() {
        return scanner.nextLine();
    }
}
