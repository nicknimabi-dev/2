package com.qf.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qf.entity.YonghuEntity;
import com.qf.entity.ZhishileibieEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZhishileibieDao extends BaseMapper<ZhishileibieEntity> {
    List<ZhishileibieEntity> selectListView(@Param("ew") Wrapper<ZhishileibieEntity> wrapper);
    List<ZhishileibieEntity> selectListView(Pagination page, @Param("ew") Wrapper<ZhishileibieEntity> wrapper);
}
