package com.allenway.visitor.entity;

import com.allenway.commons.entity.BaseEntity;
import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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

    private String classifyId;
    private transient String classifyName;

    private transient List<Tag> tags;

    private transient List<Comment> comments;
    private transient int commentNum;

    private int readNum = 0;

}
