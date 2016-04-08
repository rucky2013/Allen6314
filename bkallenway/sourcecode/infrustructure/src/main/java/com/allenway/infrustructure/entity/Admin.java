package com.allenway.infrustructure.entity;

import com.allenway.commons.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by wuhuachuan on 16/3/3.
 */

//callSuper = true 表示 父类的toString 也会打印
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "tb_admin")
@NoArgsConstructor
public class Admin extends BaseEntity {

    private String username;  //登录名
    private String password;
    private String salt; //用户密码加盐
    private Date lastLoginDate;


    private String github;
    private String nickName;
    private Date birthday;

    private String qq;
    private String wechat;
    private String email;
}
