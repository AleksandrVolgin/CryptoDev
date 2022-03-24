package model;

import exeptions.CryptographyException;



public class Cryptographer {

    private String originalText;
    private String encryptedText;
    private int key = 0;

    public Cryptographer() {

    }

    public void encrypt() {

        checkParam();

        Library lib = new Library();
        StringBuilder stringBuilder = new StringBuilder();

        char[] charset = originalText.toCharArray();

        for(char ch: charset) {
            int normalKey = normalizationKey(key, lib.getIndexCharFromLibrary(ch), Library.ALPHABET_SIZE, false);
            stringBuilder.append(lib.getCharFromLibrary(normalKey));
        }
        setEncryptedText(stringBuilder.toString());

    }

    public void decode() {
        checkParam();

        Library lib = new Library();
        StringBuilder stringBuilder = new StringBuilder();

        char[] charset = originalText.toCharArray();

        for(char ch: charset) {
            int normalKey = normalizationKey(key, lib.getIndexCharFromLibrary(ch), Library.ALPHABET_SIZE, true);
            stringBuilder.append(lib.getCharFromLibrary(normalKey));
        }
        setEncryptedText(stringBuilder.toString());

    }

    public int findKeyForDecodeWithBruteForce(String encryptText) {

        Cryptographer decoder = new Cryptographer();
        decoder.setOriginalText(encryptText);
        int maxMatch = 0;
        int maxMatchesKey = 1;

        for (int i = 1; i < Library.ALPHABET_SIZE; i++) {

            int count = 0;

            decoder.setPrivateKey(i);
            decoder.decode();

            for (String line: Library.getUniqueLibrary()) {
                count += countInner(decoder.getEncryptText(), line);
            }

            System.out.println("Key: " + i + " find " + count + " matches");

            if (count > maxMatch) {
                maxMatch = count;
                maxMatchesKey = i;
            }

        }

        System.out.println("Best of the best " + maxMatchesKey);
        return maxMatchesKey;
    }

    public static int countInner(String str, String target) {
        return (str.length() - str.replace(target, "").length()) / target.length();
    }

    private void checkParam() {
        if (originalText.isBlank()) {
            throw new CryptographyException("Original text is blank!");

        }
        if (key == 0) {
            throw new CryptographyException("Key is empty");

        }
    }

    public void setOriginalText(String originalText) {

        this.originalText = originalText;
    }

    public void setPrivateKey(int key) {
        if(key <= 0) {
            throw new CryptographyException("Key must be positive, end not zero!");
        }
        if(key >= Library.ALPHABET_SIZE) {
            throw new CryptographyException("Key is too big!");
        }

        this.key = key;
    }

    public String getEncryptText() {
        return encryptedText;
    }

    private void setEncryptedText(String encryptedText) {
        this.encryptedText = encryptedText;
    }

    private int normalizationKey(int key, int index, int charSetSize, boolean decode) {
        charSetSize = charSetSize-1;
        if (decode) {
            if ((index - key) < 0) {
                return (charSetSize + (index - key));
            } else {
                return index - key;
            }
        } else {
            if ((index + key) > charSetSize) {
                return (index + key - charSetSize);
            } else {
                return index + key;
            }
        }
    }
}


