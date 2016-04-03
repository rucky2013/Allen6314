package com.allenway.entity;

import com.allenway.boot.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

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
}
