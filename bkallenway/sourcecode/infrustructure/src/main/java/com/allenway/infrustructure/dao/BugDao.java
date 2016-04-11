package com.allenway.infrustructure.dao;

import com.allenway.infrustructure.entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wuhuachuan on 16/4/11.
 */
public interface BugDao extends JpaRepository<Bug, String>{

    @Query("select bug from Bug bug where isDelete=false order by createDate")
    List<Bug> findAllBugs();
}
