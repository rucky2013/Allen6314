package com.allenway.visitor.entity;

import com.allenway.commons.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by wuhuachuan on 16/3/9.
 */

@ToString(callSuper = true)
@Data
@Entity
@Table(name = "tb_classify")
@NoArgsConstructor
public class Classify  extends BaseEntity {

    private String name;

    private String parentClassifyId;

    private int articleNum = 0;  //该分类下的文章数量

    @OneToMany(mappedBy = "id")
    private List<Article> articles;

}
