package com.qf.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.dao.UsersDao;
import com.qf.entity.UsersEntity;
import com.qf.service.UsersService;
import com.qf.utils.PageUtils;
import com.qf.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("usersService")
public class UsersServiceImpl extends ServiceImpl<UsersDao, UsersEntity>implements UsersService {
    @Override
    public List<UsersEntity> queryPage(Wrapper<UsersEntity> wrapper) {
        return baseMapper.selectList(wrapper);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> page, Wrapper<UsersEntity> wrapper) {
        Page<UsersEntity> page1 = new Query<UsersEntity>(page).getPage();
        //设置数据列表
        page1.setRecords(baseMapper.selectPage(page1, wrapper));
        //封装到PageUtils
        PageUtils pageUtils = new PageUtils(page1);

        return pageUtils;
    }
}
