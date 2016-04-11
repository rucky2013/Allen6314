package com.allenway.infrustructure.entity;

import com.allenway.commons.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wuhuachuan on 16/4/11.
 */

//callSuper = true 表示 父类的toString 也会打印
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "tb_bug")
@NoArgsConstructor
public class Bug extends BaseEntity{
    private String ex;
    private String requestUrl;

    public Bug(String ex,String requestUrl){
        this.ex = ex;
        this.requestUrl = requestUrl;
    }
}
