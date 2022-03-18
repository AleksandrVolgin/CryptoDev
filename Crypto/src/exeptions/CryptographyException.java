package exeptions;

public class CryptographyException extends RuntimeException {

    private String errMessage;

    public CryptographyException(String message) {
        errMessage = message;
//        System.out.println();
//        System.out.println("Exception - " + message);
    }

    public void showErrorMessage() {
        System.out.println();
        System.out.println("Exception - " + errMessage);
    }

}
