package com.allenway.visitor.entity;

import com.allenway.commons.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by wuhuachuan on 16/4/2.
 */

@ToString(callSuper = true)
@Data
@Entity
@Table(name = "tb_tag")
@NoArgsConstructor
public class Tag extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "tags")
    private transient List<Article> articles;

}
