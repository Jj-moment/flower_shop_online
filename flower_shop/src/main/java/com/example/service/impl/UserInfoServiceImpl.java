package com.example.service.impl;

import com.example.entity.UserInfo;
import com.example.mapper.UserInfoMapper;
import com.example.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Jj
 * @since 2022-08-30 04:52:51
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
