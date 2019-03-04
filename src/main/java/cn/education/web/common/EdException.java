package cn.education.web.common;



import java.text.MessageFormat;

public class EdException extends Exception {

    private String errorCode;

    private String desc;

    public EdException(ErrorCode errorCode) {
        super(errorCode.getDesc());
        this.errorCode = errorCode.getCode();
        this.desc = errorCode.getDesc();
    }

    public EdException(ErrorCode errorCode, Object ... o) {
        super(MessageFormat.format(errorCode.getDesc(), o));
        this.errorCode = errorCode.getCode();
        this.desc = MessageFormat.format(errorCode.getDesc(), o);
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

    @Override
    public String getMessage() {
        return desc;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EdException{");
        sb.append("{code='").append(errorCode).append('\'');
        sb.append(", message='").append(desc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
