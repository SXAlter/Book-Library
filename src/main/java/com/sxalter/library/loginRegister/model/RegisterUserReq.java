package com.sxalter.library.loginRegister.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegisterUserReq {

    @NotBlank(message = "真实姓名不能为空！")
    private String nickName;

    @NotBlank(message = "用户电话不能为空！")
    private String userPhone;

    @NotBlank(message = "密码不能为空！")
    private String password;

}
