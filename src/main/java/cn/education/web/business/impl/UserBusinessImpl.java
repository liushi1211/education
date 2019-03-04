package cn.education.web.business.impl;

import cn.education.web.business.UserBusiness;
import cn.education.web.mapper.TbUserMapper;
import cn.education.web.model.TbUser;
import cn.education.web.model.TbUserExample;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liushi on 19/2/27.
 */
@Service
public class UserBusinessImpl extends BaseImpl implements UserBusiness {

    @Resource
    private TbUserMapper userMapper;


    @Override
    public void saveUser(TbUser user) {
        if(null == user || StringUtils.isBlank(user.getWxNo())){
            return;
        }

        userMapper.insertSelective(user);
    }

    @Override
    public  TbUser queryUserByWxNo(String wxNo) {
        TbUserExample ex = new TbUserExample();
        ex.createCriteria().andWxNoEqualTo(wxNo);
        List<TbUser> users = userMapper.selectByExample(ex);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }else {
            return users.get(0);
        }
    }

    @Override
    public void updateUser(TbUser user) {
        if(null == user || null == user.getId()){
            return;
        }
        userMapper.updateByPrimaryKeySelective(user);

    }
}
