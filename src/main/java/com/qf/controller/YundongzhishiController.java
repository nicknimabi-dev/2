package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.entity.YundongzhishiEntity;
import com.qf.service.YundongzhishiService;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/yundongzhishi")
public class YundongzhishiController {
    @Autowired
    private YundongzhishiService yundongzhishiService;
    /**
     * 分页查询
     * @param params
     * @param yundongzhishi
     * @return
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, YundongzhishiEntity yundongzhishi ) {
        //设置查询条件
        EntityWrapper<YundongzhishiEntity> ew = new EntityWrapper<>();
        //执行查询
        PageUtils pageUtils = yundongzhishiService.queryPage(params, MPUtil.likeOrEq(ew, yundongzhishi));
        return R.ok().put("data", pageUtils);
    }
    /**
     * 添加
     * @param yundongzhishi
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody YundongzhishiEntity yundongzhishi ) {
        //查重
        if (yundongzhishiService.selectOne(new EntityWrapper<YundongzhishiEntity>().eq("zhishibiaoti", yundongzhishi.getZhishibiaoti())) != null) {
            return R.error("此运动标题已存在，更换重试");
        }
        //正常新增
        yundongzhishi.setId(new Date().getTime());
        yundongzhishiService.insert(yundongzhishi);
        return R.ok().put("data", yundongzhishi.getId());
    }

    /**
     * 查看
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable Long id){
        YundongzhishiEntity yundongzhishi = yundongzhishiService.selectById(id);
        return R.ok().put("data", yundongzhishi);

    }

    /**
     * 修改
     * @param yundongzhishi
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody YundongzhishiEntity yundongzhishi){
        yundongzhishiService.updateById(yundongzhishi);
        return R.ok();


    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yundongzhishiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
