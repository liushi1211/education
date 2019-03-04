package cn.education.web.controller;


import cn.education.web.business.UserBusiness;
import cn.education.web.common.JsonResponse;
import cn.education.web.model.TbUser;
import cn.education.web.common.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBusiness userBusiness;

    /**
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public JsonResponse<Boolean> save(@RequestBody TbUser user){
        userBusiness.saveUser(user);
        return new JsonResponse<Boolean>(ErrorCode.SUCCESS,true);
    }

}
