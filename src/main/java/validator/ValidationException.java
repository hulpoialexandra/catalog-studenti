package validator;

public class ValidationException extends RuntimeException {
    private String mesaj;
    public ValidationException(String mesaj){
        super(mesaj);this.mesaj=mesaj;
    }

    public String getMesaj() {
        return mesaj;
    }
}
