package cn.education.web.model;

import java.util.Date;

public class TbLearnRecord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_learn_record.id
     *
     * @mbggenerated Tue Feb 26 20:24:56 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_learn_record.class_id
     *
     * @mbggenerated Tue Feb 26 20:24:56 CST 2019
     */
    private Integer classId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_learn_record.user_id
     *
     * @mbggenerated Tue Feb 26 20:24:56 CST 2019
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_learn_record.learn_time
     *
     * @mbggenerated Tue Feb 26 20:24:56 CST 2019
     */
    private Date learnTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_learn_record.id
     *
     * @return the value of tb_learn_record.id
     *
     * @mbggenerated Tue Feb 26 20:24:56 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_learn_record.id
     *
     * @param id the value for tb_learn_record.id
     *
     * @mbggenerated Tue Feb 26 20:24:56 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_learn_record.class_id
     *
     * @return the value of tb_learn_record.class_id
     *
     * @mbggenerated Tue Feb 26 20:24:56 CST 2019
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_learn_record.class_id
     *
     * @param classId the value for tb_learn_record.class_id
     *
     * @mbggenerated Tue Feb 26 20:24:56 CST 2019
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_learn_record.user_id
     *
     * @return the value of tb_learn_record.user_id
     *
     * @mbggenerated Tue Feb 26 20:24:56 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_learn_record.user_id
     *
     * @param userId the value for tb_learn_record.user_id
     *
     * @mbggenerated Tue Feb 26 20:24:56 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_learn_record.learn_time
     *
     * @return the value of tb_learn_record.learn_time
     *
     * @mbggenerated Tue Feb 26 20:24:56 CST 2019
     */
    public Date getLearnTime() {
        return learnTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_learn_record.learn_time
     *
     * @param learnTime the value for tb_learn_record.learn_time
     *
     * @mbggenerated Tue Feb 26 20:24:56 CST 2019
     */
    public void setLearnTime(Date learnTime) {
        this.learnTime = learnTime;
    }
}