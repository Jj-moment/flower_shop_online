package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Admin;
import com.example.entity.Role;
import com.example.entity.RoleAdmin;
import com.example.exception.code.ServiceCode;
import com.example.exception.service.ServiceException;
import com.example.mapper.AdminMapper;
import com.example.mapper.RoleAdminMapper;
import com.example.mapper.RoleMapper;
import com.example.service.AdminService;
import com.example.utils.JwtUtils;
import com.example.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 管理员 服务实现类
 * </p>
 *
 * @author Jj
 * @since 2022-08-22 10:38:11
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Resource
    AdminMapper adminMapper;

    @Resource
    RoleAdminMapper roleAdminMapper;

    @Resource
    RoleMapper roleMapper;

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    @Override
    public String login(String username, String password) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty(username)) {
            throw new ServiceException(ServiceCode.LOGIN_EXCEPTION, "用户名不能为空");
        } else if (StringUtils.isEmpty(password)) {
            throw new ServiceException(ServiceCode.LOGIN_EXCEPTION, "密码不能为空");
        } else {
            wrapper.eq("username", username).eq("password", password);
            Admin admin = adminMapper.selectOne(wrapper);
            if (admin == null) {
                throw new ServiceException(ServiceCode.LOGIN_EXCEPTION, "用户名或密码不正确");
            } else {
                Map<String, String> hashMap = new HashMap<>(3);
                hashMap.put("id", admin.getId().toString());
                hashMap.put("username", username);
                /*返回token*/
                return JwtUtils.createToken(hashMap);
            }
        }
    }

    @Override
    public IPage<Admin> page(Page<Admin> page, Wrapper<Admin> queryWrapper) {
        Page<Admin> adminPage = adminMapper.selectPage(page, queryWrapper);
        getRole(adminPage.getRecords());

        return adminPage;
    }

    /**
     * 管理员获取角色
     */
    public void getRole(List<Admin> admins) {
        for (Admin admin : admins) {
            List<Long> roleIds = roleAdminMapper.selectList(new QueryWrapper<RoleAdmin>()
                    .eq("admin_id", admin.getId()))
                    .stream().map(RoleAdmin::getRoleId).collect(Collectors.toList());

            List<Role> roles = roleMapper.selectBatchIds(roleIds);
            admin.setRole(roles);
        }

    }

}
