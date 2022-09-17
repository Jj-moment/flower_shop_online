package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Admin;
import com.example.service.AdminService;
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
 * 管理员 前端控制器
 * </p>
 *
 * @author Jj
 * @since 2022-08-22 11:23:42
 */
@Slf4j
@RestController
@RequestMapping("/admin")
@Api(value = "Admin", tags = "管理员控制层")
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param limit 查询页            
     * @param admin 查询条件
     * @return 分页查询结果
     */
    @GetMapping("/queryByPage")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public Result<List<Admin>> getAdminByPage(@ApiParam("管理员对象") Admin admin,
                                        @RequestParam @ApiParam(value="当前页", required=true) int page,
                                        @RequestParam @ApiParam(value="查询页", required=true) int limit) {
        Page<Admin> adminPage = new Page<>(page, limit);
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.like(!StringUtils.isEmpty(admin.getUsername()), "username", admin.getUsername());
        return Result.success(adminService.page(adminPage, wrapper).getRecords(), adminPage.getTotal());
    }

    /**
     * 新增管理员
     * @param admin 管理员
     * @return 新增结果
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增管理员", notes = "新增管理员")
    public Result<String> save(@RequestBody @ApiParam("新增对象") Admin admin) {
        adminService.save(admin);
        return Result.success("添加成功！");
    }

    /**
     * 修改管理员
     * @param admin 管理员
     * @return 修改结果
     */
    @PostMapping("/updateById")
    @ApiOperation(value = "修改管理员", notes = "通过id修改管理员")
    public Result<String> updateById(@RequestBody @ApiParam("修改对象") Admin admin) {
        adminService.updateById(admin);
        return Result.success("更新成功！");
    }

    /**
     * 通过id删除管理员
     * @param id 删除条件
     * @return 删除结果
     */
    @PostMapping("/deleteById")
    @ApiOperation(value = "删除管理员", notes = "通过id删除管理员")
    public Result<String> removeById(@RequestParam @ApiParam(value = "id", required = true)Integer id) {
        adminService.removeById(id);
        return Result.success("删除成功！");
    }

    /**
     * 管理员
     *
     * @param admin 用户对象
     * @return Result<String>
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public Result<String> login(@RequestBody Admin admin) {
        return Result.success(adminService.login(admin.getUsername(), admin.getPassword()));
    }
}