package com.allenway.visitor.entity;

import com.allenway.commons.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wuhuachuan on 16/4/8.
 */

@ToString(callSuper = true)
@Data
@Entity
@Table(name = "tb_article_tag")
@NoArgsConstructor
public class Article_Tag extends BaseEntity{
    private String articleId;
    private String tagId;

    public Article_Tag(String articleId,String tagId){
        this.articleId = articleId;
        this.tagId = tagId;
    }
}
