package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.YundongzhishiEntity;
import com.qf.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface YundongzhishiService extends IService<YundongzhishiEntity> {
    List<YundongzhishiEntity> queryPage(Wrapper<YundongzhishiEntity> wrapper);
    PageUtils queryPage(Map<String,Object> params, Wrapper<YundongzhishiEntity> wrapper);
}
