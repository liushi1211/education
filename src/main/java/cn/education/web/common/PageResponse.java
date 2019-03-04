package cn.education.web.common;

import java.util.List;


public class PageResponse<T> {

    private String errorCode;
    private String desc;
    private List<T> result;
    private Long total;

    public PageResponse() {
        this(ErrorCode.SUCCESS,null,null);
    }

    public PageResponse(String errorCode, String desc, List<T> result, Long total) {
        this.errorCode = errorCode;
        this.desc = desc;
        this.result = result;
        this.total = total;
    }

    public PageResponse(ErrorCode errorCode,List<T> result, Long total){
        this.errorCode = errorCode.getCode();
        this.desc = errorCode.getDesc();
        this.result = result;
        this.total = total;
    }

    public PageResponse(String errorCode, String desc) {
        this.errorCode = errorCode;
        this.desc = desc;
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

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
