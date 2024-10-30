package com.sxalter.library.loginRegister.service;

import com.sxalter.library.domain.ResponseResult;
import com.sxalter.library.domain.User;

public interface LoginService {
    ResponseResult login(User user);
    ResponseResult loginOut();
}
