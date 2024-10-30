package com.sxalter.library.loginRegister.service.impl;

import com.sxalter.library.domain.LoginUser;
import com.sxalter.library.domain.ResponseResult;
import com.sxalter.library.domain.User;
import com.sxalter.library.loginRegister.service.LoginService;
import com.sxalter.library.util.JwtUtil;
import com.sxalter.library.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        log.info("authenticationToken:{}",authenticationToken);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (Objects.isNull(authentication)) {
            throw new RuntimeException("登陆失败！");
        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();

        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token",jwt);

        redisUtil.setCacheObject("LoggedIn:" + userId, loginUser);
        return new ResponseResult(200, "登陆成功", map);
    }

    @Override
    public ResponseResult loginOut() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication:{}", authentication);
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        redisUtil.deleteObject("LoggedIn:" + loginUser.getUser().getId());
        return new ResponseResult(200, "退出成功");
    }
}
