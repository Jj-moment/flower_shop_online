package com.example.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Category;

/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 * @author Jj
 * @since 2022-09-12 10:27:03
 */
public interface CategoryService extends IService<Category> {
    /**
     * 分页查询
     *
     * @param page 分页
     * @param queryWrapper 查询条件
     * @return IPage<Category>
     */
    IPage<Category> page(Page<Category> page, Wrapper<Category> queryWrapper);
}
