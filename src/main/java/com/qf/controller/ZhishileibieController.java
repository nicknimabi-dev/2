package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.entity.YonghuEntity;
import com.qf.entity.ZhishileibieEntity;
import com.qf.service.ZhishileibieService;
import com.qf.utils.MPUtil;
import com.qf.utils.PageUtils;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/zhishileibie")
public class ZhishileibieController {
    @Autowired
    private ZhishileibieService zhishileibieService;
    /**
     * 分页查询
     * @param params
     * @param zhishileibie
     * @return
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, ZhishileibieEntity zhishileibie ) {
        //设置查询条件
        EntityWrapper<ZhishileibieEntity> ew = new EntityWrapper<>();
        //执行查询
        PageUtils pageUtils = zhishileibieService.queryPage(params, MPUtil.likeOrEq(ew, zhishileibie));
        return R.ok().put("data", pageUtils);
    }
    /**
     * 添加
     * @param zhishileibie
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhishileibieEntity zhishileibie ) {
        //查重
        if (zhishileibieService.selectOne(new EntityWrapper<ZhishileibieEntity>().eq("zhishileibie", zhishileibie.getZhishileibie())) != null) {
            return R.error("此类别已存在，更换重试");
        }
        //正常插入
        zhishileibieService.insert(zhishileibie);
        return R.ok().put("data", zhishileibie.getId());
    }

    /**
     * 查看
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable Long id){
        ZhishileibieEntity zhishileibie = zhishileibieService.selectById(id);
        return R.ok().put("data", zhishileibie);

    }

    /**
     * 修改
     * @param zhishileibie
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody ZhishileibieEntity zhishileibie){
        zhishileibieService.updateById(zhishileibie);
        return R.ok();


    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhishileibieService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
