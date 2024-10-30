package com.sxalter.library.loginRegister.controller;

import com.sxalter.library.domain.ResponseResult;
import com.sxalter.library.domain.User;
import com.sxalter.library.loginRegister.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/api/user/login")
    public ResponseResult login(@RequestBody User user) {
        log.info("用户信息为{}",user);
        return loginService.login(user);
    }

    @RequestMapping("/api/user/loginInfo")
    @PreAuthorize("@customEx.hasAuthority('logged')")
    public ResponseResult toLogin() {
        log.info("已登录");
        return new ResponseResult(200,"已登录");
    }

    @RequestMapping("api/user/loginOut")
    public ResponseResult loginOut() {
        return loginService.loginOut();
    }

}
