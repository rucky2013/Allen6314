package com.allenway.infrustructure.serviceImpl;

import com.allenway.infrustructure.dao.AdminDao;
import com.allenway.infrustructure.entity.Admin;
import com.allenway.infrustructure.service.AdminService;
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

    @Override
    public Admin findAdminByUsername(String username) {
        return adminDao.findAdminByUsername(username);
    }
}
