package cn.education.web.business;

import cn.education.web.common.JsonResponse;
import cn.education.web.common.PageResponse;
import cn.education.web.model.TbChapter;
import cn.education.web.model.dto.AddCommentDto;
import cn.education.web.model.dto.QueryClassDto;
import cn.education.web.model.vo.ClassDetail;
import cn.education.web.model.vo.ClassInstance;
import cn.education.web.model.vo.CommentDto;

import java.util.List;

public interface ClassBusiness {


    /**
     * 根据语种获取课程
     * @param queryClassDto
     * @return
     */
    PageResponse<ClassInstance> queryClass(QueryClassDto queryClassDto);

    /**
     * 查询最近学习记录
     * @param queryClassDto
     * @return
     */

    PageResponse<ClassInstance> queryCurrentLearnRecord(QueryClassDto queryClassDto);

    /**
     * 查询收藏课程
     * @param queryClassDto
     * @return
     */
    PageResponse<ClassInstance> queryFavoriteClass(QueryClassDto queryClassDto);

    /**
     * 获取课程详情
     * @param ClassId
     * @return
     */
    JsonResponse<ClassDetail> getClassDetail(Integer ClassId);


    /**
     * 获取课程评论
     * @param queryClassDto
     * @return
     */
    PageResponse<CommentDto> getCommentByClassId(QueryClassDto queryClassDto);

    /**
     * 兑换课程
     * @param queryClassDto
     * @return
     */
    JsonResponse<Boolean> conversionClass(QueryClassDto queryClassDto);

    /**
     * 添加评论
     * @param addCommentDto
     * @return
     */
    JsonResponse<Boolean> commentClass(AddCommentDto addCommentDto);

    /**
     * 查询兑换课程记录
     * @param queryClassDto
     * @return
     */
    PageResponse<ClassInstance> getConversionClass(QueryClassDto queryClassDto);

    /**
     * 获取下载 播放 url
     * @param chapter
     * @return
     */
    JsonResponse<String> getDownLoanUrl(TbChapter chapter);

    /**
     * 提交学习记录
     * @param queryClassDto
     * @return
     */
    JsonResponse<Boolean> commitLearn(QueryClassDto queryClassDto);

    /**
     * 收藏课程
     * @param queryClassDto
     * @return
     */
    JsonResponse<Boolean> favoriteClass(QueryClassDto queryClassDto);
}
