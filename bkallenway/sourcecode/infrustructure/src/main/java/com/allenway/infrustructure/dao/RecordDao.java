package com.allenway.infrustructure.dao;

import com.allenway.infrustructure.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wuhuachuan on 16/4/11.
 */
public interface RecordDao extends JpaRepository<Record, String> {

    @Query("select record from Record record where isDelete=false order by createDate")
    List<Record> findAllRecords();
}
