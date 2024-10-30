package com.sxalter.library.loginRegister.manager;

import com.sxalter.library.domain.User;
import com.sxalter.library.loginRegister.exception.LoginException;
import com.sxalter.library.loginRegister.model.RegisterUserReq;
import com.sxalter.library.loginRegister.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserManager {

    private static final String passwordRegx = "^[\\w|\\W]{6,16}$";

    @Resource
    private UserService userService;

    @Transactional(rollbackFor = Exception.class)
    public void registerUser(RegisterUserReq req) {
        if (!Pattern.matches(passwordRegx, req.getPassword())) {
            throw new LoginException("账号密码不合法！");
        }
        if (userService.existUserPhone(req.getUserPhone())) {
            throw new LoginException("手机号已存在！");
        }
        User user = new User();
        user.setNickName(req.getNickName());
        user.setPassword(req.getPassword());
        user.setUserPhone(req.getUserPhone());
        userService.insertUser(user);
        userService.updateUserName(String.format("takeaway_%d", user.getId()), user.getUserPhone());
    }

}
