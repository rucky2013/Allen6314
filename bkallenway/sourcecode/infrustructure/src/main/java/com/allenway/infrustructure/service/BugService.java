package com.allenway.infrustructure.service;

import com.allenway.infrustructure.entity.Bug;

import java.util.List;

/**
 * Created by wuhuachuan on 16/4/11.
 */
public interface BugService {
    List<Bug> findAllBugs();

    void save(Bug bug);
}
