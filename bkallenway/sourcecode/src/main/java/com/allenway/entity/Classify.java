package com.allenway.entity;

import com.allenway.commons.BaseEntity;
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
@Table(name = "tb_classify")
@NoArgsConstructor
public class Classify  extends BaseEntity {

    private String name;

    private String parentClassifyId;
}
