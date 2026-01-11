package com.qf.controller;

import com.qf.service.CommonService;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class CommonController {
    @Autowired
    private CommonService commonService;

    /**
     * 通用接口
     * @param tableName
     * @param columnName
     * @return
     */
    @RequestMapping("/option/{tableName}/{columnName}")
    public R geOptions(@PathVariable("tableName") String tableName,
                       @PathVariable("columnName") String columnName){
        //封装参数
        HashMap<String, Object> params = new HashMap<>();
        params.put("table",tableName);
        params.put("column",columnName);
        List<String> options = commonService.getOptions(params);
        return R.ok().put("data",options);

    }

}
