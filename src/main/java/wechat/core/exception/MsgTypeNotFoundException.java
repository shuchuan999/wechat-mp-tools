package wechat.core.exception;

public class MsgTypeNotFoundException extends WeChatException {
    public MsgTypeNotFoundException() {
    }

    public MsgTypeNotFoundException(String message) {
        super(message);
    }

    public MsgTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MsgTypeNotFoundException(Throwable cause) {
        super(cause);
    }
}
