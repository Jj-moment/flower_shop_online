package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Flower;
import com.example.service.FlowerService;
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
 * 鲜花 前端控制器
 * </p>
 *
 * @author Jj
 * @since 2022-08-22 11:23:42
 */
@Slf4j
@RestController
@RequestMapping("/flower")
@Api(value = "Flower", tags = "鲜花控制层")
public class FlowerController {

    @Resource
    private FlowerService flowerService;

    /**
     * 分页查询
     *
     * @param page   分页对象
     * @param limit  查询页
     * @param flower 查询条件
     * @return 分页查询结果
     */
    @GetMapping("/queryByPage")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public Result<List<Flower>> getFlowerByPage(@ApiParam("鲜花对象") Flower flower,
                                                @RequestParam @ApiParam(value = "当前页", required = true) int page,
                                                @RequestParam @ApiParam(value = "查询页", required = true) int limit) {
        Page<Flower> flowerPage = new Page<>(page, limit);
        QueryWrapper<Flower> wrapper = new QueryWrapper<>();
        wrapper.like(!StringUtils.isEmpty(flower.getFlowerName()), "flower_name", flower.getFlowerName())
                .likeRight(flower.getPrice()!=null, "price", flower.getPrice())
                .likeRight(flower.getMarketPrice()!=null, "market_price", flower.getMarketPrice())
                .likeRight(flower.getStock()!=null, "stock", flower.getStock())
                .likeRight(flower.getAmount()!=null, "amount", flower.getAmount());
        return Result.success(flowerService.page(flowerPage, wrapper).getRecords(), flowerPage.getTotal());
    }

    /**
     * 新增鲜花
     *
     * @param flower 鲜花
     * @return 新增结果
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增鲜花", notes = "新增鲜花")
    public Result<String> save(@RequestBody @ApiParam("新增对象") Flower flower) {
        flowerService.save(flower);
        return Result.success("添加成功！");
    }

    /**
     * 修改鲜花
     *
     * @param flower 鲜花
     * @return 修改结果
     */
    @PostMapping("/updateById")
    @ApiOperation(value = "修改鲜花", notes = "通过id修改鲜花")
    public Result<String> updateById(@RequestBody @ApiParam("修改对象") Flower flower) {
        flowerService.updateById(flower);
        return Result.success("更新成功！");
    }

    /**
     * 通过id删除鲜花
     *
     * @param id 删除条件
     * @return 删除结果
     */
    @PostMapping("/deleteById")
    @ApiOperation(value = "删除鲜花", notes = "通过id删除鲜花")
    public Result<String> removeById(@RequestParam @ApiParam(value = "id", required = true) Integer id) {
        flowerService.removeById(id);
        return Result.success("删除成功！");
    }
}

