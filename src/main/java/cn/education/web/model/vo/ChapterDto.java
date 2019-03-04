package cn.education.web.model.vo;

import java.util.Date;

public class ChapterDto {

    private Integer id;

    private Short chapterNo;

    private String url;

    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(Short chapterNo) {
        this.chapterNo = chapterNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
