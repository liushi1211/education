package cn.education.web.mapper;

import cn.education.web.model.TbFavorite;
import cn.education.web.model.TbFavoriteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbFavoriteMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_favorite
     *
     * @mbggenerated Mon Mar 04 20:07:19 CST 2019
     */
    int countByExample(TbFavoriteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_favorite
     *
     * @mbggenerated Mon Mar 04 20:07:19 CST 2019
     */
    int deleteByExample(TbFavoriteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_favorite
     *
     * @mbggenerated Mon Mar 04 20:07:19 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_favorite
     *
     * @mbggenerated Mon Mar 04 20:07:19 CST 2019
     */
    int insert(TbFavorite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_favorite
     *
     * @mbggenerated Mon Mar 04 20:07:19 CST 2019
     */
    int insertSelective(TbFavorite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_favorite
     *
     * @mbggenerated Mon Mar 04 20:07:19 CST 2019
     */
    List<TbFavorite> selectByExample(TbFavoriteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_favorite
     *
     * @mbggenerated Mon Mar 04 20:07:19 CST 2019
     */
    TbFavorite selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_favorite
     *
     * @mbggenerated Mon Mar 04 20:07:19 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbFavorite record, @Param("example") TbFavoriteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_favorite
     *
     * @mbggenerated Mon Mar 04 20:07:19 CST 2019
     */
    int updateByExample(@Param("record") TbFavorite record, @Param("example") TbFavoriteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_favorite
     *
     * @mbggenerated Mon Mar 04 20:07:19 CST 2019
     */
    int updateByPrimaryKeySelective(TbFavorite record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_favorite
     *
     * @mbggenerated Mon Mar 04 20:07:19 CST 2019
     */
    int updateByPrimaryKey(TbFavorite record);
}