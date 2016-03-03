package com.allenway.serviceImpl;

import com.allenway.dao.AdminDao;
import com.allenway.entity.Admin;
import com.allenway.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wuhuachuan on 16/3/3.
 */


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin findAdmin() {
        return adminDao.findAdmin();
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminDao.saveAndFlush(admin);
    }
}
