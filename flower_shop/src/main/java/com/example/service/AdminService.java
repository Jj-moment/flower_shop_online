package com.example.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Admin;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @author Jj
 * @since 2022-08-22 10:38:11
 */
public interface AdminService extends IService<Admin> {
    /**
     * 管理员
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    String login(String username, String password);

    /**
     * 请描述功能
     *
     * @param page -page
     * @param queryWrapper -queryWrapper
     * @return IPage<Admin>
     */
    IPage<Admin> page(Page<Admin> page, Wrapper<Admin> queryWrapper);
}