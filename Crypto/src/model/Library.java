package model;

import exeptions.CryptographyException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Library {

    private static final Path LIBRARY_FILE = Path.of("Library.txt");
    private static HashSet<String> uniqueLibrary;
    private static final int COUNT_ON_LINE = 10;
    private static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р',
            'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',
            '.', ',', '«', '»', '"', '\'', ':', '!', '?', '\n' ,' ',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    public static final int ALPHABET_SIZE = ALPHABET.length;



    public int getIndexCharFromLibrary(char c) {

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (ALPHABET[i] == c) {
                return i;
            }
        }
        throw new CryptographyException("wrong symbol in text:" + c);
    }

    public char getCharFromLibrary(int index) {
        if (index < 0 || index > ALPHABET_SIZE) {
            throw new CryptographyException("wrong index for library");
        }

        return ALPHABET[index];
    }

    public static HashSet<String> getUniqueLibrary() {

        return uniqueLibrary;

    }

    public static void initiationLibraryWords(){
        try {
            uniqueLibrary = new HashSet<>();
            String text = FileUtile.readFileFromPath(LIBRARY_FILE);
            String clearText = text.replaceAll(".,-\n—.,", "");


            String[] result = clearText.split(" ");

            uniqueLibrary.addAll(Arrays.asList(result));
        }catch (IOException e) {
            System.out.println("Error to load library.txt");
        }


    }

    public static void showUniqueLibrary() {
        System.out.println("Library: ");
        int count = 0;
        for(String curLine: uniqueLibrary) {
            System.out.print(curLine + " | ");
            count++;
            if (count % COUNT_ON_LINE == 0) {
                System.out.println();
            }
        }
    }
}


