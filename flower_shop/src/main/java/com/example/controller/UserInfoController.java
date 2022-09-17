package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.UserInfo;
import com.example.service.UserInfoService;
import com.example.utils.Result;
import com.example.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author Jj
 * @since 2022-08-30 04:52:51
 */
@Slf4j
@RestController
@RequestMapping("/userInfo")
@Api(value = "UserInfo", tags = "用户信息表控制层")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param limit 查询页            
     * @param userInfo 查询条件
     * @return 分页查询结果
     */
    @GetMapping("/queryByPage")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public Result<List<UserInfo>> getUserInfoByPage(@ApiParam("用户信息表对象") UserInfo userInfo,
                                        @RequestParam @ApiParam(value="当前页", required=true) int page,
                                        @RequestParam @ApiParam(value="查询页", required=true) int limit) {
        Page<UserInfo> userInfoPage = new Page<>(page, limit);
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper
                .like(!StringUtils.isEmpty(userInfo.getRealName()), "real_name", userInfo.getRealName())
                .like(!StringUtils.isEmpty(userInfo.getUserEmail()), "user_email", userInfo.getUserEmail())
                .like(!StringUtils.isEmpty(userInfo.getIdentityCardNo()), "identity_card_no", userInfo.getIdentityCardNo())
                .like(!StringUtils.isEmpty(userInfo.getMobilePhone()), "mobile_phone", userInfo.getMobilePhone())
                .like(userInfo.getIdentityCardType()!=null, "identity_card_type", userInfo.getIdentityCardType())
                .like(userInfo.getGender()!=null, "gender", userInfo.getGender());
        return Result.success(userInfoService.page(userInfoPage, wrapper).getRecords(), userInfoPage.getTotal());
    }

    /**
     * 新增用户信息表
     * @param userInfo 用户信息表
     * @return 新增结果
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增用户信息表", notes = "新增用户信息表")
    public Result<String> save(@RequestBody @ApiParam("新增对象") UserInfo userInfo) {
        userInfoService.save(userInfo);
        return Result.success("添加成功！");
    }

    /**
     * 修改用户信息表
     * @param userInfo 用户信息表
     * @return 修改结果
     */
    @PostMapping("/updateById")
    @ApiOperation(value = "修改用户信息表", notes = "通过id修改用户信息表")
    public Result<String> updateById(@RequestBody @ApiParam("修改对象") UserInfo userInfo) {
        userInfoService.updateById(userInfo);
        return Result.success("更新成功！");
    }

    /**
     * 通过id删除用户信息表
     * @param id 删除条件
     * @return 删除结果
     */
    @PostMapping("/deleteById")
    @ApiOperation(value = "删除用户信息表", notes = "通过id删除用户信息表")
    public Result<String> removeById(@RequestParam @ApiParam(value = "id", required = true)Integer id) {
        userInfoService.removeById(id);
        return Result.success("删除成功！");
    }
}

