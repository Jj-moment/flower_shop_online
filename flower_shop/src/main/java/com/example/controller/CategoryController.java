package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Category;
import com.example.service.CategoryService;
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
 * 商品分类 前端控制器
 * </p>
 *
 * @author Jj
 * @since 2022-09-12 10:27:03
 */
@Slf4j
@RestController
@RequestMapping("/category")
@Api(value = "Category", tags = "商品分类控制层")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 分页查询
     *
     * @param page     分页对象
     * @param limit    查询页
     * @param category 查询条件
     * @return 分页查询结果
     */
    @GetMapping("/queryByPage")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public Result<List<Category>> getCategoryByPage(@ApiParam("商品分类对象") Category category,
                                                    @RequestParam @ApiParam(value = "当前页", required = true) int page,
                                                    @RequestParam @ApiParam(value = "查询页", required = true) int limit) {
        Page<Category> categoryPage = new Page<>(page, limit);
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper
                .like(!StringUtils.isEmpty(category.getCategoryCode()), "categoryCode", category.getCategoryCode())
                .like(!StringUtils.isEmpty(category.getCategoryName()), "categoryName", category.getCategoryName())
                .like(category.getCategoryStatus()!=null, "categoryStatus", category.getCategoryStatus());
        return Result.success(categoryService.page(categoryPage, wrapper).getRecords(), categoryPage.getTotal());
    }

    /**
     * 新增商品分类
     *
     * @param category 商品分类
     * @return 新增结果
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增商品分类", notes = "新增商品分类")
    public Result<String> save(@RequestBody @ApiParam("新增对象") Category category) {
        categoryService.save(category);
        return Result.success("添加成功！");
    }

    /**
     * 修改商品分类
     *
     * @param category 商品分类
     * @return 修改结果
     */
    @PostMapping("/updateById")
    @ApiOperation(value = "修改商品分类", notes = "通过id修改商品分类")
    public Result<String> updateById(@RequestBody @ApiParam("修改对象") Category category) {
        categoryService.updateById(category);
        return Result.success("更新成功！");
    }

    /**
     * 通过id删除商品分类
     *
     * @param id 删除条件
     * @return 删除结果
     */
    @PostMapping("/deleteById")
    @ApiOperation(value = "删除商品分类", notes = "通过id删除商品分类")
    public Result<String> removeById(@RequestParam @ApiParam(value = "id", required = true) Integer id) {
        categoryService.removeById(id);
        return Result.success("删除成功！");
    }
}

