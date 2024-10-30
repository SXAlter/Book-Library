package com.sxalter.library.loginRegister.mapper;

import com.sxalter.library.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserRegisterMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO USER (USER_NAME, NICK_NAME, USER_SEX, USER_PHONE, PASSWORD, IS_ADMIN) " +
            "VALUES (#{userName}, #{nickName}, #{userSex}, #{userPhone}, #{password}, #{isAdmin})")
    void insertUser(User user);

    @Select("SELECT 1 FROM USER WHERE USER_PHONE = #{userPhone} LIMIT 1")
    Integer existUserPhone(@Param("userPhone") String userPhone);

    @Update("UPDATE USER SET USER_NAME = #{userName} WHERE USER_PHONE = #{userPhone}")
    void updateUserName(@Param("userName") String userName, @Param("userPhone") String userPhone);

}
