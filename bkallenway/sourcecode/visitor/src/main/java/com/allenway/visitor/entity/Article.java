package com.allenway.visitor.entity;

import com.allenway.commons.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wuhuachuan on 16/3/9.
 */

@ToString(callSuper = true)
@Data
@Entity
@Table(name = "tb_article")
@NoArgsConstructor
public class Article extends BaseEntity {

    private String title;
    private String content;
    private String classifyId;  //属于哪个分类
    private int readNum = 0;
}
