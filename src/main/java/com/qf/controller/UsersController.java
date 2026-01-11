package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.annotation.IgnoreAuth;
import com.qf.entity.UsersEntity;
import com.qf.service.TokenService;
import com.qf.service.UsersService;
import com.qf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @IgnoreAuth
    @RequestMapping("/login")
    public R login(String username, String password) {
        //调用usersService 完成查询
        UsersEntity user = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
        if (user == null || !user.getPassword().equals(password)) {
            return R.error("账号或者密码不正确");
        }
        //输入正确 获取token
        String token = tokenService.generateToken(user.getId(), username, "users", user.getRole());
        return R.ok().put("token", token);

    }

    /**
     * 获取用户session信息
     * @param request
     * @return
     */
    @RequestMapping("/session")
    public R getCurrentUser(HttpServletRequest request) {
        Long userId = (Long)request.getSession().getAttribute("userId");
        UsersEntity user = usersService.selectById(userId);
        return R.ok().put("data", user);
    }

}
