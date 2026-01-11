package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.dao.YundongzhishiDao;
import com.qf.entity.YundongzhishiEntity;
import com.qf.service.YundongzhishiService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("yundongzhishiService")

public class YundongzhishiServiceImpl extends ServiceImpl< YundongzhishiDao, YundongzhishiEntity> implements YundongzhishiService {

    @Override
    public List<YundongzhishiEntity> queryPage(Wrapper<YundongzhishiEntity> wrapper) {
        List<YundongzhishiEntity> list = this.baseMapper.selectListView(wrapper);
        return list;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<YundongzhishiEntity> wrapper) {
        Page<YundongzhishiEntity> page = new Query<YundongzhishiEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView( wrapper));
        PageUtils pageUtils = new PageUtils(page);
        return pageUtils;
    }
}
