package com.qf.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface CommonDao {
    List<String> getOptions(Map<String,Object> map);
}
