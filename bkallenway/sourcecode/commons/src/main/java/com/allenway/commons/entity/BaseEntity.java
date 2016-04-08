package com.allenway.commons.entity;

import com.allenway.commons.timeconverter.CustomLocalDateTimeDeserializer;
import com.allenway.commons.timeconverter.CustomLocalDateTimeSerializer;
import com.allenway.commons.timeconverter.LocalDateTimestampConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

/**
 * Created by wuhuachuan on 16/3/3.
 */

@ToString
@Data
//@MappedSuperclass 表示 该类不会对应数据库表，但是它的字段会映射到子类表中
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "systemUUID")
    @GenericGenerator(name="systemUUID",strategy="uuid2")
    private String id;

    @JsonSerialize(using=CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using=CustomLocalDateTimeDeserializer.class)
    @Convert(converter=LocalDateTimestampConverter.class)
    private LocalDateTime createDate = LocalDateTime.now();

    @JsonIgnore
    @JsonSerialize(using=CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using=CustomLocalDateTimeDeserializer.class)
    @Convert(converter=LocalDateTimestampConverter.class)
    private LocalDateTime updateDate;

    @JsonIgnore
    private Boolean isDelete = false; // true:已经删除  false：未删除

}
