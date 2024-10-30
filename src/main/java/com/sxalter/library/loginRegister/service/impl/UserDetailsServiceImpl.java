package com.sxalter.library.loginRegister.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sxalter.library.domain.LoginUser;
import com.sxalter.library.domain.User;
import com.sxalter.library.loginRegister.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service("serDetailsService")
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("username:{}",username);
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(userWrapper);
        log.info("查询到的user:{}",user);

        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("用户名或密码错误！");
        }

        return new LoginUser(user, new ArrayList<>());
    }

}
