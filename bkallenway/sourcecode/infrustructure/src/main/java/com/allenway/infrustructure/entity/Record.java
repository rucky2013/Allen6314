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
@Table(name = "tb_record")
@NoArgsConstructor
public class Record extends BaseEntity {

    private String requestUrl;
    private String remoteAddress;
    private String remoteHost;
    private int port;

    public Record(Builder builder){
        this.requestUrl = builder.requestUrl;
        this.remoteAddress = builder.remoteAddress;
        this.remoteHost = builder.remoteHost;
        this.port = builder.port;
    }

    public static class Builder{
        private String requestUrl;
        private String remoteAddress;
        private String remoteHost;
        private int port;

        public Builder requestUrl(String requestUrl){
            this.requestUrl = requestUrl;
            return this;
        }
        public Builder remoteAddress(String remoteAddress){
            this.remoteAddress = remoteAddress;
            return this;
        }
        public Builder remoteHost(String remoteHost){
            this.remoteHost = remoteHost;
            return this;
        }
        public Builder port(int port){
            this.port = port;
            return this;
        }
        public Record build(){
            return new Record(this);
        }
    }
}
