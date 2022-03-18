package model;

import exeptions.CryptographyException;

public class Library {

    private static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и','к', 'л', 'м', 'н', 'о', 'п', 'р',
            'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',
            '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
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



}
