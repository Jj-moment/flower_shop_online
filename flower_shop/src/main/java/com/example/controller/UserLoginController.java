package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.UserLogin;
import com.example.service.UserLoginService;
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
 * 用户登录 前端控制器
 * </p>
 *
 * @author Jj
 * @since 2022-08-22 11:23:42
 */
@Slf4j
@RestController
@RequestMapping("/login")
@Api(value = "UserLogin", tags = "用户登录控制层")
public class UserLoginController {

    @Resource
    private UserLoginService userLoginService;

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param limit 查询页
     * @param login 查询条件
     * @return 分页查询结果
     */
    @GetMapping("/queryByPage")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public Result<List<UserLogin>> getUserLoginByPage(@ApiParam("用户登录对象") UserLogin login,
                                                      @RequestParam @ApiParam(value = "当前页", required = true) int page,
                                                      @RequestParam @ApiParam(value = "查询页", required = true) int limit) {
        QueryWrapper<UserLogin> wrapper = new QueryWrapper<>();
        wrapper.like(!StringUtils.isEmpty(login.getUsername()), "username", login.getUsername())
                .like(login.getUserStatus() != null, "user_status", login.getUserStatus());
        Page<UserLogin> userPage = userLoginService.page(new Page<>(page, limit), wrapper);
        return Result.success(userPage.getRecords(), userPage.getTotal());
    }

    /**
     * 新增用户登录
     *
     * @param userLogin 用户登录
     * @return 新增结果
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增用户登录", notes = "新增用户")
    public Result<String> save(@RequestBody @ApiParam("新增对象") UserLogin userLogin) {
        userLoginService.save(userLogin);
        return Result.success("添加成功！");
    }

    /**
     * 修改用户登录
     *
     * @param userLogin 用户登录
     * @return 修改结果
     */
    @PostMapping("/updateById")
    @ApiOperation(value = "修改用户登录", notes = "通过id修改用户")
    public Result<String> updateById(@RequestBody UserLogin userLogin) {
        userLoginService.updateById(userLogin);
        return Result.success("更新成功！");
    }

    /**
     * 通过id批量删除用户登录
     *
     * @param ids 删除条件id
     * @return 删除结果
     */
    @PostMapping("/deleteBatchByIds")
    @ApiOperation(value = "删除用户登录", notes = "通过id删除用户")
    @ApiParam(value = "login", required = true)
    public Result<String> removeBatchByIds(@RequestBody List<Long> ids) {
        userLoginService.removeBatchByIds(ids);
        return Result.success("删除成功！");
    }

    /**
     * 用户登录
     *
     * @param login 用户对象
     * @return Result<String>
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public Result<String> login(@RequestBody UserLogin login) {
        return Result.success(userLoginService.login(login.getUsername(), login.getPassword()));
    }
}