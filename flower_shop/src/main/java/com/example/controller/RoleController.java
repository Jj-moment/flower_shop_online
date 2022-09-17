package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Role;
import com.example.service.RoleService;
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
 * 角色表 前端控制器
 * </p>
 *
 * @author Jj
 * @since 2022-09-13 10:35:49
 */
@Slf4j
@RestController
@RequestMapping("/role")
@Api(value = "Role", tags = "角色表控制层")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param limit 查询页            
     * @param role 查询条件
     * @return 分页查询结果
     */
    @GetMapping("/queryByPage")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public Result<List<Role>> getRoleByPage(@ApiParam("角色表对象") Role role,
                                        @RequestParam @ApiParam(value="当前页", required=true) int page,
                                        @RequestParam @ApiParam(value="查询页", required=true) int limit) {
        Page<Role> rolePage = new Page<>(page, limit);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.like(!StringUtils.isEmpty(role.getRole()), "role", role.getRole())
        .like(!StringUtils.isEmpty(role.getDescription()), "description", role.getDescription());
        return Result.success(roleService.page(rolePage, wrapper).getRecords());
    }

    /**
     * 新增角色表
     * @param role 角色表
     * @return 新增结果
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增角色表", notes = "新增角色表")
    public Result<String> save(@RequestBody @ApiParam("新增对象") Role role) {
        roleService.save(role);
        return Result.success("添加成功！");
    }

    /**
     * 修改角色表
     * @param role 角色表
     * @return 修改结果
     */
    @PostMapping("/updateById")
    @ApiOperation(value = "修改角色表", notes = "通过id修改角色表")
    public Result<String> updateById(@RequestBody @ApiParam("修改对象") Role role) {
        roleService.updateById(role);
        return Result.success("更新成功！");
    }

    /**
     * 通过id删除角色表
     * @param id 删除条件
     * @return 删除结果
     */
    @PostMapping("/deleteById")
    @ApiOperation(value = "删除角色表", notes = "通过id删除角色表")
    public Result<String> removeById(@RequestParam @ApiParam(value = "id", required = true)Integer id) {
        roleService.removeById(id);
        return Result.success("删除成功！");
    }
}

