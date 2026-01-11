package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.dao.YonghuDao;
import com.qf.entity.YonghuEntity;
import com.qf.service.YonghuService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("yonghuServiceImpl")
public class YonghuServiceImpl extends ServiceImpl<YonghuDao, YonghuEntity>implements YonghuService {
    @Override
    public List<YonghuEntity> queryPage(Wrapper<YonghuEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> page, Wrapper<YonghuEntity> wrapper) {
        Page<YonghuEntity> page1 = new Query<YonghuEntity>(page).getPage();
        page1.setRecords(baseMapper.selectListView(wrapper));
        return new PageUtils(page1);
    }
}
