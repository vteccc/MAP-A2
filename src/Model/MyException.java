package Model;

public class MyException extends Throwable {
    private String message;

    public MyException(String s) {
        this.message = s;
    }

    public String getMessage() {
        return this.message;
    }
}
