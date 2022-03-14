package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        System.out.println(line);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Hi");
    }
}
