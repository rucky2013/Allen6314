package com.allenway.commons.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wuhuachuan on 16/3/3.
 */


@Data
@ToString
//@MappedSuperclass 表示 该类不会对应数据库表，但是它的字段会映射到子类表中
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "systemUUID")
    @GenericGenerator(name="systemUUID",strategy="uuid2")
    private String id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private Boolean isDelete = false; // true:已经删除  false：未删除

}
