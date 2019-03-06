package cn.education.web.common;

public enum ErrorCode {
SUCCESS("0000","成功"),
FAIL("1000","系统异常"),
PARAM_ERROR("1001","参数错误"),
USER_NOT_EXIST("1002","用户不存在"),
    CLASS_NOT_EXIST("1003","课程不存在"),
    SCORE_NOT_ENOUGH("1004","积分不够")
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
