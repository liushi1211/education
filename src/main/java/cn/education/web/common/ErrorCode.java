package cn.education.web.common;

public enum ErrorCode {
SUCCESS("0000","成功"),
FAIL("1000","系统异常")
    ;


    private String code;

    private String desc;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.desc = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
