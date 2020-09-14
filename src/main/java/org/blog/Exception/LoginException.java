package org.blog.Exception;

public class LoginException extends RuntimeException {
    private Integer code;
    private String message;

    public LoginException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public LoginException(String message, Integer code, String message1) {
        super(message);
        this.code = code;
        this.message = message1;
    }

    public LoginException(String message, Throwable cause, Integer code, String message1) {
        super(message, cause);
        this.code = code;
        this.message = message1;
    }

    public LoginException(Throwable cause, Integer code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message1;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

    protected LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
