package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Menu;
import com.example.mapper.MenuMapper;
import com.example.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author Jj
 * @since 2022-08-22 10:38:12
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    MenuMapper menuMapper;

    @Override
    public List<Menu> list() {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("order_num");
        List<Menu> menus = menuMapper.selectList(wrapper);
        return handleMenus(menus);
    }

    @Override
    public IPage<Menu> page(Page<Menu> page, Wrapper<Menu> queryWrapper) {
        Page<Menu> menuPage = baseMapper.selectPage(page, queryWrapper);
        handleMenus(menuPage.getRecords());

        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 0);
        page.setTotal(menuMapper.selectCount(wrapper));
        return menuPage;
    }

    /**组合父子主键*/
    public List<Menu> handleMenus(List<Menu> menus) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        for (Menu menu : menus) {
            wrapper.eq("parent_id", menu.getId());
            wrapper.orderByAsc("order_num");
            List<Menu> children = menuMapper.selectList(wrapper);
            wrapper.clear();
            menu.setChildren(children);
        }
        menus.removeIf(m -> m.getParentId() != 0);
        return menus;
    }

}
