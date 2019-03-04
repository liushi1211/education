package cn.education.web.business.impl;

import cn.education.web.business.ClassBusiness;
import cn.education.web.common.ErrorCode;
import cn.education.web.common.JsonResponse;
import cn.education.web.common.PageResponse;
import cn.education.web.mapper.TbClassMapper;
import cn.education.web.mapper.TbCommentMapper;
import cn.education.web.mapper.TbUserMapper;
import cn.education.web.model.TbComment;
import cn.education.web.model.TbUser;
import cn.education.web.model.TbUserExample;
import cn.education.web.model.dto.QueryClassDto;
import cn.education.web.model.vo.ClassDetail;
import cn.education.web.model.vo.ClassInstance;
import cn.education.web.model.vo.CommentDto;
import com.github.pagehelper.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassBusinessImpl extends BaseImpl implements ClassBusiness {

    @Resource
    private TbClassMapper classMapper;

    @Resource
    private TbUserMapper userMapper;

    @Resource
    private TbCommentMapper commentMapper;


    @Override
    public PageResponse<ClassInstance> queryClass(QueryClassDto queryClassDto) {
        Page page = setPageInfo(queryClassDto);
        List<ClassInstance> list = classMapper.queryClass(queryClassDto);
        PageResponse<ClassInstance> response = new PageResponse<ClassInstance>();
        response.setResult(list);
        response.setTotal(page.getTotal());
        return response;
    }

    @Override
    public PageResponse<ClassInstance> queryCurrentLearnRecord(QueryClassDto queryClassDto) {
        if(queryClassDto== null || StringUtils.isBlank(queryClassDto.getWxNo())){
            return new PageResponse<>();
        }
        TbUserExample ex = new TbUserExample();
        ex.createCriteria().andWxNoEqualTo(queryClassDto.getWxNo());
        List<TbUser> users = userMapper.selectByExample(ex);
        if(CollectionUtils.isEmpty(users)){
            return new PageResponse<>();
        }

        Page page = setPageInfo(queryClassDto);
        List<ClassInstance> list = classMapper.queryCurrentLearnRecord(users.get(0).getId());
        PageResponse<ClassInstance> response = new PageResponse<ClassInstance>();
        response.setResult(list);
        response.setTotal(page.getTotal());
        return response;
    }

    @Override
    public PageResponse<ClassInstance> queryFavoriteClass(QueryClassDto queryClassDto) {
        if(queryClassDto== null || StringUtils.isBlank(queryClassDto.getWxNo())){
            return new PageResponse<>();
        }
        TbUserExample ex = new TbUserExample();
        ex.createCriteria().andWxNoEqualTo(queryClassDto.getWxNo());
        List<TbUser> users = userMapper.selectByExample(ex);
        if(CollectionUtils.isEmpty(users)){
            return new PageResponse<>();
        }

        Page page = setPageInfo(queryClassDto);
        List<ClassInstance> list = classMapper.queryFavoriteClass(users.get(0).getId());
        PageResponse<ClassInstance> response = new PageResponse<ClassInstance>();
        response.setResult(list);
        response.setTotal(page.getTotal());
        return response;
    }

    @Override
    public JsonResponse<ClassDetail> getClassDetail(Integer id) {
        if(id == null){
            return new JsonResponse<ClassDetail>();
        }
        ClassDetail classDetail = classMapper.getDetailById(id);
        return new JsonResponse<ClassDetail>(ErrorCode.SUCCESS,classDetail) ;
    }

    @Override
    public PageResponse<CommentDto> getCommentByClassId(QueryClassDto queryClassDto) {
        Page page = setPageInfo(queryClassDto);
        List<CommentDto> list = commentMapper.getCommentByClassId(queryClassDto.getId());
        PageResponse<CommentDto> response = new PageResponse<CommentDto>();
        response.setResult(list);
        response.setTotal(page.getTotal());
        return response;
    }
}
