package com.allenway.infrustructure.service;

import com.allenway.infrustructure.entity.Record;

import java.util.List;

/**
 * Created by wuhuachuan on 16/4/11.
 */
public interface RecordService {
    void save(Record record);

    List<Record> findAllRecords();
}
