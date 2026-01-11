package com.qf.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qf.entity.YundongzhishiEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface YundongzhishiDao extends BaseMapper<YundongzhishiEntity> {
    List<YundongzhishiEntity> selectListView(@Param("ew") Wrapper<YundongzhishiEntity> wrapper);
    List<YundongzhishiEntity> selectListView(Pagination page, @Param("ew") Wrapper<YundongzhishiEntity> wrapper);
}
