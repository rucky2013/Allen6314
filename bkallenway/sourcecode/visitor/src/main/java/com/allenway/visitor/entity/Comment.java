package com.allenway.visitor.entity;

import com.allenway.commons.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by wuhuachuan on 16/4/8.
 */

@ToString(callSuper = true)
@Data
@Entity
@Table(name = "tb_comment")
@NoArgsConstructor
public class Comment extends BaseEntity {

    private String name;
    private String email;
    private String content;

    private String articleId;
}
