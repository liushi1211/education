package cn.education.web.service.impl;


import cn.education.web.service.BaseService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public class BaseServiceImpl<D extends Mapper<T>, T> implements BaseService<D, T> {

    @Autowired
    protected D mapper;

    public List<T> selectAll() {
        return mapper.selectAll();
    }

    public int insertSelective(T t) {
        return mapper.insertSelective(t);
    }

    public T selectByPrimaryKey(T t) {
        return mapper.selectByPrimaryKey(t);
    }

    public int deleteByPrimaryKey(T t) {
        return mapper.deleteByPrimaryKey(t);
    }

    public int updateByPrimaryKeySelective(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    public T selectOne(T t) {
        return mapper.selectOne(t);
    }

    public List<T> select(T t) {
        return mapper.select(t);
    }

    public int updateByPrimaryKey(T t) {
        return mapper.updateByPrimaryKey(t);
    }

    /**
     * 设置分页信息
     * @param limit
     * @param offset
     */
    protected void setPageInfo(int limit, int offset){
        int pageNumber = offset / limit + 1;
        PageHelper.startPage(pageNumber, limit);
    }
}
