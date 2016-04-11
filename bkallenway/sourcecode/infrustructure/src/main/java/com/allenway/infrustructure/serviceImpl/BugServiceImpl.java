package com.allenway.infrustructure.serviceImpl;

import com.allenway.infrustructure.dao.BugDao;
import com.allenway.infrustructure.entity.Bug;
import com.allenway.infrustructure.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wuhuachuan on 16/4/11.
 */
@Service
public class BugServiceImpl implements BugService {

    @Autowired
    private BugDao bugDao;

    @Override
    public List<Bug> findAllBugs() {
        return bugDao.findAllBugs();
    }

    @Override
    public void save(Bug bug) {
        bugDao.save(bug);
    }
}
