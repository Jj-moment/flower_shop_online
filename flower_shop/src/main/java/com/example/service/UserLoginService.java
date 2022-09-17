package com.example.service;

import com.example.entity.UserLogin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户登录 服务类
 * </p>
 *
 * @author Jj
 * @since 2022-08-22 10:38:12
 */
public interface UserLoginService extends IService<UserLogin> {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    String login(String username, String password);
}
