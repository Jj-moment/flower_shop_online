package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.UserLogin;
import com.example.exception.code.ServiceCode;
import com.example.exception.service.ServiceException;
import com.example.mapper.UserLoginMapper;
import com.example.service.UserLoginService;
import com.example.utils.JwtUtils;
import com.example.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户登录 服务实现类
 * </p>
 *
 * @author Jj
 * @since 2022-08-22 10:38:12
 */
@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, UserLogin> implements UserLoginService {

    @Resource
    UserLoginMapper userLoginMapper;

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    @Override
    public String login(String username, String password) {
        QueryWrapper<UserLogin> wrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty(username)) {
            throw new ServiceException(ServiceCode.LOGIN_EXCEPTION, "用户名不能为空");
        } else if (StringUtils.isEmpty(password)) {
            throw new ServiceException(ServiceCode.LOGIN_EXCEPTION, "密码不能为空");
        } else {
            wrapper.eq("username", username).eq("password", password);
            UserLogin userLogin = userLoginMapper.selectOne(wrapper);
            if (userLogin != null) {
                Map<String, String> hashMap = new HashMap<>(3);
                hashMap.put("id", userLogin.getId().toString());
                hashMap.put("username", username);
                /*返回token*/
                return JwtUtils.createToken(hashMap);
            } else {
                throw new ServiceException(ServiceCode.LOGIN_EXCEPTION, "用户名或密码不正确");
            }
        }
    }

    @Override
    public boolean updateById(UserLogin entity) {
        QueryWrapper<UserLogin> wrapper = new QueryWrapper<>();
        wrapper.eq(entity.getId()!=null, "id", entity.getId());
        UserLogin userLogin = userLoginMapper.selectOne(wrapper);
        /*
        * 对比数据库中的用户名和用户提交的用户名是否一致
        * 若一致, 则进行正常更新, 若不一致, 则检测用户名冲突
        * */
        if (!entity.getUsername().equals(userLogin.getUsername())) {
            isExist(entity.getUsername());
        }
        return super.updateById(entity);
    }

    @Override
    public boolean save(UserLogin entity) {
        isExist(entity.getUsername());
        return super.save(entity);
    }

    /**
     * 检测用户名是否冲突
     *
     * @param username username
     */
    public void isExist(String username){
        QueryWrapper<UserLogin> wrapper = new QueryWrapper<>();
        wrapper.eq(!StringUtils.isEmpty(username), "username", username);
        UserLogin userLogin = userLoginMapper.selectOne(wrapper);
        if(userLogin != null){
            throw new ServiceException(ServiceCode.USERNAME_CONFLICT, "用户名已存在");
        }
    }
}
