package com.allenway.infrustructure.dao;

import com.allenway.infrustructure.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by wuhuachuan on 16/3/3.
 */

public interface AdminDao extends JpaRepository<Admin, String> {

    @Query("select admin from Admin admin where isDelete=false")
    Admin findAdmin();

    @Query("select admin from Admin admin where isDelete=false and username=:username")
    Admin findAdminByUsername(@Param(value = "username") String username);
}
