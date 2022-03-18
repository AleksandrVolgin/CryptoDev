package model;

import exeptions.CryptographyException;

public class Cryptographer {

    private String originalText;
    private String encryptedText;
    private int key = 0;

    public Cryptographer() {

    }

    public Cryptographer(String originalText, int key) {

        this.originalText = originalText.toLowerCase();
        this.key = key;

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

    private void checkParam() {
        if (originalText.isBlank()) {
            throw new CryptographyException("the original text is blank!");

        }
        if (key == 0) {
            throw new CryptographyException("the key is empty");

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

        if (decode) {
            if ((index + key) > charSetSize) {
                return (index + key - charSetSize);
            } else {
                return index + key;
            }
        } else {
            if ((index - key) < 0) {
                return (charSetSize + (index - key));
            } else {
                return index - key;
            }
        }
    }
}
