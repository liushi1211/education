package cn.education.web.model.vo;

import java.util.Date;

/**
 * Created by liushi on 19/3/3.
 */
public class ClassInstance {

    private Integer id;

    private String className;

    private String classDesc;

    private String langType;

    private String imageUrl;

    private Short totalScore;

    /**
     * 最近一次学习时间
     */
    private Date learnTime;

    /**
     * 学习次数
     */
    private Integer totalLearnTimes;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    public String getLangType() {
        return langType;
    }

    public void setLangType(String langType) {
        this.langType = langType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Short getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Short totalScore) {
        this.totalScore = totalScore;
    }

    public Date getLearnTime() {
        return learnTime;
    }

    public void setLearnTime(Date learnTime) {
        this.learnTime = learnTime;
    }

    public Integer getTotalLearnTimes() {
        return totalLearnTimes;
    }

    public void setTotalLearnTimes(Integer totalLearnTimes) {
        this.totalLearnTimes = totalLearnTimes;
    }
}
