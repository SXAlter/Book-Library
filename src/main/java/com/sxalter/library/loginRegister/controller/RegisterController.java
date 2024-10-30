package com.sxalter.library.loginRegister.controller;

import com.sxalter.library.domain.ResponseResult;
import com.sxalter.library.loginRegister.exception.LoginException;
import com.sxalter.library.loginRegister.manager.UserManager;
import com.sxalter.library.loginRegister.model.RegisterUserReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class RegisterController {

    @Resource
    private UserManager userManager;

    @PostMapping("/register")
    public ResponseResult<String> register(@RequestBody RegisterUserReq req) {
        try {
            userManager.registerUser(req);
            return ResponseResult.success(200, "注册账号成功！");
        } catch (LoginException e) {
            log.error("注册账户失败: {}", e.getMessage());
            return ResponseResult.fail(500, e.getMessage());
        } catch (Exception e) {
            log.error("注册账户异常", e);
            return ResponseResult.fail(500, "注册账户失败！");
        }
    }

}
