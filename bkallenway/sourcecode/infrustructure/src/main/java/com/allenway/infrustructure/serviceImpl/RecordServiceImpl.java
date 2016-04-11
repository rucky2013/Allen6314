package com.allenway.infrustructure.serviceImpl;

import com.allenway.infrustructure.dao.RecordDao;
import com.allenway.infrustructure.entity.Record;
import com.allenway.infrustructure.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wuhuachuan on 16/4/11.
 */

@Service
public class RecordServiceImpl implements RecordService{

    @Autowired
    private RecordDao recordDao;

    @Override
    public void save(Record record) {
        recordDao.save(record);
    }

    @Override
    public List<Record> findAllRecords() {
        return recordDao.findAllRecords();
    }
}
