package wechat.core.entity.api;

public class APIResult <T> {
    private String code;
    private ErrorMsg errorMsg;
    private T successMsg;

    public static final String SUCCESS="success";//接口执行成功
    public static final String FAILURE="failure";//接口返回错误
    public static final String ERROR="error";//接口调用异常

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ErrorMsg getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(ErrorMsg errorMsg) {
        this.errorMsg = errorMsg;
        this.code=FAILURE;
    }

    public T getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(T successMsg) {
        this.successMsg = successMsg;
        this.code=SUCCESS;
    }

    public APIResult() {
    }

    public APIResult(ErrorMsg errorMsg) {
        this.errorMsg = errorMsg;
        this.code=FAILURE;
    }

    public APIResult(T successMsg){
        this.successMsg=successMsg;
        this.code=SUCCESS;
    }

    public APIResult(String code){
        this.code=code;
    }
}
