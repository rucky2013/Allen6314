package com.allenway.infrustructure.service;

import com.allenway.infrustructure.entity.Admin;

/**
 * Created by wuhuachuan on 16/3/3.
 */
public interface AdminService {
    Admin findAdmin();

    void updateAdmin(Admin admin);

    Admin findAdminByUsername(String username);
}
