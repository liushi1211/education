package cn.education.web.business.impl;

import cn.education.web.business.ClassBusiness;
import cn.education.web.common.ErrorCode;
import cn.education.web.common.JsonResponse;
import cn.education.web.common.PageResponse;
import cn.education.web.mapper.*;
import cn.education.web.model.*;
import cn.education.web.model.dto.AddCommentDto;
import cn.education.web.model.dto.QueryClassDto;
import cn.education.web.model.vo.ClassDetail;
import cn.education.web.model.vo.ClassInstance;
import cn.education.web.model.vo.CommentDto;
import com.github.pagehelper.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ClassBusinessImpl extends BaseImpl implements ClassBusiness {

    @Resource
    private TbClassMapper classMapper;

    @Resource
    private TbUserMapper userMapper;

    @Resource
    private TbCommentMapper commentMapper;

    @Resource
    private TbChapterMapper chapterMapper;

    @Resource
    private TbLearnRecordMapper learnRecordMapper;

    @Resource
    private TbFavoriteMapper favoriteMapper;


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

        TbUser user = getUserByWxNo(queryClassDto.getWxNo());
        if(null == user){
            return new PageResponse<>();
        }

        Page page = setPageInfo(queryClassDto);
        List<ClassInstance> list = classMapper.queryCurrentLearnRecord(user.getId());
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
        TbUser user = getUserByWxNo(queryClassDto.getWxNo());
        if(null == user){
            return new PageResponse<>();
        }
        Page page = setPageInfo(queryClassDto);
        List<ClassInstance> list = classMapper.queryFavoriteClass(user.getId());
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

    @Override
    @Transactional
    public JsonResponse<Boolean> conversionClass(QueryClassDto queryClassDto) {
        if(null == queryClassDto.getId() || StringUtils.isBlank(queryClassDto.getWxNo())){
            new JsonResponse<ClassDetail>(ErrorCode.PARAM_ERROR) ;
        }
        TbUser user = getUserByWxNo(queryClassDto.getWxNo());
        if(null == user){
            return new JsonResponse<>(ErrorCode.USER_NOT_EXIST);
        }
        TbClass tbClass = classMapper.selectByPrimaryKey(queryClassDto.getId());
        if(null == tbClass){
            return new JsonResponse<>(ErrorCode.CLASS_NOT_EXIST);
        }
        if(user.getScore() < tbClass.getTotalScore()){
            return new JsonResponse<>(ErrorCode.SCORE_NOT_ENOUGH);
        }
        TbUser updateUser = new TbUser();
        updateUser.setId(user.getId());
        Short score = new Short((short)(user.getScore()-tbClass.getTotalScore()));
        updateUser.setScore(score);
        userMapper.updateByPrimaryKeySelective(updateUser);
        classMapper.conversionClass(queryClassDto.getId(),user.getId());
        return new JsonResponse<>();
    }

    @Override
    public JsonResponse<Boolean> commentClass(AddCommentDto addCommentDto) {
        if(StringUtils.isBlank(addCommentDto.getContent()) || addCommentDto.getContent().length() > 256){
            new JsonResponse<ClassDetail>(ErrorCode.PARAM_ERROR) ;
        }

        TbUser user = getUserByWxNo(addCommentDto.getWxNo());
        if(null == user){
            return new JsonResponse<>(ErrorCode.USER_NOT_EXIST);
        }
        TbClass tbClass = classMapper.selectByPrimaryKey(addCommentDto.getClassId());
        if(null == tbClass){
            return new JsonResponse<>(ErrorCode.CLASS_NOT_EXIST);
        }
        TbComment comment = new TbComment();
        comment.setClassId(addCommentDto.getClassId());
        comment.setContent(addCommentDto.getContent());
        comment.setUserId(user.getId());
        commentMapper.insertSelective(comment);
        return  new JsonResponse<>();
    }

    @Override
    public PageResponse<ClassInstance> getConversionClass(QueryClassDto queryClassDto) {
        Page page = setPageInfo(queryClassDto);
        List<ClassInstance> list = classMapper.getConversionClass(queryClassDto);
        PageResponse<ClassInstance> response = new PageResponse<ClassInstance>();
        response.setResult(list);
        response.setTotal(page.getTotal());
        return response;
    }

    @Override
    public JsonResponse<String> getDownLoanUrl(TbChapter chapter) {
        TbChapterExample ex = new TbChapterExample();
        ex.createCriteria().andChapterNoEqualTo(chapter.getChapterNo()).andClassIdEqualTo(chapter.getClassId());
        List<TbChapter> chapters = chapterMapper.selectByExample(ex);
        if(CollectionUtils.isNotEmpty(chapters)){
            return new JsonResponse<>(ErrorCode.SUCCESS,chapters.get(0).getUrl());
        }
        return new JsonResponse<>();
    }

    @Override
    public JsonResponse<Boolean> commitLearn(QueryClassDto queryClassDto) {
        TbUser user = getUserByWxNo(queryClassDto.getWxNo());
        if(null == user){
            return new JsonResponse<>(ErrorCode.USER_NOT_EXIST);
        }
        TbClass tbClass = classMapper.selectByPrimaryKey(queryClassDto.getId());
        if(null == tbClass){
            return new JsonResponse<>(ErrorCode.CLASS_NOT_EXIST);
        }
        TbLearnRecord learnRecord = new TbLearnRecord();
        learnRecord.setClassId(queryClassDto.getId());
        learnRecord.setUserId(user.getId());
        learnRecord.setLearnTime(new Date());
        learnRecordMapper.insertSelective(learnRecord);
        classMapper.commitLearn(queryClassDto.getId());

        return new JsonResponse<>();
    }

    @Override
    public JsonResponse<Boolean> favoriteClass(QueryClassDto queryClassDto) {
        TbUser user = getUserByWxNo(queryClassDto.getWxNo());
        if(null == user){
            return new JsonResponse<>(ErrorCode.USER_NOT_EXIST);
        }
        TbClass tbClass = classMapper.selectByPrimaryKey(queryClassDto.getId());
        if(null == tbClass){
            return new JsonResponse<>(ErrorCode.CLASS_NOT_EXIST);
        }
        TbFavorite favorite = new TbFavorite();
        favorite.setClassId(tbClass.getId());
        favorite.setUserId(user.getId());
        favoriteMapper.insertSelective(favorite);
        return new JsonResponse<>();
    }

    private TbUser getUserByWxNo(String wxNo){
        TbUserExample ex = new TbUserExample();
        ex.createCriteria().andWxNoEqualTo(wxNo);
        List<TbUser> users = userMapper.selectByExample(ex);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }
        return users.get(0);
    }
}
