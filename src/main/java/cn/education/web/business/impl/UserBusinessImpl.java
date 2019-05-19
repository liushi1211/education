package cn.education.web.business.impl;

import cn.education.web.business.UserBusiness;
import cn.education.web.common.util.DateUtil;
import cn.education.web.mapper.TbUserMapper;
import cn.education.web.model.TbUser;
import cn.education.web.model.TbUserExample;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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

    @Override
    public void signIn(TbUser user) {
        if(null == user || StringUtils.isBlank(user.getWxNo())){
            return;
        }

        TbUser user1 = queryUserByWxNo(user.getWxNo());
        if(null == user1){
            return;
        }
        int num=1;
        if(isYesterday(user1.getSignInTime()) && user1.getSignInNumber() < 7){
            num = user1.getSignInNumber()+1;
        }
        TbUser updateUser = new TbUser();
        updateUser.setId(user1.getId());
        updateUser.setScore((short)(user1.getScore().intValue()+num));
        updateUser.setSignInTime(new Date());
        updateUser.setSignInNumber((byte)num);
        userMapper.updateByPrimaryKeySelective(updateUser);
    }

    private static boolean isYesterday(Date signInTime) {
        signInTime = DateUtil.parseDate(signInTime);
        Date now = DateUtil.parseDate(new Date());
        Date signIn = DateUtil.getDayAddDate(signInTime,1);
        return signIn.equals(now);
    }
}
