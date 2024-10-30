package com.sxalter.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Integer id;

    private String userName;

    private String nickName;

    private String password;

    private char userSex ;

    private String userPhone;

    private Integer isAdmin;

    private Date createTime;

    private Date modifyTime;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * `ID` INT AUTO_INCREMENT COMMENT '主键Id',
     *     `USER_NAME` VARCHAR(64) NOT NULL DEFAULT '李华' COMMENT '用户名',
     *     `NICK_NAME` VARCHAR(64) NOT NULL DEFAULT '真实姓名',
     *     `USER_SEX` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '用户性别(0.未知 1.男 2.女)',
     *     `USER_PHONE` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '用户手机号',
     *     `PASSWORD` VARCHAR(32) NOT NULL DEFAULT '88888888' COMMENT '密码',
     *     `IS_ADMIN` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '是否为管理员(0.否 1.是)',
     *     `CREATE_TIME` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     *     `MODIFY_TIME` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     */
}
