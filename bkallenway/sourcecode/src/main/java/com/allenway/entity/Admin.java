package com.allenway.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.allenway.commons.BaseEntity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by wuhuachuan on 16/3/3.
 */

//callSuper = true 表示 父类的toString 也会打印
@ToString(callSuper = true)
@Data
@Entity
@Table(name = "tb_admin")
@NoArgsConstructor
public class Admin extends BaseEntity {

    private String username;  //登录名
    private String passphrase;
    private Date lastLoginDate;
    private String salt; //用户密码加盐

    private String email;
    private String qq;
}
