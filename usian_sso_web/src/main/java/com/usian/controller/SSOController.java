package com.usian.controller;

import com.usian.feign.SSOSeriviceFeign;
import com.usian.pojo.TbUser;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("frontend/sso")
public class SSOController {
    @Autowired
    private SSOSeriviceFeign ssoSeriviceFeign;


    @RequestMapping("checkUserInfo/{checkValue}/{checkFlag}")
    public Result checkUserInfo(@PathVariable String checkValue, @PathVariable Integer checkFlag) {
        Boolean checkUserInfo = ssoSeriviceFeign.checkUserInfo(checkValue, checkFlag);
        if (checkUserInfo) {
            return Result.ok(checkUserInfo);
        }
        return Result.error("校验失败");
    }


    @RequestMapping("userRegister")
    public Result userRegister(TbUser tbUser) {
        Integer i = ssoSeriviceFeign.userRegister(tbUser);
        if (i == 1) {
            return Result.ok();
        }
        return Result.error("注册失败");
    }

    @RequestMapping("userLogin")
    public Result userLogin(TbUser user) {
        Map<String, Object> map = ssoSeriviceFeign.userLogin(user);
        if (map != null) {
            return Result.ok(map);
        }
        return Result.error("登录失败");
    }
}
