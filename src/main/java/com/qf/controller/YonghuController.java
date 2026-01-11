package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.entity.YonghuEntity;
import com.qf.service.YonghuService;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/yonghu")
public class YonghuController {
    @Autowired
    private YonghuService yonghuService;

    /**
     * 用户列表
     * @param params
     * @param yonghu
     * @return
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, YonghuEntity yonghu) {
        //设置查询条件
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<>();
        //执行查询
        PageUtils pageUtils = yonghuService.queryPage(params, MPUtil.likeOrEq(ew, yonghu));
        return R.ok().put("data", pageUtils);
    }

    /**
     * 用户添加
     * @param yonghu
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody YonghuEntity yonghu){
        //查重
        if (yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao())) != null) {
         return R.error("此用户已存在，更换重试");
        }
        //正常插入
        yonghuService.insert(yonghu);
        return R.ok().put("data", yonghu.getId());
    }

    /**
     * 查看
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable Long id){
        YonghuEntity yonghu = yonghuService.selectById(id);
        return R.ok().put("data", yonghu);

    }

    /**
     * 修改
     * @param yonghu
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody YonghuEntity yonghu){
        yonghuService.updateById(yonghu);
        return R.ok();


    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yonghuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
