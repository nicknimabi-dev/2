package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.dao.ZhishileibieDao;
import com.qf.entity.ZhishileibieEntity;
import com.qf.service.ZhishileibieService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("zhishileibieService")
public class ZhishileibieServiceImpl extends ServiceImpl<ZhishileibieDao, ZhishileibieEntity> implements ZhishileibieService {


    @Override
    public List<ZhishileibieEntity> queryPage(Wrapper<ZhishileibieEntity> wrapper) {
        List<ZhishileibieEntity> list = this.baseMapper.selectListView(wrapper);
        return list;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ZhishileibieEntity> wrapper) {
        Page<ZhishileibieEntity> page = new Query<ZhishileibieEntity>(params).getPage();
        page.setRecords(this.baseMapper.selectListView(wrapper));
        PageUtils pageUtils = new PageUtils(page);
        return pageUtils;
    }
}
