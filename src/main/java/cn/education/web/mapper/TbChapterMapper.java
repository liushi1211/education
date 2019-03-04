package cn.education.web.mapper;

import cn.education.web.model.TbChapter;
import cn.education.web.model.TbChapterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbChapterMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chapter
     *
     * @mbggenerated Tue Feb 26 20:22:30 CST 2019
     */
    int countByExample(TbChapterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chapter
     *
     * @mbggenerated Tue Feb 26 20:22:30 CST 2019
     */
    int deleteByExample(TbChapterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chapter
     *
     * @mbggenerated Tue Feb 26 20:22:30 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chapter
     *
     * @mbggenerated Tue Feb 26 20:22:30 CST 2019
     */
    int insert(TbChapter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chapter
     *
     * @mbggenerated Tue Feb 26 20:22:30 CST 2019
     */
    int insertSelective(TbChapter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chapter
     *
     * @mbggenerated Tue Feb 26 20:22:30 CST 2019
     */
    List<TbChapter> selectByExample(TbChapterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chapter
     *
     * @mbggenerated Tue Feb 26 20:22:30 CST 2019
     */
    TbChapter selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chapter
     *
     * @mbggenerated Tue Feb 26 20:22:30 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbChapter record, @Param("example") TbChapterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chapter
     *
     * @mbggenerated Tue Feb 26 20:22:30 CST 2019
     */
    int updateByExample(@Param("record") TbChapter record, @Param("example") TbChapterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chapter
     *
     * @mbggenerated Tue Feb 26 20:22:30 CST 2019
     */
    int updateByPrimaryKeySelective(TbChapter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_chapter
     *
     * @mbggenerated Tue Feb 26 20:22:30 CST 2019
     */
    int updateByPrimaryKey(TbChapter record);
}