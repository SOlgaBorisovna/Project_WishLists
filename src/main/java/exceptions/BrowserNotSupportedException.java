package exceptions;

public class BrowserNotSupportedException extends RuntimeException{

    public BrowserNotSupportedException(String browserName) {
        super(String.format("Браузер %s не поддерживается, {}", browserName));
    }
}
