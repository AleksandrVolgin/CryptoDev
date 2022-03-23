package exeptions;

public class CryptographyException extends RuntimeException {

    private String errMessage;

    public CryptographyException(String message) {

        errMessage = message;

    }

    public void showErrorMessage() {
        System.out.println();
        System.out.println("Exception - " + errMessage);
    }

}
