package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Category;
import com.example.mapper.CategoryMapper;
import com.example.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 * @author Jj
 * @since 2022-09-12 10:27:03
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @Override
    public IPage<Category> page(Page<Category> page, Wrapper<Category> queryWrapper) {
        Page<Category> categoryPage = categoryMapper.selectPage(page, queryWrapper);
        handle(page.getRecords());

        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 0);
        page.setTotal(categoryMapper.selectCount(wrapper));
        return categoryPage;
    }

    /**组合父子主键*/
    public void handle(List<Category> menus) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        for (Category menu : menus) {
            wrapper.eq("parent_id", menu.getId());
            List<Category> children = categoryMapper.selectList(wrapper);
            wrapper.clear();
            menu.setChildren(children);
        }
        menus.removeIf(m -> m.getParentId() != 0);
    }
}