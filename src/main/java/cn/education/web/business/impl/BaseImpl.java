package cn.education.web.business.impl;

import cn.education.web.common.PageParam;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Created by liushi on 19/2/27.
 */
public class BaseImpl {

    public static final Integer DEFAULT_PAGE_NO = 1;

    public static final Integer DEFAULT_PAGE_SIZE = 10;



    protected Page setPageInfo(PageParam pageParam){
        if(pageParam.getPageNo() ==null){
            pageParam.setPageNo(DEFAULT_PAGE_NO);
        }
        if(pageParam.getPageSize() == null ){
            pageParam.setPageSize(DEFAULT_PAGE_SIZE);
        }
        Page page = PageHelper.startPage(pageParam.getPageNo(),pageParam.getPageSize());
        return page;
    }

}
