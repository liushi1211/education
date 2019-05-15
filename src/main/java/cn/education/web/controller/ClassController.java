package cn.education.web.controller;


import cn.education.web.business.ClassBusiness;
import cn.education.web.common.JsonResponse;
import cn.education.web.common.PageResponse;
import cn.education.web.model.TbChapter;
import cn.education.web.model.TbClass;
import cn.education.web.model.TbUser;
import cn.education.web.model.dto.AddCommentDto;
import cn.education.web.model.dto.QueryClassDto;
import cn.education.web.model.vo.ClassDetail;
import cn.education.web.model.vo.ClassInstance;
import cn.education.web.model.vo.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassBusiness classBusiness;

    /**
     * 根据语种获取课程
     * @param queryClassDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public PageResponse<ClassInstance> getClassByType(@RequestBody QueryClassDto queryClassDto){
        return classBusiness.queryClass(queryClassDto);
    }

    /**
     * 查询最近学习记录
     * @param queryClassDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryCurrentLearnRecord",method = RequestMethod.POST)
    public PageResponse<ClassInstance> queryCurrentLearnRecord(@RequestBody QueryClassDto queryClassDto){
        return classBusiness.queryCurrentLearnRecord(queryClassDto);
    }

    /**
     * 查询收藏课程
     * @param queryClassDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryFavoriteClass",method = RequestMethod.POST)
    public PageResponse<ClassInstance> queryFavoriteClass(@RequestBody QueryClassDto queryClassDto){
        return classBusiness.queryFavoriteClass(queryClassDto);
    }

    /**
     * 收藏课程
     * @param queryClassDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/favoriteClass",method = RequestMethod.POST)
    public JsonResponse<Boolean> favoriteClass(@RequestBody QueryClassDto queryClassDto){
        return classBusiness.favoriteClass(queryClassDto);
    }

    /**
     * 获取课程详情
     * @param classId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getClassDetail/{classId}",method = RequestMethod.GET)
    public JsonResponse<ClassDetail> getClassDetail(@PathVariable Integer classId){
        return classBusiness.getClassDetail(classId);
    }
    /**
     * 查询评论
     * @param queryClassDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getComment",method = RequestMethod.POST)
    public PageResponse<CommentDto> getCommentByClassId(@RequestBody QueryClassDto queryClassDto){
        return classBusiness.getCommentByClassId(queryClassDto);
    }

    /**
     * 提交评论
     * @param addCommentDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public JsonResponse<Boolean> commentClass(@RequestBody AddCommentDto addCommentDto){
        return classBusiness.commentClass(addCommentDto);
    }

    /**
     * 兑换课程
     * @param queryClassDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/conversion",method = RequestMethod.POST)
    public JsonResponse<Boolean> conversionClass(@RequestBody QueryClassDto queryClassDto){
        return classBusiness.conversionClass(queryClassDto);
    }

    /**
     * 查询兑换课程记录
     * @param queryClassDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getConversionClass",method = RequestMethod.POST)
    public PageResponse<ClassInstance> getConversionClass(@RequestBody QueryClassDto queryClassDto){
        return classBusiness.getConversionClass(queryClassDto);
    }

    /**
     * 查询兑换课程记录
     * @param chapter
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDownLoanUrl",method = RequestMethod.POST)
    public JsonResponse<String> getDownLoanUrl(@RequestBody TbChapter chapter){
        return classBusiness.getDownLoanUrl(chapter);
    }

    /**
     * 提交学习记录
     * @param queryClassDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/commitLearn",method = RequestMethod.POST)
    public JsonResponse<Boolean> commitLearn(@RequestBody QueryClassDto queryClassDto){
        return classBusiness.commitLearn(queryClassDto);
    }

}
