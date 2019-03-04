package cn.education.web.model.dto;

import cn.education.web.common.PageParam;

/**
 * Created by liushi on 19/2/27.
 */
public class QueryClassDto extends PageParam {

    private Integer id;

    private String langType;

    private String className;

    private byte recommend;

    private String wxNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLangType() {
        return langType;
    }

    public void setLangType(String langType) {
        this.langType = langType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public byte getRecommend() {
        return recommend;
    }

    public void setRecommend(byte recommend) {
        this.recommend = recommend;
    }

    public String getWxNo() {
        return wxNo;
    }

    public void setWxNo(String wxNo) {
        this.wxNo = wxNo;
    }
}
