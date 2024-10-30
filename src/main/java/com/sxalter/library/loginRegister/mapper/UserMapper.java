package com.sxalter.library.loginRegister.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxalter.library.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
