package com.example.service.impl;

import com.example.entity.Flower;
import com.example.mapper.FlowerMapper;
import com.example.service.FlowerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 鲜花 服务实现类
 * </p>
 *
 * @author Jj
 * @since 2022-08-22 10:38:12
 */
@Service
public class FlowerServiceImpl extends ServiceImpl<FlowerMapper, Flower> implements FlowerService {

}
