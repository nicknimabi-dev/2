package com.qf.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.qf.entity.UsersEntity;
import com.qf.entity.YonghuEntity;
import com.qf.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface YonghuService extends IService<YonghuEntity> {
    List<YonghuEntity> queryPage(Wrapper<YonghuEntity> wrapper);
    PageUtils queryPage(Map<String,Object> page, Wrapper<YonghuEntity> wrapper);
}
