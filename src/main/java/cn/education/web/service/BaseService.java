package cn.education.web.service;




import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * baseSerivice用于封装一些公共方法
 */
public interface BaseService<D extends Mapper<T>, T> {

    /**
     * 查询所有
     * @return
     */
    List<T> selectAll();

    /**
     * 插入
     * @param t
     * @return
     */
    int insertSelective(T t);

    /**
     * 查询
     * @param t
     * @return
     */
    T selectByPrimaryKey(T t);

    /**
     * 查询
     * @param t
     * @return
     */
    T selectOne(T t);

    /**
     * 查询
     * @param t
     * @return
     */
    List<T> select(T t);

    /**
     * 更新
     * @param t
     * @return
     */
    int updateByPrimaryKey(T t);

    /**
     * 删除
     * @param t
     */
    int deleteByPrimaryKey(T t);

    /**
     * 更新
     * @param t
     * @return
     */
    int updateByPrimaryKeySelective(T t);


}
