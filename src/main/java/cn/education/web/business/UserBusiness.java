package cn.education.web.business;

import cn.education.web.model.TbUser;

import java.util.List;

/**
 * Created by liushi on 19/2/27.
 */
public interface UserBusiness {

    void saveUser(TbUser user);

    TbUser queryUserByWxNo(String wxNo);

    void updateUser(TbUser user);

    void signIn(TbUser user);
}
