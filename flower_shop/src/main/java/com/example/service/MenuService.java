package com.example.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Menu;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author Jj
 * @since 2022-08-22 10:38:12
 */
public interface MenuService extends IService<Menu> {
    /**
     * 分页查询
     *
     * @param page         分页条件
     * @param queryWrapper 查询条件
     * @return 查询结果
     */
    IPage<Menu> page(Page<Menu> page, Wrapper<Menu> queryWrapper);
}
