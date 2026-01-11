package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.YonghuEntity;
import com.qf.entity.ZhishileibieEntity;
import com.qf.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface ZhishileibieService extends IService<ZhishileibieEntity> {
    List<ZhishileibieEntity> queryPage(Wrapper<ZhishileibieEntity> wrapper);
    PageUtils queryPage(Map<String,Object> params, Wrapper<ZhishileibieEntity> wrapper);
}
