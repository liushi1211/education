package cn.education.web.common;


public class JsonResponse<T> {
    private String errorCode;
    private String desc;
    private T result;

    public JsonResponse() {
        this(ErrorCode.SUCCESS,null);
    }

    public JsonResponse(String errorCode, String desc, T result) {
        this.desc = desc;
        this.errorCode = errorCode;
        this.result = result;
    }

    public JsonResponse(ErrorCode errorCode,T result){
        this.errorCode = errorCode.getCode();
        this.desc = errorCode.getDesc();
        this.result = result;
    }

    public JsonResponse(String errorCode, String desc) {
        this.desc = desc;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
