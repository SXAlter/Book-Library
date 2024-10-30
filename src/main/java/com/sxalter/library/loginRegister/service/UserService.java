package com.sxalter.library.loginRegister.service;

import com.sxalter.library.domain.User;
import com.sxalter.library.loginRegister.mapper.UserRegisterMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserRegisterMapper userRegisterMapper;

    public void insertUser(User user) {
        userRegisterMapper.insertUser(user);
    }

    public boolean existUserPhone(String userName) {
        Integer res = userRegisterMapper.existUserPhone(userName);
        return res != null;
    }

    public void updateUserName(String userName, String userPhone) {
        userRegisterMapper.updateUserName(userName, userPhone);
    }

}
