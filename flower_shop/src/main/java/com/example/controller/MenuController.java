package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Menu;
import com.example.service.MenuService;
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
 * 菜单 前端控制器
 * </p>
 *
 * @author Jj
 * @since 2022-08-22 11:23:42
 */
@Slf4j
@RestController
@RequestMapping("/menu")
@Api(value = "Menu", tags = "菜单控制层")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/queryAll")
    @ApiOperation(value = "查询所有信息", notes = "查询所有信息")
    public Result<List<Menu>> getAll() {
        return Result.success(menuService.list(), menuService.count());
    }

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param limit 查询页
     * @param menu  查询条件
     * @return 分页查询结果
     */
    @GetMapping("/queryByPage")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public Result<List<Menu>> getMenuByPage(@ApiParam("菜单对象") Menu menu,
                                            @RequestParam @ApiParam(value = "当前页", required = true) int page,
                                            @RequestParam @ApiParam(value = "查询页", required = true) int limit) {
        Page<Menu> menuPage = new Page<>(page, limit);
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper
                .like(!StringUtils.isEmpty(menu.getName()), "name", menu.getName())
                .like(menu.getStatus() != null, "status", menu.getStatus());
        return Result.success(menuService.page(menuPage, wrapper).getRecords(), menuPage.getTotal());
    }

    /**
     * 新增菜单
     *
     * @param menu 菜单
     * @return 新增结果
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增菜单", notes = "新增菜单")
    public Result<String> save(@RequestBody @ApiParam("新增对象") Menu menu) {
        menuService.save(menu);
        return Result.success("添加成功！");
    }

    /**
     * 修改菜单
     *
     * @param menu 菜单
     * @return 修改结果
     */
    @PostMapping("/updateById")
    @ApiOperation(value = "修改菜单", notes = "通过id修改菜单")
    public Result<String> updateById(@RequestBody @ApiParam("修改对象") Menu menu) {
        menuService.updateById(menu);
        return Result.success("更新成功！");
    }

    /**
     * 通过id删除菜单
     *
     * @param id 删除条件
     * @return 删除结果
     */
    @PostMapping("/deleteById")
    @ApiOperation(value = "删除菜单", notes = "通过id删除菜单")
    public Result<String> removeById(@RequestParam @ApiParam(value = "id", required = true) Integer id) {
        menuService.removeById(id);
        return Result.success("删除成功！");
    }
}

